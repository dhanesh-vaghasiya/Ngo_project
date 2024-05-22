package com.example.ngo_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class user_page_dashboard extends Fragment {
    public user_page_dashboard() {
        // Required empty public constructor
    }
    Button toLoginBtn;
    LinearLayout toFormBtn, toDonateBtn, toFeedbackBtn, toReportBtn, toAboutBtn, toContactBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_user_page_dashboard, container, false);
        toLoginBtn = view.findViewById(R.id.dashboard_to_login_btn);
        toFormBtn = view.findViewById(R.id.dashboard_to_form_btn);
        toFeedbackBtn = view.findViewById(R.id.dashboard_to_feedback_btn);
        toDonateBtn = view.findViewById(R.id.dashboard_to_donate_btn);
        toReportBtn = view.findViewById(R.id.dashboard_to_reports_btn);
        toAboutBtn = view.findViewById(R.id.dashboard_to_about_btn);
        toContactBtn = view.findViewById(R.id.dashboard_to_contact_btn);
        final MainActivity activity = (MainActivity) getActivity();

        if (activity.isUserLogined){
            toLoginBtn.setVisibility(View.GONE);
        } 


        toLoginBtn.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), Login_Activity.class));
        });

        toFormBtn.setOnClickListener(v -> {
            activity.loadFragment(new child_adoption_page(), "Adoption request", activity.isUserLogined);
        });

        toFeedbackBtn.setOnClickListener(v -> {
            activity.loadFragment(new user_page_feedback(), "Feedbacks");;
        });

        toDonateBtn.setOnClickListener(v -> {
            activity.loadFragment(new user_page_donate(), "Donate", activity.isUserLogined);
        });

        toReportBtn.setOnClickListener(v -> {
            activity.loadFragment(new page_reports(), "Funds report", activity.isUserLogined);
        });

        toAboutBtn.setOnClickListener(v -> {
            activity.loadFragment(new page_about_us(), "About us");
        });

        toContactBtn.setOnClickListener(v -> {
            activity.loadFragment(new page_contact_us(), "Contact us");
        });

        return view;
    }
}