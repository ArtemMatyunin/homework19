package ru.matyunin.inno.homework10.service;

/**
 * @author Артём Матюнин
 *
 * Позволяет клиентскому коду использовать предлгаемый набор методов, отделяя его от БД
 *
 */
public interface BlogService {
    void printArticles();
    void addArticle();
    int[] addUsers();

}
