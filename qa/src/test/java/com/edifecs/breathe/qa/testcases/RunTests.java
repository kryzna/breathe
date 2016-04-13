package com.edifecs.breathe.qa.testcases;

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

        XmlTest test = new XmlTest(suite,0);
        test.setName("TmpTest");
        List<XmlClass> classes = new ArrayList<XmlClass>();
        classes.add(new XmlClass("com.edifecs.breathe.qa.testcases.TestHealthRiskAssessment"));
        classes.add(new XmlClass("com.edifecs.breathe.qa.testcases.TestHealthAssessmentQuestions"));
        test.setXmlClasses(classes) ;

        //suites.add(suite);

        XmlTest test2 = new XmlTest(suite,1);
        test2.setName("TmpTest2");
        List<XmlClass> classes2 = new ArrayList<XmlClass>();
        classes2.add(new XmlClass("com.edifecs.breathe.qa.testcases.TestHealthAssessmentQuestions"));
        test2.setXmlClasses(classes2) ;

        XmlTest test3 = new XmlTest(suite,2);
        test3.setName("TmpTest3");
        List<XmlClass> classes3 = new ArrayList<XmlClass>();
        classes3.add(new XmlClass("com.edifecs.breathe.qa.testcases.TestHealthAssessmentQuestions"));
        test3.setXmlClasses(classes3) ;

        suites.add(suite);
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }
}
