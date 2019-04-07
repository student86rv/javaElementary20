package com.company;

import java.io.File;
import java.util.List;

public class FileStorage implements Storage{

    private List<User> users;
    //private String fileName;
    private int count = 0;

    public FileStorage(String fileName) {
        File file = new File(System.getProperty("user.dir"), fileName);
        System.out.println(file.getAbsolutePath());
        if (file.exists()) {
            this.users = readFromFile(fileName);
            System.out.println("Users in file: " + users.size());
        }
        else {
            System.out.println("New file: " + fileName);
            writeToFile(null, fileName);
        }
    }

    @Override
    public void removeAll() {
        users.clear();
    }

    @Override
    public void removeUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
            }
        }
    }

    @Override
    public void removeUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                users.remove(user);
            }
        }
    }

    @Override
    public void addUser(User user) {
        user.setId(count);
        users.add(user);
        count++;
    }

    @Override
    public void updateUser(User user) {
        for (User oldUser : users) {
            if (oldUser.getId() == user.getId()) {
                users.set(users.indexOf(oldUser), user);
            }
        }
    }

    @Override
    public User getUser(int id) {
        for (User user : users) {
                if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    private void writeToFile(List<User> users, String fileName) {

    }

    private List<User> readFromFile(String fileName) {

        return null;
    }
}
