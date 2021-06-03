package com.moutamid.mapsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context context = MainActivity.this;

    // YE LOCATION ACCESS KRNE KA OBJECT HE
    private FusedLocationProviderClient fusedLocationProviderClient;

    AppCompatButton submitButton, viewButton;
    TextInputEditText nameEditText, dateEditText;
    RelativeLayout locationLayout;
    TextView locationTextView;
    String nameString, dateTimeString;
    double latitude, longitude;
    private ProgressDialog progressDialog;

    // YE ONLINE DATABASE KA LINK HE YAHAN PAR SARA DATA STORE HOGA JO KAY BAD MEN MAPS PAR SHOW KIA JAEGA
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    boolean isLocationget = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        submitButton = findViewById(R.id.submitBtn);
        viewButton = findViewById(R.id.viewSubmissionsBtn);
        nameEditText = findViewById(R.id.name_edittext);
        dateEditText = findViewById(R.id.date_edittext);
        locationLayout = findViewById(R.id.location_layout);
        locationTextView = findViewById(R.id.location_textview);

        dateEditText.setText(getTime());

        locationLayoutListener();

        submitButtonListener();

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permissions are not granted!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.show();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);

        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();

        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    //We have a location
//                    Log.d(TAG, "onSuccess: " + location.toString());
                    Log.d(TAG, "onSuccess: startLocation: " + location.getLatitude());
                    Log.d(TAG, "onSuccess: startLocation: " + location.getLongitude());

                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    locationTextView.setText(String.valueOf(
                            latitude + "," + longitude
                    ));
                    isLocationget = true;
//                    startLocationChecker();
                } else {
                    Toast.makeText(MainActivity.this, "Location is null", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onSuccess: Location was null...");
                }
                progressDialog.dismiss();
            }
        });
        locationTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "onFailure: " + e.getLocalizedMessage());
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void locationLayoutListener() {
        locationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // LOCATION ACCESS KRNE K LIE PEHLE USER SE PERMISSION LENI HOGI
                Dexter.withContext(context)
                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Dexter.withContext(context)
                                        .withPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                                        .withListener(new PermissionListener() {
                                            @Override
                                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                                // PERMISSION MILNE PAR LOCATION GET HOGI
                                                getLastLocation();

                                            }

                                            @Override
                                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                                if (response.isPermanentlyDenied()) {
                                                    // open device settings when the permission is
                                                    // denied permanently
                                                    Toast.makeText(context, "You need to provide permission!", Toast.LENGTH_SHORT).show();

                                                    Intent intent = new Intent();
                                                    intent.setAction(
                                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                    Uri uri = Uri.fromParts("package",
                                                            BuildConfig.APPLICATION_ID, null);
                                                    intent.setData(uri);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(intent);
                                                }
                                            }

                                            @Override
                                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                                token.continuePermissionRequest();
                                            }
                                        }).check();

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if (response.isPermanentlyDenied()) {
                                    // open device settings when the permission is
                                    // denied permanently
                                    Toast.makeText(context, "You need to provide permission!", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent();
                                    intent.setAction(
                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package",
                                            BuildConfig.APPLICATION_ID, null);
                                    intent.setData(uri);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();

            }
        });
    }

    private void submitButtonListener() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameString = nameEditText.getText().toString();
                dateTimeString = dateEditText.getText().toString();

                if (nameString.isEmpty() || nameString == null) {
                    Toast.makeText(context, "Please enter a name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (dateTimeString.isEmpty() || dateTimeString == null) {
                    Toast.makeText(context, "Please enter date!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isLocationget) {
                    Toast.makeText(context, "Please enter your location!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.show();

                LocationModel model = new LocationModel(nameString, dateTimeString, latitude, longitude);

                databaseReference.child("locations").push()
                        .setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            nameEditText.setText("");
                            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public String getTime() {

        try {

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return sdf.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error";

    }

    private static class LocationModel {

        private String name, dateTime;
        private double latitude, longitude;

        public LocationModel(String name, String dateTime, double latitude, double longitude) {
            this.name = name;
            this.dateTime = dateTime;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        LocationModel() {
        }
    }

}