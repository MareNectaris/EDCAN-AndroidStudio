package ga.azsa.first_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OtherClass extends AppCompatActivity{
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        textView = findViewById(R.id.other_text);

        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("key"));
    }
}
