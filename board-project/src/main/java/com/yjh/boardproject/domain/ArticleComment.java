package com.yjh.boardproject.domain;

public class ArticleComment {

    private Long id;
    private Article article;
    private String content;

    private LocalDateTime createAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
