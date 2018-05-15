package me.ka_mo.a180516project;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.TextView;

public class calculatorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView monitor;
    String operator = "0";
    String res = "0", num = "0", res_a = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        monitor = (TextView) findViewById(R.id.monitor);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.openCalc) {
            // Handle the camera action
        } else if (id == R.id.openTodo) {
            Intent calculator = new Intent(calculatorActivity.this, todolistActivity.class);
            startActivity(calculator);


        } else if (id == R.id.logout) {
            Intent login = new Intent(calculatorActivity.this, LoginActivity.class);
            finish();
            startActivity(login);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void num_0(View v){
        ch_number("0");
    }

    public void num_1(View v){
        ch_number("1");
    }

    public void num_2(View v){
        ch_number("2");
    }

    public void num_3(View v){
        ch_number("3");
    }

    public void num_4(View v){
        ch_number("4");
    }

    public void num_5(View v){
        ch_number("5");
    }

    public void num_6(View v){
        ch_number("6");
    }

    public void num_7(View v){
        ch_number("7");
    }

    public void num_8(View v){
        ch_number("8");
    }

    public void num_9(View v){
        ch_number("9");
    }


    public void num_divide(View v){
        ch_operator("/");
    }

    public void num_multiply(View v){
        ch_operator("*");
    }

    public void num_subtract(View v){
        ch_operator("-");
    }

    public void num_add(View v){
        ch_operator("+");
    }

    public void num_c(View v){
        num = "0";
        res = "0";
        res_a = "0";
        operator = "0";
        monitor.setText(res);
    }

    public void num_equals(View v){
        if (res.equals("0")){
            monitor.setText(num);
        } else {
            if (operator.equals("/")){
                if (num.equals("0")){
                    monitor.setText(":( [Error]");
                } else {
                    res = String.valueOf(Integer.parseInt(res)/Integer.parseInt(num));
                    monitor.setText(res);
                }
            } else if (operator.equals("*")){
                res = String.valueOf(Integer.parseInt(res)*Integer.parseInt(num));
                monitor.setText(res);
            } else if (operator.equals("-")){
                res = String.valueOf(Integer.parseInt(res)-Integer.parseInt(num));
                monitor.setText(res);
            } else if (operator.equals("+")){
                res = String.valueOf(Integer.parseInt(res)+Integer.parseInt(num));
                monitor.setText(res);
            }
        }
        res_a = res;
        res = "0";
        num = "0";
        operator = "0";

    }

    public void ch_number(String number){
        if (num.equals("0")){
            num = number;
        } else {
            num += number;
        }

        monitor.setText(num);
        res_a = "0";
    }

    public void ch_operator(String ope){
        if (!res_a.equals("0")){
            num = res_a;
            res_a = "0";
        }
        if (res.equals("0")){
            res = num;
            num = "0";
            operator = ope;
        } else {
            if (operator.equals("/")){
                if (num.equals("0")){
                    monitor.setText(":( [Error]");
                } else {
                    res = String.valueOf(Integer.parseInt(res)/Integer.parseInt(num));
                    monitor.setText(res);
                }
            } else if (operator.equals("*")){
                res = String.valueOf(Integer.parseInt(res)*Integer.parseInt(num));
                monitor.setText(res);
            } else if (operator.equals("-")){
                res = String.valueOf(Integer.parseInt(res)-Integer.parseInt(num));
                monitor.setText(res);
            } else if (operator.equals("+")){
                res = String.valueOf(Integer.parseInt(res)+Integer.parseInt(num));
                monitor.setText(res);
            }
            num = "0";
            operator = ope;
        }
    }
}


