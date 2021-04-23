package ru.matyunin.inno.homework10.repo;

import ru.matyunin.inno.homework10.models.Article;
import ru.matyunin.inno.homework10.dbutils.ConnectionManager;
import ru.matyunin.inno.homework10.dbutils.ConnectionManagerImpl;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Артём матюнин
 * реализует CRUD-операции для модели Article
 */

public class CrudArticleImpl implements CrudArticle {
    private static final ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();

    @Override
    public int[] save(List<Article> entity) {
        return new int[0];
    }

    /**
     * По заданию такого метода не требуется. Сделал, пока проверял коннект с базой и оставил
     * @return список всех постов, с указанием имени автора
     */

    @Override
    public List<Article> findAll() {
        String SQL_FIND_BY_ID = "SELECT * FROM articles right join users u on u.user_id = articles.article_author";

        List<Article> articles = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)
        ) {
            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {

                    articles.add(new Article.Builder()
                            .articleId(rs.getInt(1))
                            .articleAuthor(rs.getString(8) + " " + rs.getString(9))
                            .articleTitle(rs.getString(2))
                            .articleText(rs.getString(3))
                            .articleAuthorId(rs.getInt(6)) // почему-то в этом месте вылетает NPE
                            .articleDate(rs.getDate(5).toLocalDate())
                            .build()
                    );
                }

            }

        } catch (SQLException throwables) {
            System.err.println("Не удалось получить данные из БД");
            throwables.printStackTrace();
        } catch (NullPointerException ignored) {

            // NPE вылетает при добавлении даты к экземпляру Article в том случае,
            // если собираем объект из недавно добавленной записи из метода addArticle.
            // Как он получается, я так и не понял - запись в БД происходит без проблем.
            // Если вызвать метод findAll после renewDatabase баз без добавления нового поста, то NPE не возникает
        }
        return articles;
    }

    /**
     * Добавяем записи в таблииы articles и hide. Отлючим автокоммит.
     * После выполнения или отката транзации включаем автокоммит
     * savepoint между добавлением поста и добавлением списка ограничений.
     *
     * @param article подготовленный пост, с заполненным списком пользователей, которым запрещено следить за этим постом
     */

    @Override
    public void addArticle(Article article) {

        String SQL_ADD_ARTICLE = "INSERT INTO articles (article_title, article_text, article_author, article_date) VALUES (?,?,?,?)";
        String SQL_ADD_HIDE = "INSERT INTO hide (hide_article, hide_user) VALUES (?,?)";
        try (Connection connection = connectionManager.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement psArt = connection.prepareStatement(SQL_ADD_ARTICLE);


            ) {
                psArt.setString(1, article.getArticleTitle());
                psArt.setString(2, article.getArticleText());
                psArt.setInt(3, article.getArticleAuthorId());
                psArt.setDate(4, Date.valueOf(article.getArticleDate()));

                psArt.executeUpdate();

                Savepoint after_article = connection.setSavepoint("after_article");

                try (PreparedStatement psHide = connection.prepareStatement(SQL_ADD_HIDE)) {
                    for (int hideForUser : article.getArticleHideForUsers()) {
                        psHide.setInt(1, 5);
                        psHide.setInt(2, hideForUser);
                        psHide.executeUpdate();
                    }
                } catch (SQLException throwables) {
                    connection.rollback(after_article);
                    System.err.println("Не удалось добавить ограничения");
                    throwables.printStackTrace();
                }

                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                System.err.println("Не удалось сохранить элементы");
                e.printStackTrace();

            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            System.err.println("Не удалось установить соединение");
            e.printStackTrace();
        }

    }

}
