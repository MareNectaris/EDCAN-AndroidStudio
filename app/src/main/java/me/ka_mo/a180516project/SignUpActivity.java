package me.ka_mo.a180516project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {


     Button signupButton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupButton = (Button) findViewById(R.id.email_sign_up_button);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupButtonClicked();
            }
        });
    }

    public void signupButtonClicked() {
        TextView semail = (TextView) findViewById(R.id.signupemail);
        String signupemail = semail.getText().toString();
        TextView spw = (TextView) findViewById(R.id.signuppassword);
        String signuppassword = spw.getText().toString();
        LoginActivity.DUMMY_CREDENTIALS.add(signupemail + ":" + signuppassword);
        Toast.makeText(SignUpActivity.this,
                "Signed up with e-mail " + signupemail, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
        finish();
        startActivity(i);

    }



}


