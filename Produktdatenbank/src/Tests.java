import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Tests {

    public void creatTestObjectName() {
        Person person = new Person();

        person.Name = "Max Counterman";
        person.ID = 1;
        person.Gender = Gender.Male;
        person.isFriend.add(2);
        person.boughtProduct.add(22);

        ObjectsDB.personMap.put(1, person);
    }

    public void createFriend() {
        Person friend = new Person();

        friend.Name = "Albert Einstein";
        friend.ID = 2;
        friend.Gender = Gender.Male;
        friend.isFriend.add(1);
        friend.boughtProduct.add(22);
        friend.boughtProduct.add(23);

        ObjectsDB.personMap.put(2, friend);
    }

    public void creatTestObjectProduct() {
        Product product = new Product();

        product.Name = "Lenovo Legion";
        product.ID = 22;
        product.isBoughtBy.add(1);
        product.isBoughtBy.add(2);
        product.isBoughtBy.add(4);
        product.isOwnedBy = 10;
        ObjectsDB.productMap.put(22, product);
    }

    public void creatFriendProduct() {
        Product product = new Product();

        product.Name = "Razer Mouse";
        product.ID = 23;
        product.isBoughtBy.add(2);
        product.isBoughtBy.add(4);
        product.isOwnedBy = 11;
        ObjectsDB.productMap.put(23, product);
    }

    public void createSecondTestCompany() {
        Company company = new Company();
        company.ID = 10;
        company.Name = "Lenovo";
        company.ownsProduct.add(22);

        ObjectsDB.companyMap.put(10, company);
    }

    public void createTestCompany() {
        Company company = new Company();
        company.ID = 11;
        company.Name = "Razer";
        company.ownsProduct.add(23);

        ObjectsDB.companyMap.put(11, company);
    }

    @Test
    public void testExistingName() {
        creatTestObjectName();
        String expected = "Person 0:\n" +
                "PersonID: 1\n" +
                "PersonName: Max Counterman\n" +
                "Gender: Male\n" +
                "Friend with (ID): [2]\n" +
                "Bought Product (ID): [22]";
        String request = Interactions.searchPerson("Max Counterman");

        Assertions.assertEquals(expected, request);
    }

    @Test
    public void testNotExistingName() {
        creatTestObjectName();
        String expected = "Person don't exists in this storage";
        String request = Interactions.searchPerson("Emil");

        Assertions.assertEquals(expected, request);
    }

    @Test
    public void testExistingProduct() {
        creatTestObjectProduct();
        String expected = "Product 0: \n" +
                "ProductID: 22\n" +
                "ProductName: Lenovo Legion\n" +
                "Owner (ID): 10\n" +
                "Bought by (ID): [1, 2, 4]";
        String request = Interactions.searchProduct("Lenovo");

        Assertions.assertEquals(expected, request);
    }

    @Test
    public void testNotExistingProduct() {
        creatTestObjectProduct();
        String expected = "Product don't exists in this storage";
        String request = Interactions.searchProduct("Huawei");

        Assertions.assertEquals(expected, request);
    }

    @Test
    public void testProductNetwork() {
        creatTestObjectProduct();
        creatTestObjectName();
        createFriend();
        creatFriendProduct();
        createTestCompany();

        HashMap<Integer, String> resultProductNetwork = Interactions.productNetwork(1);
        List<String> testResults = new ArrayList<>(resultProductNetwork.values());
        Collections.sort(testResults);

        List<String> testSolution = new ArrayList<>();
        testSolution.add("Razer Mouse");

        System.out.println("\nNetwork of Products: ");
        for (String productNames : testSolution) {
            System.out.print(productNames + ", ");
        }

        Assertions.assertEquals(testSolution, testResults);
    }

    @Test
    public void testFirmNetwork() {
        creatTestObjectProduct();
        creatTestObjectName();
        createFriend();
        creatFriendProduct();
        createTestCompany();

        List<String> testResults = Interactions.firmNetwork(1);

        System.out.println("Network of company: ");
        for (String companyName : testResults) {
            System.out.print(companyName + ", ");
        }
        List<String> testSolution = new LinkedList<>();
        testSolution.add("Razer");

        Assertions.assertEquals(testSolution, testResults);
    }

    @Test
    public void testImport() {
        DataReader.dataReader("test.csv");

        HashMap<Integer, Person> personTestMap = ObjectsDB.personMap;
        HashMap<Integer, Product> productTestMap = ObjectsDB.productMap;
        HashMap<Integer, Company> companyTestMap = ObjectsDB.companyMap;

        creatTestObjectProduct();
        creatTestObjectName();
        createFriend();
        creatFriendProduct();
        createSecondTestCompany();

        Assertions.assertEquals(ObjectsDB.personMap, personTestMap);
        Assertions.assertEquals(ObjectsDB.productMap,productTestMap);
        Assertions.assertEquals(ObjectsDB.companyMap, companyTestMap);

    }


}
