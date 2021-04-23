package ru.matyunin.inno.homework10.service;

import ru.matyunin.inno.homework10.models.Article;
import ru.matyunin.inno.homework10.models.User;
import ru.matyunin.inno.homework10.repo.Crud;
import ru.matyunin.inno.homework10.repo.CrudArticle;
import ru.matyunin.inno.homework10.repo.CrudArticleImpl;
import ru.matyunin.inno.homework10.repo.CrudUserImpl;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;
import static java.time.LocalDate.of;

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
    public void printArticles() {
        System.out.println(crudArticleParent.findAll());
    }

    //добавим пост и ограничения на просмотр
    public void addArticle() {
        List<Integer> hideList = new ArrayList<>();

        hideList.add(2);
        hideList.add(3);
        crudArticle.addArticle(
                new Article.Builder().articleAuthorId(1)
                        .articleTitle("Декларативное управление зависимостями")
                        .articleText("Большинство проектов Java для правильного функционирования полагаются на другие " +
                                " проекты и фреймворки с открытым кодом.\n Ручное скачивание этих зависимостей и поддержка их " +
                                " версий при работе над проектом может оказаться довольно затруднительным делом")
                        .articleDate(now())
                        .articleHideForUsers(hideList)
                        .build()
        );
    }

    //проверяем бачинг
    @Override
    public int[] addUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User.Builder()
                .userName("testuser10")
                .userFirstName("Ivan")
                .userSurName("Ivanov")
                .userBirthDate(LocalDate.parse("1987-01-01"))
                .build());
        users.add(new User.Builder()
                .userName("testuser11")
                .userFirstName("Petr")
                .userSurName("Petrov")
                .userBirthDate(LocalDate.parse("1964-03-08"))
                .build());
        users.add(new User.Builder()
                .userName("testuser12")
                .userFirstName("Sidor")
                .userSurName("Sidorov")
                .userBirthDate(LocalDate.parse("2000-05-22"))
                .build());

        return crudUser.save(users);

    }


}
