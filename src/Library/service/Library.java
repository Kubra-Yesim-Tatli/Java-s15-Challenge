package Library.service;
import Library.Model.Book;
import Library.Model.Invoice;
import Library.Model.User;
import Library.util.Category;
import Library.util.InvoiceStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Library {
    private Map<Integer, Book> books;
    private List<User> users;
    private List<Invoice> invoices;
    private static final double BORROW_FEE = 10.0;
    private static final int MAX_BORROW_LIMIT = 5;
    public Library() {
        this.books = new HashMap<>();
        this.users = new ArrayList<>();
        this.invoices = new ArrayList<>();
    }
    public void addBook(Book book) {
        books.put(book.getId(), book);
        System.out.println("Kitap Eklendi: " + book);
    }
    public void addUser(User user) {
        users.add(user);
        System.out.println("Kullanıcı Eklendi: " + user);
    }
    public User findUserById(int userId) {
        return users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);
    }
    public Book findBookById(int bookId) {
        return books.get(bookId);
    }
    public List<Book> findBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }
    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }
    public boolean borrowBook(User user, int bookId) {
        if (user.getBorrowedBooks().size() >= MAX_BORROW_LIMIT) {
            System.out.println("Kullanıcı zaten maksimum kitap ödünç aldı (" + MAX_BORROW_LIMIT + "). Daha fazla kitap alınamaz.");
            return false;
        }
        Book book = books.get(bookId);
        if (book != null && book.isAvailable()) {
            if (user.borrowBook(book)) {
                Invoice invoice = new Invoice(user, book, BORROW_FEE);
                invoices.add(invoice);
                invoice.printInvoice();
                return true;
            }
        }
        return false;
    }
    public boolean removeBook(int bookId) {
        Book book = books.get(bookId);
        if (book == null) {
            System.out.println("Kitap Bulunamadı.");
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println("Kitap şu anda ödünçte olduğu için silinemez: " + book);
            return false;
        }
        books.remove(bookId);
        System.out.println("Kitap Silindi: " + book);
        return true;
    }
    public boolean returnBook(User user, int bookId) {
        Book book = books.get(bookId);
        if (book != null && user.returnBook(book)) {
            for (Invoice invoice : invoices) {
                if (invoice.getUser().equals(user) && invoice.getBook().equals(book) && invoice.getStatus() == InvoiceStatus.PAID) {
                    invoice.refund();
                    break;
                }
            }
            return true;
        }
        return false;
    }
    public List<Book> findBooksByCategory(Category category) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getCategory() == category) {
                result.add(book);
            }
        }
        return result;
    }
    public boolean updateBook(int bookId, String newTitle, String newAuthor, Category newCategory) {
        Book book = books.get(bookId);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setCategory(newCategory);
            System.out.println("Kitap Güncellendi: " + book);
            return true;
        }
        return false;
    }
    public List<Invoice> getInvoices() {
        return invoices;
    }
}
