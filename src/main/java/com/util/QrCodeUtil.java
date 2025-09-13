package com.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;

public class QrCodeUtil {

    public static byte[] generateQrCode(String content, int width, int height) throws IOException {
        try {
            BitMatrix matrix = new com.google.zxing.qrcode.QRCodeWriter()
                    .encode(content, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, "PNG", baos);
            return baos.toByteArray();
        } catch (WriterException e) {
            throw new IOException("Erreur lors de la génération du QR code", e);
        }
    }

    public static String decodeQrCode(byte[] qrImage) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(qrImage)) {
            BufferedImage bufferedImage = ImageIO.read(bais);
            BinaryBitmap bitmap = new BinaryBitmap(
                    new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
            return new MultiFormatReader().decode(bitmap).getText();
        } catch (NotFoundException e) {
            throw new IOException("QR code introuvable ou illisible", e);
        }
    }
}
