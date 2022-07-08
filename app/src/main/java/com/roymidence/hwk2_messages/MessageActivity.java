package com.roymidence.hwk2_messages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MessageActivity extends AppCompatActivity {
    private FirebaseFirestore db;

    private Button btnGreeting;
    private Button btnGoodbye;
    private  Button btnShowCity;
    private EditText editTextPersonName;
    private EditText editTextCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        btnGoodbye = findViewById(R.id.btnGoodbye);
        btnGreeting = findViewById(R.id.btnGreeting);
        btnShowCity = findViewById(R.id.btnShowCity);
        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextCity = findViewById(R.id.editTextCity);

        // When you click the greeting button
        btnGreeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),getString(R.string.greeting_button_event), Toast.LENGTH_SHORT).show();
            }
        });// End of Click

        // When you click the goodbye button
        btnGoodbye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),getString(R.string.goodbye_button_event), Toast.LENGTH_SHORT).show();
            }
        }); // End of Click

        // When you click the Show City button
        btnShowCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize FirebaseFirestore
                db = FirebaseFirestore.getInstance();
                String name = editTextPersonName.getText().toString();

                db.collection("Hwk2Addresses")
                        .whereEqualTo("name", name)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        editTextCity.setText(document.getString("city"));}
                                } else {
                                    Log.w("MYDEBUG", "Error getting documents.", task.getException());
                                }
                            }
                        });// End of onComplete
            }
        }); // End of On Click

    }
}