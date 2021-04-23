package ru.matyunin.inno.homework10.models;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Артём Матюнин
 * Модель комментария к посту в блоге
 * Для создания экземпляров используется Builder
 */

public class Comment {

    private Integer commentId;
    private Integer commentArticle;
    private Integer commentAuthor;
    private String commentText;
    private LocalDate commentDate;

    public Comment() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId && commentArticle == comment.commentArticle && commentAuthor == comment.commentAuthor && Objects.equals(commentText, comment.commentText) && Objects.equals(commentDate, comment.commentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, commentArticle, commentAuthor, commentText, commentDate);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentArticle=" + commentArticle +
                ", commentAuthor=" + commentAuthor +
                ", commentText='" + commentText + '\'' +
                ", commentDate='" + commentDate + '\'' +
                '}';
    }

    public int getCommentId() {
        return commentId;
    }

    public int getCommentArticle() {
        return commentArticle;
    }

    public int getCommentAuthor() {
        return commentAuthor;
    }

    public String getCommentText() {
        return commentText;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public static class Builder {

        private final Comment newComment;

        public Builder() {
            newComment = new Comment();
        }

        public Builder commentId(Integer commentId) {
            newComment.commentId = commentId;
            return this;
        }

        public Builder commentArticle(Integer commentArticle) {
            newComment.commentArticle = commentArticle;
            return this;
        }

        public Builder commentAuthor(Integer commentAuthor) {
            newComment.commentAuthor = commentAuthor;
            return this;
        }

        public Builder commentText(String commentText) {
            newComment.commentText = commentText;
            return this;
        }

        public Builder commentText(LocalDate commentDate) {
            newComment.commentDate = commentDate;
            return this;
        }

        public Comment build() {
            return newComment;
        }

    }
}
