package Library.Model;

import Library.util.InvoiceStatus;



public class Invoice {
    private static int invoiceCounter = 1;
    private int invoiceId;
    private User user;
    private Book book;
    private double amount;
    private InvoiceStatus status;
    public Invoice(User user, Book book, double amount) {
        this.user = user;
        this.book = book;
        this.amount = amount;
        this.invoiceId = invoiceCounter++;
        this.status = InvoiceStatus.PAID;
    }
    public int getInvoiceId() {
        return invoiceId;
    }
    public User getUser() {
        return user;
    }
    public Book getBook() {
        return book;
    }
    public double getAmount() {
        return amount;
    }
    public InvoiceStatus getStatus() {
        return status;
    }
    public void refund() {
        if (status == InvoiceStatus.PAID) {
            status = InvoiceStatus.REFUNDED;
            System.out.println("Refund of $" + amount + " processed for " + user.getName());
        } else {
            System.out.println("Invoice " + invoiceId + " has already been refunded.");
        }
    }
    public void printInvoice() {
        System.out.println("--- Fatura ---");
        System.out.println("Kullanıcı: " + user.getName());
        System.out.println("Ödünç Alınan Kitaplar:");
        for (Book book : user.getBorrowedBooks()) {  // borrowedBooks yerine user.getBorrowedBooks() kullanılmalı
            System.out.println(book);
        }
        System.out.println("--- ------- ---");
    }

}

