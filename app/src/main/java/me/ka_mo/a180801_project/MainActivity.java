package me.ka_mo.a180801_project;

import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    long etc_s = 0;
    long leisure_s = 0;
    long game_s = 0;
    long wage = 0;
    long runtime_ms = 0;
    long runtime_s = 0;
    long runtime_m = 0;
    long runtime_h = 0;
    long tmp_s = 0;
    long tmp_m = 0;
    long tmp_h = 0;
    float total_cost = 0;
    float total_cost_tmp = 0;
    boolean running;
    Chronometer chronometer;
    Spinner spin;
    String spin_val;
    String[] list = { "게임", "여가" , "기타"};//Spinner에 들어갈 list



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadContents();

        EditText wage_e = (EditText)findViewById(R.id.wage);
        if (wage_e.getText().toString().equals("0")) wage_e.setText("");

        spin = (Spinner) findViewById(R.id.categorySpinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
                spin_val = list[position];//Spinner 내 item 설정시 spin_val을 list에서의 position에 있는 String을 그대로 가져옴
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });
        //spinner에 ArrayAdapter를 적용
        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, list);

        // Spinner에 adapter 설정
        spin.setAdapter(spin_adapter);

        // chronometer 설정
        chronometer = findViewById(R.id.chronometer);


    }

    public void startChronometer(View v){
        EditText wage_e = (EditText)findViewById(R.id.wage);

        if (wage_e.getText().toString().matches("")){
            Toast.makeText(this, "시급 정보가 없습니다.", Toast.LENGTH_LONG).show();

        }
        else if (running){
            Toast.makeText(this, "이미 시간 측정이 시작되었습니다.", Toast.LENGTH_LONG).show();
        }
        else{
            wage_e.setFocusable(false);
            wage_e.setEnabled(false); // editText 비활성화
            chronometer.setBase(SystemClock.elapsedRealtime()); //chronometer 시간 설정 (이 설정이 없으면 현재 HH:MM:SS에서 SS 부분부터 카운팅)
            runtime_ms = 0;
            chronometer.start();
            running = true;
        }
    }


    // 시작시 저장된 값들 로딩
    public void loadContents(){
        TextView game_t = (TextView)findViewById(R.id.game);
        TextView leisure_t = (TextView)findViewById(R.id.leisure);
        TextView etc_t = (TextView)findViewById(R.id.etc);
        TextView total_cost_t = (TextView)findViewById(R.id.total_cost);
        EditText wage_e = (EditText)findViewById(R.id.wage);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        total_cost = sp.getFloat("TotalCost", 0);
        total_cost_t.setText(String.format("₩%.2f", total_cost));

        game_s = sp.getLong("gameS", 0);
        tmp_s = game_s;
        tmp_m = tmp_s/60;
        tmp_h = tmp_m/60;
        tmp_m -= tmp_h*60;
        tmp_s -= (tmp_m*60) + (tmp_h*3600);
        game_t.setText(String.format("%d시간 %d분 %d초", tmp_h, tmp_m, tmp_s));

        leisure_s = sp.getLong("leisureS", 0);
        tmp_s = leisure_s;
        tmp_m = tmp_s/60;
        tmp_h = tmp_m/60;
        tmp_m -= tmp_h*60;
        tmp_s -= (tmp_m*60) + (tmp_h*3600);
        leisure_t.setText(String.format("%d시간 %d분 %d초", tmp_h, tmp_m, tmp_s));

        etc_s = sp.getLong("etc_S", 0);
        tmp_s = etc_s;
        tmp_m = tmp_s/60;
        tmp_h = tmp_m/60;
        tmp_m -= tmp_h*60;
        tmp_s -= (tmp_m*60) + (tmp_h*3600);
        etc_t.setText(String.format("%d시간 %d분 %d초", tmp_h, tmp_m, tmp_s));


        wage = Long.parseLong(sp.getString("wage", "0"));
        wage_e.setText(String.format("%d", wage));


    }

    public void stopChronometer(View v){

        EditText wage_e = (EditText)findViewById(R.id.wage);
        wage_e.setFocusableInTouchMode(true);
        wage_e.setClickable(true);
        wage_e.setFocusable(true);
        wage_e.setEnabled(true); // editText 활성화

        SharedPreferences sp =  PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        TextView game_t = (TextView)findViewById(R.id.game);
        TextView leisure_t = (TextView)findViewById(R.id.leisure);
        TextView etc_t = (TextView)findViewById(R.id.etc);
        chronometer.stop();
        runtime_ms = SystemClock.elapsedRealtime() - chronometer.getBase();
        chronometer.setBase(SystemClock.elapsedRealtime());

        runtime_s = runtime_ms/1000;


        //chronometer 정지시 spinner의 value (spin_val 사용)에 따라서 값을 저장
       if (spin_val == "게임"){
           game_s += runtime_s;
           editor.putLong("gameS", game_s);
           tmp_s = game_s;
           tmp_m = tmp_s/60;
           tmp_h = tmp_m/60;
           tmp_m -= tmp_h*60;
           tmp_s -= (tmp_m*60) + (tmp_h*3600);
           game_t.setText(String.format("%d시간 %d분 %d초", tmp_h, tmp_m, tmp_s));
        }
        else if (spin_val == "여가"){
            leisure_s += runtime_s;
            editor.putLong("leisureS", leisure_s);
            tmp_s = leisure_s;
            tmp_m = tmp_s/60;
            tmp_h = tmp_m/60;
            tmp_m -= tmp_h*60;
            tmp_s -= (tmp_m*60) + (tmp_h*3600);
            leisure_t.setText(String.format("%d시간 %d분 %d초", tmp_h, tmp_m, tmp_s));
        }
        else{
            etc_s += runtime_s;
           editor.putLong("etcS", etc_s);
            tmp_s = etc_s;
            tmp_m = tmp_s/60;
            tmp_h = tmp_m/60;
            tmp_m -= tmp_h*60;
            tmp_s -= (tmp_m*60) + (tmp_h*3600);
            etc_t.setText(String.format("%d시간 %d분 %d초", tmp_h, tmp_m, tmp_s));
        }

        wage = Long.parseLong(wage_e.getText().toString());
        total_cost_tmp = Float.valueOf(etc_s + leisure_s + game_s)*(Float.valueOf(wage)/3600f);
        total_cost += total_cost_tmp;
        TextView total_cost_t = (TextView)findViewById(R.id.total_cost);
        total_cost_t.setText(String.format("₩%.2f", total_cost));

        editor.putString("wage", wage_e.getText().toString());
        editor.putFloat("TotalCost", total_cost);
        editor.commit();

        runtime_m = runtime_s/60;
        runtime_h = runtime_m/60;
        runtime_m -= runtime_h*60;
        runtime_s -= (runtime_m*60) + (runtime_h*3600);

        //Toast로 저장
        Toast.makeText(this, "총 " + runtime_h + "시간 " + runtime_m + "분 " + runtime_s + "초 동안 " + spin_val + " 활동을 실시하였습니다. " + "("+ total_cost_tmp +"원)", Toast.LENGTH_LONG).show();

        running = false;
    }


    //2018년식 리셋 방법

    public void resetAll(View v){
        TextView game_t = (TextView)findViewById(R.id.game);
        TextView leisure_t = (TextView)findViewById(R.id.leisure);
        TextView etc_t = (TextView)findViewById(R.id.etc);
        TextView total_cost_t = (TextView)findViewById(R.id.total_cost);
        EditText wage_e = (EditText)findViewById(R.id.wage);
        etc_s = 0;
        leisure_s = 0;
        game_s = 0;
        wage = 0;
        runtime_ms = 0;
        runtime_s = 0;
        runtime_m = 0;
        runtime_h = 0;
        tmp_s = 0;
        tmp_m = 0;
        tmp_h = 0;
        SharedPreferences sp =  PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong("gameS", game_s);
        editor.putLong("leisureS", leisure_s);
        editor.putLong("etcS", etc_s);
        editor.putString("wage", "0");
        editor.putFloat("TotalCost", total_cost);
        editor.commit();
        game_t.setText(String.format("%d시간 %d분 %d초", tmp_h, tmp_m, tmp_s));
        leisure_t.setText(String.format("%d시간 %d분 %d초", tmp_h, tmp_m, tmp_s));
        etc_t.setText(String.format("%d시간 %d분 %d초", tmp_h, tmp_m, tmp_s));
        total_cost_t.setText(String.format("₩0.00", total_cost));
        wage_e.setText("");
    }
}
