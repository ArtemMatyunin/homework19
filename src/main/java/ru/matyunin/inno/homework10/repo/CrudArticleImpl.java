package ru.matyunin.inno.homework10.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String SQL_ADD_ARTICLE = "INSERT INTO articles (article_title, article_text, article_author, article_date) VALUES (?,?,?,?)";
    private static final String SQL_ADD_HIDE = "INSERT INTO hide (hide_article, hide_user) VALUES (?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM articles inner join users u on u.user_id = articles.article_author";



    @Override
    public int[] save(List<Article> entity) {
        return new int[0];
    }

    /**
     * По заданию такого метода не требуется. Сделал, пока проверял коннект с базой и оставил
     *
     * @return список всех постов, с указанием имени автора
     */

    @Override
    public List<Article> findAll() {

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

        } catch (SQLException e) {
            logger.error("Не удалось получить данные", e);
        }
        logger.info("Получено {} записей", articles.size());
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

        boolean autoCommit;
        try (Connection connection = connectionManager.getConnection()) {
            autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            try (PreparedStatement psArt = connection.prepareStatement(SQL_ADD_ARTICLE)
            ) {
                psArt.setString(1, article.getArticleTitle());
                psArt.setString(2, article.getArticleText());
                psArt.setInt(3, article.getArticleAuthorId());
                psArt.setDate(4, Date.valueOf(article.getArticleDate()));
                psArt.executeUpdate();

                Savepoint after_article = connection.setSavepoint("after_article");
                addHides(connection, article.getArticleHideForUsers(), after_article);
                connection.commit();

                logger.info("Добавлена запись");

            } catch (SQLException e) {
                connection.rollback();
                logger.error("Не удалось выполнить запись", e);

            } finally {
                connection.setAutoCommit(autoCommit);
            }

        } catch (SQLException e) {
            logger.error("Не удалось выполнить запись", e);
        }
    }

    private int[] addHides(Connection connection, List<Integer> hides, Savepoint savepoint) throws SQLException {

        int[] rowsHides = new int[0];
        try (PreparedStatement psHide = connection.prepareStatement(SQL_ADD_HIDE)) {
                for (int hideForUser : hides) {
                psHide.setInt(1, 5);
                psHide.setInt(2, hideForUser);
                psHide.addBatch();
            }
            rowsHides = psHide.executeBatch();

        } catch (SQLException e) {
                  connection.rollback(savepoint);
                   logger.error("Не удалось выполнить запись", e);
        }

        logger.info("Добавлено {} записей", rowsHides.length);
        return rowsHides;
    }
}
