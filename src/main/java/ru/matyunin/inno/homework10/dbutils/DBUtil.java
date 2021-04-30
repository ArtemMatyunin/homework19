package ru.matyunin.inno.homework10.dbutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

/***
 * @author Артём Матюнин
 * Используем этот класс для обновления БД
 *
 */

public class DBUtil {

    private static final Logger logger = LoggerFactory.getLogger(DBUtil.class);

    public static void renewDatabase(Connection connection) {
        try {
            String initBlogTables = Files.lines(Paths.get("init_db.sql")).collect(Collectors.joining());
            String initLoggerTables = Files.lines(Paths.get("init_log_tables.sql")).collect(Collectors.joining());

            try (Statement statement = connection.createStatement()) {
                statement.execute(initBlogTables);
                statement.execute(initLoggerTables);
                logger.info("База данных '{}' обновлена", connection.getCatalog());
            }
        } catch (IOException | SQLException e) {
            logger.error("База данных не обновлена", e);
        }
    }
}
