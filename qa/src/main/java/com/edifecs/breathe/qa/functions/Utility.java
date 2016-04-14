package com.edifecs.breathe.qa.functions;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            InputStream inputStream =
                    new FileInputStream(System.getProperty("user.dir") + "\\" + PFileName);
            prop.load(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getValueOf(String key) {
        return prop.getProperty(key);
    }


    public static void executeCommandPrompt(String aCommand) {
        File currDir = new File(System.getProperty("user.dir"));
        String line;
        try {
            ProcessBuilder probuilder = new ProcessBuilder("CMD", "/C", aCommand);
            probuilder.directory(currDir);
            Process process = probuilder.start();

            BufferedReader inputStream =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorStream =
                    new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // reading output of the command
            int inputLine = 0;
            while ((line = inputStream.readLine()) != null) {
                if (inputLine == 0) {
                    System.out.printf("Output of the running command is: \n");
                }
                System.out.println(line);
                inputLine++;
            }

            // reading errors from the command
            int errLine = 0;
            while ((line = errorStream.readLine()) != null) {
                if (errLine == 0) {
                    System.out.println("Error of the command is: \n");
                }
                System.out.println(line);
                errLine++;
            }

        } catch (IOException e) {
            System.err.println("Exception occured: \n");
            System.err.println(e.getMessage());
        }
    }
    public static String getFormattedDate(String dateformat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        return sdf.format(new Date());
    }

}
