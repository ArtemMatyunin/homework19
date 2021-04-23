package ru.matyunin.inno.homework10.app;

import ru.matyunin.inno.homework10.dbutils.ConnectionManager;
import ru.matyunin.inno.homework10.dbutils.ConnectionManagerImpl;
import ru.matyunin.inno.homework10.models.Article;
import ru.matyunin.inno.homework10.repo.Crud;
import ru.matyunin.inno.homework10.repo.CrudArticleImpl;
import ru.matyunin.inno.homework10.service.BlogService;
import ru.matyunin.inno.homework10.service.BlogServiceImpl;
import ru.matyunin.inno.homework10.dbutils.DBUtil;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {

        ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();
        try {
            DBUtil.renewDatabase(connectionManager.getConnection());
        } catch (SQLException throwables) {
            System.err.println("Не удалось подключиться к БД");
            throwables.printStackTrace();
        }

        BlogService blogService = new BlogServiceImpl();
        System.out.println("Добавлено " + blogService.addUsers().length + " пользователей");
        blogService.addArticle();
        blogService.printArticles();

    }
}
