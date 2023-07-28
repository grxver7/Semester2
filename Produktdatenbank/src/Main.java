import java.util.*;

enum Gender {
    Male,
    Female
}

public class Main {
    public static void main(String[] args) {
        String file = "database.csv";
        DataReader.dataReader(file);
        if (args.length!=1) {
            System.out.println("Error reading argument!");
            return;
        }
        String input=args[0];
        String[] inputSplit=input.split("=");

        if(input.startsWith("personensuche=")) {
            Interactions.searchPerson(inputSplit[1].replace("\"", ""));
        }
        else if(input.startsWith("produktsuche=")) {
            Interactions.searchProduct(inputSplit[1].replace("\"", ""));
        }
        else if(input.startsWith("produktnetzwerk=")) {
            HashMap<Integer, String> resultProductNetwork = Interactions.productNetwork(Integer.parseInt(inputSplit[1]));
            List<String> sortedProductNames = new ArrayList<>(resultProductNetwork.values());
            Collections.sort(sortedProductNames);
            System.out.println("\nNetwork of Products: ");
            for (String productNames : sortedProductNames) {
                System.out.print(productNames + ", ");
            }
        }
        else if(input.startsWith("firmennetzwerk=")) {
            List<String> resultCompanyNetwork= Interactions.firmNetwork(Integer.parseInt(inputSplit[1]));
            System.out.println("Network of company: ");
            for (String companyName : resultCompanyNetwork) {
                System.out.print(companyName + ", ");
            }
        }
        else {
            System.out.println("Error reading argument!");
        }
    }
}