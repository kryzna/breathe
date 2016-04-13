package com.edifecs.breathe.qa.testcases;

import com.edifecs.breathe.qa.functions.ExcelReader;
import com.edifecs.breathe.qa.functions.StandardFunctions;
import com.edifecs.breathe.qa.pageobjects.HealthRiskAssesment;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class TestHealthAssessmentSubmit
        {
                public static int riskscore=0;
                HealthRiskAssesment healthRiskAssessment = new HealthRiskAssesment();
                ExcelReader excelReader = new ExcelReader();

                @Test(priority = 1,invocationCount = 1)
                // Getting score
                public void verifyRiskScore(){
                        try {
                                StandardFunctions.click(healthRiskAssessment.getHealtAssessmentSubmit);
                                riskscore= Integer.parseInt(excelReader.getCellData("RiskAssessment","Riskc1",5));
                                System.out.println(riskscore);
                                System.out.println(StandardFunctions.getText(TestHealthRiskAssessment.elementQuestionTitle));
                                assert (StandardFunctions.getText(TestHealthRiskAssessment.elementQuestionTitle).toString().equals(riskscore));
                        }
                        catch(Exception e)
                        {
                                e.printStackTrace();
                        }
                       // WebElement elementNextButton =   HealthRiskAssesment.getElementNextButton();

                       // TestHealthRiskAssessment.questionNumber=TestHealthRiskAssessment.questionNumber+1;
                }

        }
