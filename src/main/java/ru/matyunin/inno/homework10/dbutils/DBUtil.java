package ru.matyunin.inno.homework10.dbutils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/***
 * @author Артём Матюнин
 * Используем этот класс для обновления БД
 *
 */
public class DBUtil {


    public static void renewDatabase(Connection connection) throws SQLException {

        try (Statement statement = connection.createStatement()) {
            statement.execute("-- Database: blog\n"

                    + "DROP TABLE IF EXISTS hide;"
                    + "\n"
                    + "DROP TABLE IF EXISTS comments;"
                    + "\n"
                    + "DROP TABLE IF EXISTS articles;"
                    + "\n"
                    + "DROP TABLE IF EXISTS users;"
                    + "\n"
                    + "create table users\n"
                    + "(\n"
                    + "user_id        bigserial   not null\n"
                    + "        constraint users_pk\n"
                    + "            primary key,\n"
                    + "    user_name      char(10)    not null,\n"
                    + "    user_firstname varchar(15) not null,\n"
                    + "    user_surname   varchar(15),\n"
                    + "    user_birthdate date\n"
                    + ");\n"
                    + "    comment on table users is 'all users of this blog';\n"
                    + "    alter table users\n"
                    + "    owner to postgres;\n"
                    + "    create unique index users_user_id_uindex\n"
                    + "    on users (user_id);\n"
                    + "    create unique index users_user_name_uindex\n"
                    + "    on users (user_name);\n"
                    + "\n"
                    + "create table articles\n"
                    + "(article_id bigserial not null\n"
                    + "constraint articles_pk\n"
                    + "primary key,\n"
                    + "article_title text,\n"
                    + "article_text   text,\n"
                    + "article_author integer                    not null\n"
                    + "constraint articles_users_user_id_fk\n"
                    + "references users,\n"
                    + "article_date   date\n"
                    + ");\n"
                    + "comment on table articles is 'main repository of all articles';\n"
                    + "alter table articles\n"
                    + "owner to postgres;\n"

                    + "create unique index articles_article_id_uindex\n"
                    + "on articles (article_id);\n"

                    + "create table comments\n"
                    + "(\n"
                    + "    comments_id      bigserial not null\n"
                    + "        constraint comments_pk\n"
                    + "            primary key,\n"
                    + "    comments_article integer   not null\n"
                    + "        constraint comments_articles_article_id_fk\n"
                    + "            references articles,\n"
                    + "    comments_author  integer   not null\n"
                    + "        constraint comments_users_user_id_fk\n"
                    + "            references users,\n"
                    + "    comments_text    text      not null,\n"
                    + "    comments_date    date      not null\n"
                    + ");\n"
                    + "create table hide\n"
                    + "(\n"
                    + "    \"hide_Id\"    bigserial not null\n"
                    + "        constraint hide_pk\n"
                    + "            primary key,\n"
                    + "    hide_article integer\n"
                    + "        constraint hide_articles_article_id_fk\n"
                    + "            references articles,\n"
                    + "    hide_user    integer   not null\n"
                    + "        constraint hide_users_user_id_fk\n"
                    + "            references users\n"
                    + ");\n"
                    + "\n"
                    + "comment on table comments is 'comments on articles';\n"
                    + "\n"
                    + "alter table comments\n"
                    + "    owner to postgres;\n"
                    + "\n"
                    + "create unique index comments_comments_id_uindex\n"
                    + "    on comments (comments_id);"

                    + "INSERT INTO users (user_name, user_firstname, user_surname, user_birthdate)\n"
                    + "VALUES\n"
                    + "   ('TestUser01', 'Silvester', 'Stallone', '01.02.1960'),\n"
                    + "   ('TestUser02', 'Jackie', 'Chan', '02.08.1965'),\n"
                    + "   ('TestUser03', 'Jim', 'Carrey', '01.02.1967');\n"
                    + "INSERT INTO articles(article_title, article_text, article_author, article_date)\n"
                    + "VALUES \n"
                    + "('JDBC (Java DataBase Connectivity)', 'JDBC (Java DataBase Connectivity) – стандартный прикладной интерфейс (API) языка Java для\n"
                    + "организации взаимодействия между приложением и СУБД.', '2','10.04.2021'),\n"
                    + "('Driver', 'Кроме классов с методами доступа к базам данных для каждой СУБД необходим драйвер\n"
                    + "JDBC — промежуточная программа, реализующая интерфейсы JDBC методами данной СУБД.','2','11.04.2021'),\n"
                    + "('Driver 2', 'Драйверы JDBC могут быть написаны разработчиками СУБД или независимыми фирмами.','1','12.04.2021'),\n"
                    + "('Driver 3', 'В настоящее время написано несколько сотен драйверов JDBC для разных СУБД под разные их версии и платформы', '3', '13.04.2021');\n"
                    + "INSERT INTO comments (comments_article, comments_author, comments_text, comments_date)\n"
                    + "VALUES \n"
                    + "('3','1','Все понятно','16.04.2021'),\n"
                    + "('1','2','Ерунда','16.04.2021'),\n"
                    + "('2','3','Отлично!','15.04.2021'),\n"
                    + "('4','2','Скукотища','16.04.2021'),\n"
                    + "('4','3','Это хороший текст','17.04.2021'),\n"
                    + "('1','2','Не интересно','18.04.2021'),\n"
                    + "('1','3','Круто!','16.04.2021');\n"
            );
        }
    }
}
