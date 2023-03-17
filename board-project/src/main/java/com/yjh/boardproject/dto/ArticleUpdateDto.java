package com.yjh.boardproject.dto;

public record ArticleUpdateDto(
        String title,
        String content,
        String hasgtag) {
    public static ArticleUpdateDto of(String title, String content, String hasgtag) {
        return new ArticleUpdateDto(title, content, hasgtag);
    }
}
