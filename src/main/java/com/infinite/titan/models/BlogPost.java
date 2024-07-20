package com.infinite.titan.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPost {

    // ID of the blog post
    private Long id;

    // Title of the blog post
    private String title;

    // Content of the blog post
    private String content;

    // Author information
    private String authorName;

    // Date when the blog post was created
    private LocalDateTime createdDate;

    // Date when the blog post was last updated
    private LocalDateTime updatedDate;

    // Status of the blog post (e.g., published, draft)
    private String status;

}