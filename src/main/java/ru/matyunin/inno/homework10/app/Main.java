package ru.matyunin.inno.homework10.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matyunin.inno.homework10.dbutils.ConnectionManager;
import ru.matyunin.inno.homework10.dbutils.ConnectionManagerImpl;

import ru.matyunin.inno.homework10.models.Article;
import ru.matyunin.inno.homework10.models.User;
import ru.matyunin.inno.homework10.service.BlogService;
import ru.matyunin.inno.homework10.service.BlogServiceImpl;
import ru.matyunin.inno.homework10.dbutils.DBUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static java.time.LocalDate.now;


public class Main {

    private final static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Программа запущена");
        ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();
        DBUtil.renewDatabase(connectionManager.getConnection());

        BlogService blogService = new BlogServiceImpl();

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
        int countUsers = blogService.addUsers(users).length;
        logger.info("Добавлено {} пользователей", countUsers);


        List<Integer> hideList = new ArrayList<>();
        hideList.add(2);
        hideList.add(3);
        Article article = new Article.Builder().articleAuthorId(1)
                .articleTitle("Декларативное управление зависимостями")
                .articleText("Большинство проектов Java для правильного функционирования полагаются на другие " +
                        "проекты и фреймворки с открытым кодом.\n" +
                        "Ручное скачивание этих зависимостей и поддержка их " +
                        "версий при работе над проектом может оказаться довольно затруднительным делом")
                .articleDate(now())
                .articleHideForUsers(hideList)
                .build();
        blogService.addArticle(article);

        logger.info("{}",blogService.printArticles());
        logger.info("Работа программы завершена");
    }
}
