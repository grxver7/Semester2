import java.util.*;
public class Product extends Company {
    int ID;
    String Name;
    List<Integer> isBoughtBy = new LinkedList<>();
    int isOwnedBy;
}
