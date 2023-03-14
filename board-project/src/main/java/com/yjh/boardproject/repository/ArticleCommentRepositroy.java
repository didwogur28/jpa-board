package com.yjh.boardproject.repository;

import com.yjh.boardproject.domain.Article;
import com.yjh.boardproject.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepositroy extends JpaRepository<ArticleComment, Long> {
}
