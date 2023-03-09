package com.yjh.boardproject.repository;

import com.yjh.boardproject.domain.Article;
import com.yjh.boardproject.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepositroy extends JpaRepository<ArticleComment, Long> {
}
