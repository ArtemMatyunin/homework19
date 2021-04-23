package ru.matyunin.inno.homework10.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Артём Матюнин
 * Модель  поста в блоге
 * Для создания экземпляров используется Builder
 */

public class Article {

    private Integer articleId;
    private String articleTitle;
    private String articleText;
    private Integer articleAuthorId;
    private String articleAuthor;
    private LocalDate articleDate;
    private final List<Integer> articleHideForUsers = new ArrayList<>();

    public Article() {

    }


    public int getArticleId() {
        return articleId;
    }

    public String getArticleText() {
        return articleText;
    }

    public int getArticleAuthorId() {
        return articleAuthorId;
    }

    public LocalDate getArticleDate() {
        return articleDate;
    }

    public List<Integer> getArticleHideForUsers() {
        return articleHideForUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleId, article.articleId) && Objects.equals(articleTitle, article.articleTitle) && Objects.equals(articleText, article.articleText) && Objects.equals(articleAuthorId, article.articleAuthorId) && Objects.equals(articleAuthor, article.articleAuthor) && Objects.equals(articleDate, article.articleDate) && Objects.equals(articleHideForUsers, article.articleHideForUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, articleTitle, articleText, articleAuthorId, articleAuthor, articleDate, articleHideForUsers);
    }

    @Override
    public String toString() {
        return "Article: " + articleTitle + "{"
                + '\n' + articleText + '\''
                + '\n'
                + "Author:" + articleAuthor + "; "
                + "date:" + articleDate + '}'
                + '\n';
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public static class Builder {

        private final Article newArticle;

        public Builder() {
            newArticle = new Article();
        }

        public Builder articleId(Integer articleId) {
            newArticle.articleId = articleId;
            return this;
        }

        public Builder articleTitle(String articleTitle) {
            newArticle.articleTitle = articleTitle;
            return this;
        }

        public Builder articleText(String articleText) {
            newArticle.articleText = articleText;
            return this;
        }

        public Builder articleAuthorId(Integer articleAuthor) {
            newArticle.articleAuthorId = articleAuthor;
            return this;
        }

        public Builder articleAuthor(String articleAuthor) {
            newArticle.articleAuthor = articleAuthor;
            return this;
        }

        public Builder articleDate(LocalDate articleDate) {
            newArticle.articleDate=articleDate;
            return this;
        }

        public Builder articleHideForUsers(List<Integer> articleHideForUsers) {
            newArticle.articleHideForUsers.addAll(articleHideForUsers);
            return this;
        }

        public Article build() {
            return newArticle;
        }
    }
}
