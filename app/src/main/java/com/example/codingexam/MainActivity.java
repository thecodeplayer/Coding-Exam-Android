package com.example.codingexam;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.codingexam.databinding.ActivityMainBinding;
import com.example.codingexam.models.ResponseModel;
import com.example.codingexam.utils.AppConstants;
import com.example.codingexam.viewmodels.MainActivityViewModel;
import com.example.codingexam.widgets.DialogBox;

import java.text.DateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {
    private ActivityMainBinding activityMainBinding;
    private MainActivityViewModel mainActivityViewModel;
    private static final String[] _gender = {"Male", "Female"};
    private static String fullName, emailAddress, mobileNumber, birthday, age;
    private com.example.codingexam.widgets.DatePicker datePicker;
    private Pattern emailPattern, fullNamePattern, mobileNumberPattern;
    private Matcher emailMatcher, fullNameMatcher, mobileNumberMatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(MainActivity.this).get(MainActivityViewModel.class);
        fullNamePattern = Pattern.compile(AppConstants.fullnamePattern);
        emailPattern = Pattern.compile(AppConstants.emailaddressPattern);
        mobileNumberPattern = Pattern.compile(AppConstants.mobilenumberPattern);

        activityMainBinding.customEndIcon.setEndIconOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                datePicker = new com.example.codingexam.widgets.DatePicker();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });

        activityMainBinding.spGender.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, _gender);
        arrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        activityMainBinding.spGender.setAdapter(arrayAdapter);

        activityMainBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fullName = activityMainBinding.etFullName.getText().toString();
                emailAddress = activityMainBinding.etEmailAddress.getText().toString();
                mobileNumber = activityMainBinding.etMobileNumber.getText().toString();
                birthday = activityMainBinding.etDateOfBirth.getText().toString();
                age = activityMainBinding.tvAge.getText().toString();
                fullNameMatcher = fullNamePattern.matcher(fullName);
                emailMatcher = emailPattern.matcher(emailAddress);
                mobileNumberMatcher = mobileNumberPattern.matcher(mobileNumber);

                if(TextUtils.isEmpty(fullName)) {
                    activityMainBinding.etFullName.setError("Please enter your full name");
                    activityMainBinding.etFullName.requestFocus();
                } else if (!fullNameMatcher.matches()) {
                    activityMainBinding.etFullName.setError("Text only and characters like comma and period");
                    activityMainBinding.etFullName.requestFocus();
                }else if(TextUtils.isEmpty(emailAddress)){
                    activityMainBinding.etEmailAddress.setError("Please enter your email");
                    activityMainBinding.etEmailAddress.requestFocus();
                } else if (!emailMatcher.matches()) {
                    activityMainBinding.etEmailAddress.setError("Invalid Email");
                    activityMainBinding.etEmailAddress.requestFocus();
                } else if (TextUtils.isEmpty(mobileNumber)) {
                    activityMainBinding.etMobileNumber.setError("Please enter your mobile number");
                    activityMainBinding.etMobileNumber.requestFocus();
                } else if (!mobileNumberMatcher.matches()) {
                    activityMainBinding.etMobileNumber.setError("Invalid mobile number");
                    activityMainBinding.etMobileNumber.requestFocus();
                } else if (TextUtils.isEmpty(birthday)) {
                    Toast.makeText(MainActivity.this, "Please select date of birth", Toast.LENGTH_LONG).show();
//                    activityMainBinding.etDateOfBirth.setError("Please select date of birth");
//                    activityMainBinding.etDateOfBirth.requestFocus();
                } else if (Integer.parseInt(age) < 18) {
                    Toast.makeText(MainActivity.this, "Invalid age", Toast.LENGTH_LONG).show();
//                    activityMainBinding.etDateOfBirth.setError("Invalid age");
//                    activityMainBinding.etDateOfBirth.requestFocus();
                }
                else {
                    getUserInformation();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        activityMainBinding.etDateOfBirth.setText(selectedDate);
        activityMainBinding.tvAge.setText(String.valueOf(Year.now().getValue() - year));
    }

    private void getUserInformation(){
        mainActivityViewModel.getInformation().observe(MainActivity.this, new Observer<ResponseModel>() {
            @Override
            public void onChanged(ResponseModel responseModel) {
                if(responseModel != null) {
                    DialogBox.showDialog(responseModel, MainActivity.this);
                } else {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}