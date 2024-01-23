package com.example.ngo_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    FrameLayout userPageContainer;
    NavigationView navigationView;
    BottomNavigationView bnView;


    ImageButton menuBtn, menuBackBtn;
    TextView pageTitle;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();

    @SuppressLint("UnsafeIntentLaunch")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.user_drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.user_navigation);
        menuBtn = findViewById(R.id.nav_drawer_menu_btn);
        menuBackBtn = findViewById(R.id.nav_drawer_menu_back_btn);
        userPageContainer = findViewById(R.id.user_page_container);
        pageTitle = findViewById(R.id.page_title);

        bnView = findViewById(R.id.user_bottom_nav);



        // Checking who  is logged in or not
        SharedPreferences preferences = getSharedPreferences("AdminPrefs", MODE_PRIVATE);
        if (preferences.getBoolean("isAdminLoggedIn", false)){
            // If admin is logged in
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.navigation_drawer_admins_items);

        } else if (currentUser != null) {
            // if user is logged in
            Toast.makeText(this, "User is signed", Toast.LENGTH_SHORT).show();
            navigationView.getMenu().removeItem(R.id.login_drawer_menu_btn);
            navigationView.getMenu().add(Menu.NONE, Menu.FIRST, navigationView.getMenu().size(), "Logout");
        }

        // Bottom navigation chooses
        //      start
        loadFragment(new user_page_dashboard());
        bnView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId==R.id.dashboard_menu_btn){
                loadFragment(new user_page_dashboard(), "Dashboard");
            } else if (itemId==R.id.child_adoption_menu_btn) {
                loadFragment(new user_page_child_adoption(), "Child adoption requests", currentUser != null);
            } else if (itemId==R.id.donate_menu_btn) {
                loadFragment(new user_page_donate(), "Donation reports", currentUser != null);
            } else if (itemId==R.id.feedback_menu_btn) {
                loadFragment(new user_page_feedback(), "Feedback review", currentUser != null);
            }
            return true;
        });
        //      end

//        menuBtn.setOnClickListener(view->{
//                    drawerLayout.openDrawer(GravityCompat.START);
//            });
//
//        menuBackBtn.setOnClickListener(v -> {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        });

        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId==R.id.dashboard_drawer_menu_btn){
                loadFragment(new user_page_dashboard(), "Dashboard");
                drawerLayout.closeDrawer(GravityCompat.START);

            } else if (itemId==R.id.child_adoption_drawer_menu_btn) {
                loadFragment(new user_page_child_adoption(), "Child adoption form", currentUser!=null);
                drawerLayout.closeDrawer(GravityCompat.START);

            } else if (itemId==R.id.report_drawer_menu_btn) {
                loadFragment(new user_page_donate(), "Donations log", currentUser!=null);
                drawerLayout.closeDrawer(GravityCompat.START);

            } else if (itemId==R.id.contact_us_drawer_menu_btn) {
                loadFragment(new page_contact_us(), "Contact us");
                drawerLayout.closeDrawer(GravityCompat.START);

            } else if (itemId==R.id.feedback_drawer_menu_btn) {
                loadFragment(new user_page_feedback(), "Feedbacks", currentUser!=null);
                drawerLayout.closeDrawer(GravityCompat.START);

            } else if (itemId==R.id.donate_drawer_menu_btn) {
                loadFragment(new user_page_feedback(), "Make a donation", currentUser!=null);
                drawerLayout.closeDrawer(GravityCompat.START);

            } else if (itemId==R.id.about_us_drawer_menu_btn) {
                loadFragment(new page_about_us(), "About us");
                drawerLayout.closeDrawer(GravityCompat.START);

            } else if (itemId==R.id.services_drawer_menu_btn) {
                loadFragment(new user_page_feedback(), "Services");
                drawerLayout.closeDrawer(GravityCompat.START);

            } else if (itemId==R.id.login_drawer_menu_btn) {
                startActivity(new Intent(getApplicationContext(), Login_Activity.class));
                drawerLayout.closeDrawer(GravityCompat.START);

            } else if (itemId==1){
                auth.signOut();
                finish();
            }

            if (itemId==R.id.admin_logout_btn){
                Toast.makeText(getApplicationContext(), "Log out", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getSharedPreferences("AdminPrefs", MODE_PRIVATE).edit();
                auth.signOut();
                editor.putBoolean("isAdminLoggedIn", false).apply();
                drawerLayout.closeDrawer(GravityCompat.START);
                finish();
                startActivity(getIntent());
            }

            return true;
        });

    }

    public void loadFragment(Fragment fg){
        getSupportFragmentManager().beginTransaction().add(R.id.user_page_container, fg).commit();
    }
    public void loadFragment(Fragment fg, String titleTExt){
        getSupportFragmentManager().beginTransaction().add(R.id.user_page_container, fg).commit();
        pageTitle.setText(titleTExt);
    }

    public void loadFragment(Fragment fg, String titleTExt, boolean userloginStatus){
        if (userloginStatus) {
            getSupportFragmentManager().beginTransaction().add(R.id.user_page_container, fg).commit();
            pageTitle.setText(titleTExt);
        } else {
            Toast.makeText(this, "First sign in", Toast.LENGTH_SHORT).show();
        }
    }

    public void formDropDownToggle(View view) {
        LinearLayout dropHeader = (LinearLayout) view;
        LinearLayout dropContent = (LinearLayout) ((LinearLayout) dropHeader.getParent()).getChildAt(1);
        if (dropContent.getVisibility()== View.VISIBLE){
            dropHeader.getChildAt(1).animate().rotation(0f).setDuration(250);
            dropContent.setVisibility(View.GONE);
        } else {
            dropHeader.getChildAt(1).animate().rotation(180f).setDuration(250);
            dropContent.setVisibility(View.VISIBLE);
        }
    }

    public void toggleNavDrawer(View view){
        if (view.getId()==R.id.nav_drawer_menu_back_btn){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (view.getId() == R.id.nav_drawer_menu_btn) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
}