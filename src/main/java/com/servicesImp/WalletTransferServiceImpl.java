package com.servicesImp;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.OperationType;
import org.springframework.stereotype.Service;

import com.service.WalletTransferService;
import com.model.FEES;
import com.model.FEE_RULE;
import com.model.FEE_SCHEMA;
import com.model.OPERATION_DETAILS;
import com.model.OPERATION_TYPE;
import com.model.WALLET;
import com.model.WALLET_BALANCE_HISTORY;
import com.model.WALLET_OPERATIONS;
import com.model.WALLET_OPERATION_TYPE_MAP;
import com.repository.*;

import dto.WalletToWalletTransferRequest;
import dto.WalletToWalletTransferResponse;
import jakarta.transaction.Transactional;

@Service
public class WalletTransferServiceImpl implements WalletTransferService {

        @Autowired
        private WalletRepository walletRepo;
        @Autowired
        private OperationTypeRepository operationTypeRepo;
        @Autowired
        private WalletCategoryOperationTypeMapRepository wcotmRepo;
        @Autowired
        private WalletOperationTypeMapRepository wotmRepo;
        @Autowired
        private FeeRuleRepository feeRuleRepo;
        @Autowired
        private WalletOperationsRepository walletOpRepo;
        @Autowired
        private OperationsDetailsRepository operationDetailsRepo;
        @Autowired
        private WalletBalanceHistoryRepository balanceHistoryRepo;

