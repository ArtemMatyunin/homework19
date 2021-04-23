package ru.matyunin.inno.homework10.repo;

import ru.matyunin.inno.homework10.models.User;
import ru.matyunin.inno.homework10.dbutils.ConnectionManager;
import ru.matyunin.inno.homework10.dbutils.ConnectionManagerImpl;

import java.sql.*;
import java.util.List;

/**
 * @author Артём Матюнин
 * реализует CRUD-операции с моделью User
 *
 */

public class CrudUserImpl implements Crud<User> {

    private static final ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();

    /**
     * Используем батчинг и параметризованный запрос
     * @param entities список пользователей, который нужно добавить в БД
     * @return возвращаем набор id's добавленных записей
     *
     */

    @Override
    public int[] save(List<User> entities) {
        String SQL_ADD_USERS = "INSERT INTO users (user_name, user_firstname, user_surname, user_birthdate) values (?,?,?,?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USERS)
        ) {
            for (User entity : entities) {
                preparedStatement.setString(1, entity.getUserName());
                preparedStatement.setString(2, entity.getUserFirstName());
                preparedStatement.setString(3, entity.getUserSurName());
                preparedStatement.setDate(4, (Date.valueOf(entity.getUserBirthDate())));
                preparedStatement.addBatch();

            }

            return preparedStatement.executeBatch();

        } catch (SQLException throwables) {

            System.err.println("Не удалось произвести запись в базу");
            throwables.printStackTrace();
        }

        return new int[0];
    }

    @Override
    public List<User> findAll() {
        return null;
    }


}
