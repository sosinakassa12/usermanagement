package com.example.ums;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    SharedPreferenceConfig sharedPreferenceConfig;
    DatabaseHelper helper;
    EditText user_nameFilled, passwordFilled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_nameFilled = findViewById(R.id.user_name);
        passwordFilled = findViewById(R.id.password);
        helper = new DatabaseHelper(this);
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        if (sharedPreferenceConfig.readFromPreference()) {
            startActivity(new Intent(this, UserList.class));
            finish();
        }

    }

    public void login(View view) {

        String userName = user_nameFilled.getText().toString();
        String password = passwordFilled.getText().toString();

        if(!userName.isEmpty() && !password.isEmpty()) {
            if (helper.is_vallide(userName, password)) {
                Toast.makeText(this, "Succesfully Login", Toast.LENGTH_LONG).show();
                sharedPreferenceConfig.writeToPreference(true,userName);//saving true if Login successfully

                startActivity(new Intent(this, UserList.class));
                Log.e("logg",sharedPreferenceConfig.readUserFromPreference());
                finish();


            } else {


                Toast.makeText(this, "Invallid U Name or password..try..again", Toast.LENGTH_LONG).show();
                user_nameFilled.setText("");
                passwordFilled.setText("");
            }
        }else{
            Toast.makeText(this, "Field Required", Toast.LENGTH_LONG).show();

        }
    }
        public void create (View view){

            startActivity(new Intent(this, Register.class));
        }

}
