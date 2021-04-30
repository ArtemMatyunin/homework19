package ru.matyunin.inno.homework10.repo;

import java.util.List;

/**
 * @author Артём Матюнин
 *
 * @param <T> позволяет принимать на вход любой элемент (из моделей User, Article, Comment) для совершения стандартных
 *           CRUD - операций. Для каждой модели есть свой интерфейс, если ее поведение специфично. Конкретный тип
 *           подставляется в реализациях
 */
public interface Crud<T> {

    /**
     * "Пакетное" добавление
     * @param entity список сущностей, которые нужно добавить в БД
     * @return массив id добавленных элементов
     */

    int[] save(List<T> entity);

    /**
     * @return возвращает список всех постов
     */
    List<T> findAll();

}
