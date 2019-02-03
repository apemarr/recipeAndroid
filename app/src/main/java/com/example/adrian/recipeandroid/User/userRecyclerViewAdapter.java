package com.example.adrian.recipeandroid.User;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.adrian.recipeandroid.R;
import com.example.adrian.recipeandroid.User.data.UserContract;
import com.example.adrian.recipeandroid.constants.Utilidades;

import java.io.IOException;


public class userRecyclerViewAdapter extends RecyclerView.Adapter<userRecyclerViewAdapter.ViewHolder>
    implements View.OnLongClickListener {

   Cursor cursor;
    OnItemLongClick listener;


    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public boolean onLongClick(View v) {
        Log.i("tiburcio", "long click");
        listener.onItemLongClick(v);
        return true;
    }

    public interface OnItemLongClick{
        boolean onItemLongClick(View v);
    }

    public userRecyclerViewAdapter(OnItemLongClick listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        String name=cursor.getString(cursor.getColumnIndex(UserContract.NAME));
        int ID =cursor.getInt(cursor.getColumnIndex(UserContract._ID));
        holder.mName.setText(name);
        holder.mEmail.setText(cursor.getString(cursor.getColumnIndex(UserContract.EMAIL)));


    try{
        Utilidades.loadImageFromStorage(holder.mContext,"img_"+ID+".jpg",holder.miImageView);
    }catch(IOException e){

        ColorGenerator generator=ColorGenerator.MATERIAL;
        TextDrawable drawable=TextDrawable.builder().buildRound(
                name.substring(0,1),
                generator.getColor(name));
        holder.miImageView.setImageDrawable(drawable);
    }
        holder.mView.setTag(ID);
        holder.mView.setOnLongClickListener(this);
    }

    @Override
    public int getItemCount() {
        if(cursor==null) return 0;
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mName;
        public final TextView mEmail;
        public final ImageView miImageView;
        public final String mID;
        public final Context mContext;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = view.findViewById(R.id.textViewUsername);
            mEmail = view.findViewById(R.id.textViewEmail);
            miImageView=view.findViewById(R.id.imageView5);
            mID = UserContract._ID;
            mContext=view.getContext();
        }

    }
}
