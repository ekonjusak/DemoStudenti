package demo.app.mgmt;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesReader {
    public String name_max_char;

    public static void main(String[] args) {
        PropertiesReader app = new PropertiesReader();
        app.printAll("config.properties");
    }

    private void printAll(String filename) {

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }
            prop.load(input);
            name_max_char = prop.getProperty("name_max_char") ;
            // prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
