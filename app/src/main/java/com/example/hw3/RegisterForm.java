package com.example.hw3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterForm extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtRetype;
    private EditText edtBirthdate;

    private Button btnSignUp;
    private Button btnReset;
    private Button btnSelectDay;

    private CheckBox cbTennis;
    private CheckBox cbFutbal;
    private CheckBox cbOther;

    private RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        initView();
    }

    private void initView() {
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtRetype = findViewById(R.id.edt_retype);
        edtBirthdate = findViewById(R.id.edt_birthday);
        cbFutbal = findViewById(R.id.cb_futbal);
        cbOther = findViewById(R.id.cb_others);
        cbTennis = findViewById(R.id.cb_tennis);

        initPickDateDialog();
        initReset();
        initSignup();
        initGender();
    }

    private void initPickDateDialog() {
        btnSelectDay = findViewById(R.id.btn_select_day);
        Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);

        btnSelectDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegisterForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month,
                                          int dayOfMonth) {
                        month += 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        edtBirthdate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void initReset() {
        btnReset = findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtUsername.setText("");
                edtPassword.setText("");
                edtRetype.setText("");
                edtBirthdate.setText("");
                cbTennis.setChecked(false);
                cbFutbal.setChecked(false);
                cbOther.setChecked(false);
                rgGender.clearCheck();
            }
        });
    }

    private boolean isValidData() {

        //Username field Or Password field Or Retype password field is empty => invalid
        if (edtUsername.getText().toString().isEmpty() ||
                edtPassword.getText().toString().isEmpty() ||
                edtRetype.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill in the required fields",
                    Toast.LENGTH_LONG).show();
            return false;
        }

        //Password != Retype password => invalid
        if (!edtPassword.getText().toString().equals(edtRetype.getText().toString())) {
            Toast.makeText(this, "Please re-type your password.",
                    Toast.LENGTH_LONG).show();
            return false;
        }

        //Birthday field empty => invalid
        if (edtBirthdate.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please choose your birthdate.",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private String getHobbies() {
        StringBuilder hobbies = new StringBuilder("");
        if (cbTennis.isChecked()) hobbies.append("tennis, ");
        if (cbFutbal.isChecked()) hobbies.append("futbal, ");
        if (cbOther.isChecked()) hobbies.append("others, ");
        if (hobbies.length() > 2) {
            hobbies.setLength(hobbies.length() - 2);
        }
        return hobbies.toString();
    }

    //Return the text of the selected gender
    private String getGender() {
        int selectedGenderId = rgGender.getCheckedRadioButtonId();
        RadioButton selectedGender = (RadioButton)findViewById(selectedGenderId);
        return selectedGender.getText().toString();
    }

    //Initialize for btnSignup to save register data and transfer to ResultActiviy
    private void initSignup() {
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //If invalid data => throw warning and do nothing
                if (!isValidData())
                {
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putString("username", edtUsername.getText().toString());
                bundle.putString("password", edtPassword.getText().toString());
                bundle.putString("birthdate", edtBirthdate.getText().toString());
                bundle.putString("gender", getGender());
                bundle.putString("hobbies", getHobbies());

                Intent intent = new Intent(RegisterForm.this, ResultForm.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //Initialize for rgGender to get selected gender
    private void initGender() {
        rgGender = (RadioGroup) findViewById(R.id.rgGender);

        //default value of gender is "Male"
        rgGender.check(R.id.rb_male);
    }


}