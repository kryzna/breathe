package com.rgp.breathe.dao;

import com.rgp.breathe.model.Question;
import com.rgp.breathe.model.Questionnaire;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdansari on 3/29/2016.
 */
public class QuestionnaireDataGenerator {

    static String[] titles = {"Asthma Primary Control Questionnaire (APCQ)", "Asthma Secondary Control Questionnaire (ASCQ)"};
    static String[] status = {"completed", "not completed"};

    static List<Questionnaire> questionnaireList;


    public static void setQuestionnairesData() {
        questionnaireList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            questionnaireList.add(new Questionnaire(titles[i], status[i], getQuestionList(i)));
        }
    }

    public static List<Questionnaire> getQuestionnairesData() {
        return questionnaireList;
    }

    public static List<Question> getQuestionList(int i) {
        List<Question> questionList = new ArrayList<>();
        if (i == 1) {
            //1st questionnary
            String ques = "Have you heard wheezing in your chest when you breathe?";
            List<String> possibleAnswers = new ArrayList<>();
            possibleAnswers.add("YES");
            possibleAnswers.add("NO");
            List<Object> answers = new ArrayList<>();
            answers.add(null);
            answers.add(null);
            Question question = new Question(ques, "RADIO", possibleAnswers, answers);
            questionList.add(question);

            ques = "How often do you have a hard time with coughing, breathing or wheezing?";
            possibleAnswers = new ArrayList<>();
            possibleAnswers.add("Two times a week or less");
            possibleAnswers.add("More than two times a week");
            possibleAnswers.add("Every day (at least once every day");
            answers = new ArrayList<>();
            answers.add(null);
            answers.add(null);
            answers.add(null);
            question = new Question(ques, "CHECKBOX", possibleAnswers, answers);
            questionList.add(question);

            ques = "Have you had a hard time coughing, breathing or wheezing in the daytime?";
            possibleAnswers = new ArrayList<>();
            possibleAnswers.add("YES");
            possibleAnswers.add("NO");
            answers = new ArrayList<>();
            answers.add(null);
            answers.add(null);
            question = new Question(ques, "RADIO", possibleAnswers, answers);
            questionList.add(question);

        } else {
            //2nd questionnary

            String ques = "Do you smoke?";
            List<String> possibleAnswers = new ArrayList<>();
            possibleAnswers.add("YES");
            possibleAnswers.add("NO");
            possibleAnswers.add("SOMETIMES");
            List<Object> answers = new ArrayList<>();
            answers.add(null);
            answers.add(null);
            answers.add(null);
            Question question = new Question(ques, "RADIO", possibleAnswers, answers);
            questionList.add(question);

            ques = "Do your friends smoke?";
            possibleAnswers = new ArrayList<>();
            possibleAnswers.add("YES");
            possibleAnswers.add("NO");
            possibleAnswers.add("SOMETIMES");
            answers = new ArrayList<>();
            answers.add(null);
            answers.add(null);
            answers.add(null);
            question = new Question(ques, "RADIO", possibleAnswers, answers);
            questionList.add(question);

            ques = "Does anyone smoke at home?";
            possibleAnswers = new ArrayList<>();
            possibleAnswers.add("YES");
            possibleAnswers.add("NO");
            possibleAnswers.add("SOMETIMES");
            answers = new ArrayList<>();
            answers.add(null);
            answers.add(null);
            answers.add(null);
            question = new Question(ques, "RADIO", possibleAnswers, answers);
            questionList.add(question);
        }
        return questionList;
    }

}
