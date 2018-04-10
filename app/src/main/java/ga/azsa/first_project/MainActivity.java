package ga.azsa.first_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    TextView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.output);
        editText = findViewById(R.id.input);
        button = findViewById(R.id.copyBtn);
    }
    public void click(View v){
        Intent intent = new Intent(getApplicationContext(),OtherClass.class);
        //this
        //MainActirity.this
        intent.putExtra("key",editText.getText().toString());
        startActivity(intent);
    }
}
