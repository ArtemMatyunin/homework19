package ru.matyunin.inno.homework10.service;

import ru.matyunin.inno.homework10.models.Article;
import ru.matyunin.inno.homework10.models.User;

import java.util.List;

/**
 * @author Артём Матюнин
 *
 * Позволяет клиентскому коду использовать предлгаемый набор методов, отделяя его от БД
 *
 */

public interface BlogService {
    List<Article> printArticles();
    void addArticle(Article article);
    int[] addUsers(List<User> users);

}
