package com.rgp.breathe.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rgp.breathe.R;
import com.rgp.breathe.database.SharedPreferencesHelper;
import com.rgp.breathe.view.fragment.FragmentAbout;
import com.rgp.breathe.view.fragment.FragmentAlerts;
import com.rgp.breathe.view.fragment.FragmentHealthRiskAssesment;
import com.rgp.breathe.view.fragment.FragmentTracking;
import com.rgp.breathe.view.fragment.TreatmentPlanFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int RESULT_SETTINGS = 1;
    private Toolbar toolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private TextView userNameView;
    private TextView userEmailView;
    private SharedPreferencesHelper sharedPreferencesHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferencesHelper =
                new SharedPreferencesHelper(PreferenceManager.getDefaultSharedPreferences(this));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        mDrawer = (NavigationView) findViewById(R.id.nav_view);
        mDrawer.setNavigationItemSelectedListener(this);

        /*View header = mDrawer.getHeaderView(0);
        userNameView = (TextView) header.findViewById(R.id.user_name);
        userEmailView = (TextView) header.findViewById(R.id.user_email);

        userNameView.setText(sharedPreferencesHelper.getmUserName());
        userEmailView.setText(sharedPreferencesHelper.getmEmail());*/

        //Add the Very First Fragment to the Container
        selectFragmentView(new TreatmentPlanFragment());
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivityForResult(i, RESULT_SETTINGS);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SETTINGS:
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
                userNameView.setText(sharedPrefs.getString("prefUsername", "NULL"));
                break;
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int mSelectedId = item.getItemId();

        if (mSelectedId == R.id.nav_treatment_plan) {
            selectFragmentView(new TreatmentPlanFragment());
        } else if (mSelectedId == R.id.nav_health_risk_assesment) {
            selectFragmentView(new FragmentHealthRiskAssesment());
        } else if (mSelectedId == R.id.nav_alerts) {
            selectFragmentView(new FragmentAlerts());
        } else if (mSelectedId == R.id.nav_tracking) {
            selectFragmentView(new FragmentTracking());
        } else if (mSelectedId == R.id.nav_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareMessageBody = getString(R.string.share_message);
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.share_subject);
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareMessageBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        } else if (mSelectedId == R.id.nav_about) {
            selectFragmentView(new FragmentAbout());
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void selectFragmentView(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerView, fragment, null);
        fragmentTransaction.commit();
    }

}
