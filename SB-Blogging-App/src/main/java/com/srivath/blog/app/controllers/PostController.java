package com.srivath.blog.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srivath.blog.app.dtos.PostDto;
import com.srivath.blog.app.payloads.ApiResponse;
import com.srivath.blog.app.payloads.PostResponse;
import com.srivath.blog.app.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
            @PathVariable("userId") Integer uId, @PathVariable("categoryId") Integer cId) {
        PostDto savedPostDto = postService.createPost(postDto, uId, cId);
        return new ResponseEntity<PostDto>(savedPostDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{postId}/posts")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId) {
        return ResponseEntity.ok(postService.updatePost(postDto, postId));
    }

    @GetMapping("/get/category/{id}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("id") Integer categoryId) {
        return ResponseEntity.ok(postService.getPostByCategory(categoryId));
    }

    @GetMapping("/get/user/{id}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok(postService.getPostByUser(userId));
    }

    @GetMapping("/getall/posts")
    public ResponseEntity<List<PostDto>> getAllPost() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    @GetMapping("/get/{postId}/posts")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @DeleteMapping("/delete/{postId}/posts")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(new ApiResponse("Post deleted successfully", true));
    }

    @GetMapping("/get/pagination")
    public ResponseEntity<PostResponse> getPostWithPagination(
            @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        return ResponseEntity.ok(postService.getPostWithPagination(pageNumber, pageSize, sortBy, sortDir));
    }
}
