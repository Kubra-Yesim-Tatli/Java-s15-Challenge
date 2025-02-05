package Library.Model;
import java.util.HashSet;
import java.util.Set;
public abstract class User {
    private static int idCounter = 1;
    private int id;
    private String name;
    protected Set<Book> borrowedBooks;
    public User(String name) {
        this.name = name;
        this.id = idCounter++;
        this.borrowedBooks = new HashSet<>();
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    public abstract int getBookLimit();
    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() >= getBookLimit()) {
            System.out.println(name + " cannot borrow more than " + getBookLimit() + " books.");
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println("Book is not available.");
            return false;
        }
        borrowedBooks.add(book);
        book.setAvailable(false);
        System.out.println(name + " borrowed " + book.getTitle());
        return true;
    }
    public boolean returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
            System.out.println(name + " returned " + book.getTitle());
            return true;
        }
        System.out.println("Book was not borrowed by " + name);
        return false;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}