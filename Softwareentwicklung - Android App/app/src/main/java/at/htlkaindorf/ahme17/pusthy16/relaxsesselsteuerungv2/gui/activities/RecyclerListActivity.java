package at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.gui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

import at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.R;
import at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.data.User;
import at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.data.Users;
import at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.gui.oldVersions.NFCActivity;
import at.htlkaindorf.ahme17.pusthy16.relaxsesselsteuerungv2.gui.recyclerAdapter.CardAdapter;

public class RecyclerListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private CardAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private ImageView card;
    private CardView cardView;
    private ImageView edit;
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_user_list);

        Toolbar toolbar = findViewById(R.id.recycler_list_toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.recycler_menu);

        drawer = findViewById(R.id.recycler_list_drawer);
        NavigationView navigationView = findViewById(R.id.recycler_nav_view_list);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CardAdapter();
        new ItemTouchHelper(itemTouchCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

        final List<User> users = Users.getInstance().getUsers();
        for(User user : users)
            adapter.add(user.getName(), getDrawable(R.drawable.ic_edit));

    }


    //LifeCycle
    @Override
    protected void onStop() {
        super.onStop();
        adapter.save();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_reslist:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                LayoutInflater inflater = this.getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.add_dialog,null));

                builder.setPositiveButton(getString(R.string.dialog_ok), (DialogInterface dialogInterface, int i) ->
                {
                    EditText username = dialog.findViewById(R.id.user_name);
                    String user = username.getText().toString();
                    addUser(user);
                });

                builder.setNegativeButton(getString(R.string.dialog_cancel), (DialogInterface dialogInterface, int i) ->
                {
                   dialog.cancel();
                });

                dialog = builder.show();
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }

    public void addUser(String user) {

        adapter.add(user, getDrawable(R.drawable.ic_edit));
        adapter.notifyItemInserted(adapter.getItemCount()-1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recycler_menu, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.nav_home:
                drawer.closeDrawer(Gravity.LEFT);
                Intent homeIntent = new Intent(RecyclerListActivity.this, MainActivity.class);
                startActivity(homeIntent);
                break;

            case R.id.nav_user:
                drawer.closeDrawer(Gravity.LEFT);
                /*Intent intent = new Intent(RecyclerUserList.this, RecyclerUserList.class);
                startActivity(intent);*/
                break;

            case R.id.nav_nfc:
                drawer.closeDrawer(Gravity.LEFT);
                Intent nfcIntent = new Intent(RecyclerListActivity.this, NFCActivity.class);
                startActivity(nfcIntent);
                break;

            case R.id.nav_loaduser:
                drawer.closeDrawer(Gravity.LEFT);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle(getString(R.string.menu_load));
                builder.setMessage(getString(R.string.dialog_load_));

                builder.setPositiveButton(getString(R.string.dialog_ok), (DialogInterface dialogInterface, int i) ->
                {
                    dialog.cancel();
                });

                builder.setNegativeButton(getString(R.string.dialog_cancel), (DialogInterface dialogInterface, int i)->
                {
                    dialog.cancel();
                });

                dialog = builder.show();
                break;

            case R.id.nav_saveposition:
                drawer.closeDrawer(Gravity.LEFT);
                AlertDialog.Builder albuilder = new AlertDialog.Builder(this);

                albuilder.setTitle(R.string.menu_save);
                albuilder.setMessage(R.string.dialog_save);

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


    ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            final int fromPos = viewHolder.getAdapterPosition();
            final int toPos = target.getAdapterPosition();

            adapter.move(fromPos, toPos);
            adapter.notifyItemMoved(fromPos, toPos);

            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            AlertDialog.Builder builder = new AlertDialog.Builder(RecyclerListActivity.this);

            builder.setTitle(getString(R.string.dialog_delete_head));
            builder.setMessage(getString(R.string.dialog_delete));

            builder.setPositiveButton(R.string.positive_button, (DialogInterface dialogInterface, int i)->
            {
                adapter.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                dialog.cancel();
            });

            builder.setNegativeButton(R.string.negative_button, (DialogInterface dialogInterface, int i)->
            {
                adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                dialog.cancel();
            });

            dialog = builder.show();
        }
    };


}
