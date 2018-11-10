package com.example.adrian.recipeandroid.User;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.adrian.recipeandroid.R;
import com.example.adrian.recipeandroid.User.data.UserContract;
import com.example.adrian.recipeandroid.UserDetailActivity;
import com.example.adrian.recipeandroid.constants.G;
import com.example.adrian.recipeandroid.provider.UserProvider;


public class userFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private userRecyclerViewAdapter mAdapter;
    ActionMode mActionMode;
    View viewSeleccionado;
    public userFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0,null,this);


    }
ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater=mode.getMenuInflater();
        inflater.inflate(R.menu.contextual_menu,menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_delete:
                int userid=(Integer) viewSeleccionado.getTag();
                UserProvider.delete(getActivity().getContentResolver(),userid);
                break;
            case R.id.menu_edit:
                break;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mActionMode=null;
    }
};
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem menuItem = menu.add(Menu.NONE,G.INSERTAR,Menu.NONE,"Insertar");
       menuItem.setIcon(R.drawable.ic_action_add);
       menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case G.INSERTAR:
                Intent intent=new Intent(getActivity(),UserDetailActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
           mAdapter=new userRecyclerViewAdapter(new userRecyclerViewAdapter.OnItemLongClick() {
               @Override
               public boolean onItemLongClick() {
                   Log.i("tiburcio", "long click en el fragmento");
                   if(mActionMode!=null){
                       return false;
                   }
                   mActionMode=getActivity().startActionMode(mActionModeCallback);
                   view.setSelected(true);
                   viewSeleccionado=view;
                   return true;
               }
           });
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String projection []={
                UserContract.NAME,
                UserContract.EMAIL
        };
        Uri uri= Uri.parse(UserContract.CONTENT_URI);
        return new CursorLoader(getActivity(),uri,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.setCursor(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.setCursor(null);
        mAdapter.notifyDataSetChanged();
    }
}
