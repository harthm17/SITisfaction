package at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.gui.oldVersions;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.R;

public class UserListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> users;
    ArrayAdapter<String> adapter;
    private DrawerLayout drawer;

    EditText userinput;
    ImageView add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        Toolbar toolbar = findViewById(R.id.list_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.list_drawer);
        NavigationView navigationView = findViewById(R.id.nav_view_list);
        //navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //ListView erstellen

        listView = findViewById(R.id.list_view);

        users = new ArrayList<>();
        users.add("Thomas Harrer");
        users.add("Mike Adam");
        users.add("Thomas Pust");

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, users);
        listView.setAdapter(adapter);

        //User hinzufügen

        userinput = findViewById(R.id.user_input);
        add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = userinput.getText().toString();
                /*if(text == null || text.length() == 0){

                }*/
                addUser(text);
                userinput.setText("");
            }
        });
    }


    public void addUser(String user) {

        users.add(user);
        listView.setAdapter(adapter);
    }

}