package ru.matyunin.inno.homework10.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matyunin.inno.homework10.models.User;
import ru.matyunin.inno.homework10.dbutils.ConnectionManager;
import ru.matyunin.inno.homework10.dbutils.ConnectionManagerImpl;


import java.sql.*;
import java.util.List;


/**
 * @author Артём Матюнин
 * реализует CRUD-операции с моделью User
 */

public class CrudUserImpl implements Crud<User> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();

    private static final String SQL_ADD_USERS = "INSERT INTO users (user_name, user_firstname, user_surname, user_birthdate) values (?,?,?,?)";

    /**
     * Используем батчинг и параметризованный запрос
     *
     * @param users список пользователей, который нужно добавить в БД
     * @return возвращаем набор id's добавленных записей
     */

    @Override
    public int[] save(List<User> users) {
        int[] result = new int[0];
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USERS)
        ) {

            for (User user : users) {
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getUserFirstName());
                preparedStatement.setString(3, user.getUserSurName());
                preparedStatement.setDate(4, (Date.valueOf(user.getUserBirthDate())));
                preparedStatement.addBatch();

            }

            result = preparedStatement.executeBatch();


        } catch (SQLException e) {
            logger.error("Не удалось выполнить запись в Базу данных", e);
        }

        logger.info("Добавлено {} записей.", result.length);
        return result;
    }

    @Override
    public List<User> findAll() {
        return null;
    }


}
