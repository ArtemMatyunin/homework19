package ru.matyunin.inno.homework10.service;

import ru.matyunin.inno.homework10.models.Article;
import ru.matyunin.inno.homework10.models.User;
import ru.matyunin.inno.homework10.repo.Crud;
import ru.matyunin.inno.homework10.repo.CrudArticle;
import ru.matyunin.inno.homework10.repo.CrudArticleImpl;
import ru.matyunin.inno.homework10.repo.CrudUserImpl;

import java.util.List;



/**
 * @author Артём Матюнин
 * реализует логику работы программы
 * Пока от реализованных здесь методов немного пользы, они, по-сути, тестовые. Но если попытаться реализовать логику
 * ближе к реальности, то лучше это сделать в этом классе.
 */

public class BlogServiceImpl implements BlogService {
    private final Crud<User> crudUser = new CrudUserImpl();
    private final CrudArticle crudArticle = new CrudArticleImpl();
    private final Crud<Article> crudArticleParent = crudArticle;

    public BlogServiceImpl() {

    }

    //пробуем печатать все посты
    @Override
    public List<Article> printArticles() {
       return crudArticleParent.findAll();
    }

    //добавим пост и ограничения на просмотр
    public void addArticle(Article article) {
        crudArticle.addArticle(article);
    }

    //проверяем бачинг
    @Override
    public int[] addUsers(List<User> users) {
        return crudUser.save(users);

    }


}
