package Library.Model;

public class RegularUser extends User{
    private static final int BOOK_LIMIT = 5;
    public RegularUser(String name) {
        super(name);
    }
    @Override
    public int getBookLimit() {
        return BOOK_LIMIT;
    }
}