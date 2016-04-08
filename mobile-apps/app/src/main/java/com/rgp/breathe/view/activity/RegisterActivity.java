package com.rgp.breathe.view.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rgp.breathe.R;
import com.rgp.breathe.database.SharedPreferencesHelper;

import java.util.Calendar;

/**
 * Created by mdansari on 3/23/2016.
 */
public class RegisterActivity extends AppCompatActivity {

    private static String TAG = RegisterActivity.class.getSimpleName();

    private EditText mNameView;
    private EditText mEmailView;
    private EditText mDateOfBirthView;
    private EditText mPasswordView;
    private Button mBtnRegisterView;
    private TextView mLinkSigninView;

    private UserRegisterTask mAuthTask = null;
    private ProgressDialog progressDialog;
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mNameView = (EditText) findViewById(R.id.input_name);
        mEmailView = (EditText) findViewById(R.id.input_email);
        mDateOfBirthView = (EditText) findViewById(R.id.input_dateOfBirth);
        mDateOfBirthView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });

        mPasswordView = (EditText) findViewById(R.id.input_password);

        mBtnRegisterView = (Button) findViewById(R.id.sign_out_button);
        mBtnRegisterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        mLinkSigninView = (TextView) findViewById(R.id.link_signin);
        mLinkSigninView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignInScreen();
            }
        });

        sharedPreferencesHelper = new SharedPreferencesHelper(PreferenceManager.getDefaultSharedPreferences(this));
    }


    private void registerUser() {
        if (mAuthTask != null) {
            return;
        }

        mNameView.setError(null);
        mEmailView.setError(null);
        mDateOfBirthView.setError(null);
        mPasswordView.setError(null);

        String name = mNameView.getText().toString();
        String email = mEmailView.getText().toString();
        String dob = mDateOfBirthView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (isRegisterFormValid(name, email, dob, password)) {
            progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("wait for a while user is creating...");
            progressDialog.show();
            mAuthTask = new UserRegisterTask(name, email, dob, password);
            mAuthTask.execute((Void) null);
        }
    }

    void setDate() {
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                mDateOfBirthView.setText(selectedday + "/" + (selectedmonth + 1) + "/" + selectedyear);
            }
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }

    private boolean isRegisterFormValid(String name, String email, String dob, String password) {
        if (isNameValid(name) && isEmailValid(email) && isDOBValid(dob) && isPasswordValid(password)) {
            return true;
        }
        return false;
    }

    private boolean isNameValid(String name) {
        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            return false;
        } else {
            if (!(name.length() > 1)) {
                mNameView.setError(getString(R.string.error_invalid_password));
                return false;
            } else {
                return true;
            }
        }
    }

    private boolean isEmailValid(String email) {
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            return false;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mEmailView.setError(getString(R.string.error_invalid_email));
                return false;
            } else {
                return true;
            }
        }
    }

    private boolean isDOBValid(String dob) {
        if (TextUtils.isEmpty(dob)) {
            mDateOfBirthView.setError(getString(R.string.error_field_required));
            return false;
        }
        return true;

    }

    private boolean isPasswordValid(String password) {
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            return false;
        } else {
            if (!(password.length() > 1)) {
                mPasswordView.setError(getString(R.string.error_invalid_password));
                return false;
            } else {
                return true;
            }
        }
    }

    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUserName;
        private final String mEmail;
        private final String mDOB;
        private final String mPassword;

        public UserRegisterTask(String mUserName, String mEmail, String mDOB, String mPassword) {
            this.mUserName = mUserName;
            this.mEmail = mEmail;
            this.mDOB = mDOB;
            this.mPassword = mPassword;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            progressDialog.show();
            // TODO: create user against a network service.
            sharedPreferencesHelper.saveUserInfo(mUserName, mEmail, mDOB, mPassword);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            progressDialog.hide();
            mAuthTask = null;
            if (success) {
                Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_LONG);
                showSignInScreen();
            } else {
                Toast.makeText(getApplicationContext(), "User already exist!", Toast.LENGTH_LONG);
            }
        }

        @Override
        protected void onCancelled() {
            progressDialog.hide();
            mAuthTask = null;
        }
    }

    private void showSignInScreen() {
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
