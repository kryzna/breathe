package Functions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by rohikris on 3/31/2016.
 */
public class Utility {

    private static Properties prop = new Properties();
    public static void loadPropertyFile(String PropertyFileName) {
        try {
            String PFileName = PropertyFileName;
            InputStream inputStream = new FileInputStream(System.getProperty("user.dir")+ "\\" +  PFileName);
            prop.load(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getValueOf(String key) {
        return prop.getProperty(key);
    }

}
