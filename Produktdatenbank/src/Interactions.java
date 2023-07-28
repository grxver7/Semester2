import java.util.*;

public class Interactions {
    public static String searchPerson(String input) {
        input = input.toLowerCase();
        int count = 0;
        List<Integer> safeID = new LinkedList<>();
        String answer="";
        boolean checkID = false;
        for (Map.Entry<Integer, Person> entry : ObjectsDB.personMap.entrySet()) {
            Person personToCompare = entry.getValue();
            String comparePerson = personToCompare.Name;
            comparePerson = comparePerson.toLowerCase();
            String[] cutInput;
            String[] cutCompare;
            cutInput = input.split(" ");
            cutCompare = comparePerson.split(" ");
            for (String element1 : cutCompare) {
                for (String element2 : cutInput) {
                    if (safeID.contains(personToCompare.ID)) {
                        checkID = true;
                    }
                    if (element1.startsWith(element2) && !checkID) {
                        answer="Person " + count + ":";
                        answer+="\nPersonID: " + personToCompare.ID;
                        answer+="\nPersonName: " + personToCompare.Name;
                        answer+="\nGender: " + personToCompare.Gender;
                        answer+="\nFriend with (ID): " + personToCompare.isFriend;
                        answer+="\nBought Product (ID): " + personToCompare.boughtProduct;
                        System.out.println(answer+"\n");
                        safeID.add(personToCompare.ID);
                        count++;
                    }
                    checkID=false;
                }
            }
        }
        if (count == 0) {
            answer="Person don't exists in this storage";
            System.out.println(answer);
        }
        return answer;
    }

    public static String searchProduct(String input) {
        int counter = 0;
        String answer="";
        input = input.toLowerCase();
        for (Map.Entry<Integer, Product> entry : ObjectsDB.productMap.entrySet()) {
            Product productToCompare = entry.getValue();
            String compare = productToCompare.Name;
            compare = compare.toLowerCase();
            if (compare.startsWith(input)) {
                //Checkout: System.out.println("The key for value " + input + " is " + entry.getKey());
                Product product = ObjectsDB.productMap.get(entry.getKey());
                answer="Product " + counter + ": ";
                answer+= "\nProductID: " + product.ID;
                answer+="\nProductName: " + product.Name;
                answer+="\nOwner (ID): " + product.isOwnedBy;
                answer+="\nBought by (ID): " + product.isBoughtBy;
                System.out.println(answer+"\n");
                counter++;
            }
        }
        if (counter == 0) {
            answer="Product don't exists in this storage";
            System.out.println(answer);
        }
        return answer;
    }

    public static HashMap<Integer, String> productNetwork(int id) {
        HashMap<Integer, String> mapOfProducts = new HashMap<>();
        List<Integer> checkProductReplications = new LinkedList<>();
        List<Integer> friendList;
        boolean checkIDExistence = false;

        Person friend;
        Person person = ObjectsDB.personMap.get(id);
        List<Integer> checkProductPersonAlreadyHas = person.boughtProduct;
        friendList = person.isFriend;
        Product product;

        for (int friendID : friendList) {
            friend = ObjectsDB.personMap.get(friendID);
            for (int productID : friend.boughtProduct) {
                if (!checkProductReplications.contains(productID)&&!checkProductPersonAlreadyHas.contains(productID)) {
                    product = ObjectsDB.productMap.get(productID);
                    mapOfProducts.put(productID, product.Name);
                    checkProductReplications.add(productID);
                    checkIDExistence = true;
                }
            }
        }
        if (!checkIDExistence) {
            System.out.println("ID not found");
        }
        return mapOfProducts;
    }

    public static List<String> firmNetwork(int id) {
        Person person = ObjectsDB.personMap.get(id);
        HashMap<Integer, String> productOfNetwork = productNetwork(id);
        Product product;
        Company company;
        List<String> listOfCompany = new LinkedList<>();
        LinkedList<Integer> checkDoubledID = new LinkedList<>();
        for (int idOfProductChosenPerson : person.boughtProduct) {
            product = ObjectsDB.productMap.get(idOfProductChosenPerson);
            checkDoubledID.add(product.isOwnedBy);
        }
        boolean checkIDExistence = false;
        for (int idOfProduct : productOfNetwork.keySet()) {
            product = ObjectsDB.productMap.get(idOfProduct);
            int companyID = product.isOwnedBy;
            company = ObjectsDB.companyMap.get(companyID);
            if (!checkDoubledID.contains(companyID)) {
                listOfCompany.add(company.Name);
                checkDoubledID.add(companyID);
                checkIDExistence = true;
            }
        }
        if (!checkIDExistence) {
            System.out.println("No firms found!");
        }
        Collections.sort(listOfCompany);
        return listOfCompany;
    }
}
