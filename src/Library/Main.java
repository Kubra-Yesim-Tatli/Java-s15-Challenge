package Library;
import Library.Model.Book;

import Library.Model.Invoice;

import Library.Model.User;
import Library.Model.PremiumUser;
import Library.Model.RegularUser;
import Library.service.Library;
import Library.util.Category;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        // örnek kullanıcı ekledim.
        RegularUser regularUser  = new RegularUser ("Havva");
        PremiumUser premiumUser  = new PremiumUser ("Kadir");
        library.addUser (regularUser );
        library.addUser (premiumUser );
        // örnek kitaplar ekledim.
        library.addBook(Book.createBook("Notre Dame'in Kamburu", "Victor Hugo", Category.FICTION));
        library.addBook(Book.createBook("Kuyucaklı Yusuf", "Sabahattin Ali", Category.SCIENCE));
        library.addBook(Book.createBook("Nutuk", "Mustafa Kemal Atatürk", Category.HISTORY));
        library.addBook(Book.createBook("Kitap 4", "Yazar 4", Category.BIOGRAPHY));
        library.addBook(Book.createBook("Kitap 5", "Yazar 4", Category.HORROR));
        while (true) {
            System.out.println("\n * Kütüphane Yönetim Sistemi *");
            System.out.println("1. Kitap Ekle");
            System.out.println("2. Ödünç Kitap Al");
            System.out.println("3. Kitabı İade Et");
            System.out.println("4. Faturaları Görüntüle");
            System.out.println("5. Kitabı Id, Başlık, Yazara veya Kategoriye göre Arama");
            System.out.println("6. Kitabı Güncelle");
            System.out.println("7. Kitabı Sil");
            System.out.println("8. Yazarın Tüm Kitaplarını Listele");
            System.out.println("9. Çıkış");
            System.out.print("Bir seçenek seçin: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Kitap Adını Girin: ");
                    String title = scanner.nextLine();
                    System.out.print("Yazarı Girin: ");
                    String author = scanner.nextLine();
                    System.out.print("Kategori Girin: ");
                    Category category = Category.valueOf(scanner.nextLine().toUpperCase());
                    library.addBook(Book.createBook(title, author, category));
                    break;
                case 2:
                    System.out.print("Kullanıcı Id'sini Girin: ");
                    int userId = scanner.nextInt();
                    User user = library.findUserById(userId);
                    if (user != null) {
                        System.out.print("Ödünç Alınacak Kitabın Id'sini Girin: ");
                        int bookId = scanner.nextInt();
                        library.borrowBook(user, bookId);
                    } else {
                        System.out.println("Kullanıcı Bulunamadı!");
                    }
                    break;
                case 3:
                    System.out.print("Kullanıcı Id'sini Girin: ");
                    userId = scanner.nextInt();
                    user = library.findUserById(userId);
                    if (user != null) {
                        System.out.print("İade Edilecek Kitabın Id'sini Girin: ");
                        int bookId = scanner.nextInt();
                        library.returnBook(user, bookId);
                    } else {
                        System.out.println("Kullanıcı Bulunamadı!");
                    }
                    break;
                case 4:
                    List<Invoice> invoices = library.getInvoices();
                    for (Invoice invoice : invoices) {
                        invoice.printInvoice();
                    }
                    break;
                case 5:
                    System.out.print("(1) Id, (2) Başlık, (3) Yazara göre arama yapın, (4) Kategoriye göre arama: ");
                    int searchOption = scanner.nextInt();
                    scanner.nextLine();
                    if (searchOption == 1) {
                        System.out.print("Kitap Id'sini Girin: ");
                        int bookId = scanner.nextInt();
                        Book book = library.findBookById(bookId);
                        System.out.println(book != null ? book : "Kitap Bulunamadı.");
                    } else if (searchOption == 2) {
                        System.out.print("Kitap Başlığını Girin: ");
                        String searchTitle = scanner.nextLine();
                        List<Book> booksByTitle = library.findBooksByTitle(searchTitle);
                        booksByTitle.forEach(System.out::println);
                    } else if (searchOption == 3) {
                        System.out.print("Yazar Adını Girin: ");
                        String searchAuthor = scanner.nextLine();
                        List<Book> booksByAuthor = library.findBooksByAuthor(searchAuthor);
                        booksByAuthor.forEach(System.out::println);
                    } else if (searchOption == 4) {
                        System.out.print("Kategori Girin: ");
                        Category searchCategory = Category.valueOf(scanner.nextLine().toUpperCase());
                        List<Book> booksByCategory = library.findBooksByCategory(searchCategory);
                        booksByCategory.forEach(System.out::println);
                    } else {
                        System.out.println("Geçersiz Seçenek.");
                    }
                    break;
                case 6:
                    System.out.print("Güncellenecek Kitabın Id'sini Girin: ");
                    int updateBookId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Yeni Kitap Adını Girin: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Yeni Yazarı Girin: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Yeni Kategoriyi Girin: ");
                    Category newCategory = Category.valueOf(scanner.nextLine().toUpperCase());
                    library.updateBook(updateBookId, newTitle, newAuthor, newCategory);
                    break;
                case 7:
                    System.out.print("Silinecek Kitabın Id'sini Girin: ");
                    int removeBookId = scanner.nextInt();
                    library.removeBook(removeBookId);
                    break;
                case 8:
                    System.out.print("Yazar Adını Girin: ");
                    String authorName = scanner.nextLine();
                    List<Book> booksByAuthor = library.findBooksByAuthor(authorName);
                    booksByAuthor.forEach(System.out::println);
                    break;
                case 9:
                    System.out.println("Çıkış yapılıyor...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Geçersiz Seçenek. Lütfen Tekrar Deneyin.");
            }
        }
    }
}