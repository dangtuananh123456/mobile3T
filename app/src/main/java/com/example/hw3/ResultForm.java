package com.example.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultForm extends AppCompatActivity implements View.OnClickListener {

    private TextView tvResultUsername;
    private TextView tvResultPassword;
    private TextView tvResultBirthdate;
    private TextView tvResultGender;
    private TextView tvResultHobbies;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_form);
        initView();
        loadDataFromRegisterForm();
    }

    private void loadDataFromRegisterForm() {
        Intent data = getIntent();
        Bundle bundle = data.getExtras();

        StringBuilder password = new StringBuilder("");
        int passwordSize = bundle.getString("password").length();
        for (int i = 0; i < passwordSize; ++i) {
            password.append('*');
        }

        tvResultUsername.setText("Username: " + bundle.getString("username"));
        tvResultPassword.setText("Password: " + password.toString());
        tvResultBirthdate.setText("Birthdate: " + bundle.getString("birthdate"));
        tvResultGender.setText("Gender: " + bundle.getString("gender"));
        tvResultHobbies.setText("Hobbies: " + bundle.getString("hobbies"));
    }

    private void initView() {
        tvResultBirthdate = findViewById(R.id.tv_result_birthdate);
        tvResultPassword = findViewById(R.id.tv_result_password);
        tvResultUsername = findViewById(R.id.tv_result_username);
        tvResultGender = findViewById(R.id.tv_result_gender);
        tvResultHobbies = findViewById(R.id.tv_result_hobbies);
        btnExit = findViewById(R.id.btn_exit);

        btnExit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_exit) {
            finishAffinity();
        }
    }
}