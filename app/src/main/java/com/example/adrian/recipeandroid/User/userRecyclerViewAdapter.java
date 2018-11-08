package com.example.adrian.recipeandroid.User;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.adrian.recipeandroid.R;
import com.example.adrian.recipeandroid.User.data.UserContract;


public class userRecyclerViewAdapter extends RecyclerView.Adapter<userRecyclerViewAdapter.ViewHolder> {

   Cursor cursor;

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public userRecyclerViewAdapter() {

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
        holder.mName.setText(name);
        holder.mEmail.setText(cursor.getString(cursor.getColumnIndex(UserContract.EMAIL)));

        ColorGenerator generator=ColorGenerator.MATERIAL;
        TextDrawable drawable=TextDrawable.builder().buildRound(
                name.substring(0,1),
                generator.getColor(name));
        holder.miImageView.setImageDrawable(drawable);
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

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = view.findViewById(R.id.textViewUsername);
            mEmail = view.findViewById(R.id.textViewEmail);
            miImageView=view.findViewById(R.id.imageView5);
        }

    }
}
