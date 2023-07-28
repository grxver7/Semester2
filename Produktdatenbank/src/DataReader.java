import java.io.*;

public class DataReader {
    public static String[] cutterInput(String line) {
        String[] cuttedLine;
        int counter = 0;
        cuttedLine = line.split(","); //split based on ";"
        for (String element : cuttedLine) {
            cuttedLine[counter] = element.replace("\"", ""); //removes unneeded character
            counter++;
        }
        return cuttedLine;
    }

    public static int identifyInputs(String line) { //identify each character set, giving id as return, method onlyfor better structure
        int taskID = -1;
        if (line.contains("person")) {
            //create object
            if (line.contains("person_name")) {
                taskID = 0;
            } else if (line.contains("product_id")) {
                taskID = 1;
            } else {
                //the person is friend with
                taskID = 2;
            }
        }
        if (line.contains("company_name")) {
            taskID = 3;
        }

        if (line.contains("product_id")&&!line.contains("person")) {
            if (line.contains("product_name")) {
                taskID = 4;
            } else {
                //the company owns this product
                taskID = 5;
            }
        }
        if (taskID == -1) {
            System.out.println("Something went wrong, seperating the input");
        }
        return taskID;
    }

    public static void dataReader(String file) { //reads the data input and safes it as the correct object
        String line;
        String[] cuttedLine;
        int taskID = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                if (line.startsWith("New_Entity: ")) {
                    taskID = identifyInputs(line);

                } else {

                    switch (taskID) {
                        case 0 -> { //Create Person --> Problem: Value in HashMap ist crypted or wrong
                            cuttedLine = cutterInput(line);
                            //System.out.printf("\n" + cuttedLine[1]);
                            ObjectsDB.initialisePerson(cuttedLine[0], cuttedLine[1], cuttedLine[2]); //personID, personName, person Gender
                        }
                        case 1 -> {
                            cuttedLine = cutterInput(line);
                            CreateRelationships.productStorage(cuttedLine[0], cuttedLine[1]); //personID, productID
                        }
                        case 2 -> {
                            cuttedLine = cutterInput(line);
                            CreateRelationships.friendship(cuttedLine[0], cuttedLine[1]); //person1ID, person2ID
                        }
                        case 3 -> {
                            cuttedLine = cutterInput(line);
                            ObjectsDB.initialiseCompany(cuttedLine[0], cuttedLine[1]); //companyID, companyName
                        }
                        case 4 -> {
                            cuttedLine = cutterInput(line);
                            ObjectsDB.initialiseProduct(cuttedLine[0], cuttedLine[1]); //productID, productName
                        }
                        case 5 -> {
                            cuttedLine = cutterInput(line);
                            CreateRelationships.productPortfolio(cuttedLine[0], cuttedLine[1]); //productID, companyID
                        }
                    }

                }
            }
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
