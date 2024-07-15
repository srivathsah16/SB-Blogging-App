package com.srivath.blog.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srivath.blog.app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
