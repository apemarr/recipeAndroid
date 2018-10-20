package com.example.adrian.recipeandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.w3c.dom.Text;

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
                editTextName.setError(null);
                String name=editTextName.getText().toString();
                if(TextUtils.isEmpty(name)){
                    editTextName.setError(getString(R.string.empty_field));
                    editTextName.requestFocus();
                    return;
                }
                if(!name.matches("[a-zA-Z ]+")){
                    editTextName.setError(getString(R.string.not_numeric_field));
                    editTextName.requestFocus();
                    return;
                }
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_help:
                Toast.makeText(getApplicationContext(),"Ha pulsado la ayuda",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
