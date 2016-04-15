package com.rgp.breathe.view.activity;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rgp.breathe.R;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by mdansari on 4/14/2016.
 */
@RunWith(AndroidJUnit4.class)
public class HealthRiskAssessmentInstrumentTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    Matcher<View> isInToolbar;

    @Before
    public void showHealthRiskAssessmentPage() {
        //click on image button on Toolbar to Open NavigationView
        onView(withId(R.id.nav_view)).check(matches(notNullValue()));
        Matcher<View> isImageButton = withClassName(is(ImageButton.class.getName()));
        isInToolbar = isDescendantOfA(withId(R.id.toolbar));
        onView(allOf(isInToolbar, isImageButton)).perform(click());

        //once NavigationView opened, drawer shows up and first menu (Treatment plan) loses focus.
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()));
        onView(withId(R.id.fragmen_treatment_plan)).check(matches(not(hasFocus())));

        onView(withText(R.string.health_risk_assesment)).perform(click());
        onView(withId(R.id.fragmen_health_risk_assessment)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void testTitleHealthRiskAssessment() {
        onView(allOf(isInToolbar, withText(mActivityRule.getActivity().getString(R.string.health_risk_assesment)))).check(matches(isDisplayed()));
    }

    @Test
    public void testQuestionnaire() {
        //check for questionnaires list on this page
        onView(withId(R.id.questionnaire_list_view)).check(matches(notNullValue()));
        onView(withId(R.id.questionnaire_list_view)).check(matches(isDisplayed()));

        onView(withId(R.id.questionnaire_list_view)).perform(RecyclerViewActions.scrollToPosition(0));
        onView(withId(R.id.questionnaire_row)).check(matches(isDisplayed()));
        onView(withId(R.id.questionnaire_title)).check(matches(isDisplayed()));
        onView(withId(R.id.questionnaire_status_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testClickOnQuestionnaireToShowQuestionDialog() {
        onView(withId(R.id.questionnaire_list_view)).perform(RecyclerViewActions.scrollToPosition(0));

        //get questionnaire title
        String questionnaireTitle = getText(withId(R.id.questionnaire_title));

        //click on questionnaire status button to see question dialog
        onView(withId(R.id.questionnaire_list_view)).perform(RecyclerViewActions
                .actionOnItem(hasDescendant(withId(R.id.questionnaire_status_button)), click()));
        onView(withId(R.id.question_dialod)).check(matches(isDisplayed()));

        //check title of question dialog
        onView(withId(R.id.questionnaire_title)).check(matches(withText(questionnaireTitle)));

        onView(withId(R.id.radio_group_raq)).check(matches(isDisplayed()));

        /*//
        Matcher<View> radiogroup = withId(R.id.radio_group_raq);
        Matcher<View> radioButton = isDescendantOfA(radiogroup);
        onView(allOf(radiogroup, radioButton)).perform(click());*/
    }

    String getText(final Matcher<View> matcher) {
        final String[] stringHolder = {null};
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView) view;
                stringHolder[0] = tv.getText().toString();
            }
        });
        return stringHolder[0];
    }

    @After
    public void clean() {
        // mActivityRule = null;
    }
}