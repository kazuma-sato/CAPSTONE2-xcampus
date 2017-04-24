package com.app.capstone.xcampus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;
import com.amazonaws.regions.Regions;
import com.app.capstone.xcampus.activities.MainActivity;
import com.app.capstone.xcampus.dataLayer.Services;


public class RegisterActivity extends AppCompatActivity {

    private Context context;

    private EditText emailInput, passwordInput;

    private Button register;
    private TextView toLogin;

    private Regions regions = Regions.DEFAULT_REGION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        context = this;



        Services.getAllUsers(this);

        emailInput = (EditText)findViewById(R.id.input_email);
        passwordInput = (EditText)findViewById(R.id.input_password);

        register = (Button) findViewById(R.id.button_register);
        toLogin = (TextView) findViewById(R.id.label_button_toLogin);

        Services.getUserFavourites(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity( new Intent( context, MainActivity.class));
                //Services.getEntries(context, toLogin);

                if( emailInput.getText().toString().length() < 0 && passwordInput.getText().toString().length() < 0 ) {
                    Toast.makeText(context, "Please make sure all fields are filled in!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Services.AccountManeger.saveUser(context, emailInput.getText().toString() , "asdfasdfsd", "adasdfasdf" , "dfSADFASDFSD");
                    startActivity(new Intent(context , MainActivity.class));
                }

                /*
                ClientConfiguration clientConfiguration = new ClientConfiguration();

                CognitoUserPool userPool = new CognitoUserPool(context, "us-west-2_9SoJmSN74", "15lmvchrkih1hs0p25mvf7f1i7", "t4dsrr4j4bepie9bcut46ltq05qupp6972l7d8ksce3onnmhstu", clientConfiguration);

                CognitoUserAttributes userAttributes = new CognitoUserAttributes();

                userAttributes.addAttribute("email", emailInput.getText().toString());
                userAttributes.addAttribute("name",emailInput.getText().toString());
                userAttributes.addAttribute("preferred_username", emailInput.getText().toString());
                userAttributes.addAttribute("given_name", emailInput.getText().toString());

                userPool.signUpInBackground(emailInput.getText().toString(), passwordInput.getText().toString(), userAttributes, null, handler);


                //Services.getUserFavourites(context);
                Services.getAllUsers(context);
                */
            }
        });

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( context, LoginActivity.class));
                //finish();
                //Services.getEntries(context, toLogin);
                //startActivity(new Intent(context , MainActivity.class));
            }
        });




    }

    SignUpHandler handler = new SignUpHandler() {

        @Override
        public void onSuccess(CognitoUser user, boolean signUpConfirmationState, CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
            Log.d("test Result" ,user.toString());
        }

        @Override
        public void onFailure(Exception exception) {
            // Sign up failed, code check the exception for cause and perform remedial actions.
            Log.d("fail result", exception.toString());
        }
    };


    // test register
    public void register(){

        CognitoUserPool userPool = new CognitoUserPool(context, "us-west-2_9SoJmSN74", "15lmvchrkih1hs0p25mvf7f1i7", "t4dsrr4j4bepie9bcut46ltq05qupp6972l7d8ksce3onnmhstu");

        CognitoUserAttributes userAttributes = new CognitoUserAttributes();

        userAttributes.addAttribute("email", emailInput.getText().toString());

        userAttributes.addAttribute("password", passwordInput.getText().toString());

        userPool.signUpInBackground(emailInput.getText().toString(), passwordInput.getText().toString(), userAttributes, null, handler);

    }


}
