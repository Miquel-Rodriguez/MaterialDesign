package cat.itb.materialdesignapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class Register extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText editTextUserName, editTextPassword, editTextRepeatPassword, editTextEmail, editTextName, editTextSurname;
    TextInputLayout layoutUserName, layoutPassword, layoutRepeatPassword, layoutEmail, layoutName, layoutSurName;

    Button buttonLogin, buttonRegister, buttonDatePicker;
    CheckBox checkBoxTerms;
    DatePicker datePicker;

    boolean showDatePicker;

    boolean boolUserName, boolPassword, boolRepeatPassword, boolEmail, boolName, boolSurName, boolSpinner, boolCheckBox;

    String texts, passwordText;

    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = findViewById(R.id.spinner);
        getWindow().setBackgroundDrawableResource(R.drawable.face);

        editTextEmail = findViewById(R.id.edit_text_email);
        editTextName = findViewById(R.id.edit_text_name);
        editTextUserName = findViewById(R.id.edit_text_username);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextRepeatPassword = findViewById(R.id.edit_text_repeat_password);
        editTextSurname = findViewById(R.id.edit_text__surname);
        buttonLogin = findViewById(R.id.button_login);
        buttonRegister = findViewById(R.id.button_register);
        buttonDatePicker = findViewById(R.id.button_date_picker);
        checkBoxTerms = findViewById(R.id.checkbox_accept);
        datePicker = findViewById(R.id.date_picker);

        layoutEmail = findViewById(R.id.layout_email);
        layoutName = findViewById(R.id.layout_name);
        layoutPassword = findViewById(R.id.layout_password);
        layoutRepeatPassword = findViewById(R.id.layout_repeatpassword);
        layoutUserName = findViewById(R.id.layout_user_name);
        layoutSurName = findViewById(R.id.layout_surname);

        datePicker.setVisibility(View.GONE);

        buttonRegister.setEnabled(false);

        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
        buttonDatePicker.setOnClickListener(this);



        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    ComprobarEmail();
                }else layoutEmail.setError(null);
            }
        });

        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                   ComprobarNom();
                } else layoutName.setError(null);
            }
        });

        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    ComprobarPassword();
                }else layoutPassword.setError(null);
            }
        });

        editTextRepeatPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    ComprobarRepeatPassword();
                }else layoutRepeatPassword.setError(null);
            }
        });

        editTextUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    ComprobarUserName();
                }else layoutUserName.setError(null);
            }
        });

        editTextSurname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    ComprobarSurname();
                }else layoutSurName.setError(null);
            }
        });

        checkBoxTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBoxTerms.isChecked()) {
                    checkBoxTerms.setError("Es  necessari que aceptis les condicions  ");
                    boolCheckBox = false;
                }else   {
                    checkBoxTerms.setError(null);
                    boolCheckBox = true;
                }
                ComprobarBooleans();
            }
        });



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boolSpinner = position >= 1;
                ComprobarBooleans();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public boolean ComprobarSiMesDe8(String text, TextInputLayout layout){
        if(text.length()<=8){
            layout.setError("Ha de tenir més de 8 caràcters");
            return false;
        }else {
            layout.setError(null);
            return true;
        }
    }

    public boolean ComprobarEspaisEnBlanc(String text, TextInputLayout layout){
        if(text.contains(" ")){
            layout.setError("No pot contenir espais en blanc");
            return false;
        }else {
            layout.setError(null);
            return true;
        }
    }

    public boolean ComprobarSiText(String text, TextInputLayout layout){
        if(text.equals("")){
            layout.setError("Has d'introduir alguna dada");
            return false;
        }else {
            layout.setError(null);
            return true;
        }
    }

    public boolean ComprobarContrasenyasIguals(String pass1, String pass2, TextInputLayout layout){
        if(pass1.equals(pass2)){
            return true;
        }else {
            layout.setError("Les contrasenyas han de ser iguals");
            return false;
        }
    }

    public boolean ComprobarBooleans(){
        buttonRegister.setEnabled(boolEmail && boolName && boolPassword && boolRepeatPassword && boolUserName && boolSurName && checkBoxTerms.isChecked() && boolSpinner);
        return boolEmail && boolName && boolPassword && boolRepeatPassword && boolUserName && boolSurName && checkBoxTerms.isChecked() && boolSpinner;
    }


    public void ComprobarEmail(){
        texts = Objects.requireNonNull(editTextEmail.getText()).toString();
        boolEmail = ComprobarEspaisEnBlanc(texts, layoutEmail) && ComprobarSiText(texts, layoutEmail);
        ComprobarBooleans();
    }

    public void ComprobarNom(){
            texts = Objects.requireNonNull(editTextName.getText()).toString();
            boolName = ComprobarEspaisEnBlanc(texts, layoutName) && ComprobarSiText(texts, layoutName);
            ComprobarBooleans();
    }

    public  void ComprobarPassword(){
        texts = Objects.requireNonNull(editTextPassword.getText()).toString();
        boolPassword = ComprobarSiMesDe8(texts, layoutPassword) && ComprobarEspaisEnBlanc(texts, layoutPassword) && ComprobarSiText(texts, layoutPassword);
        ComprobarBooleans();
        passwordText = texts;
    }

    public void ComprobarRepeatPassword(){
        texts = Objects.requireNonNull(editTextRepeatPassword.getText()).toString();
        boolRepeatPassword = ComprobarContrasenyasIguals(texts, passwordText, layoutRepeatPassword) && ComprobarSiMesDe8(texts, layoutRepeatPassword) && ComprobarEspaisEnBlanc(texts, layoutRepeatPassword) && ComprobarSiText(texts, layoutRepeatPassword);
        ComprobarBooleans();
    }

    public void ComprobarUserName(){
        texts = Objects.requireNonNull(editTextUserName.getText()).toString();
        boolUserName = ComprobarEspaisEnBlanc(texts, layoutUserName) && ComprobarSiText(texts, layoutUserName);
        ComprobarBooleans();
    }

    public void ComprobarSurname(){
        texts = Objects.requireNonNull(editTextSurname.getText()).toString();
        boolSurName = ComprobarEspaisEnBlanc(texts, layoutSurName) && ComprobarSiText(texts, layoutSurName);
        ComprobarBooleans();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                startActivity(new Intent(Register.this, Login.class));
                break;
            case R.id.button_register:
                ComprobarEmail();
                ComprobarNom();
                ComprobarPassword();
                ComprobarRepeatPassword();
                ComprobarUserName();
                ComprobarSurname();
                if(ComprobarBooleans()){
                    startActivity(new Intent(Register.this, FinalActivity.class));
                }

                break;
            case R.id.button_date_picker:
                showDatePicker = !showDatePicker;
                if(showDatePicker){
                    datePicker.setVisibility(View.VISIBLE);
                }else  datePicker.setVisibility(View.GONE);
                break;
        }
    }

}