
package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.MenuOption;
import com.model.Modules;

@Repository
public interface ModulesRepository extends JpaRepository<Modules, Integer> {

    @Query("SELECT m FROM Modules m WHERE " +
            "LOWER(m.identifier) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(m.label) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR " +
            "LOWER(m.accessPath) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(m.order AS string) LIKE CONCAT('%', :searchWord, '%') OR " +
            "CAST(m.isMenu AS string) LIKE CONCAT('%', :searchWord, '%')")
    List<Modules> search(String searchWord);
}
