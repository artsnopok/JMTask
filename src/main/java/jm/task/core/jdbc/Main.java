package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 42);
        userService.saveUser("Petr", "Petrov", (byte) 22);
        userService.saveUser("Aleksandr", "Aleksandrov", (byte) 62);
        userService.saveUser("Vasiliy", "Vasiliev", (byte) 25);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
        }


    }