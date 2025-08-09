package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.UserProfileMenuOption;
import com.model.MenuOption;
import java.util.List;
import com.model.UserProfile;

public interface UserProfileMenuOptionRepository extends JpaRepository<UserProfileMenuOption, Integer> {

    List<UserProfileMenuOption> findByProfileAndMenuOption(UserProfile profile, MenuOption menuOption);

    /* @Query("SELECT upmo FROM UserProfileMenuOption upmo WHERE " +
            "CAST(upmo.canAccess AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(upmo.canInsert AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(upmo.canUpdate AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(upmo.canEdit AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(upmo.canDelete AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(upmo.canPrint AS string) LIKE CONCAT('%', :searchWord, '%')")
    List<UserProfileMenuOption> search(String searchWord); */

}
