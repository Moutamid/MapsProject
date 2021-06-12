package com.moutamid.mapsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddEmergencyActivity extends AppCompatActivity {
    private static final String TAG = "AddEmergencyActivity";
    private Context context = AddEmergencyActivity.this;
    private Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emergency);

        EditText number1Et = findViewById(R.id.number1_edittext);
        EditText number2Et = findViewById(R.id.number2_edittext);
        EditText number3Et = findViewById(R.id.number3_edittext);

        findViewById(R.id.submitBtn_emergencyNumber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number1 = number1Et.getText().toString();
                String number2 = number2Et.getText().toString();
                String number3 = number3Et.getText().toString();

                if (number1.isEmpty() && number2.isEmpty() && number3.isEmpty()) {
                    Toast.makeText(AddEmergencyActivity.this, "Please add at least 1 Emergency number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!number1.isEmpty()) {
                    utils.storeString(context, "number1", number1);
                }
                if (!number2.isEmpty()) {
                    utils.storeString(context, "number1", number2);
                }
                if (!number3.isEmpty()) {
                    utils.storeString(context, "number1", number3);
                }

                utils.storeBoolean(context, "emer", true);

                finish();

            }
        });

    }
}