package com.edifecs.breathe.qa.functions;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by amolverm on 3/29/2016.
 */
public class Andriodsetup {
    public static AndroidDriver aDriver;
    Process p;
    Properties prop;

    public void andySetup() throws IOException, InterruptedException {
        Appiumserver.startAppiumServer(p);
        // System.out.println("coming in andriod setup");
        Utility.loadPropertyFile("config.properties");
        prop = new Properties();
        InputStream readProperties = null;
        try {
            readProperties =
                    new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prop.load(readProperties);

        File breathApkDir = new File(System.getProperty("user.dir") + "\\app\\");
        File breathApk = new File(breathApkDir, prop.getProperty("apkName"));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //  capabilities.setCapability("BROWSER_NAME","Android");
        //define the type of mobile device or emulator to use
        capabilities.setCapability("deviceName", "My Emulator");
        //This capability is used to reset the app’s state before the session starts;
        capabilities.setCapability("noreset", "false");
        //this will delete the entire simulator folder
        // capabilities.setCapability("fullReset","true");
        //used to set the language on the simulator/emulator
        capabilities.setCapability("locale", "en");
        //used to start in a certain orientation in simulator/emulator
        capabilities.setCapability("orientation", "LANDSCAPE");
        //used to set the mobile OS platform.
        capabilities.setCapability("platformName", "Android");
        //Appium will install the app binary on the appropriate device
        capabilities.setCapability("app", breathApk.getAbsolutePath());
        aDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
}
