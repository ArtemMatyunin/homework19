package ru.matyunin.inno.homework10.models;

import java.time.LocalDate;
import java.util.Objects;
/**
 * @author Артём Матюнин
 * Модель  пользователя в блоге
 * Для создания экземпляров используется Builder
 *
 */
public class User {

    private Integer userId;
    private String userName;
    private String userFirstName;
    private String userSurName;
    private LocalDate userBirthDate;

    public User() {


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) && Objects.equals(userFirstName, user.userFirstName) && Objects.equals(userSurName, user.userSurName) && Objects.equals(userBirthDate, user.userBirthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userFirstName, userSurName, userBirthDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userSurName='" + userSurName + '\'' +
                ", userBirthDate='" + userBirthDate + '\'' +

                '}';
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserSurName() {
        return userSurName;
    }

    public LocalDate getUserBirthDate() {
        return userBirthDate;
    }

    public static class Builder {

        private final User newUser;

        public Builder() {
            newUser = new User();
        }

        public Builder userId(Integer userId) {
            newUser.userId = userId;
            return this;
        }

        public Builder userName(String userName) {
            newUser.userName = userName;
            return this;
        }

        public Builder userFirstName(String userFirstName) {
            newUser.userFirstName = userFirstName;
            return this;
        }

        public Builder userSurName(String userSurName) {
            newUser.userSurName = userSurName;
            return this;
        }

        public Builder userBirthDate(LocalDate userBirthDate) {
            newUser.userBirthDate = userBirthDate;
            return this;
        }

        public User build() {
            return newUser;
        }

    }


}
