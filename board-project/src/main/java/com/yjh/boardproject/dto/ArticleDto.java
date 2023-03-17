package com.yjh.boardproject.dto;

import java.time.LocalDateTime;

public record ArticleDto(
        LocalDateTime createdAt,
        String createdBy,
        String title,
        String content,
        String hasgtag) {
    public static ArticleDto of(LocalDateTime createdAt, String createdBy, String title, String content, String hasgtag) {
        return new ArticleDto(createdAt, createdBy, title, content, hasgtag);
    }
}
