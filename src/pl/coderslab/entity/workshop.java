package pl.coderslab.entity;

import java.util.Scanner;


public class workshop {

    public static void printOptions(String[] tab) {

        System.out.println("Please select an option: ");
        for (String option : tab) {
            System.out.println(option);
        }
    }

    static final String[] OPTIONS = {"add", "remove", "update", "list", "listAll", "exit"};

    public static void main(String[] args) {
        
          printOptions(OPTIONS);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                switch (input) {
                    case "exit":
                        System.out.println("Bye, bye.");
                        System.exit(0);
                        break;
                    case "add":
                        createUser();
                        System.out.println("\n");
                        break;
                    case "remove":
                        deleteUser();
                        System.out.println("\n");
                        break;
                    case "update":
                        updateUser();
                        System.out.println("\n");
                        break;
                    case "list":
                        readUser();
                        System.out.println("\n");
                        break;
                    case "listAll":
                        findAllUsers();
                        System.out.println("\n");
                        break;
                    default:
                        System.out.println("Please select a correct option.");
                }
                printOptions(OPTIONS);
            }
    }

    public static User createUser() {
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj email nowego użytkownika: ");
        String email = scanner.nextLine();
        System.out.println("Podaj imię nowego użytkownika: ");
        String name = scanner.nextLine();
        System.out.println("Podaj hasło nowego użytkownika: ");
        String password = scanner.nextLine();
        User user = new User(email, name, password);
        userDao.create(user);
        System.out.println("Utworzono nowego użytkowniaka");
        return user;
    }
    
    public static void readUser() {
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer użytkownika do wyświetlenia: ");
        
        String number = scanner.next();
        System.out.println("Użytkownik numer " + number + " ma następujące dane: " + userDao.read(Integer.parseInt(number)));
    }

    public static void deleteUser() {
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

    public static void findAllUsers() {
        UserDao userDao = new UserDao();
        User[] all = userDao.findAll();
        System.out.println("Oto lista wszystkich użytkowników w bazie danych: \n");
        for (User u : all) {
            System.out.println(u);
        }
    }

    public static void updateUser(){
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podać numer użytkownika do uaktualnienia: ");
        while(!scanner.hasNextInt()){
            System.out.println("Proszę podać prawidłowe dane: ");
            scanner.next();
        }
        String number = scanner.next();
        User user = userDao.read(Integer.parseInt(number));
        
        System.out.println("Podaj email użytkownika: ");
        String email = scanner.nextLine();
        
        System.out.println("Podaj imię użytkownika: ");
        String name = scanner.nextLine();
        
        System.out.println("Podaj hasło użytkownika: ");
        String password = scanner.nextLine();
        userDao.create(user);
        user.setEmail(email);
        user.setUserName(name);
        user.setPassword(password);
        userDao.update(user);
    }
} 
    
 /*   public static void noInt(){
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Proszę podać prawidłowe dane: ");
        }
    }
*/