        @Override
        @Transactional
        public WalletToWalletTransferResponse transfer(WalletToWalletTransferRequest request) {
                WALLET sender = walletRepo.findOneByWalIden(request.getSenderWalletIden())
                                .orElseThrow(() -> new RuntimeException("Sender wallet not found"));
                WALLET receiver = walletRepo.findOneByWalIden(request.getReceiverWalletIden())
                                .orElseThrow(() -> new RuntimeException("Receiver wallet not found"));

                if (sender.getWalletStatus().getWstLabe().equals("BLOCKED"))
                        throw new RuntimeException("Sender wallet is blocked");

                OPERATION_TYPE operationType = operationTypeRepo.findByOptIden(request.getOperationTypeIden())
                                .orElseThrow(() -> new RuntimeException("Operation type not found"));

                wcotmRepo.findByOperationTypeAndWalletCategory(operationType, sender.getWalletCategory())
                                .orElseThrow(() -> new RuntimeException(
                                                "Operation not allowed for this wallet category"));

                WALLET_OPERATION_TYPE_MAP wotm = wotmRepo.findByOperationTypeAndWallet(operationType, sender)
                                .orElseThrow(() -> new RuntimeException("Operation not allowed for this wallet"));

                if (request.getAmount().compareTo(BigDecimal.valueOf(wotm.getWotmLimitMax())) > 0)
                        throw new RuntimeException("Amount exceeds max limit for this wallet");

                FEES fees = wotm.getFees();
                BigDecimal amount = request.getAmount();
                BigDecimal fee = BigDecimal.ZERO;

                if (amount.compareTo(BigDecimal.valueOf(fees.getFeeMinLimit())) >= 0 &&
                                amount.compareTo(BigDecimal.valueOf(fees.getFeeMaxLimit())) <= 0) {
                        fee = new BigDecimal(fees.getFeePercentage()).multiply(amount);
                        if (fee.compareTo(BigDecimal.valueOf(fees.getFeeAmount())) < 0) {
                                fee = BigDecimal.valueOf(fees.getFeeAmount());
                        }
                } else if (amount.compareTo(BigDecimal.valueOf(fees.getFeeMaxLimit())) > 0) {
                        fee = BigDecimal.valueOf(fees.getFeeMaxAmount());
                }

                FEE_RULE feeRule = feeRuleRepo
                                .findByFeeSchema_FscCodeAndFeeRuleType_FrtIden(
                                                operationType.getFeeSchema().getFscCode(), "I")
                                .orElseThrow(() -> new RuntimeException("Fee rule not found"));

                BigDecimal vat = BigDecimal.ZERO;
                if (feeRule.getFruTva() != null && feeRule.getFruTva().getVatRate() != null) {
                        vat = fee.multiply(feeRule.getFruTva().getVatRate());
                }

                BigDecimal total = amount.add(fee).add(vat);

                if (sender.getWalLogicBalance() == null
                                || BigDecimal.valueOf(sender.getWalLogicBalance()).compareTo(total) < 0)
                        throw new RuntimeException("Insufficient balance");

                BigDecimal senderNewBalance = BigDecimal.valueOf(sender.getWalLogicBalance()).subtract(total);
                sender.setWalLogicBalance(senderNewBalance.floatValue());
                walletRepo.save(sender);
                saveWalletBalanceHistory(sender);

                BigDecimal receiverNewBalance = BigDecimal.valueOf(receiver.getWalLogicBalance()).add(amount);
                receiver.setWalLogicBalance(receiverNewBalance.floatValue());
                walletRepo.save(receiver);
                saveWalletBalanceHistory(receiver);

                WALLET feeWallet = walletRepo.findById(feeRule.getFruFeesWalletId())
                                .orElseThrow(() -> new RuntimeException("Fee wallet not found"));
                feeWallet.setWalLogicBalance(BigDecimal.valueOf(feeWallet.getWalLogicBalance()).add(fee).floatValue());
                walletRepo.save(feeWallet);
                saveWalletBalanceHistory(feeWallet);

                WALLET vatWallet = walletRepo.findById(feeRule.getFruTvaWalletId())
                                .orElseThrow(() -> new RuntimeException("VAT wallet not found"));
                vatWallet.setWalLogicBalance(BigDecimal.valueOf(vatWallet.getWalLogicBalance()).add(vat).floatValue());
                walletRepo.save(vatWallet);
                saveWalletBalanceHistory(vatWallet);

                WALLET_OPERATIONS op = new WALLET_OPERATIONS();
                op.setWallet(sender);
                op.setWopOtyCode(operationType.getOptCode());
                op.setWopAmount(amount.floatValue());
                op.setWopCurrency("TND");
                op.setWopStatus("SUCCESS");
                op.setWopLabel("Transfert de " + sender.getWalIden() + " vers " + receiver.getWalIden());
                op.setWopTimestamps(new Date());
                walletOpRepo.save(op);

                operationDetailsRepo.save(createOpDetail(op, "DEBIT", amount, fee, sender, receiver.getWalIden()));
                operationDetailsRepo.save(
                                createOpDetail(op, "CREDIT", amount, BigDecimal.ZERO, receiver, receiver.getWalIden()));
                operationDetailsRepo.save(createOpDetail(op, "FEE", fee, fee, sender, feeWallet.getWalIden()));
                operationDetailsRepo.save(createOpDetail(op, "TVA", vat, vat, sender, vatWallet.getWalIden()));

                return new WalletToWalletTransferResponse("TXN-" + op.getWopCode(), total, "Transfer successful");
        }

        private void saveWalletBalanceHistory(WALLET wallet) {
                WALLET_BALANCE_HISTORY history = new WALLET_BALANCE_HISTORY();
                history.setWallet(wallet);
                history.setWbhEffBal(wallet.getWalEffBal()); // ✅ Correction ici
                history.setWbhLogicBalance(wallet.getWalLogicBalance());
                history.setWbhSpecificBalance(wallet.getWalSpecificBalance());
                history.setWbhLastUpdated(new Date());
                balanceHistoryRepo.save(history);
        }

        private OPERATION_DETAILS createOpDetail(WALLET_OPERATIONS op, String type, BigDecimal value, BigDecimal fee,
                        WALLET wallet, String recipientWalletIden) {
                OPERATION_DETAILS detail = new OPERATION_DETAILS();
                detail.setWalletOperation(op);
                detail.setOdeType(type);
                detail.setOdeValue(value.toPlainString());
                detail.setOdeFeeAmount(fee.floatValue());
                detail.setOdePayMeth("WALLET_TO_WALLET");
                detail.setOdeRecipientWallet(recipientWalletIden);
                detail.setOdeCusCode(wallet.getCustomer().getCusCode());
                return detail;
        }
}
