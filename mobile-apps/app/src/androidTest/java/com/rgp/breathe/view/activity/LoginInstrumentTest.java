package com.rgp.breathe.view.activity;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rgp.breathe.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by mdansari on 4/11/2016.
 */

@RunWith(AndroidJUnit4.class)
public class LoginInstrumentTest {

    @Rule
    public IntentsTestRule<LoginActivity> mActivityRule = new IntentsTestRule<>(LoginActivity.class);

    @Test
    public void testValidLoginForm() {
        onView(withId(R.id.input_email)).check(matches(notNullValue()));
        onView(withId(R.id.input_password)).check(matches(notNullValue()));
        onView(withId(R.id.sign_in_button)).check(matches(notNullValue()));
        onView(withId(R.id.link_signup)).check(matches(notNullValue()));

        /*onView(withId(R.id.sign_in_button)).check(matches(withText("Login")));
        onView(withId(R.id.link_signup)).check(matches(withText("No account yet? Create one")));*/
    }

    @Test
    public void testClickOnSignIn() {
        onView(withId(R.id.input_email)).perform(typeText("anas@email.com"), closeSoftKeyboard());
        onView(withId(R.id.input_email)).check(matches(withText("anas@email.com")));

        onView(withId(R.id.input_password)).perform(typeText("1234"), closeSoftKeyboard());
        onView(withId(R.id.input_password)).check(matches(withText("1234")));

        onView(withId(R.id.sign_in_button)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));
    }

    @Test
    public void testClickOnLinkToRegister() {
        onView(withId(R.id.link_signup)).perform(click());
        intended(hasComponent(RegisterActivity.class.getName()));
    }

}