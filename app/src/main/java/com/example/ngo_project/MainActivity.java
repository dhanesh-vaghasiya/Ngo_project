package com.example.ngo_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.net.Uri;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.Bitmap;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    FrameLayout userPageContainer;
    NavigationView navigationView;
    //    BottomNavigationView bnView;
    ImageButton menuBtn, menuBackBtn;
    LinearLayout toSignUpPageBtn;
    TextView pageTitle;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser currentUser;
    Boolean isUserLogined=false;

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
        toSignUpPageBtn = findViewById(R.id.to_sign_up_page_btn);
        FirebaseUser currentUser = auth.getCurrentUser();


        List<Navigation_menu_item> navigationMenuItemList = new ArrayList<>();
        if (currentUser != null) {
            if (!currentUser.isEmailVerified()) {
                startActivity(new Intent(getApplicationContext(), email_verify_page.class));
            }
            isUserLogined = true;
            ((TextView) toSignUpPageBtn.getChildAt(1)).setText("Logout");
//            ((Button) findViewById(R.id.dashboard_to_login_btn)).setVisibility(View.GONE);
            ((ImageView) toSignUpPageBtn.getChildAt(0)).setImageResource(R.drawable.ic_34);
        } else {
            ((TextView) toSignUpPageBtn.getChildAt(1)).setText("Login");
        }

        Fragment userDashboard = new user_page_dashboard();

        loadFragment(userDashboard);

        toSignUpPageBtn.setOnClickListener(v -> {
            if (isUserLogined) {
                auth.signOut();
                startActivity(new Intent(this, MainActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
                if (auth.getCurrentUser() != null)
                    Log.d("TAG", "true");
            } else {
                startActivity(new Intent(this, Login_Activity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navigationMenuItemList.add(new Navigation_menu_item(R.drawable.ic_26, R.drawable.ic_27, "Dashboard", true, ()->{
            loadFragment(new user_page_dashboard(), "Dashboard");
            drawerLayout.closeDrawer(GravityCompat.START);
        }));
        navigationMenuItemList.add(new Navigation_menu_item(R.drawable.ic_20, R.drawable.ic_28, "Child Adoption", false, ()->{
            loadFragment(new child_adoption_page(), "Adoption request", isUserLogined);
            drawerLayout.closeDrawer(GravityCompat.START);
        }));
        navigationMenuItemList.add(new Navigation_menu_item(R.drawable.ic_21, R.drawable.ic_29, "Donate", false, ()->{
            loadFragment(new user_page_donate(), "Donate", isUserLogined);
            drawerLayout.closeDrawer(GravityCompat.START);
        }));
        navigationMenuItemList.add(new Navigation_menu_item(R.drawable.ic_23, R.drawable.ic_31, "Reports", false ,()->{
            loadFragment(new page_reports(), "Funds report", isUserLogined);
            drawerLayout.closeDrawer(GravityCompat.START);
        }));
        navigationMenuItemList.add(new Navigation_menu_item(R.drawable.ic_22, R.drawable.ic_30, "Feedbacks", false, ()->{
            loadFragment(new user_page_feedback(), "Feedbacks");
            drawerLayout.closeDrawer(GravityCompat.START);
        }));
        navigationMenuItemList.add(new Navigation_menu_item(R.drawable.ic_24, R.drawable.ic_32, "Contact us", false ,()->{
            loadFragment(new page_contact_us(), "Contact us");
            drawerLayout.closeDrawer(GravityCompat.START);
        }));
        navigationMenuItemList.add(new Navigation_menu_item(R.drawable.ic_25, R.drawable.ic_33, "About us", false, ()->{
            loadFragment(new page_about_us(), "About us");
            drawerLayout.closeDrawer(GravityCompat.START);
        }));
        ((ListView) findViewById(R.id.nav_drawer_list)).setAdapter(new custom_navigation_menu_adapter(getApplicationContext(), navigationMenuItemList, isUserLogined));

    }

    public void loadFragment(Fragment fg) {
        getSupportFragmentManager().clearBackStack(String.valueOf(R.id.user_page_container));
        getSupportFragmentManager().beginTransaction().add(R.id.user_page_container, fg).commit();
    }

    public void loadFragment(Fragment fg, String titleTExt) {
        getSupportFragmentManager().beginTransaction().add(R.id.user_page_container, fg).commit();
        pageTitle.setText(titleTExt);
    }

    public void loadFragment(Fragment fg, String titleTExt, boolean userloginStatus) {
        if (userloginStatus) {
            getSupportFragmentManager().beginTransaction().add(R.id.user_page_container, fg).commit();
            pageTitle.setText(titleTExt);

        } else {
            Toast.makeText(this, "Please sign in first.", Toast.LENGTH_SHORT).show();
        }
    }

    public void formDropDownToggle(View view) {
        LinearLayout dropHeader = (LinearLayout) view;
        LinearLayout dropContent = (LinearLayout) ((LinearLayout) dropHeader.getParent()).getChildAt(1);
        if (dropContent.getVisibility() == View.VISIBLE) {
            dropHeader.getChildAt(1).animate().rotation(0f).setDuration(250);
            dropContent.setVisibility(View.GONE);
        } else {
            dropHeader.getChildAt(1).animate().rotation(180f).setDuration(250);
            dropContent.setVisibility(View.VISIBLE);
        }
    }

    public void toggleNavDrawer(View view) {
        if (view.getId() == R.id.nav_drawer_menu_back_btn) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (view.getId() == R.id.nav_drawer_menu_btn) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        super.onBackPressed();
        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_exit_dialog, null);

        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        // Set up buttons and other views inside the dialog
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button yesButton = dialogView.findViewById(R.id.exit_confirm_yes_btn);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button noButton = dialogView.findViewById(R.id.exit_no_btn);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Set up click listeners for the buttons
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Exit the app
                finish();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog
                alertDialog.dismiss();
            }
        });
    }

}