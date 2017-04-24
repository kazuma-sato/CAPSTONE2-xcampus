package com.app.capstone.xcampus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.capstone.xcampus.activities.MainActivity;
import com.app.capstone.xcampus.dataLayer.Services;

public class LoginActivity extends AppCompatActivity {

    private Context context;

    private EditText emailInput, passwordInput;

    private Button loginButton;
    private TextView toRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        emailInput = (EditText) findViewById(R.id.input_email);
        passwordInput = (EditText) findViewById(R.id.input_password);

        loginButton = (Button)findViewById(R.id.button_login);
        toRegisterButton = (TextView)findViewById(R.id.label_button_toRegister);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( emailInput.getText().toString().length() < 0) {
                    Toast.makeText(context, "Please make sure all fields are filled in!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Services.AccountManeger.saveUser(context, emailInput.getText().toString() , "asdfasdfsd", "adasdfasdf" , "dfSADFASDFSD");
                    startActivity(new Intent(context , MainActivity.class));
                }

            }
        });

        toRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent( context , RegisterActivity.class )
                );
                finish();
            }
        });


    }
}
