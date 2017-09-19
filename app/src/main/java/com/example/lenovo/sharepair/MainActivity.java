package com.example.lenovo.sharepair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2;
    String uname,pass;
    SharedPreferences sp;
    DatabaseReference root= FirebaseDatabase.getInstance().getReference();
    DatabaseReference child=root.child("users").getRef();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.editTextUser);
        ed2=(EditText)findViewById(R.id.editTextUPass);
        sp=getSharedPreferences("User",MODE_PRIVATE);
    }
    public void signin(View v){
        uname=ed1.getText().toString();
        pass=ed2.getText().toString();
        child.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(uname)){
                    String p=dataSnapshot.child(uname).child("pass").getValue(String.class);
                    if (p.equals(pass)){
                        String pl;
                        pl=dataSnapshot.child(uname).child("pl").getValue(String.class);
                        sp.edit().putString("user",uname).commit();
                        if(pl.equalsIgnoreCase("Student")){
                            Toast.makeText(MainActivity.this, "Welcome! Signed in as student.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,SSignInActivity.class));
                        }else{
                            Toast.makeText(MainActivity.this, "Welcome! Signed in as Teacher.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,TSignInActivity.class));
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "wrong Password entered.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "User doesn't exist.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void ssignup(View v){
        startActivity(new Intent(this,SSignUpActivity.class));
    }
}
