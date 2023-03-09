package com.yjh.boardproject.repository;

import com.yjh.boardproject.config.JpaConfig;
import com.yjh.boardproject.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("testdb")
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepositroy articleCommentRepositroy;

    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository,
                             @Autowired ArticleCommentRepositroy articleCommentRepositroy) {
        this.articleRepository = articleRepository;
        this.articleCommentRepositroy = articleCommentRepositroy;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelection_thenWorksFine() {
        // Given

        // when
        List<Article> articles = articleRepository.findAll();

        //then
        assertThat(articles)
                .isNotNull()
                .hasSize(123);

    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given

        // when
        long previoisCount = articleRepository.count();

        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));

        //then
        assertThat(articleRepository.count()).isEqualTo(previoisCount + 1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {

        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#Springboot";
        article.setHashtag(updatedHashtag);

        // when
        long previoisCount = articleRepository.count();

        Article savedArticle = articleRepository.saveAndFlush(article);

        //then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {

        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepositroy.count();
        long deletedCommentSize = article.getArticleComments().size();

        // when
        articleRepository.delete(article);

        //then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleRepository.count()).isEqualTo(previousArticleCommentCount- deletedCommentSize);
    }
}