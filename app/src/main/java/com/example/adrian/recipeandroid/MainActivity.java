package com.example.adrian.recipeandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button botonOk;
    String typeOfFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonOk = (Button) findViewById(R.id.botonOk);
        RadioGroup radioGroupType= (RadioGroup) findViewById(R.id.radioGroupType);


        botonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextName = (EditText) findViewById(R.id.editTextName);
                String name=editTextName.getText().toString();
                Intent intent =new Intent(getApplicationContext(), WelcomeActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("type",typeOfFood);
                startActivity(intent);
            }
        });

        radioGroupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.radioButtonDish:
                        typeOfFood="Platos";
                        break;
                    case R.id.radioButtonDessert:
                        typeOfFood="Postres";
                        break;
                }
            }
        });

        CheckBox onePerson=(CheckBox) findViewById(R.id.checkBoxQuantity1);
        CheckBox twoPerson=(CheckBox) findViewById(R.id.checkBoxQuantity2);
        CheckBox treePerson=(CheckBox) findViewById(R.id.checkBoxQuantity3);

        onePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Ha seleccionado recetas para una persona", Toast.LENGTH_SHORT).show();
            }
        });

        twoPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Ha seleccionado recetas para dos personas", Toast.LENGTH_SHORT).show();

            }
        });

        treePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Ha seleccionado recetas para tres o mas personas", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
