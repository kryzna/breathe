package com.edifecs.breathe.qa.functions;

import java.io.File;

/**
 * Created by rohikris on 4/8/2016.
 */
public class Appiumserver {

    public static String appiumInstallationDir = "E:/Program Files (x86)";
    public static String appiumNode =
            appiumInstallationDir + File.separator + "Appium" + File.separator + "node.exe";
    public static String appiumNodeModule = appiumInstallationDir + File.separator + "Appium"
            + File.separator + "node_modules" + File.separator + "appium" + File.separator + "bin"
            + File.separator + "Appium.js";
    public static String appiumServicePort = "57571";

    /**
     * Starts appium server
     */
    public static Process startAppiumServer(Process p) throws NullPointerException {
        try {
            Process p1;
            Runtime r = null;
            p1 = r.exec("node.exe node_modules\\appium\\lib\\server\\main.js --address 127.0.0.1\n"
                    + "             --port 4723 --platform-name Android --platform-version 23 --automation-name App\n"
                    + "            ium --log-no-color");
            System.out.println("Appium server started");
            //Utility.executeCommandPrompt("node.exe node_modules\\appium\\lib\\server\\main.js --address 127.0.0.1\n" +
            //    "             --port 4723 --platform-name Android --platform-version 23 --automation-name App\n" +
            //     "            ium --log-no-color");
            return p1;
        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    /**
     * Stops appium server
     */
    public static void stopAppiumServer() {
        Utility.executeCommandPrompt("cmd /c echo off & FOR /F \"usebackq tokens=5\" %a in"
                + " (`netstat -nao ^| findstr /R /C:\"" + appiumServicePort
                + "\"`) do (FOR /F \"usebackq\" %b in"
                + " (`TASKLIST /FI \"PID eq %a\" ^| findstr /I node.exe`) do taskkill /F /PID %a)");
        System.out.println("Appium server stopped");
    }



}
