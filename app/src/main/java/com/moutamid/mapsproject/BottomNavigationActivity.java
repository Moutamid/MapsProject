package com.moutamid.mapsproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class BottomNavigationActivity extends AppCompatActivity {
    private static final String TAG = "BottomNavigationActivit";

    private ImageView profileTabBtn, homeTabBtn, settingsTabBtn;
    private View homeViewLine, profileViewLine, settingsViewLine;

    private ImageView currentImageView;
    private View currentViewLine;

    private Utils utils = new Utils();

//    private HomeFragment homeFragment;
//    private ProfileFragment profileFragment;
//    private ChatFragment chatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        if (firebaseAuth.getCurrentUser() == null) {
//            finish();
//            Intent intent = new Intent(com.moutamid.tweetytheclone.BottomNavigationActivity.this, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            finish();
//            startActivity(intent);
//            return;
//        }

        profileTabBtn = findViewById(R.id.profile_tab_button_bottom_navigation);
        homeTabBtn = findViewById(R.id.home_tab_button_bottom_navigation);
        settingsTabBtn = findViewById(R.id.settings_tab_button_bottom_navigation);
        currentImageView = homeTabBtn;

        homeViewLine = findViewById(R.id.secline);
        profileViewLine = findViewById(R.id.firstline);
        settingsViewLine = findViewById(R.id.thirdline);
        currentViewLine = homeViewLine;

        profileTabBtn.setOnClickListener(profileTabBtnClickListener());
        homeTabBtn.setOnClickListener(homeTabBtnClickListener());
        settingsTabBtn.setOnClickListener(settingsTabBtnClickListener());

//        utils.storeString(BottomNavigationActivity.this, "current_fragment", "home");

        loadFragment(new HomeFragment());

//        if (new Utils().getStoredString(
//                BottomNavigationActivity.this, "usernameStr")
//                .equals("Error")
//                || new Utils().getStoredString(
//                BottomNavigationActivity.this, "profileUrl")
//                .equals("Error")
//
//        ) {
//        getUserdetails();
//        }

//        listenForProfileImageChanges();
    }

//    private void listenForProfileImageChanges() {
//
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//        databaseReference.child("users").child(mAuth.getCurrentUser().getUid())
//                .child("profileUrl").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                String url = snapshot.getValue(String.class);
//                CircleImageView circleImageView = findViewById(R.id.profile_image_view_bottom_navigation);
//                Glide.with(com.moutamid.tweetytheclone.BottomNavigationActivity.this)
//                        .load(url)
//                        .apply(new RequestOptions()
//                                .placeholder(R.color.grey)
//                                .error(R.color.grey)
//                        )
//                        .into(circleImageView);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.d(TAG, "onCancelled: "+error.getMessage());
//            }
//        });
//
//    }

//    private void getUserdetails() {
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//        databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid())
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        if (!snapshot.exists()) {
//                            return;
//                        }
//
//                        new Utils().storeString(com.moutamid.tweetytheclone.BottomNavigationActivity.this,
//                                "usernameStr", snapshot.child("name").getValue(String.class));
//
//                        new Utils().storeString(com.moutamid.tweetytheclone.BottomNavigationActivity.this,
//                                "profileUrl", snapshot.child("profileUrl").getValue(String.class));
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(com.moutamid.tweetytheclone.BottomNavigationActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }

    private View.OnClickListener settingsTabBtnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                currentImageView.setImageResource();
                currentViewLine.setVisibility(View.INVISIBLE);

//                currentImageView = settingsTabBtn;
                currentViewLine = settingsViewLine;

//                currentImageView.setImageResource(R.drawable.ic_outline_settings_24_selected);
                currentViewLine.setVisibility(View.VISIBLE);

                homeTabBtn.setImageResource(R.drawable.ic_outline_home_24_unselected);
                profileTabBtn.setImageResource(R.drawable.ic_baseline_person_outline_24_unselected);
                settingsTabBtn.setImageResource(R.drawable.ic_outline_settings_24_selected);
//                utils.storeString(com.moutamid.tweetytheclone.BottomNavigationActivity.this, "current_fragment", "message");

                loadFragment(new SettingsFragment());
            }
        };
    }

    private View.OnClickListener homeTabBtnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                currentImageView.setBackgroundColor(0);
                currentViewLine.setVisibility(View.INVISIBLE);

//                currentImageView = homeTabBtn;
                currentViewLine = homeViewLine;

//                currentLayout.setBackgroundColor(getResources().getColor(R.color.tabbtnsbg));
                currentViewLine.setVisibility(View.VISIBLE);
                homeTabBtn.setImageResource(R.drawable.ic_outline_home_24_selected);
                profileTabBtn.setImageResource(R.drawable.ic_baseline_person_outline_24_unselected);
                settingsTabBtn.setImageResource(R.drawable.ic_outline_settings_24_unselected);
//                utils.storeString(com.moutamid.tweetytheclone.BottomNavigationActivity.this, "current_fragment", "home");

                loadFragment(new HomeFragment());
            }
        };
    }

    private View.OnClickListener profileTabBtnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                currentImageView.setBackgroundColor(0);
                currentViewLine.setVisibility(View.INVISIBLE);
//
//                currentImageView = profileTabBtn;
                currentViewLine = profileViewLine;

//                currentLayout.setBackgroundColor(getResources().getColor(R.color.tabbtnsbg));
                currentViewLine.setVisibility(View.VISIBLE);
                homeTabBtn.setImageResource(R.drawable.ic_outline_home_24_unselected);
                profileTabBtn.setImageResource(R.drawable.ic_person_outline_24_selected);
                settingsTabBtn.setImageResource(R.drawable.ic_outline_settings_24_unselected);
//                utils.storeString(com.moutamid.tweetytheclone.BottomNavigationActivity.this, "current_fragment", "profile");

                loadFragment(new ProfileFragment());

            }
        };
    }

    private void loadFragment(Fragment fragment) {

        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

        } else {
            Toast.makeText(this, "Fragment is null!", Toast.LENGTH_SHORT).show();
        }

    }

}