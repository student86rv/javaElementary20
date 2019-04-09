package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStorage implements Storage {

    private List<User> users = new ArrayList<>();
    private File userFile;
    private int count = 0;

    public FileStorage(String fileName) {
        this.userFile = new File(System.getProperty("user.dir"), fileName);
        if (userFile.exists()) {
            this.users = readFromFile(userFile);
            System.out.println("Users in file: " + users.size());
            count = users.size();
        } else {
            System.out.println("New file: " + userFile.getName());
            writeToFile(users, userFile);
        }
    }

    @Override
    public void removeAll() {
        users.clear();
        count = 0;
        writeToFile(users, userFile);
    }

    @Override
    public void removeUser(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
            }
        }
        writeToFile(users, userFile);
    }

    @Override
    public void removeUserByName(String name) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(name)) {
                users.remove(i);
            }
        }
        writeToFile(users, userFile);
    }

    @Override
    public void addUser(User user) {
        user.setId(count);
        users.add(user);
        count++;
        writeToFile(users, userFile);
    }

    @Override
    public void updateUser(User user) {
        for (User oldUser : users) {
            if (oldUser.getId() == user.getId()) {
                users.set(users.indexOf(oldUser), user);
            }
        }
        writeToFile(users, userFile);
    }

    @Override
    public User getUser(int id) {
        users = readFromFile(userFile);
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

    private void writeToFile(List<User> users, File file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(users);
        try {
            FileUtils.writeStringToFile(file, jsonString, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<User> readFromFile(File file) {
        String jsonString = "";
        try {
            jsonString = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<User> usersList = new Gson()
                .fromJson(jsonString, new TypeToken<ArrayList<User>>() {}
                .getType());
        return usersList;
    }
}
