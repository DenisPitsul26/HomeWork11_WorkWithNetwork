package ex3;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            WriteURLInFile writeUrl = new WriteURLInFile("https://prog.kiev.ua/forum/index.php",
                    new File("E:\\JavaOOP\\HomeWork#11_WorkWithNetwork\\src\\ex3\\file.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
