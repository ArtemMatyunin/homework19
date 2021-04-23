package ru.matyunin.inno.homework10.repo;

import ru.matyunin.inno.homework10.models.Article;

/**
 * @author Артём Матюнин
 * Описывает специфичные CRUD-операции для модели Article
 */

public interface CrudArticle extends Crud<Article> {
    void addArticle(Article article);
}
