package Library.Model;

public class PremiumUser extends User {
    private static final int BOOK_LIMIT = 10;
    public PremiumUser(String name) {
        super(name);
    }
    @Override
    public int getBookLimit() {
        return BOOK_LIMIT;
    }
}