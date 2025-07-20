package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.MenuOption;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Integer> {}
