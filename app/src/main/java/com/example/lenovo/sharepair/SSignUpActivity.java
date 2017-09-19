package com.example.lenovo.sharepair;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SSignUpActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5,ed6;
    String name,roll,year,username,pass;
    DatabaseReference root= FirebaseDatabase.getInstance().getReference();
    DatabaseReference child=root.child("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssign_up);
        ed1=(EditText)findViewById(R.id.editTextSname);
        ed2=(EditText)findViewById(R.id.editTextSRoll);
        ed3=(EditText)findViewById(R.id.editTextSyear);
        ed4=(EditText)findViewById(R.id.editTextSusername);
        ed5=(EditText)findViewById(R.id.editTextSPassword);
        ed6=(EditText)findViewById(R.id.editTextSpass);
    }
    public void signup(View v){
        name=ed1.getText().toString();
        roll=ed2.getText().toString();
        year=ed3.getText().toString();
        username=ed4.getText().toString();
        pass=ed5.getText().toString();
        if(name.equals("")||roll.equals("")||year.equals("")||username.equals("")||pass.equals("")||ed6.getText().toString().equals("")){
            Toast.makeText(this, "Fields empty", Toast.LENGTH_SHORT).show();
        }else{
            if (pass.equals(ed6.getText().toString())){
                child.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(username)){
                            Toast.makeText(SSignUpActivity.this, "Username already taken.", Toast.LENGTH_SHORT).show();
                        }else{
                            Student s=new Student(name,roll,year,username,pass,"student");
                            child.child(username).setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SSignUpActivity.this, "Signed up succesfully!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else{
                                        Toast.makeText(SSignUpActivity.this, "Error signing up!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }else {
                Toast.makeText(this, "Passwords don't match.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
