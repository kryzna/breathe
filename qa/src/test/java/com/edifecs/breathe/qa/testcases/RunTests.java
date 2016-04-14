package com.edifecs.breathe.qa.testcases;

import com.edifecs.breathe.qa.functions.ExcelReader;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amolverm on 4/12/2016.
 */
public class RunTests {
    public static void main(String[] args) {
        ExcelReader excelReader = new ExcelReader();

/*// Create object of TestNG Class
        TestNG runner=new TestNG();

// Create a list of String
        List<String> suitefiles=new ArrayList<String>();

// Add xml file which you have to execute
        suitefiles.add(System.getProperty("user.dir")+"\\testng.xml");

// now set xml file for execution
        runner.setTestSuites(suitefiles);

// finally execute the runner using run method

        runner.run();*/

        XmlSuite suite = new XmlSuite();
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suite.setName("TmpSuite");
        List<XmlTest> listXmlTests = new ArrayList<XmlTest>();

        //XmlTest test = new XmlTest();
        XmlTest test = new XmlTest(suite,0);
        test.setName("TmpTest");
        List<XmlClass> classes = new ArrayList<XmlClass>();
        classes.add(new XmlClass("com.edifecs.breathe.qa.testcases.TestHealthRiskAssessment"));
        test.setXmlClasses(classes) ;

        int questionsCount = excelReader.getRowCount("RiskAssessment");
        for(int i=1;i<questionsCount;i++) {
            XmlTest newTest = new XmlTest(suite,i);
            newTest.setName("Test"+i);
            List<XmlClass> newClasses = new ArrayList<XmlClass>();
            newClasses.add(new XmlClass("com.edifecs.breathe.qa.testcases.TestHealthAssessmentQuestions"));
            newTest.setXmlClasses(newClasses) ;
        }
         XmlTest test1 = new XmlTest(suite,4);
         test1.setName("TmpTest28");
         List<XmlClass> classes1 = new ArrayList<XmlClass>();
         classes1.add(new XmlClass("com.edifecs.breathe.qa.testcases.TestHealthRiskAssessmentSubmit"));
         test1.setXmlClasses(classes1) ;


        suites.add(suite);
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        try {
            tng.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
