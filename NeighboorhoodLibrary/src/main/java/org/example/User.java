package org.example;

import java.util.Scanner;

public class User {
    private String name;
    private int numberOfBooks;

    public User(String name , int numberOfBooks) {
        this.name = name;
        this.numberOfBooks = numberOfBooks;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

}
