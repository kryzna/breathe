package com.rgp.breathe.view.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rgp.breathe.R;
import com.rgp.breathe.view.fragment.FragmentTracking;
import com.rgp.breathe.view.fragment.FragmentTreatmentPlan;
import com.rgp.breathe.view.fragment.FragmentAlerts;
import com.rgp.breathe.view.fragment.FragmentHealthRiskAssesment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private  ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        mDrawer = (NavigationView) findViewById(R.id.nav_view);
        mDrawer.setNavigationItemSelectedListener(this);

        //Add the Very First Fragment to the Container
        selectFragmentView(new FragmentTreatmentPlan(getApplicationContext()));
    }

    public void setActionBarTitle(String title){
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int mSelectedId = item.getItemId();

        if (mSelectedId == R.id.nav_treatment_plan) {
           selectFragmentView(new FragmentTreatmentPlan(getApplicationContext()));
        } else if (mSelectedId == R.id.nav_health_risk_assesment) {
            selectFragmentView(new FragmentHealthRiskAssesment(getApplicationContext()));
        } else if (mSelectedId == R.id.nav_alerts) {
            selectFragmentView(new FragmentAlerts(getApplicationContext()));
        } else if (mSelectedId == R.id.nav_tracking) {
            selectFragmentView(new FragmentTracking(getApplicationContext()));
        } else if (mSelectedId == R.id.nav_share) {
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.list_row);
            dialog.setTitle("Title Questionarie");
            dialog.show();
        } else if (mSelectedId == R.id.nav_about) {

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void selectFragmentView(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerView,fragment,null);
        fragmentTransaction.commit();
    }

}
