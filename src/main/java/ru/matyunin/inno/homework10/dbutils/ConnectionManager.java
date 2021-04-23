package ru.matyunin.inno.homework10.dbutils;

import java.sql.Connection;

/***
 * @author Артём
 * Интерфейс подключения к БД
 */
public interface ConnectionManager {
    Connection getConnection();
}
