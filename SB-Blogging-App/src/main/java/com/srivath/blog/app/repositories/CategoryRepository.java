package com.srivath.blog.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srivath.blog.app.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
