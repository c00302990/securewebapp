package com.changmin.securewebapp.service;

import com.changmin.securewebapp.dto.PostRequestDto;
import com.changmin.securewebapp.dto.PostResponseDto;
import com.changmin.securewebapp.dto.PostSummaryDto;
import com.changmin.securewebapp.entity.Post;
import com.changmin.securewebapp.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

        private final PostRepository postRepository;

        public PostResponseDto createPost(PostRequestDto dto, String username) {
                Post post = Post.builder()
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .author(username)
                        .createdAt(LocalDateTime.now())
                        .build();

                Post saved = postRepository.save(post);

                return new PostResponseDto(
                        saved.getId(),
                        saved.getTitle(),
                        saved.getContent(),
                        saved.getAuthor(),
                        saved.getCreatedAt().toString()
                );
        }

        public PostResponseDto updatePost(Long id, PostRequestDto dto, String username) {
                Post post = postRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

                if (!post.getAuthor().equals(username)) {
                        throw new RuntimeException("작성자만 수정할 수 있습니다.");
                }

                post.setTitle(dto.getTitle());
                post.setContent(dto.getContent());
                postRepository.save(post);

                return new PostResponseDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getAuthor(),
                        post.getCreatedAt().toString()
                );
        }

        public void deletePost(Long id, String username) {
                Post post = postRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

                if (!post.getAuthor().equals(username)) {
                        throw new RuntimeException("작성자만 삭제할 수 있습니다.");
                }

                postRepository.delete(post);
        }

        public List<PostSummaryDto> getAllPosts() {
                return postRepository.findAll().stream()
                        .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt())) // 최신순 정렬
                        .map(post -> new PostSummaryDto(
                                post.getId(),
                                post.getTitle(),
                                post.getAuthor(),
                                post.getCreatedAt().toString()
                        ))
                        .collect(Collectors.toList());
        }

        public PostResponseDto getPostById(Long id) {
                Post post = postRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

                return new PostResponseDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getAuthor(),
                        post.getCreatedAt().toString()
                );
        }
}
