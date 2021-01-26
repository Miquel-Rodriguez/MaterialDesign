package cat.itb.materialdesignapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonLogin, buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        buttonLogin = findViewById(R.id.button_login1);
        buttonRegister = findViewById(R.id.button_register1);

        buttonRegister.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login1:
                startActivity(new Intent(MainActivity.this, Login.class));
                break;
            case R.id.button_register1:
                startActivity(new Intent(MainActivity.this, Register.class));
                break;
        }
    }
}