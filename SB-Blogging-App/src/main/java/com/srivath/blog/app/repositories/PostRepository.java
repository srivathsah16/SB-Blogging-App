package com.srivath.blog.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srivath.blog.app.entities.Category;
import com.srivath.blog.app.entities.Post;
import com.srivath.blog.app.entities.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);

}
