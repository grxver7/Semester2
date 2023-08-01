public class CreateRelationships {
    public static void friendship(String personReaderID, String friendReaderID) { //create relationships between person object
        int idPerson=Integer.parseInt(personReaderID);
        int idFriend=Integer.parseInt(friendReaderID);
        Person person = ObjectsDB.personMap.get(idPerson);
        Person friend = ObjectsDB.personMap.get(idFriend);
        person.isFriend.add(idFriend);
        friend.isFriend.add(idPerson);

        ObjectsDB.personMap.replace(idPerson, person);
        ObjectsDB.personMap.replace(idFriend, friend);
    }
    public static void productPortfolio(String productReaderID, String companyReaderID){ //creates relationship between product and company
        int idProduct=Integer.parseInt(productReaderID);
        int idCompany=Integer.parseInt(companyReaderID);
        Product product = ObjectsDB.productMap.get(idProduct);
        Company company = ObjectsDB.companyMap.get(idCompany);
        product.isOwnedBy = idCompany;
        company.ownsProduct.add(idProduct);

        ObjectsDB.productMap.replace(idProduct, product);
        ObjectsDB.companyMap.replace(idCompany, company);
    }
    public static void productStorage(String personReaderID, String productReaderID){ //creates relationship between person and product
        int idProduct=Integer.parseInt(productReaderID);
        int idPerson=Integer.parseInt(personReaderID);
        Product product = ObjectsDB.productMap.get(idProduct);
        Person person = ObjectsDB.personMap.get(idPerson);
        product.isBoughtBy.add(idPerson);
        person.boughtProduct.add(idProduct);

        ObjectsDB.productMap.replace(idProduct, product);
        ObjectsDB.personMap.replace(idPerson, person);
    }
}
