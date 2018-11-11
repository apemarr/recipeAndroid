package com.example.adrian.recipeandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.adrian.recipeandroid.User.data.UserContract;
import com.example.adrian.recipeandroid.constants.G;
import com.example.adrian.recipeandroid.pojos.User;
import com.example.adrian.recipeandroid.provider.UserProvider;

public class UserUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        EditText editTextUsername=findViewById(R.id.editTextUsername);
        EditText editTextEmail=findViewById(R.id.editTextEmail);

        int userId=this.getIntent().getExtras().getInt(UserContract._ID);
        User user=UserProvider.readRecord(getContentResolver(),userId);
        Log.i("Username------->",user.getName());
        Log.i("Useremail------->",user.getEmail());
        editTextUsername.setText(user.getName());
        editTextEmail.setText(user.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem=menu.add(Menu.NONE, G.GUARDAR,Menu.NONE,"Guardar");
        menuItem.setShowAsAction(menuItem.SHOW_AS_ACTION_ALWAYS);
        menuItem.setIcon(R.drawable.ic_action_save);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case G.GUARDAR:
               attempSave();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void attempSave() {
        EditText editTextUsername=findViewById(R.id.editTextUsername);
        EditText editTextEmail=findViewById(R.id.editTextEmail);

        editTextUsername.setError(null);
        editTextEmail.setError(null);

        String username=String.valueOf(editTextUsername.getText());
        String email=String.valueOf(editTextEmail.getText());

        if(TextUtils.isEmpty(username)){
            editTextUsername.setError(getString(R.string.empty_field));
            editTextUsername.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(email)){
            editTextEmail.setError(getString(R.string.empty_field));
            editTextEmail.requestFocus();
            return;
        }
        User user=new User(G.SIN_VALOR,username,email);
        UserProvider.insert(getContentResolver(),user);
        finish();
    }
}
