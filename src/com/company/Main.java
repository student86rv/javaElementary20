package com.company;

public class Main {

    public static void main(String[] args) {

        FileStorage fileStorage = new FileStorage("0904.txt");

        fileStorage.addUser(new User("Alex", 25));
        fileStorage.addUser(new User("Oleg", 20));
        fileStorage.addUser(new User("Dmitrii", 27));
        fileStorage.addUser(new User("Anna", 19));
        fileStorage.addUser(new User("Andrey", 31));
        System.out.println("Users: " + fileStorage.getAllUsers());

        fileStorage.updateUser(new User(1, "Victor", 24));
        System.out.println("Update user 1...");
        System.out.println("Users: " + fileStorage.getAllUsers());

        System.out.println("User 2: " + fileStorage.getUser(2));

        fileStorage.removeUser(2);
        System.out.println("Remove user 2...");
        System.out.println("Users: " + fileStorage.getAllUsers());

        fileStorage.removeUserByName("Anna");
        System.out.println("Remove user Anna...");
        System.out.println("Users: " + fileStorage.getAllUsers());
    }
}
