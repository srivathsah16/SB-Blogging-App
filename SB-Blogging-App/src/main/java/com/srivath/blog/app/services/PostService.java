package com.srivath.blog.app.services;

import java.util.List;

import com.srivath.blog.app.dtos.PostDto;
import com.srivath.blog.app.payloads.PostResponse;

public interface PostService {

    // create Post
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    // update Post
    PostDto updatePost(PostDto postDto, Integer postId);

    // delete Post
    void deletePost(Integer postId);

    // get All Post
    List<PostDto> getAllPost();

    // get Posts with Pagination
    PostResponse getPostWithPagination(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    // get Post by Id
    PostDto getPostById(Integer postId);

    // get Post by Category
    List<PostDto> getPostByCategory(Integer CategoryId);

    // get Post by User
    List<PostDto> getPostByUser(Integer userId);

}
