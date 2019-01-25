package ex1;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AddressAvailability availability = new AddressAvailability();
        try {
            availability.printResultFromFile(new File("E:\\JavaOOP\\HomeWork#11_WorkWithNetwork\\src\\ex1\\links.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
