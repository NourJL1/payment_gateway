package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.UserProfileMenuOption;
import com.model.MenuOption;
import java.util.List;
import com.model.UserProfile;

public interface UserProfileMenuOptionRepository extends JpaRepository<UserProfileMenuOption, Integer> {

    List<UserProfileMenuOption> findByProfileAndMenuOption(UserProfile profile, MenuOption menuOption);

    List<UserProfileMenuOption> findByProfile(UserProfile profile);

    @Query("SELECT upmo FROM UserProfileMenuOption upmo " +
           "JOIN upmo.menuOption mo " +         // Join to MenuOption
           "JOIN mo.module mod " +              // Join to Module from MenuOption
           "WHERE mod.code = :moduleCode and upmo.profile.code = :profileCode")      // Filter by module code
    List<UserProfileMenuOption> findByModuleAndProfile(@Param("moduleCode") Integer moduleCode, @Param("profileCode") Integer profileCode);

    /* @Query("SELECT upmo FROM UserProfileMenuOption upmo WHERE " +
            "CAST(upmo.canAccess AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(upmo.canInsert AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(upmo.canUpdate AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(upmo.canEdit AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(upmo.canDelete AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(upmo.canPrint AS string) LIKE CONCAT('%', :searchWord, '%')")
    List<UserProfileMenuOption> search(String searchWord); */

}
