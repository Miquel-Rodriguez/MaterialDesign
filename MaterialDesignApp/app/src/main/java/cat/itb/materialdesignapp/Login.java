package cat.itb.materialdesignapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText editTextUseName, editTextPassword;
    TextInputLayout layoutUserName, layoutPassword;
    Button buttonLogin, buttonRegister;

    boolean name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextPassword = findViewById(R.id.edit_text_password);
        editTextUseName = findViewById(R.id.edit_text_username);
        buttonLogin = findViewById(R.id.button_login);
        buttonRegister = findViewById(R.id.button_register);

        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);

        layoutPassword = findViewById(R.id.layout_password);
        layoutUserName = findViewById(R.id.layout_user_name);




        buttonLogin.setEnabled(false);
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()<8){
                    layoutPassword.setError("La contraseña ha de tenir més de 8 caràcters");
                    buttonLogin.setEnabled(false);
                }else
                    if(s.toString().contains(" ")){
                    layoutPassword.setError("la contraseña no pot contenir espais en blanc");
                    buttonLogin.setEnabled(false);
                }else
                    if(s.toString().equals("")){
                    layoutPassword.setError("No pots deixar l'espai en blanc");
                    buttonLogin.setEnabled(false);
                }else {
                        layoutPassword.setError(null);
                        password = true;
                        ComprobarEditsTexts();
                    }
            }
        });

        editTextUseName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().contains(" ")){
                    layoutUserName.setError("la contraseña no pot contenir espais en blanc");
                    buttonLogin.setEnabled(false);
                }else
                    if(s.toString().equals("")){
                    layoutUserName.setError("No pots deixar l'espai en blanc");

                }else {
                    layoutUserName.setError(null);
                    name=true;
                    ComprobarEditsTexts();
                }
            }
        });
    }

    public void ComprobarEditsTexts(){
        if(name && password){
            buttonLogin.setEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                startActivity(new Intent(Login.this,FinalActivity.class));
                break;
            case R.id.button_register:
                startActivity(new Intent(Login.this,Register.class));
                break;
        }
    }
}