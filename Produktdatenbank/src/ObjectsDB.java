import java.util.*;

public class ObjectsDB {
    public static HashMap<Integer, Person> personMap = new HashMap<>();
    public static HashMap<Integer, Company> companyMap = new HashMap<>();
    public static HashMap<Integer, Product> productMap = new HashMap<>();

    public static void initialisePerson(String personReaderID, String personReaderName, String personReaderGender) {
        //name is still crypted, issue here, data input is correct, so problem is in creating the object
        Integer id=Integer.parseInt(personReaderID);
        if (personMap.containsValue(id)) {
            return;
        }
        Person person = new Person();
        person.Name = personReaderName;
        person.ID = id;
        person.Gender = Gender.valueOf(personReaderGender);
        personMap.put(id, person);
    }

    public static void initialiseCompany(String companyReaderID, String companyReaderName) {
        int id=Integer.parseInt(companyReaderID);
        if (companyMap.containsValue(id)) {
            return;
        }
        Company company = new Company();
        company.Name = companyReaderName;
        Company.ID = id;
        companyMap.put(id, company);
    }

    public static void initialiseProduct(String productReaderID, String productReaderName) {
        int id=Integer.parseInt(productReaderID);
        if (productMap.containsValue(id)) {
            return;
        }
        Product product = new Product();
        product.Name = productReaderName;
        product.ID = id;
        productMap.put(id, product);
    }
}