//    public void num_0(View v){
//        if (num.equals("0")){
//            num = "0";
//        } else {
//            num += "0";
//        }
//
//        monitor.setText(num);
//        res_a = "0";
//    }
//
//    public void num_1(View v){
//        if (num.equals("0")){
//            num = "1";
//        } else {
//            num += "1";
//        }
//
//        monitor.setText(num);
//        res_a = "0";
//    }
//
//    public void num_2(View v){
//        if (num.equals("0")){
//            num = "2";
//        } else {
//            num += "2";
//        }
//
//        monitor.setText(num);
//        res_a = "0";
//    }
//
//    public void num_3(View v){
//        if (num.equals("0")){
//            num = "3";
//        } else {
//            num += "3";
//        }
//
//        monitor.setText(num);
//        res_a = "0";
//    }
//
//    public void num_4(View v){
//        if (num.equals("0")){
//            num = "4";
//        } else {
//            num += "4";
//        }
//
//        monitor.setText(num);
//        res_a = "0";
//    }
//
//    public void num_5(View v){
//        if (num.equals("0")){
//            num = "5";
//        } else {
//            num += "5";
//        }
//
//        monitor.setText(num);
//        res_a = "0";
//    }
//
//    public void num_6(View v){
//        if (num.equals("0")){
//            num = "6";
//        } else {
//            num += "6";
//        }
//
//        monitor.setText(num);
//        res_a = "0";
//    }
//
//    public void num_7(View v){
//        if (num.equals("0")){
//            num = "7";
//        } else {
//            num += "7";
//        }
//
//        monitor.setText(num);
//        res_a = "0";
//    }
//
//    public void num_8(View v){
//        if (num.equals("0")){
//            num = "8";
//        } else {
//            num += "8";
//        }
//
//        monitor.setText(num);
//        res_a = "0";
//    }
//
//    public void num_9(View v){
//        if (num.equals("0")){
//            num = "9";
//        } else {
//            num += "9";
//        }
//
//        monitor.setText(num);
//        res_a = "0";
//    }
//
//
//
//
//    public void num_divide(View v){
//        if (!res_a.equals("0")){
//            num = res_a;
//            res_a = "0";
//        }
//        if (res.equals("0")){
//            res = num;
//            num = "0";
//            operator = "/";
//        } else {
//            if (operator.equals("/")){
//                res = String.valueOf(Integer.parseInt(res)/Integer.parseInt(num));
//            } else if (operator.equals("*")){
//                res = String.valueOf(Integer.parseInt(res)*Integer.parseInt(num));
//            } else if (operator.equals("-")){
//                res = String.valueOf(Integer.parseInt(res)-Integer.parseInt(num));
//            } else if (operator.equals("+")){
//                res = String.valueOf(Integer.parseInt(res)+Integer.parseInt(num));
//            }
//            monitor.setText(res);
//            num = "0";
//            operator = "/";
//        }
//    }
//
//    public void num_multiply(View v){
//        if (!res_a.equals("0")){
//            num = res_a;
//            res_a = "0";
//        }
//        if (res.equals("0")){
//            res = num;
//            num = "0";
//            operator = "*";
//        } else {
//            if (operator.equals("/")){
//                res = String.valueOf(Integer.parseInt(res)/Integer.parseInt(num));
//            } else if (operator.equals("*")){
//                res = String.valueOf(Integer.parseInt(res)*Integer.parseInt(num));
//            } else if (operator.equals("-")){
//                res = String.valueOf(Integer.parseInt(res)-Integer.parseInt(num));
//            } else if (operator.equals("+")){
//                res = String.valueOf(Integer.parseInt(res)+Integer.parseInt(num));
//            }
//            monitor.setText(res);
//            num = "0";
//            operator = "*";
//        }
//    }
//
//    public void num_subtract(View v){
//        if (!res_a.equals("0")){
//            num = res_a;
//            res_a = "0";
//        }
//        if (res.equals("0")){
//            res = num;
//            num = "0";
//            operator = "-";
//        } else {
//            if (operator.equals("/")){
//                res = String.valueOf(Integer.parseInt(res)/Integer.parseInt(num));
//            } else if (operator.equals("*")){
//                res = String.valueOf(Integer.parseInt(res)*Integer.parseInt(num));
//            } else if (operator.equals("-")){
//                res = String.valueOf(Integer.parseInt(res)-Integer.parseInt(num));
//            } else if (operator.equals("+")){
//                res = String.valueOf(Integer.parseInt(res)+Integer.parseInt(num));
//            }
//            monitor.setText(res);
//            num = "0";
//            operator = "-";
//        }
//    }
//
//    public void num_add(View v){
//        if (!res_a.equals("0")){
//            num = res_a;
//            res_a = "0";
//        }
//        if (res.equals("0")){
//            res = num;
//            num = "0";
//            operator = "+";
//        } else {
//            if (operator.equals("/")){
//                res = String.valueOf(Integer.parseInt(res)/Integer.parseInt(num));
//            } else if (operator.equals("*")){
//                res = String.valueOf(Integer.parseInt(res)*Integer.parseInt(num));
//            } else if (operator.equals("-")){
//                res = String.valueOf(Integer.parseInt(res)-Integer.parseInt(num));
//            } else if (operator.equals("+")){
//                res = String.valueOf(Integer.parseInt(res)+Integer.parseInt(num));
//            }
//            monitor.setText(res);
//            num = "0";
//            operator = "+";
//        }
//    }
//
//    public void num_c(View v){
//        num = "0";
//        res = "0";
//        operator = "0";
//        monitor.setText(res);
//    }
//
//    public void num_equals(View v){
//        if (res.equals("0")){
//            monitor.setText(num);
//        } else {
//            if (operator.equals("/")){
//                res = String.valueOf(Integer.parseInt(res)/Integer.parseInt(num));
//            } else if (operator.equals("*")){
//                res = String.valueOf(Integer.parseInt(res)*Integer.parseInt(num));
//            } else if (operator.equals("-")){
//                res = String.valueOf(Integer.parseInt(res)-Integer.parseInt(num));
//            } else if (operator.equals("+")){
//                res = String.valueOf(Integer.parseInt(res)+Integer.parseInt(num));
//            }
//            monitor.setText(res);
//        }
//        res_a = res;
//        res = "0";
//        num = "0";
//        operator = "0";
//
//    }