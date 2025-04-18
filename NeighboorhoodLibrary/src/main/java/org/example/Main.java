package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Book[] inventory =new Book[20];

        inventory[0] = new Book(100, "1011234567", "Goosebumps", true, "Will");
        inventory[1] = new Book(101, "1011234568", "Mystery Manor", false, "No one");
        inventory[2] = new Book(102, "1011234569", "The Lost Planet", true, "Clark");
        inventory[3] = new Book(103, "1011234570", "Under the Sea", true, "Marina");
        inventory[4] = new Book(104, "1011234571", "Time Traveler", false, "No one");
        inventory[5] = new Book(105, "1011234572", "Galaxy Quest", true, "Zara");
        inventory[6] = new Book(106, "1011234573", "Haunted Hills", false, "No one");
        inventory[7] = new Book(107, "1011234574", "Desert Storm", true, "Sahara");
        inventory[8] = new Book(108, "1011234575", "Jungle Book 2", true, "Rudyard");
        inventory[9] = new Book(109, "1011234576", "Island Escape", false, "No one");
        inventory[10] = new Book(110, "1011234577", "Dragon’s Fire", true, "Tolkien");
        inventory[11] = new Book(111, "1011234578", "Winter is Coming", false, "No one");
        inventory[12] = new Book(112, "1011234579", "Cyber Age", true, "Neo");
        inventory[13] = new Book(113, "1011234580", "Parallel", true, "Quinn");
        inventory[14] = new Book(114, "1011234581", "Midnight Hour", false, "No one");
        inventory[15] = new Book(115, "1011234582", "Ocean’s Deep", true, "Nemo");
        inventory[16] = new Book(116, "1011234583", "Alien Contact", true, "Sigourney");
        inventory[17] = new Book(117, "1011234584", "Code Breaker", false, "No one");
        inventory[18] = new Book(118, "1011234585", "The Last Stand", true, "Max");
        inventory[19] = new Book(119, "1011234586", "Crimson Sky", false, "No one");

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Welcome to the library's App\n");
            System.out.println("(1) Show Available books");
            System.out.println("(2) Show Checked Out Books");
            System.out.println("(3) Exit");

            int userChoice = scanner.nextInt();

            switch(userChoice){
                case 1:
                    for(int i = 0; i < inventory.length; i++){

                        if(inventory[i].isCheckedOut() != true){
                            System.out.println(inventory[i].toString()); //Used the toString method(inserted from generator)
                        }

                    }
                    System.out.println("Want to check out a book? \n(1) yes \n(2 - 9) No, take me back to the Main Menu");
                    userChoice = scanner.nextInt();
                    switch (userChoice) {
                        case 1:
                            System.out.println("What is your name?");
                            scanner.nextLine();
                            String name = scanner.nextLine();
                            System.out.println("Which book would you like to check out? \nBook Name: ");

                            String  bookName = scanner.nextLine();
                            for(int i = 0; i < inventory.length; i++){

                                if(inventory[i].getTitle().equalsIgnoreCase(bookName)){
                                    inventory[i].setCheckedOutTo(name);
                                    inventory[i].setCheckedOut(true);
                                }

                            }

                            break;
                        default:
                            break;

                    }
                    break;
                case 2:
                    for(int i = 0; i < inventory.length; i++){

                        if(inventory[i].isCheckedOut() == true){
                            System.out.println(inventory[i].toString1()); //Used the toString method(inserted from generator)
                        }
                    }
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Want to check in a book?\n (C) Check in book\n (X) -No, take me back to Main Menu");
                    scanner.nextLine();
                    String userInput = scanner.nextLine();
                    char ch = userInput.charAt(0);
                    ch = Character.toLowerCase(ch); //Normalized
                    switch (ch){
                        case 'c':

                            System.out.println("What's the book's name that you would like to check in?");

                            int bookId = scanner.nextInt();
                            for(int i = 0; i < inventory.length; i++){
                                if(bookId == inventory[i].getId()) {
                                    inventory[i].setCheckedOut(false);
                                    inventory[i].setCheckedOutTo("No one");
                                }

                            }

                            break;
                            case 'x':
                                break;
                        }
                        break;
                case 3:
                    System.exit(0);
                    break;

            }

        }

    }


}
