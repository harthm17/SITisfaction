package at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.gui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;

import at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private Dialog dialog;
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.nav_user:
                drawer.closeDrawer(Gravity.LEFT);
                Intent intent = new Intent(MainActivity.this, RecyclerListActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_home:
                drawer.closeDrawer(Gravity.LEFT);
                break;

            case R.id.nav_nfc:
                drawer.closeDrawer(Gravity.LEFT);
                AlertDialog.Builder twinbuilder = new AlertDialog.Builder(this);

                twinbuilder.setTitle(getString(R.string.dialog_nfc_headline));
                twinbuilder.setMessage(getString(R.string.dialog_nfc_text));

                twinbuilder.setPositiveButton(getString(R.string.positive_button), (DialogInterface dialogInterface, int i) ->
                {
                    dialog.cancel();
                });

                twinbuilder.setNegativeButton(getString(R.string.negative_button), (DialogInterface dialogInterface, int i)->
                {
                    dialog.cancel();
                });

                dialog = twinbuilder.show();
                break;

            case R.id.nav_loaduser:
                drawer.closeDrawer(Gravity.LEFT);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle(getString(R.string.menu_load));
                builder.setMessage(getString(R.string.dialog_load_));

                builder.setPositiveButton(getString(R.string.positive_button), (DialogInterface dialogInterface, int i) ->
                {
                    dialog.cancel();
                });

                builder.setNegativeButton(getString(R.string.negative_button), (DialogInterface dialogInterface, int i)->
                {
                    dialog.cancel();
                });

                dialog = builder.show();
                break;

            case R.id.nav_saveposition:
                drawer.closeDrawer(Gravity.LEFT);
                AlertDialog.Builder albuilder = new AlertDialog.Builder(this);

                albuilder.setTitle(getString(R.string.menu_save));
                albuilder.setMessage(getString(R.string.dialog_save));

                albuilder.setPositiveButton(getString(R.string.positive_button), (DialogInterface dialogInterface, int i)->
                {
                   dialog.cancel();
                });

                albuilder.setNegativeButton(getString(R.string.negative_button), (DialogInterface dialogInterface, int i)->
                {
                    dialog.cancel();
                });

                dialog = albuilder.show();
                break;
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}