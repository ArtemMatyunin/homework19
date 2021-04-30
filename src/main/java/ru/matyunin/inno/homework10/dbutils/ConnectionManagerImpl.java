package ru.matyunin.inno.homework10.dbutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Артём Матюнин
 * Реализует метод подключения к БД
 * Закрытый конструктор, финальное статическое поле INSTANCE, не позволяют создавать новые экземпляры этого класса
 *
 */

public class ConnectionManagerImpl implements ConnectionManager {
    public static final ConnectionManager INSTANCE = new ConnectionManagerImpl();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ConnectionManagerImpl() {

    }

    public static ConnectionManager getInstance() {
        return INSTANCE;
    }

    /**
     * @return подготовленное соединение с БД
     *
     */
    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/blog",
                    "postgres",
                    "1111");
            logger.info("Открыто соединение с Базой данных '{}'", connection.getCatalog());
        } catch (SQLException e) {
            logger.error("Не удалось установить соединение с Базой данных", e);
        }

        return connection;
    }
}
