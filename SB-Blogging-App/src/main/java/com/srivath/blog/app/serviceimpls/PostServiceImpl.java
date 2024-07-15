package com.srivath.blog.app.serviceimpls;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.srivath.blog.app.dtos.PostDto;
import com.srivath.blog.app.entities.Category;
import com.srivath.blog.app.entities.Post;
import com.srivath.blog.app.entities.User;
import com.srivath.blog.app.exceptions.ResourceNotFoundException;
import com.srivath.blog.app.payloads.PostResponse;
import com.srivath.blog.app.repositories.CategoryRepository;
import com.srivath.blog.app.repositories.PostRepository;
import com.srivath.blog.app.repositories.UserRepository;
import com.srivath.blog.app.services.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CategoryRepository categoryRepo;

    // Used for sending response in Pagination.
    @Autowired
    private PostResponse postResponse;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Post post = mapDtoToEntity(postDto);
        post.setImageName("default.png");
        post.setUser(user);
        post.setCategory(category);

        return mapEntityToDto(postRepo.save(post));

    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        // post.setImageName(postDto.getImageName());

        return mapEntityToDto(postRepo.save(post));

    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPost() {
        return postRepo.findAll().stream().map(p -> mapEntityToDto(p)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Integer postId) {
        return mapEntityToDto(
                postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId)));
    }

    @Override
    public List<PostDto> getPostByCategory(Integer CategoryId) {
        Category category = categoryRepo.findById(CategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", CategoryId));

        return postRepo.findByCategory(category).stream().map(post -> mapEntityToDto(post))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

        return postRepo.findByUser(user).stream().map(post -> mapEntityToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostResponse getPostWithPagination(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        // creating Sort object
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        // creating Pagebale object
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> page = postRepo.findAll(pageable);
        List<PostDto> listPostDto = page.getContent().stream().map(post -> mapEntityToDto(post))
                .collect(Collectors.toList());

        postResponse.setContent(listPostDto);
        postResponse.setPageNumber(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalElements(page.getTotalElements());
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setLastPage(page.isLast());

        return postResponse;
    }

    public Post mapDtoToEntity(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }

    public PostDto mapEntityToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }
}
