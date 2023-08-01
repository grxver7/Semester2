import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Tests {

    //Objects for the Tests
    private void creatTestObjectName() {
        Person person = new Person();

        person.name = "Max Counterman";
        person.id = 1;
        person.Gender = Gender.Male;
        person.isFriend.add(2);
        person.boughtProduct.add(22);

        ObjectsDB.personMap.put(1, person);
    }

    private void createFriend() {
        Person friend = new Person();

        friend.name = "Albert Einstein";
        friend.id = 2;
        friend.Gender = Gender.Male;
        friend.isFriend.add(1);
        friend.boughtProduct.add(22);
        friend.boughtProduct.add(23);

        ObjectsDB.personMap.put(2, friend);
    }

    private void creatTestObjectProduct() {
        Product product = new Product();

        product.name = "Lenovo Legion";
        product.id = 22;
        product.isBoughtBy.add(1);
        product.isBoughtBy.add(2);
        product.isBoughtBy.add(4);
        product.isOwnedBy = 10;
        ObjectsDB.productMap.put(22, product);
    }

    private void creatFriendProduct() {
        Product product = new Product();

        product.name = "Razer Mouse";
        product.id = 23;
        product.isBoughtBy.add(2);
        product.isBoughtBy.add(4);
        product.isOwnedBy = 11;
        ObjectsDB.productMap.put(23, product);
    }

    private void createSecondTestCompany() {
        Company company = new Company();
        company.id = 10;
        company.name = "Lenovo";
        company.ownsProduct.add(22);

        ObjectsDB.companyMap.put(10, company);
    }

    private void createTestCompany() {
        Company company = new Company();
        company.id = 11;
        company.name = "Razer";
        company.ownsProduct.add(23);

        ObjectsDB.companyMap.put(11, company);
    }

    //Tests
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
    public void testNotExistingName() { //Tests the output for not existing names
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
    public void testNotExistingProduct() { //Tests the output for not existing products
        creatTestObjectProduct();
        String expected = "Product don't exists in this storage";
        String request = Interactions.searchProduct("Huawei");

        Assertions.assertEquals(expected, request);
    }

    @Test
    public void testProductNetwork() { //test the Output of the productNetwork
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
    public void testFirmNetwork() { //test the Output of the firmNetwork
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
    public void testImport() { //test if import of data is red and saved correctly
        ReadData.dataReader("test.csv");

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
