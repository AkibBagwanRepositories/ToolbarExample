package com.example.toolbarexample;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Bagwan Akib Rafiq on 10/26/2019.
 */
public class BaseActivity extends AppCompatActivity {
    AlertDialog.Builder alertDialog;
    ImageView menu_toggle, setting_menu;
    PopupMenu popup_activity_menu;
    Toolbar toolbar;

    PopupMenu.OnMenuItemClickListener menuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.mi_file_manager) {
                startActivity(new Intent(BaseActivity.this, FileManager.class));
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = findViewById(R.id.toolbar_included);
        setSupportActionBar(toolbar);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
//                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        onConfigurationChanged(getApplicationContext().getResources().getConfiguration());
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getIds();
    }

    private void getIds() {
        menu_toggle = findViewById(R.id.menu_toggle);
        menu_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup_activity_menu = new PopupMenu(BaseActivity.this, v);
                MenuInflater inflater = popup_activity_menu.getMenuInflater();
                inflater.inflate(R.menu.activity_menu, popup_activity_menu.getMenu());
                if (popup_activity_menu != null)
                    popup_activity_menu.setOnMenuItemClickListener(menuItemClickListener);
                popup_activity_menu.show();
                Toast.makeText(BaseActivity.this, "This is Base Activity menu click!", Toast.LENGTH_SHORT).show();
            }
        });

        setting_menu = findViewById(R.id.setting_toggle);
        setting_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup_activity_menu = new PopupMenu(BaseActivity.this, v);
                MenuInflater inflater = popup_activity_menu.getMenuInflater();
                inflater.inflate(R.menu.setting_menu, popup_activity_menu.getMenu());
                if (popup_activity_menu != null)
                    popup_activity_menu.setOnMenuItemClickListener(menuItemClickListener);
                popup_activity_menu.show();
                Toast.makeText(BaseActivity.this, "This is Setting menu click!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        alertDialog = new AlertDialog.Builder(BaseActivity.this);
        alertDialog.setCancelable(false);
        /*
         *  Orientation Check
         * */
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }

        /*
         *  Tab or Mobile Check
         * */
        if (newConfig.screenWidthDp >= 600) {
            alertDialog.setTitle("This Is Tablet");
            alertDialog.setMessage("Current device is the Tablet.");
            alertDialog.show();
        } else {
            alertDialog.setTitle("This Is Mobile");
            alertDialog.setMessage("Current device is the Mobile.");
            alertDialog.show();
        }

    }
}
