package com.ns.bluetoothchatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.le.AdvertiseData;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class signUp extends AppCompatActivity {
EditText Name, Address, Phone, Email, Password;
Button btnSignUp, btnSignIn;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +          //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Name = findViewById(R.id.editTextTextPersonName);
        Address =  findViewById(R.id.editTextAdd);
        Phone = findViewById(R.id.editTextPhone);
        Email = findViewById(R.id.editTextEmail);
        Password = findViewById(R.id.editPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateAddress() && validateEmail() && validatePassword()) {
                    Intent i = new Intent(signUp.this, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(signUp.this, "SIGN UP EXECUTED", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(signUp.this, "Fill Entries Properly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validateEmail(){
       String emailInput = Email.getEditableText().toString().trim();
       if(emailInput.isEmpty()){
           Email.setError("Field can't be empty");
           return false;
       }
       else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
           Email.setError("Please enter a valid email address");
           return false;
       }
       else{
           Email.setError(null);
           return true;
       }

    }
    private boolean validatePassword() {
        String passwordInput = Password.getEditableText().toString().trim();

        if (passwordInput.isEmpty()) {
            Password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            Password.setError("Password too weak");
            return false;
        } else {
            Password.setError(null);
            return true;
        }
    }
    private boolean validateUsername() {
        String usernameInput = Name.getEditableText().toString().trim();

        if (usernameInput.isEmpty()) {
            Name.setError("Field can't be empty");
            return false;
        }  else {
           Name.setError(null);
            return true;
        }
    }
    private boolean validateAddress() {
        String usernameInput = Address.getEditableText().toString().trim();

        if (usernameInput.isEmpty()) {
            Address.setError("Field can't be empty");
            return false;
        }
         else {
            Address.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }

        String input = "Email: " + Email.getEditableText().toString();
        input += "\n";
        input += "Username: " + Name.getEditableText().toString();
        input += "\n";
        input += "Password: " + Password.getEditableText().toString();
        input += "\n";
        input += "Password: " + Address.getEditableText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}


