package com.example.care;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class GuestHome extends AppCompatActivity implements View.OnClickListener {

    static Activity thisActivity = null;

    private LinearLayout btnTakeSurvey = null;
    private LinearLayout btnCovidResults = null;
    private LinearLayout btnScheduleAppts = null;
    private LinearLayout btnCalendar = null;
    private LinearLayout btnExploreBusiness = null;
    private LinearLayout btnBusinessVisited = null;

    private TextView firstName;
    private TextView lastName;
    private TextView email;

    private GuestAccount guest;
    private static GuestAccount transferGuest;

    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnTakeSurvey:
                surveyActivity(); //Survey Activity
                break;
            case R.id.btnCovidResults:
                guestCovidResultsActivity(); //Upload Activity
                break;
            case R.id.btnScheduleAppts:
                scheduleApptActivity(); //Schedule Appointments Activity
                break;
            case R.id.btnCalendar:
                calendarActivity(); //Calendar Activity
                break;
            case R.id.btnExploreBusiness:
                exploreBusinessActivity();
                break;
            case R.id.btnBusinessVisited:
                visitedBusinessActivity();
                break;


        }
    }

    // This method initializes all the needed things
    private void initialize () {

        // Initialize Activity
        thisActivity = this;

        // Initialize buttons
        this.btnTakeSurvey = findViewById(R.id.btnTakeSurvey);
        this.btnTakeSurvey.setOnClickListener(this);
        this.btnCovidResults = findViewById(R.id.btnCovidResults);
        this.btnCovidResults.setOnClickListener(this);
        this.btnScheduleAppts = findViewById(R.id.btnScheduleAppts);
        this.btnScheduleAppts.setOnClickListener(this);
        this.btnCalendar = findViewById(R.id.btnCalendar);
        this.btnCalendar.setOnClickListener(this);
        this.btnExploreBusiness = findViewById(R.id.btnExploreBusiness);
        this.btnExploreBusiness.setOnClickListener(this);
        this.btnBusinessVisited = findViewById(R.id.btnBusinessVisited);
        this.btnBusinessVisited.setOnClickListener(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);

        firstName = headerView.findViewById(R.id.nameFirst);
        lastName = headerView.findViewById(R.id.nameLast);
        email = headerView.findViewById(R.id.email);

        transferGuest = guest;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_home);

        guest = getIntent().getExtras().getParcelable("User");

        initialize();

        drawer=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolBar);

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                Fragment fragment=null;
                switch (id) {
                    case R.id.profile:
                        fragment=new GuestProfileFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.settings:
                        fragment=new SettingsFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.help:
                        fragment=new HelpFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.logout:
//                        fragment=new LogoutFragment();
//                        loadFragment(fragment);
                        loginActivity();
                        break;
                }
                return true;
            }
        });

        String name = guest.getName();
        String [] fullName = name.split(" ");
        firstName.setText(fullName[0]);
        lastName.setText(fullName[1]);
        email.setText(guest.getEmail());

    }


    // This method creates Toast to send notification
    public static void makeToast(String str) {
        Toast.makeText(thisActivity, str, Toast.LENGTH_SHORT).show();
    }

    private void surveyActivity(){
        Intent intent = new Intent(this, Survey.class);
        intent.putExtra("User", guest);
        startActivity(intent);
    }

    private void guestCovidResultsActivity(){
        Intent intent = new Intent(this, GuestCovidResults.class);
        intent.putExtra("User", guest);
        startActivity(intent);
    }
    private void scheduleApptActivity(){
        Intent intent = new Intent(this, scheduleAppointment.class);
        intent.putExtra("User", guest);
        startActivity(intent);
    }
    private void calendarActivity(){
        Intent intent = new Intent(this, GuestCalendar.class);
        intent.putExtra("User", guest);
        startActivity(intent);
    }

    private void exploreBusinessActivity(){
        Intent intent = new Intent(this, GuestExploreBusiness.class);
        intent.putExtra("User", guest);
        startActivity(intent);
    }
    private void visitedBusinessActivity(){
        Intent intent = new Intent(this, GuestVisitedBusiness.class);
        intent.putExtra("User", guest);
        startActivity(intent);
    }

    private void loadFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment).commit();
        drawer.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }
    private void loginActivity(){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    public static GuestAccount getGuest() {
        return transferGuest;
    }
}


