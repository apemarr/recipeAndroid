package com.example.adrian.recipeandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adrian.recipeandroid.User.data.UserContract;
import com.example.adrian.recipeandroid.constants.G;
import com.example.adrian.recipeandroid.constants.Utilidades;
import com.example.adrian.recipeandroid.pojos.User;
import com.example.adrian.recipeandroid.provider.UserProvider;

import java.io.FileNotFoundException;

public class UserUpdateActivity extends AppCompatActivity {
    int userId;
    final int PETICION_CAPTURAR_IMAGEN = 1;
    final int PETICION_ESCOGER_IMAGEN_DE_GALERIA = 2;
    ImageView imageViewPhoto;
    Bitmap foto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        EditText editTextUsername=findViewById(R.id.editTextUsername);
        EditText editTextEmail=findViewById(R.id.editTextEmail);

        imageViewPhoto=findViewById(R.id.imageViewPhoto);
        final ImageButton imageButtonPhoto=findViewById(R.id.imageButtonPhoto);
        final ImageButton imageButtonGaleria=findViewById(R.id.imageButtonGaleria);

        userId=this.getIntent().getExtras().getInt(UserContract._ID);
        User user=UserProvider.readRecord(getContentResolver(),userId);
        Log.i("Username------->",user.getName());
        Log.i("Useremail------->",user.getEmail());
        editTextUsername.setText(user.getName());
        editTextEmail.setText(user.getEmail());
        try{
            Utilidades.loadImageFromStorage(this,"img_"+userId+".jpg",imageViewPhoto);
            foto=((BitmapDrawable) imageViewPhoto.getDrawable()).getBitmap();
        }catch(FileNotFoundException e){
            Toast.makeText(this,"No existe la imagen para este usuario", Toast.LENGTH_LONG).show();
        }


        imageButtonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sacarFoto();
            }
        });

        imageButtonGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elegirFotoDeGaleria();
            }
        });
    }

    void sacarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, PETICION_CAPTURAR_IMAGEN);
        }
    }

    void elegirFotoDeGaleria(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, PETICION_ESCOGER_IMAGEN_DE_GALERIA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case PETICION_CAPTURAR_IMAGEN:
                if(resultCode == RESULT_OK){
                    foto = (Bitmap) data.getExtras().get("data");
                    imageViewPhoto.setImageBitmap(foto);
                } else {
                    //El usuario canceló la toma de la foto
                }
                break;
            case PETICION_ESCOGER_IMAGEN_DE_GALERIA:
                if(resultCode == RESULT_OK){
                    imageViewPhoto.setImageURI(data.getData());
                    foto = ((BitmapDrawable) imageViewPhoto.getDrawable()).getBitmap();

                } else {
                    //El usuario canceló la toma de la foto
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
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
        User user=new User(userId,username,email,foto);
        UserProvider.update(getContentResolver(),user,this);
        finish();
    }
}
