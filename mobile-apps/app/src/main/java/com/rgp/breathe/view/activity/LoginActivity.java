package com.rgp.breathe.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import com.rgp.breathe.R;
import com.rgp.breathe.database.SharedPreferencesHelper;

public class LoginActivity extends AppCompatActivity {

    private static String TAG = LoginActivity.class.getSimpleName();

    private SharedPreferencesHelper sharedPreferencesHelper;

    private EditText mEmailView;
    private EditText mPasswordView;
    private CheckBox mAutoLoginView;
    private Button mBtnLoginView;
    private TextView mLinkSignupView;

    private UserLoginTask mAuthTask = null;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(sharedPreferences);
        /*if (sharedPreferencesHelper.ismAutoLogin()) {
        gotoMainScreen();
        }*/

        mEmailView = (EditText) findViewById(R.id.input_email);

        mPasswordView = (EditText) findViewById(R.id.input_password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mAutoLoginView = (CheckBox) findViewById(R.id.auto_login);

        mBtnLoginView = (Button) findViewById(R.id.sign_in_button);
        mBtnLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        mLinkSignupView = (TextView) findViewById(R.id.link_signup);
        mLinkSignupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignUpScreen();
            }
        });
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean autologinEnabled = mAutoLoginView.isChecked();

        if (isEmailValid(email) && isPasswordValid(password)) {
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
            mAuthTask = new UserLoginTask(email, password, autologinEnabled);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        /*if (TextUtils.isEmpty(email)) {
        mEmailView.setError(getString(R.string.error_field_required));
        return false;
        } else {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        mEmailView.setError(getString(R.string.error_invalid_email));
        return false;
        } else {
        return true;
        }
        }*/

        return true;
    }

    private boolean isPasswordValid(String password) {
        /*if (TextUtils.isEmpty(password)) {
        mPasswordView.setError(getString(R.string.error_field_required));
        return false;
        } else {
        if (!(password.length() > 1)) {
        mPasswordView.setError(getString(R.string.error_invalid_password));
        return false;
        } else {
        return true;
        }
        }*/
        return true;
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        private boolean isUserValid = true;
        private boolean isPassValid = true;
        private boolean autologinEnabled = true;


        public UserLoginTask(String mEmail, String mPassword, boolean isAutologinEnabled) {
            this.mEmail = mEmail;
            this.mPassword = mPassword;
            isUserValid = true;
            isPassValid = true;
            this.autologinEnabled = isAutologinEnabled;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.


            /*String email = sharedPreferencesHelper.getmEmail();
            String pass = sharedPreferencesHelper.getmPassword();
            if (email.equals(mEmail)) {
                if (pass.equals(mPassword)) {
              return true;
                } else {
              isPassValid = false;
              return false;
                }
            } else {
                isUserValid = false;
                return false;
            }*/
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            progressDialog.hide();
            mAuthTask = null;
            if (success) {
                sharedPreferencesHelper.setAutoEnable(autologinEnabled);
                gotoMainScreen();
            } else {
                if (!isUserValid) {
                    Toast.makeText(LoginActivity.this, "User not exist, create user to log in.",
                            Toast.LENGTH_LONG).show();
                    mEmailView.setError("User not exist.");
                    mEmailView.requestFocus();
                } else {
                    Toast.makeText(LoginActivity.this, "Incorrect password.", Toast.LENGTH_LONG)
                            .show();
                    mPasswordView.setError(getString(R.string.error_incorrect_password));
                    mPasswordView.requestFocus();
                }
            }
        }

        @Override
        protected void onCancelled() {
            progressDialog.hide();
            mAuthTask = null;
        }
    }

    private void showSignUpScreen() {
        finish();
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void gotoMainScreen() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
