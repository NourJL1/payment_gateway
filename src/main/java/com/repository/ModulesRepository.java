
package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Modules;
@Repository
public interface ModulesRepository extends JpaRepository<Modules, Integer> {
}
