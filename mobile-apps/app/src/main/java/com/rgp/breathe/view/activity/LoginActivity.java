package com.rgp.breathe.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rgp.breathe.R;
import com.rgp.breathe.helper.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private static String TAG = LoginActivity.class.getSimpleName();

    private static SessionManager sessionManager;

    private EditText mEmailView;
    private EditText mPasswordView;
    private CheckBox mAutoLoginView;
    private Button mBtnLoginView;
    private TextView mLinkSignupView;

    private UserLoginTask mAuthTask = null;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        if (sessionManager.isAutoLoginEnabled()) {
            gotoMainScreen();
        }

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

        if (isEmailValid(email) && isPasswordValid(password)) {
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
            mAuthTask = new UserLoginTask(email, password);
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
            if (!(password.length() > 4)) {
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
        private String userName;

        public UserLoginTask(String mEmail, String mPassword) {
            this.mEmail = mEmail;
            this.mPassword = mPassword;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            this.userName = "default user";
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            progressDialog.hide();
            mAuthTask = null;
            if (success) {
                //sessionManager.createLogin(userName, mEmail, mAutoLoginView.isChecked());
                gotoMainScreen();
            } else {
                Toast.makeText(getApplicationContext(), "Authentication failed!", Toast.LENGTH_LONG);
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
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
