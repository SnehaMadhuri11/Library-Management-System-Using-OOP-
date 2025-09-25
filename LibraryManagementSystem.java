import java.util.ArrayList;
import java.util.Scanner;

// Book class
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void issueBook() { this.isIssued = true; }
    public void returnBook() { this.isIssued = false; }

    @Override
    public String toString() {
        return id + ". " + title + " by " + author + (isIssued ? " (Issued)" : " (Available)");
    }
}

// User class
class User {
    private int userId;
    private String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name;
    }
}

// Library class
class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void showBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void issueBook(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId && !book.isIssued()) {
                book.issueBook();
                System.out.println("✅ Book issued successfully!");
                return;
            }
        }
        System.out.println("❌ Book not available for issue.");
    }

    public void returnBook(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId && book.isIssued()) {
                book.returnBook();
                System.out.println("✅ Book returned successfully!");
                return;
            }
        }
        System.out.println("❌ Invalid return request.");
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Adding some sample books
        library.addBook(new Book(1, "Java Programming", "James Gosling"));
        library.addBook(new Book(2, "Data Structures", "Robert Lafore"));
        library.addBook(new Book(3, "Clean Code", "Robert C. Martin"));

        int choice;
        do {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Show Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> library.showBooks();
                case 2 -> {
                    System.out.print("Enter Book ID to issue: ");
                    int id = sc.nextInt();
                    library.issueBook(id);
                }
                case 3 -> {
                    System.out.print("Enter Book ID to return: ");
                    int id = sc.nextInt();
                    library.returnBook(id);
                }
                case 4 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("❌ Invalid choice.");
            }
        } while (choice != 4);

        sc.close();
    }
}

