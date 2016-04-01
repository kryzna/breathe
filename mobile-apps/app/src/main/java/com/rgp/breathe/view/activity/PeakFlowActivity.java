package com.rgp.breathe.view.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.rgp.breathe.R;
import com.rgp.breathe.helper.Helper;
import com.rgp.breathe.database.SQLiteHandler;
import com.rgp.breathe.location.LocationAddress;
import com.rgp.breathe.location.DeviceLocationListener;
import com.rgp.breathe.model.PeakFlow;

/**
 * Created by mdansari on 3/30/2016.
 */
public class PeakFlowActivity extends AppCompatActivity {

    final String DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm:ss a";

    NumberPicker numberPicker1;
    NumberPicker numberPicker2;
    NumberPicker numberPicker3;

    TextView wheelReading1;
    TextView wheelReading2;
    TextView wheelReading3;

    EditText address1;
    EditText address2;
    Button fetchCurrentLocButton;
    private DeviceLocationListener locationListener;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peakflow);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        numberPicker1 = (NumberPicker) findViewById(R.id.number_picker1);
        wheelReading1 = (TextView) findViewById(R.id.peak_reading1);
        setNumberWheel(numberPicker1, 0);
        numberPicker2 = (NumberPicker) findViewById(R.id.number_picker2);
        wheelReading2 = (TextView) findViewById(R.id.peak_reading2);
        setNumberWheel(numberPicker2, 1);
        numberPicker3 = (NumberPicker) findViewById(R.id.number_picker3);
        wheelReading3 = (TextView) findViewById(R.id.peak_reading3);
        setNumberWheel(numberPicker3, 2);

        address1 = (EditText) findViewById(R.id.address1);
        address2 = (EditText) findViewById(R.id.address2);
        fetchCurrentLocButton = (Button) findViewById(R.id.fetch);

        fetchCurrentLocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchAddress();
            }
        });

        progressDialog = new ProgressDialog(PeakFlowActivity.this);
        progressDialog.setMessage("fetching current location...");
    }

    private void fetchAddress() {
        locationListener = new DeviceLocationListener(this);
        progressDialog.show();
        new FetchUserAddressTask().execute(new Void[0]);
    }

    private class FetchUserAddressTask extends AsyncTask<Void, Void, String> {
        private FetchUserAddressTask() {
        }

        protected String doInBackground(Void... params) {
            return LocationAddress.getAddressFromLatLang(locationListener.latitude, locationListener.longitude, getApplicationContext());
        }

        protected void onPostExecute(String s) {
            String[] location = s.split("\\++");
            progressDialog.dismiss();
            if (location.length > 1) {
                address1.setText(location[0]);
                address2.setText(location[1]);
                return;
            }
            Toast.makeText(getApplicationContext(), "Unable to fetch address. Try again or change your setting!", Toast.LENGTH_LONG).show();
            address1.setText("");
            address2.setText("");
        }
    }

    private void setNumberWheel(NumberPicker np, final int pos) {
        np.setMinValue(0);
        np.setMaxValue(9);
        np.setWrapSelectorWheel(false);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if (pos == 0) {
                    wheelReading1.setText(String.valueOf(newVal));
                } else if (pos == 1) {
                    wheelReading2.setText(String.valueOf(newVal));
                } else if (pos == 2) {
                    wheelReading3.setText(String.valueOf(newVal));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_peakflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            savePeakflowInfo();
        } else {
            onBackPressed();
        }
        return true;
    }

    private void savePeakflowInfo() {
        //final ProgressDialog loading = ProgressDialog.show(this, null, "Adding new entry...", false, false);
        StringBuilder peakflowReading = new StringBuilder();
        peakflowReading.append(wheelReading1.getText()).append(wheelReading2.getText()).append(wheelReading3.getText()).append(" ml/s");
        String currentDateTime = Helper.getFormattedDate(DATE_TIME_FORMAT);
        String location = address2.getText().toString();
        PeakFlow peakFlow = new PeakFlow(peakflowReading.toString(), currentDateTime, location);
        SQLiteHandler sqLiteHandler = new SQLiteHandler(this);
        sqLiteHandler.addPeakFlowEntry(peakFlow);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
