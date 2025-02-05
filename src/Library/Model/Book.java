package Library.Model;



import Library.util.Category;

import java.util.Objects;

public class Book {
    private static int idCounter = 1;
    private final int id;
    private String title;
    private String author;
    private Category category;
    private boolean isAvailable;

    public Book(String title, String author, Category category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.id = idCounter++;
        this.isAvailable = true;
    }

    // Getter metotları
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Setter metotları
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) && category == book.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, category);
    }

    @Override
    public String toString() {
        return "Kitap {" +
                "id=" + id +
                ", başlık='" + title + '\'' +
                ", yazar='" + author + '\'' +
                ", kategori=" + category +
                ", müsait mi=" + isAvailable +
                '}';
    }

    public static Book createBook(String title, String author, Category category) {
        return new Book(title, author, category);
    }
}