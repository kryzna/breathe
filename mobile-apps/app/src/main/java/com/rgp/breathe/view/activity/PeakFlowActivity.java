package com.rgp.breathe.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.rgp.breathe.R;
import com.rgp.breathe.dao.PeakFlowDao;
import com.rgp.breathe.helper.Helper;
import com.rgp.breathe.helper.SQLiteHandler;
import com.rgp.breathe.model.PeakFlow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    private void savePeakflowInfo(){
        //final ProgressDialog loading = ProgressDialog.show(this, null, "Adding new entry...", false, false);
        StringBuilder peakflowReading = new StringBuilder();
        peakflowReading.append(wheelReading1.getText()).append(wheelReading2.getText()).append(wheelReading3.getText()).append(" ml/s");
        String currentDateTime = Helper.getFormattedDate(DATE_TIME_FORMAT);
        PeakFlow peakFlow = new PeakFlow(peakflowReading.toString(), currentDateTime);
        SQLiteHandler sqLiteHandler = new SQLiteHandler(this);
        sqLiteHandler.addPeakFlowEntry(peakFlow);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
