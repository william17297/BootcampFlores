package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException { //added the throws for the time thing
        Book[] inventory =new Book[20];

        inventory[0] = new Book(100, "1011234567", "Goosebumps", true, "Will", "Due in 9 days " );
        inventory[1] = new Book(101, "1011234568", "Mystery Manor", false, "No one", "" );
        inventory[2] = new Book(102, "1011234569", "The Lost Planet", true, "Will" , "Due in 5 days" );
        inventory[3] = new Book(103, "1011234570", "Under the Sea", true, "Marina" , "Due in 14 days");
        inventory[4] = new Book(104, "1011234571", "Time Traveler", false, "No one","" );
        inventory[5] = new Book(105, "1011234572", "Galaxy Quest", true, "Zara" , "Due in 3 days" );
        inventory[6] = new Book(106, "1011234573", "Haunted Hills", false, "No one" ,"");
        inventory[7] = new Book(107, "1011234574", "Desert Storm", true, "Sahara" , "Due in 2 days" );
        inventory[8] = new Book(108, "1011234575", "Jungle Book 2", true, "Rudyard" , "Due in 7 days" );
        inventory[9] = new Book(109, "1011234576", "Island Escape", false, "No one","" );
        inventory[10] = new Book(110, "1011234577", "Dragon’s Fire", true, "Tolkien" , "Due in 9 days");
        inventory[11] = new Book(111, "1011234578", "Winter is Coming", false, "No one","" );
        inventory[12] = new Book(112, "1011234579", "Cyber Age", true, "Neo" ,"");
        inventory[13] = new Book(113, "1011234580", "Parallel", true, "Quinn" , "Due in 11 days" );
        inventory[14] = new Book(114, "1011234581", "Midnight Hour", false, "No one" ,"");
        inventory[15] = new Book(115, "1011234582", "Ocean’s Deep", true, "Nemo" , "Due in 4 days" );
        inventory[16] = new Book(116, "1011234583", "Alien Contact", true, "Sigourney" , "Due in 5 days");
        inventory[17] = new Book(117, "1011234584", "Code Breaker", false, "No one" ,"");
        inventory[18] = new Book(118, "1011234585", "The Last Stand", true, "Max" , "Due in 7 days");
        inventory[19] = new Book(119, "1011234586", "Crimson Sky", false, "No one" ,"");

        User[] listOfUsers = new User[100];

        listOfUsers[0] = new User("Will" , 2);
        listOfUsers[1] = new User("Clark" , 0);
        listOfUsers[2] = new User("Marina" , 1);
        listOfUsers[3] = new User("Zara" , 1);
        listOfUsers[4] = new User("Sahara" , 1);
        listOfUsers[5] = new User("Rudyard" , 1);
        listOfUsers[6] = new User("Tolkien" , 1);
        listOfUsers[7] = new User("Neo" , 1);
        listOfUsers[8] = new User("Quinn" , 1);
        listOfUsers[9] = new User("Nemo" , 1);
        listOfUsers[10] = new User("Sigourney" , 1);
        listOfUsers[11] = new User("Max" , 1);

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Welcome to the library's App\n");
            System.out.println("(1) Show Available Books + Check out Books");
            System.out.println("(2) Show Checked Out Books + Check in Books");
            System.out.println("(3) Search Books by Title or ISBN");
            System.out.println("(4) Exit");
            int userChoice = scanner.nextInt();

            switch(userChoice){
                case 1:
                    availableBooks(inventory , listOfUsers , scanner);
                    break;
                case 2:
                    checkedOutBooks(inventory , scanner);
                    break;
                case 3:
                    searchByTitleOrIsbn(inventory , scanner);
                    break;
                case 4:
                    System.exit(0);
                    break;

            }

        }

    }
    public static void availableBooks(Book[] inventory ,User [] user, Scanner scanner) throws InterruptedException{
        for(int i = 0; i < inventory.length; i++){

            if(inventory[i].isCheckedOut() != true){
                System.out.println(inventory[i].toString()); //Used the toString method(inserted from generator)
                Thread.sleep(400);
            }


        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        Thread.sleep(700);
        searchByName(inventory ,user , scanner);
    }
    public static void checkedOutBooks(Book[] inventory , Scanner scanner) throws InterruptedException{
        for(int i = 0; i < inventory.length; i++){

            if(inventory[i].isCheckedOut() == true){
                System.out.println(inventory[i].toString1()); //Used the toString method(inserted from generator)
                Thread.sleep(500);
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Want to check in a book?\n (C) Check in book\n (X) No, take me back to Main Menu");
        scanner.nextLine();
        String userInput = scanner.nextLine();
        char ch = userInput.charAt(0);
        ch = Character.toLowerCase(ch); //Normalized
        boolean isNotValidId = true;
        switch (ch){
            case 'c':

                System.out.println("What's the book's ID that you would like to check in?");
                int bookId = scanner.nextInt();
               // System.out.println("\n\n\n\n\n");
                for(int i = 0; i < inventory.length; i++){
                    if(bookId == inventory[i].getId() && inventory[i].isCheckedOut()) {
                        inventory[i].setCheckedOut(false);
                        inventory[i].setCheckedOutTo("No one");
                        isNotValidId = false;
                        System.out.print("... ...");
                        Thread.sleep(700);
                        System.out.print("\nBook Successfully Checked in");
                        Thread.sleep(1000);
                        System.out.println("\n");
                    }
                    else if ((i + 1) == inventory.length && isNotValidId){
                        System.out.print("That book is either already checked in or it is not on our registry.");
                        Thread.sleep(2000);
                        System.out.println("\n");
                    }

                }
                System.out.println("\n\n\n\n");
                break;
            case 'x':
                System.out.println("\n\n\n\n");
                break;
            default:
                System.out.println("Please try again.");
                break;
        }

    }
    public static void searchByTitleOrIsbn(Book[] inventory , Scanner scanner) throws InterruptedException{
        System.out.println("Which book do you want to find?");
        scanner.nextLine();
        String books = scanner.nextLine();
        System.out.println("\n");
        int booksFound = 0; //added booksFound to determine whether a book was found at the end of the iteration.
        for(int i = 0; i < inventory.length; i++){

            String ignoreCap = inventory[i].getTitle().toLowerCase(); //Normalizing Array string
            String ignoreUserCap = books.toLowerCase(); //Normalizing User string

              //Used contains to see if characters in user input are in ISBN and title
             if(inventory[i].getIsbn().contains(books) || ignoreCap.contains(ignoreUserCap)) {
                System.out.print("Simillar " + inventory[i]);


                if (inventory[i].isCheckedOut() != true) {
                    System.out.println(" ------- Available To check out");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                    Thread.sleep(700);

                } else {
                    System.out.println(" ------- Not Available");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                    Thread.sleep(700);
                }
                booksFound++;
            } else if (booksFound == 0 && (i + 1) == inventory.length) {
                 System.out.println("No books were found");
                 System.out.println("\n\n\n\n\n\n\n");
                 Thread.sleep(1000);
             }


        }
        System.out.println("\n\n\n\n");
    }
    public static void searchByName(Book[] inventory ,User[] user , Scanner scanner) throws InterruptedException { //added the throws for the time thing
        System.out.println("What is your name?");
        scanner.nextLine();
        String name = scanner.nextLine();
        boolean firstLoopIteration = true;
        boolean isNotSameBook = true;
        for(int i = 0; i < user.length; i++){
              if(user[i] != null){ //To avoid any exception errors
                if (user[i].getName().equalsIgnoreCase(name)) {
                    for(int i2 = 0; i2 < inventory.length; i2++) { //checking for every book in the database(array)

                        if (user[i].getName().equalsIgnoreCase(inventory[i2].getCheckedOutTo())) { //Checks if the book in the database is associated to the user

                            if (firstLoopIteration) {
                                System.out.println("\nThese are the book/s you have checked out:");
                                Thread.sleep(700);
                                firstLoopIteration = false;
                            }
                            System.out.println("--- " + inventory[i2].getTitle() + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                            Thread.sleep(1000);
                        }
                    }
                    System.out.println("\n\n\n\n");
                    Thread.sleep(1000); //Just to make it more appealing
                    System.out.println("\n\nWant to check out a book? \n(1) yes \n(2 - 9) No, take me back to the Main Menu");
                    int userChoice = scanner.nextInt();
                    switch (userChoice) {
                        case 1:
                            if (user[i].getNumberOfBooks() != 3) {
                                System.out.print("Which book would you like to check out? \nBook Name: ");
                                scanner.nextLine();
                                String bookName = scanner.nextLine();
                                for (int i3 = 0; i3 < inventory.length; i3++) {

                                    if (inventory[i3].getTitle().equalsIgnoreCase(bookName)) {
                                        inventory[i3].setCheckedOutTo(name);
                                        inventory[i3].setCheckedOut(true);
                                        inventory[i3].setDueDate("Due in 14 days");
                                        user[i].setNumberOfBooks(user[i].getNumberOfBooks() + 1); //Used to add to the number of books user has.
                                        isNotSameBook = false; // once the book is found this becomes false since the book the user typed is the same as the inventory's.
                                        System.out.println("\nBook due in 14 days!");
                                        Thread.sleep(1000);
                                        System.out.println("\n\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                                    }
                                    else if((i3 + 1) == inventory.length && isNotSameBook){
                                        System.out.println("That book is not in our registry.");
                                    }
                                }
                            } else {
                                System.out.println("You have reached the maximum number of books you can have checked out!");
                                System.out.println("\n\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                Thread.sleep(1000);
                            }

                            break;
                        default:
                            System.out.println("\n\n\n\n");
                            break;
                    }
                    break;
                }

            }

            //This is to display this only once the loop reached the end of the array with an index with an actual name on it
            else if(user[i] == null){
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("That name is not on our registry.");
                  System.out.println("\n\n\n\n");
                Thread.sleep(1050);
                break;

            }

        }

    }
}


