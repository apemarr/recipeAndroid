package com.example.adrian.recipeandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showWelcome();
        showMessages();
        Button buttonValidate = (Button) findViewById(R.id.buttonValidate);


        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void showWelcome() {
        String name=getIntent().getExtras().getString("name");
        TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText("Bienvenido "+name);
    }

    private void showMessages(){
       String type=getIntent().getExtras().getString("type");
       TextView editTextType=(TextView) findViewById(R.id.textViewTypeOfFood);
       editTextType.setText(type);
    }

    private void validate(){
        EditText editTextTypeOfFood = (EditText) findViewById(R.id.editTextTypeOfFood);
        EditText editTextNumberOfPersons = (EditText) findViewById(R.id.editTextNumberOfPersons);

        editTextTypeOfFood.setError(null);
       editTextNumberOfPersons.setError(null);

        String typeOfFood=editTextTypeOfFood.getText().toString();
        String numberOfPersons=editTextNumberOfPersons.getText().toString();

        if(TextUtils.isEmpty(typeOfFood)){
            editTextTypeOfFood.setError(getString(R.string.empty_field));
            editTextTypeOfFood.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(numberOfPersons)){
            editTextNumberOfPersons.setError(getString(R.string.empty_field));
            editTextNumberOfPersons.requestFocus();
            return;
        }

        int numberOfPersonsToInt=Integer.parseInt(numberOfPersons);

        if(numberOfPersonsToInt<1 || numberOfPersonsToInt>10){
            editTextNumberOfPersons.setError(getString(R.string.between_1_and_10));
            editTextNumberOfPersons.requestFocus();
            return;
        }

        Toast.makeText(getApplicationContext(),"Se ha validado correctamente",Toast.LENGTH_SHORT).show();
    }

}
