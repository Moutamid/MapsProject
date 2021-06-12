package com.moutamid.mapsproject;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private View rootView;
    private Utils utils = new Utils();
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
//    private LinearLayout liveLocationLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        TextView nameTextView = rootView.findViewById(R.id.name_text_view_home);
        nameTextView.setText(utils.getStoredString(getActivity(), "usernameStr"));

        setLiveLocationBtn();

        setReportsBtn();

        setHideBtn();

        setAlertBtn();

        return rootView;
    }

    private void setReportsBtn() {
        rootView.findViewById(R.id.reports_layout_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(getActivity(), view);
                popupMenu.getMenuInflater().inflate(
                        R.menu.report_pop_up_options,
                        popupMenu.getMenu()
                );
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.new_report) {
                            startActivity(new Intent(getActivity(), SubmitReportsActivity.class));
                        }

                        if (menuItem.getItemId() == R.id.view_submissions) {
                            startActivity(new Intent(getActivity(), ViewSubmissionsActivity.class));
                        }
                        return true;
                    }
                });
                popupMenu.show();

            }
        });

        rootView.findViewById(R.id.view_reports_layout_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MapsActivity.class));

            }
        });
    }

    private ProgressDialog progressDialog;

    private void setAlertBtn() {

        rootView.findViewById(R.id.alert_btn_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getActivity();
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
                                                shareLocationAndMsg();
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

    private void shareLocationAndMsg() {
        Context context = getActivity();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(500);
        locationRequest.setFastestInterval(500);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context, "Permission not granted!", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.show();
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                ArrayList<String> nums = new ArrayList<String>();

                if (!utils.getStoredString(context, "number1").equals("Error"))
                    nums.add(utils.getStoredString(context, "number1"));

                if (!utils.getStoredString(context, "number2").equals("Error"))
                    nums.add(utils.getStoredString(context, "number2"));

                if (!utils.getStoredString(context, "number3").equals("Error"))
                    nums.add(utils.getStoredString(context, "number3"));

                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setDataAndType(Uri.parse("smsto:"),
                        "vnd.android-dir/mms-sms");
//                smsIntent.setData().setType();
                smsIntent.putExtra("address", nums);

                String smsBody = "Please help me! I'm in trouble. I'm here, " +
                        "http://maps.google.com?q=" + location.getLatitude() +
                        "," + location.getLongitude();

                smsIntent.putExtra("sms_body", smsBody);

                try {
                    startActivity(smsIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context,
                            "SMS failed, please try again later.", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }
        });

        locationTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setHideBtn() {
        rootView.findViewById(R.id.hide_layout_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getActivity();

                Dexter.withContext(context)
                        .withPermission(Manifest.permission.PROCESS_OUTGOING_CALLS)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                startActivity(new Intent(getActivity(), HideAppActivity.class));

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

    private void setLiveLocationBtn() {
        rootView.findViewById(R.id.live_location_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getActivity();
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

                                                startActivity(new Intent(getActivity(), MapsActivity.class).putExtra("live", true));


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


}