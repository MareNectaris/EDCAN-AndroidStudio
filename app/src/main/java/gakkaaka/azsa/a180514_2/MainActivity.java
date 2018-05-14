package gakkaaka.azsa.a180514_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ListItem> items = new ArrayList<>();
    ReAdapter adapter;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL,
                        false));
        adapter = new ReAdapter(items);
        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int position = recyclerView.getChildAdapterPosition(view);
                Toast.makeText(MainActivity.this, items.get(position).title + "\n" + items.get(position).content, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.add(new ListItem(editText.getText().toString(),
                        "content"));
                editText.setText("");
                recyclerView.getAdapter().notifyItemInserted(items.size());
            }
        });

    }
}
