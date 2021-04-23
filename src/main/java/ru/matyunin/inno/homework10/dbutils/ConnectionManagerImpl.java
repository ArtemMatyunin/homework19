package ru.matyunin.inno.homework10.dbutils;

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
        } catch (SQLException throwables) {
            System.err.println("Невозможно подключиться к базе данных. Проверьте параметры подключения");
            throwables.printStackTrace();
        }
        return connection;
    }
}
