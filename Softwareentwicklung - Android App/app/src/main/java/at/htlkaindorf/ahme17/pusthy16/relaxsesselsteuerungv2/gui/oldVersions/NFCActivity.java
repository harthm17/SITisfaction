package at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.gui.oldVersions;

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
import at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.gui.activities.MainActivity;
import at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.gui.activities.RecyclerListActivity;

public class NFCActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcactivity);

        Toolbar toolbar = findViewById(R.id.nfc_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.nfc_drawer);
        NavigationView navigationView = findViewById(R.id.nfc_navigation_view);
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
                Intent intent = new Intent(NFCActivity.this, RecyclerListActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_home:
                drawer.closeDrawer(Gravity.LEFT);
                Intent homeIntent = new Intent(NFCActivity.this, MainActivity.class);
                startActivity(homeIntent);
                break;

            case R.id.nav_nfc:
                drawer.closeDrawer(Gravity.LEFT);
                /*Intent nfcIntent = new Intent(NFCActivity.this, NFCActivity.class);
                startActivity(nfcIntent);*/
                break;

            case R.id.nav_loaduser:
                drawer.closeDrawer(Gravity.LEFT);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle(getString(R.string.menu_load));
                builder.setMessage("Load favourite position of <selected User>?");

                builder.setPositiveButton("OK", (DialogInterface dialogInterface, int i) ->
                {
                    dialog.cancel();
                });

                builder.setNegativeButton("CANCEL", (DialogInterface dialogInterface, int i)->
                {
                    dialog.cancel();
                });

                dialog = builder.show();
                break;

            case R.id.nav_saveposition:
                drawer.closeDrawer(Gravity.LEFT);
                AlertDialog.Builder albuilder = new AlertDialog.Builder(this);

                albuilder.setTitle("Save Position");
                albuilder.setMessage("Save your current position ?");

                albuilder.setPositiveButton("YES", (DialogInterface dialogInterface, int i)->
                {
                    dialog.cancel();
                });

                albuilder.setNegativeButton("NO", (DialogInterface dialogInterface, int i)->
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