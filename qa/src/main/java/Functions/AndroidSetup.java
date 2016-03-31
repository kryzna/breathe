package Functions;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by amolverm on 3/29/2016.
 */
public class AndroidSetup {
    public static AndroidDriver aDriver;
    Properties prop;
    public void andySetup() throws IOException {

        prop = new Properties();
        InputStream readProperties = null;
        try {
            readProperties = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prop.load(readProperties);
        File breathApkDir = new File(prop.getProperty("apkFolder"));
        File breathApk = new File(breathApkDir, prop.getProperty("apkName"));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("BROWSER_NAME","Android");
        capabilities.setCapability("deviceName","My Emulator");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("app",breathApk.getAbsolutePath());
        aDriver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
}