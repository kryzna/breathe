package com.rgp.breathe.database;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by mdansari on 4/7/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class SharedPreferencesHelperTest {

    private SharedPreferencesHelper classUnderTest;

    private static final String TEST_USERNAME = "anas";
    private static final String TEST_USEREMAIL = "anas@email.com";
    private static final String TEST_USERDOB = "24/4/1992";
    private static final String TEST_USERPASSWORD = "1234";
    private static final boolean TEST_AUTOENABLE = true;

    @Mock
    SharedPreferences mMockSharedPreferences;

    @Mock
    SharedPreferences.Editor mMockEditor;


    @Before
    public void initMocks() {
        when(mMockSharedPreferences.edit()).thenReturn(mMockEditor);
        classUnderTest = new SharedPreferencesHelper(mMockSharedPreferences);
    }

    @Test
    public void testUserInfo() throws Exception {
        when(mMockSharedPreferences.getString(SharedPreferencesHelper.KEY_USERNAME, SharedPreferencesHelper.NULL)).thenReturn(TEST_USERNAME);
        when(mMockSharedPreferences.getString(SharedPreferencesHelper.KEY_USEREMAIL, SharedPreferencesHelper.NULL)).thenReturn(TEST_USEREMAIL);
        when(mMockSharedPreferences.getString(SharedPreferencesHelper.KEY_USERDOB, SharedPreferencesHelper.NULL)).thenReturn(TEST_USERDOB);
        when(mMockSharedPreferences.getString(SharedPreferencesHelper.KEY_USERPASSWORD, SharedPreferencesHelper.NULL)).thenReturn(TEST_USERPASSWORD);


        classUnderTest.saveUserInfo(TEST_USERNAME, TEST_USEREMAIL, TEST_USERDOB, TEST_USERPASSWORD);

        // Make sure both written and retrieved personal information are equal.
        assertThat("Checking that SharedPreferenceEntry.name has been persisted and read correctly",
                classUnderTest.getmUserName(), is(equalTo(TEST_USERNAME)));

        assertThat("Checking that SharedPreferenceEntry.email has been persisted and read correctly",
                classUnderTest.getmEmail(), is(equalTo(TEST_USEREMAIL)));

        assertThat("Checking that SharedPreferenceEntry.dob has been persisted and read correctly",
                classUnderTest.getmDOB(), is(equalTo(TEST_USERDOB)));

        assertThat("Checking that SharedPreferenceEntry.password has been persisted and read correctly",
                classUnderTest.getmPassword(), is(equalTo(TEST_USERPASSWORD)));
    }


    @Test
    public void testAutoEnable() throws Exception {
        when(mMockSharedPreferences.getBoolean(SharedPreferencesHelper.KEY_AUTOENABLE, false)).thenReturn(TEST_AUTOENABLE);
        classUnderTest.setAutoEnable(TEST_AUTOENABLE);
        assertThat("Checking that SharedPreferenceEntry.auto enabled has been persisted and read correctly",
                classUnderTest.ismAutoLogin(), is(TEST_AUTOENABLE));
    }


}