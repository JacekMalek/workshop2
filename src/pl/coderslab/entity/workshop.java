package pl.coderslab.entity;


import java.util.Scanner;

public class workshop {

    public static void main(String[] args) {

        /*createUser();
       readUser();
       deleteUser();
*/
        UserDao userDao = new UserDao();
        User user = new User("Czarek", "czarek@gmail.com", "pass12");
        userDao.update(user);

    }
    public static User createUser(){
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię nowego użytkownika: ");
        String name = scanner.nextLine();
        System.out.println("Podaj email nowego użytkownika: ");
        String email = scanner.nextLine();
        System.out.println("Podaj hasło nowego użytkownika: ");
        String password = scanner.nextLine();

        User user = new User(name, email, password);
        userDao.create(user);
        System.out.println("Utworzono nowego użytkowniaka");
        return user;
    }


    public static void readUser() {
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer użytkownika do wyświetlenia: ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Proszę podać prawidłowe dane: ");
        }
        String number = scanner.next();
        System.out.println("Użytkownik numer " + number + " ma następujące dane: " + userDao.read(Integer.parseInt(number)));
    }

    public static void deleteUser(){
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer użytkownika do usunięcia: ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Proszę podać prawidłowe dane: ");
        }
        String number = scanner.next();
        userDao.delete(Integer.parseInt(number));
        System.out.println("Użytkownik o numerze " + number + " został usunięty ");
    }

}

