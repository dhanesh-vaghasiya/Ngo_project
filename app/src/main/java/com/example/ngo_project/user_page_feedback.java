package com.example.ngo_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class user_page_feedback extends Fragment {

    public user_page_feedback() {
    }

    Button addFeedbackBtn;
    TextView averageRating, totalRating;
    ImageView averageRatingImg;
    View view;

    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_page_feedback, container, false);
        addFeedbackBtn = view.findViewById(R.id.add_feedback_btn);


        averageRating = view.findViewById(R.id.average_rating_txt);
        averageRatingImg = view.findViewById(R.id.average_rating_start_display);
        totalRating = view.findViewById(R.id.total_rating_txt);

        final float[] avgRating = {0};
        final float[] ratingPercent = {0, 0, 0, 0, 0};
        final int[] ratingNumbers = {0, 0, 0, 0, 0};
        final View[] raterDisplay = {view.findViewById(R.id.rater_display_1), view.findViewById(R.id.rater_display_2), view.findViewById(R.id.rater_display_3), view.findViewById(R.id.rater_display_4), view.findViewById(R.id.rater_display_5)};

        ListView lv = view.findViewById(R.id.feedbacks_list_view);
        List<Feedback> feedbacks = new ArrayList<>();
        FeedbackCustomAdapter feedbackCustomAdapter = new FeedbackCustomAdapter(getContext(), feedbacks);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference("feedbacks");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot child : snapshot.getChildren()) {
                    Feedback feedback = child.getValue(Feedback.class);
                    if (feedback != null) {
                        feedbacks.add(feedback);
                    }
                }

                if (feedbacks.isEmpty()) {
                    view.findViewById(R.id.no_text_img).setVisibility(View.VISIBLE);
                } else {
                    view.findViewById(R.id.no_text_img).setVisibility(View.GONE);
                    view.findViewById(R.id.review_page_container).setVisibility(View.VISIBLE);

                    for (Feedback fb : feedbacks) {
                        avgRating[0] += fb.getRating();
//                        Log.d("DDD", String.valueOf(fb.getRating()));
                        ratingNumbers[fb.getRating() - 1] += 1;
                    }

                    for (int i = 0; i < 5; i++) {
                        ratingPercent[i] = ratingNumbers[i] / feedbacks.size() * 100f;
                        int ratSize = (int) (((double) ratingNumbers[i] / feedbacks.size()) * 350);
                        Transition transition = new AutoTransition();

                        transition.setDuration(1500);

                        TransitionManager.beginDelayedTransition((ViewGroup) raterDisplay[i].getParent(), transition);
                        TransitionManager.beginDelayedTransition((ViewGroup) averageRating.getParent(), transition);
                        TransitionManager.beginDelayedTransition((ViewGroup) totalRating.getParent(), transition);
                        TransitionManager.beginDelayedTransition((ViewGroup) averageRatingImg.getParent(), transition);

                        if (ratSize < 20) {
                            raterDisplay[i].getLayoutParams().width = 20;
                        } else {
                            raterDisplay[i].getLayoutParams().width = ratSize;
                        }
                        raterDisplay[i].requestLayout();
                    }


                    feedbackCustomAdapter.notifyDataSetChanged();

                    avgRating[0] = avgRating[0] / feedbacks.size();
                    averageRating.setText(String.format("%.1f", avgRating[0]));
                    totalRating.setText(feedbacks.size() + " Reviews");
                    int avgImg = R.drawable.star_img_1;
                    avgImg = (((int) avgRating[0]) < 2) ? R.drawable.star_img_1 : (((int) avgRating[0]) < 3) ? R.drawable.star_img_2 : (((int) avgRating[0]) < 4) ? R.drawable.star_img_3 : (((int) avgRating[0]) < 5) ? R.drawable.star_img_4 : R.drawable.star_img_5;

                    averageRatingImg.setImageResource(avgImg);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addFeedbackBtn.setOnClickListener(v -> {
            custom_dialog_box_1.showDialog(getContext(), new custom_dialog_box_1.CustomDialogListener() {
                @Override
                public void onPositiveClicked(String text1, String text2) {
                }

                @Override
                public void onNegativeClicked() {

                }
            });
        });


        lv.setDivider(null);
        lv.setDividerHeight(25);
        lv.setAdapter(feedbackCustomAdapter);


        return view;
    }
}
