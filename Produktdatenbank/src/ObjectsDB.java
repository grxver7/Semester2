import java.util.*;

public class ObjectsDB {
    public static HashMap<Integer, Person> personMap = new HashMap<>();
    public static HashMap<Integer, Company> companyMap = new HashMap<>();
    public static HashMap<Integer, Product> productMap = new HashMap<>();

    //adding the data of the object and safe it in a HashMap
    public static void createPerson(String personReaderID, String personReaderName, String personReaderGender) {
        Integer id=Integer.parseInt(personReaderID);
        if (personMap.containsValue(id)) {
            return;
        }
        Person person = new Person();
        person.name = personReaderName;
        person.id = id;
        person.Gender = Gender.valueOf(personReaderGender);
        personMap.put(id, person);
    }

    public static void createCompany(String companyReaderID, String companyReaderName) {
        int id=Integer.parseInt(companyReaderID);
        if (companyMap.containsValue(id)) {
            return;
        }
        Company company = new Company();
        company.name = companyReaderName;
        Company.id = id;
        companyMap.put(id, company);
    }

    public static void createProduct(String productReaderID, String productReaderName) {
        int id=Integer.parseInt(productReaderID);
        if (productMap.containsValue(id)) {
            return;
        }
        Product product = new Product();
        product.name = productReaderName;
        product.id = id;
        productMap.put(id, product);
    }
}
