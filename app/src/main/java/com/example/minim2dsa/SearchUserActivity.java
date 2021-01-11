package com.example.minim2dsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchUserActivity extends AppCompatActivity {

    Button button;
    Handler handler;
    String user;

    public String getUser(View v){
        EditText usernameContainer;
        usernameContainer = (EditText)findViewById(R.id.editTextTextPersonName);
        return usernameContainer.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        openMainActivity();
    }
    public void openMainActivity(){
        Intent main = new Intent(this, MainActivity.class);
        main.putExtra("username",this.user);
        startActivity(main);
    }
}