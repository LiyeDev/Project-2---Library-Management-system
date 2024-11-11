import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Class representing a Book
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isIssued;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isIssued = false; // Book is initially not issued
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn +
               (isIssued ? " (Currently Issued)" : " (Available)");
    }
}

// Class representing a Member
class Member {
    private String name;
    private String memberId;

    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }
}

// Class representing the Library
class Library {
    private List<Book> books;
    private Map<String, String> issuedBooks; // Maps ISBN to memberId

    public Library() {
        books = new ArrayList<>();
        issuedBooks = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    public void issueBook(String isbn, Member member) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (!book.isIssued()) {
                    book.issue();
                    issuedBooks.put(isbn, member.getMemberId());
                    System.out.println("Book issued to " + member.getName());
                    return;
                } else {
                    System.out.println("Book is already issued.");
                    return;
                }
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(String isbn) {
        if (issuedBooks.containsKey(isbn)) {
            for (Book book : books) {
                if (book.getIsbn().equals(isbn)) {
                    book.returnBook();
                    issuedBooks.remove(isbn);
                    System.out.println("Book returned: " + book);
                    return;
                }
            }
        } else {
            System.out.println("This book wasn't issued.");
        }
    }

    public void listBooks() {
        System.out.println("Library Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

// Main class with the program's entry point
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Simulate librarian actions
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. List Books");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Book
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    Book newBook = new Book(title, author, isbn);
                    library.addBook(newBook);
                    break;

                case 2: // Issue Book
                    System.out.print("Enter book ISBN to issue: ");
                    String isbnToIssue = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Enter member name: ");
                    String memberName = scanner.nextLine();
                    Member member = new Member(memberName, memberId);
                    library.issueBook(isbnToIssue, member);
                    break;

                case 3: // Return Book
                    System.out.print("Enter book ISBN to return: ");
                    String isbnToReturn = scanner.nextLine();
                    library.returnBook(isbnToReturn);
                    break;

                case 4: // List Books
                    library.listBooks();
                    break;

                case 5: // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return; // Exit the program

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
