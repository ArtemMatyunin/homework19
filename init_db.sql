 DROP TABLE IF EXISTS hide;

                     DROP TABLE IF EXISTS comments;

                     DROP TABLE IF EXISTS articles;

                     DROP TABLE IF EXISTS users;

                     create table users
                     (
                     user_id        bigserial   not null
                             constraint users_pk
                                 primary key,
                         user_name      char(10)    not null,
                         user_firstname varchar(15) not null,
                         user_surname   varchar(15),
                         user_birthdate date
                     );
                         comment on table users is 'all users of this blog';
                         alter table users
                         owner to postgres;
                         create unique index users_user_id_uindex
                         on users (user_id);
                         create unique index users_user_name_uindex
                         on users (user_name);

                     create table articles
                     (article_id bigserial not null
                     constraint articles_pk
                     primary key,
                     article_title text,
                     article_text   text,
                     article_author integer                    not null
                     constraint articles_users_user_id_fk
                     references users,
                     article_date   date
                     );
                     comment on table articles is 'main repository of all articles';
                     alter table articles
                     owner to postgres;

                     create unique index articles_article_id_uindex
                     on articles (article_id);

                     create table comments
                     (
                         comments_id      bigserial not null
                             constraint comments_pk
                                 primary key,
                         comments_article integer   not null
                             constraint comments_articles_article_id_fk
                                 references articles,
                         comments_author  integer   not null
                             constraint comments_users_user_id_fk
                                 references users,
                         comments_text    text      not null,
                         comments_date    date      not null
                     );
                     create table hide
                     (
                         hide_Id    bigserial not null
         constraint hide_pk
                                 primary key,
                         hide_article integer
                             constraint hide_articles_article_id_fk
                                 references articles,
                         hide_user    integer   not null
                             constraint hide_users_user_id_fk
                                 references users
                     );

                     comment on table comments is 'comments on articles';

                     alter table comments
                         owner to postgres;

                     create unique index comments_comments_id_uindex
                         on comments (comments_id);

                     INSERT INTO users (user_name, user_firstname, user_surname, user_birthdate)
                     VALUES
                        ('TestUser01', 'Silvester', 'Stallone', '01.02.1960'),
                        ('TestUser02', 'Jackie', 'Chan', '02.08.1965'),
                        ('TestUser03', 'Jim', 'Carrey', '01.02.1967');
                     INSERT INTO articles(article_title, article_text, article_author, article_date)
                     VALUES
                     ('JDBC (Java DataBase Connectivity)', 'JDBC (Java DataBase Connectivity) – стандартный прикладной интерфейс (API) языка Java для ' ||
                                                           'организации взаимодействия между приложением и СУБД.', '2','10.04.2021'),
                     ('Driver', 'Кроме классов с методами доступа к базам данных для каждой СУБД необходим драйвер JDBC — промежуточная программа, реализующая интерфейсы JDBC методами данной СУБД.','2','11.04.2021'),
                     ('Driver 2', 'Драйверы JDBC могут быть написаны разработчиками СУБД или независимыми фирмами.','1','12.04.2021'),
                     ('Driver 3', 'В настоящее время написано несколько сотен драйверов JDBC для разных СУБД под разные их версии и платформы', '3', '13.04.2021');
                     INSERT INTO comments (comments_article, comments_author, comments_text, comments_date)
                     VALUES
                     ('3','1','Все понятно','16.04.2021'),
                     ('1','2','Ерунда','16.04.2021'),
                     ('2','3','Отлично!','15.04.2021'),
                     ('4','2','Скукотища','16.04.2021'),
                     ('4','3','Это хороший текст','17.04.2021'),
                     ('1','2','Не интересно','18.04.2021'),
                    ('1','3','Круто!','16.04.2021');