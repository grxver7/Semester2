import java.util.*;
public class Product extends Company {
    int id;
    String name;
    List<Integer> isBoughtBy = new LinkedList<>();
    int isOwnedBy;
}
