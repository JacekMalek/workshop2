package pl.coderslab.entity;

public class Main {

    public static void main(String[] args) {

    User user  = new User("Koala", "koala.m@gmial.com", "password1");
    UserDao userDao = new UserDao();
    userDao.create(user);

   // User firstUser = userDao.create();



    }
}
