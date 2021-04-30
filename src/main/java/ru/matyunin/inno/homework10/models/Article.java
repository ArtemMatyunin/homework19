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

    private final Integer articleId;
    private final String articleTitle;
    private final String articleText;
    private final Integer articleAuthorId;
    private final String articleAuthor;
    private final LocalDate articleDate;
    private final List<Integer> articleHideForUsers = new ArrayList<>();

    private Article(Builder builder) {
        this.articleId = builder.articleId;
        this.articleTitle = builder.articleTitle;

        this.articleText = builder.articleText;
        this.articleAuthorId = builder.articleAuthorId;
        this.articleAuthor = builder.articleAuthor;
        this.articleDate = builder.articleDate;
        articleHideForUsers.addAll(builder.articleHideForUsers);
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
                + '\n'
                + '\n';
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public static class Builder {

        private Integer articleId;
        private String articleTitle;
        private String articleText;
        private Integer articleAuthorId;
        private String articleAuthor;
        private LocalDate articleDate;
        private final List<Integer> articleHideForUsers = new ArrayList<>();

      //  private final Article newArticle;

        public Builder() {
           // newArticle = new Article();
        }

        public Builder articleId(Integer articleId) {
            this.articleId = articleId;
            return this;
        }

        public Builder articleTitle(String articleTitle) {
            this.articleTitle = articleTitle;
            return this;
        }

        public Builder articleText(String articleText) {
            this.articleText = articleText;
            return this;
        }

        public Builder articleAuthorId(Integer articleAuthorId) {
            this.articleAuthorId = articleAuthorId;
            return this;
        }

        public Builder articleAuthor(String articleAuthor) {
            this.articleAuthor = articleAuthor;
            return this;
        }

        public Builder articleDate(LocalDate articleDate) {
            this.articleDate=articleDate;
            return this;
        }

        public Builder articleHideForUsers(List<Integer> articleHideForUsers) {
            this.articleHideForUsers.addAll(articleHideForUsers);
            return this;
        }

        public Article build() {
            return new Article(this);
        }
    }
}
