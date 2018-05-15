package me.ka_mo.a180516project;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class todolistActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


        private ArrayList<String> items;
        private ArrayAdapter<String> itemsAdapter;
        private ListView lvItems;

        private void readItems() {
                File filesDir = getFilesDir();
                File todoFile = new File(filesDir, "todo.txt");
                try {
                        items = new ArrayList<String>(FileUtils.readLines(todoFile));
                } catch (IOException e) {
                        items = new ArrayList<String>();
                }
        }

        private void writeItems() {
                File filesDir = getFilesDir();
                File todoFile = new File(filesDir, "todo.txt");
                try {
                        FileUtils.writeLines(todoFile, items);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }


@Override
protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();


}

        private void setupListViewListener() {
                lvItems.setOnItemLongClickListener(
                        new AdapterView.OnItemLongClickListener() {
                                @Override
                                public boolean onItemLongClick(AdapterView<?> adapter,
                                                               View item, int pos, long id) {
                                        items.remove(pos);
                                        itemsAdapter.notifyDataSetChanged();
                                        writeItems();
                                        return true;
                                }

                        });

        }




        public void onAddItem(View v) {
                EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
                String itemText = etNewItem.getText().toString();
                if (!itemText.equals("") ){
                        Date date = new Date();
                        String timeNow = DateFormat.getDateTimeInstance().format(date);
                        itemsAdapter.add(itemText + " (" + timeNow + ")");
                        etNewItem.setText("");
                        writeItems();
                }
                else{
                        Toast.makeText(getApplicationContext(), "your memo is empty", Toast.LENGTH_SHORT).show();
                }


        }


@Override
public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
        drawer.closeDrawer(GravityCompat.START);
        } else {
        super.onBackPressed();
        }
        }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calculator, menu);
        return true;
        }

@Override
public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
        }

@SuppressWarnings("StatementWithEmptyBody")
@Override
public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.openCalc) {
        Intent calculator = new Intent(todolistActivity.this, calculatorActivity.class);
        startActivity(calculator);

        } else if (id == R.id.openTodo) {

        } else if (id == R.id.logout) {
                Intent login = new Intent(todolistActivity.this, LoginActivity.class);
                finish();
                startActivity(login);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        }
        }

