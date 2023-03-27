package com.yjh.boardproject.service;

import com.yjh.boardproject.domain.Article;
import com.yjh.boardproject.domain.type.SearchType;
import com.yjh.boardproject.dto.ArticleDto;
import com.yjh.boardproject.dto.ArticleUpdateDto;
import com.yjh.boardproject.dto.ArticleWithCommentsDto;
import com.yjh.boardproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {

        if(searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag("#" + searchKeyword, pageable).map(ArticleDto::from);
        };

    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {

        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
    }

    public void saveArticle(ArticleDto dto) {

        articleRepository.save(dto.toEntity());
    }

    public void updateArticle(ArticleDto dto) {

        try {

            Article article = articleRepository.getReferenceById(dto.id());     // getReferenceById : 해당 Article이 존재하는지 SELECT 쿼리를 실행하지 않고 reference만 가지고옴

            if (dto.title() != null) {
                article.setTitle(dto.title());
            }
            if (dto.title() != null) {
                article.setContent(dto.content());
            }

            article.setHashtag(dto.hashtag());

        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패. 게시글을 찾을 수 없습니다 - dto: {}", dto);
        }
    }

    public void deleteArticle(long articleId) {

        articleRepository.deleteById(articleId);
    }

}
