package demo.app.mgmt;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesReader {

    public HashMap<String, String> getConfigProperties() {

        HashMap<String, String> ConfigProperties = new HashMap<>();
        String filename = "config.properties";
        try (InputStream input = PropertiesReader.class.getClassLoader().getResourceAsStream(filename)) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return ConfigProperties;
            }
            prop.load(input);

            ConfigProperties.put("name_max_char",prop.getProperty("name_max_char"));
            ConfigProperties.put("name_min_char",prop.getProperty("name_min_char"));
            return ConfigProperties ;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ConfigProperties;
    }


}
