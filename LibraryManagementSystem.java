import java.util.ArrayList; // import the ArrayList class
import java.util.InputMismatchException; // import the InputMismatchException class
import java.util.List; // import the List class
import java.util.Scanner; // import the Scanner class

public class LibraryManagementSystem { // class name
    private static List<Member> members = new ArrayList<>(); // create a list of members
    private static List<Book> books = new ArrayList<>(); // create a list of books

    public static void main(String[] args) { // main method
        Scanner scanner = new Scanner(System.in); // create a Scanner object
        int choice; // declare a variable to store the user's choice
        System.out.println("Welcome to the Library Management System!");

        do { // do-while loop
            displayMainMenu(); // call the displayMainMenu method
            choice = getIntInput(scanner, "Enter your choice: "); // call the getIntInput method

            switch (choice) { // switch statement
                case 1:
                    performAdminTasks(scanner); // call the performAdminTasks method
                    break; // break statement
                case 2:
                    performUserTasks(scanner); // call the performUserTasks method
                    break; // break statement
                case 3:
                    System.out.println("Thank you for using the Library Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3); // end of do-while loop

        scanner.close(); // close the scanner
    }

    public static void displayMainMenu() { // displayMainMenu method
        System.out.println("\n-- Main Menu --");
        System.out.println("1. Admin");
        System.out.println("2. User");
        System.out.println("3. Exit");
    }

    public static void performAdminTasks(Scanner scanner) { // performAdminTasks method
        String adminPassword = "admin123"; // declare a variable to store the admin password
        System.out.print("Enter admin password: "); // prompt the user to enter the admin password
        String password = scanner.nextLine(); // read the admin password from the user

        if (!password.equals(adminPassword)) { // if statement
            System.out.println("Incorrect password. Access denied."); // print the message
            return;
        }

        int choice; // declare a variable to store the user's choice

        do { // do-while loop
            displayAdminMenu(); // call the displayAdminMenu method
            choice = getIntInput(scanner, "Enter your choice: "); // call the getIntInput method

            switch (choice) { // switch statement
                case 1:
                    addMember(scanner); // call the addMember method
                    break;
                case 2:
                    removeMember(scanner); // call the removeMember method
                    break;
                case 3:
                    addBook(scanner); // call the addBook method
                    break;
                case 4:
                    removeBook(scanner); // call the removeBook method
                    break;
                case 5:
                    browseBooks(); // call the browseBooks method
                    break;
                case 6:
                    System.out.println("Returning to the Main Menu."); // print the message
                    break;
                default:
                    System.out.println("Invalid choice. Please try again."); // print the message
            }
        } while (choice != 6); // end of do-while loop
    }

    public static void displayAdminMenu() { // displayAdminMenu method
        System.out.println("\n-- Admin Menu --");
        System.out.println("1. Add Member");
        System.out.println("2. Remove Member");
        System.out.println("3. Add Book");
        System.out.println("4. Remove Book");
        System.out.println("5. Browse Books");
        System.out.println("6. Return to Main Menu");
    }

    public static void addMember(Scanner scanner) { // addMember method
        System.out.println("\nAdding a new member:"); // print the message

        int memberId = getIntInput(scanner, "Enter member ID: "); // call the getIntInput method

        if (findMemberById(memberId) != null) { // if statement
            System.out.println("Member with ID " + memberId + " already exists."); // print the message
            return; // return statement
        }

        String memberName = getStringInput(scanner, "Enter member name: "); // call the getStringInput method

        Member newMember = new Member(memberId, memberName); // create a new member object
        members.add(newMember); // add the new member to the list of members

        System.out.println("Member added: " + newMember); // print the message
    }

    public static void removeMember(Scanner scanner) { // removeMember method
        System.out.println("\nRemoving a member:"); // print the message

        int memberId = getIntInput(scanner, "Enter member ID: "); // call the getIntInput method

        Member memberToRemove = findMemberById(memberId); // call the findMemberById method

        if (memberToRemove == null) { // if statement
            System.out.println("Member with ID " + memberId + " not found."); // print the message
            return;
        }

        members.remove(memberToRemove); // remove the member from the list of members

        System.out.println("Member removed: " + memberToRemove); // print the message
    }

    public static void addBook(Scanner scanner) { // addBook method
        System.out.println("\nAdding a new book:"); // print the message

        int bookId = getIntInput(scanner, "Enter book ID: "); // call the getIntInput method

        if (findBookById(bookId) != null) { // if statement
            System.out.println("Book with ID " + bookId + " already exists.");
            return;
        }

        String bookTitle = getStringInput(scanner, "Enter book title: "); // call the getStringInput method
        String bookAuthor = getStringInput(scanner, "Enter book author: "); // call the getStringInput method

        Book newBook = new Book(bookId, bookTitle, bookAuthor); // create a new book object
        books.add(newBook); // add the new book to the list of books

        System.out.println("Book added: " + newBook); // print the message
    }

    public static void removeBook(Scanner scanner) { // removeBook method
        System.out.println("\nRemoving a book:"); // print the message

        int bookId = getIntInput(scanner, "Enter book ID: "); // call the getIntInput method

        Book bookToRemove = findBookById(bookId); // call the findBookById method

        if (bookToRemove == null) { // if statement
            System.out.println("Book with ID " + bookId + " not found."); // print the message
            return; // return statement
        }

        books.remove(bookToRemove); // remove the book from the list of books

        System.out.println("Book removed: " + bookToRemove); // print the message
    }

    public static void browseBooks() {  // browseBooks method
        System.out.println("\nBrowsing books:");    // print the message

        if (books.isEmpty()) {  // if statement
            System.out.println("No books available.");  // print the message
            return;
        }

        for (Book book : books) {   // for-each loop
            System.out.println(book);
        }
    }

    public static void performUserTasks(Scanner scanner) { // performUserTasks method
        int choice; // declare a variable to store the user's choice

        do { // do-while loop
            displayUserMenu(); // call the displayUserMenu method
            choice = getIntInput(scanner, "Enter your choice: "); // call the getIntInput method

            switch (choice) { // switch statement
                case 1:
                    browseBooks(); // call the browseBooks method
                    break;
                case 2:
                    searchBook(scanner); // call the searchBook method
                    break;
                case 3:
                    issueBook(scanner); // call the issueBook method
                    break;
                case 4:
                    returnBook(scanner); // call the returnBook method
                    break;
                case 5:
                    sendQuery(scanner); // call the sendQuery method
                    break;
                case 6:
                    System.out.println("Returning to the Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6); // end of do-while loop
    }

    public static void displayUserMenu() { // displayUserMenu method
        System.out.println("\n-- User Menu --");
        System.out.println("1. Browse Books");
        System.out.println("2. Search Book");
        System.out.println("3. Issue Book");
        System.out.println("4. Return Book");
        System.out.println("5. Send Query");
        System.out.println("6. Return to Main Menu");
    }

    public static void searchBook(Scanner scanner) {  // searchBook method
        System.out.print("\nEnter book title or author: ");
        String searchTerm = scanner.nextLine(); // read the user's input

        List<Book> searchResults = new ArrayList<>(); // create a new list to store the search results

        for (Book book : books) { // for-each loop
            if (book.getTitle().equalsIgnoreCase(searchTerm) || book.getAuthor().equalsIgnoreCase(searchTerm)) { // if statement
                searchResults.add(book); // add the book to the list of search results
            }
        }

        if (searchResults.isEmpty()) { // if statement
            System.out.println("No matching books found.");
        } else {
            System.out.println("Search Results:");
            for (Book book : searchResults) { // for-each loop
                System.out.println(book); // print the book
            }
        }
    }

    public static void issueBook(Scanner scanner) { // issueBook method
        System.out.print("\nEnter member ID: "); // print the message
        int memberId = getIntInput(scanner, "Enter member ID: ");   // call the getIntInput method

        Member member = findMemberById(memberId); // call the findMemberById method

        if (member == null) { // if statement
            System.out.println("Member with ID " + memberId + " not found.");
            return;
        }

        System.out.print("Enter book ID: ");
        int bookId = getIntInput(scanner, "Enter book ID: ");  // call the getIntInput method

        Book book = findBookById(bookId); // call the findBookById method

        if (book == null) { // if statement
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }

        if (!book.isAvailable()) { // if statement
            System.out.println("Book is not available for issuance.");
            return;
        }

        member.issueBook(book); // call the issueBook method
        book.setAvailable(false); // set the book to unavailable

        System.out.println("Book issued successfully.");
    }

    public static void returnBook(Scanner scanner) { // returnBook method
        System.out.print("\nEnter member ID: ");
        int memberId = getIntInput(scanner, "Enter member ID: "); // call the getIntInput method

        Member member = findMemberById(memberId); // call the findMemberById method

        if (member == null) {
            System.out.println("Member with ID " + memberId + " not found.");
            return;
        }

        System.out.print("Enter book ID: "); // print the message
        int bookId = getIntInput(scanner, "Enter book ID: "); // call the getIntInput method

        Book book = findBookById(bookId); // call the findBookById method

        if (book == null) {
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }

        if (!member.returnBook(book)) { // if statement
            System.out.println("Member does not have the specified book.");
            return; // return statement
        }

        book.setAvailable(true); // set the book to available

        System.out.println("Book returned successfully.");
    }

    public static void sendQuery(Scanner scanner) { // sendQuery method
        System.out.print("\nEnter your query: ");
        String query = scanner.nextLine(); // read the user's input

        System.out.println("Query sent: " + query);
    }

    public static Member findMemberById(int memberId) { // findMemberById method
        for (Member member : members) { // for-each loop
            if (member.getId() == memberId) { // if statement
                return member;
            }
        }
        return null; // return statement
    }

    public static Book findBookById(int bookId) { // findBookById method
        for (Book book : books) { // for-each loop
            if (book.getId() == bookId) { 
                return book;
            }
        }
        return null;
    }

    public static int getIntInput(Scanner scanner, String message) { // getIntInput method
        int input;
        while (true) { // while loop
            try { // try-catch block
                System.out.print(message); 
                input = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character
                break;
            } catch (InputMismatchException e) { // catch block
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        return input;
    }

    public static String getStringInput(Scanner scanner, String message) { // getStringInput method
        System.out.print(message);
        return scanner.nextLine(); // read the user's input
    }
}

class Member { // Member class
    private int id; // instance variables
    private String name; 
    private List<Book> borrowedBooks; 

    public Member(int id, String name) { // constructor
        this.id = id; // this keyword
        this.name = name; 
        this.borrowedBooks = new ArrayList<>(); // create a new list to store the borrowed books
    }

    public int getId() { // getId method
        return id;
    }

    public String getName() { // getName method
        return name;
    }

    public List<Book> getBorrowedBooks() { // getBorrowedBooks method
        return borrowedBooks; 
    }

    public void issueBook(Book book) { // issueBook method
        borrowedBooks.add(book); // add the book to the list of borrowed books
    }

    public boolean returnBook(Book book) { // returnBook method
        return borrowedBooks.remove(book); // remove the book from the list of borrowed books
    }

    @Override // override annotation
    public String toString() { // toString method
        return "Member ID: " + id + ", Name: " + name; // return statement
    }
}

class Book { // Book class
    private int id; // instance variables
    private String title; 
    private String author;
    private boolean available; 

    public Book(int id, String title, String author) { // constructor
        this.id = id; // this keyword
        this.title = title; 
        this.author = author; 
        this.available = true; // set the book to available by default
    }

    public int getId() { // getId method
        return id;
    }

    public String getTitle() { // getTitle method
        return title;
    }

    public String getAuthor() { // getAuthor method
        return author;
    }

    public boolean isAvailable() { // isAvailable method
        return available;
    }

    public void setAvailable(boolean available) { // setAvailable method
        this.available = available; // this keyword
    }

    @Override // override annotation
    public String toString() { // toString method
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Available: " + available; // return statement
    }
}
