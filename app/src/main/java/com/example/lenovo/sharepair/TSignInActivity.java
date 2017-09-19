package com.example.lenovo.sharepair;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TSignInActivity extends AppCompatActivity {
    SharedPreferences sp;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tsign_in);
        sp=getSharedPreferences("User",MODE_PRIVATE);
        user=sp.getString("user",null);
    }
}
