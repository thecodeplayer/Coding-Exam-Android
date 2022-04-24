package com.example.codingexam.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.codingexam.R;
import com.example.codingexam.models.ResponseModel;

public final class DialogBox {

    public static void showDialog(ResponseModel responseModel, Context context){

        Dialog showMessage;
        TextView tvFullName, tvEmailAddress, tvMobileNumber, tvBirthday, tvAge, tvGender;
        Button btnOkay;

        showMessage = new Dialog(context);
        showMessage.setContentView(R.layout.details_dialog);
        showMessage.setCancelable(false);

        tvFullName = showMessage.findViewById(R.id.tvFullName);
        tvEmailAddress = showMessage.findViewById(R.id.tvEmailAddress);
        tvMobileNumber = showMessage.findViewById(R.id.tvMobileNumber);
        tvBirthday = showMessage.findViewById(R.id.tvBirthday);
        tvAge = showMessage.findViewById(R.id.tvAge);
        tvGender = showMessage.findViewById(R.id.tvGender);
        btnOkay = showMessage.findViewById(R.id.btnOkay);

        tvFullName.setText(responseModel.getFull_name());
        tvEmailAddress.setText(responseModel.getEmail_address());
        tvMobileNumber.setText(responseModel.getMobile_number());
        tvBirthday.setText(responseModel.getBirthday());
        tvAge.setText(String.valueOf(responseModel.getAge()));
        tvGender.setText(responseModel.getGender());

        showMessage.show();

        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage.dismiss();
            }
        });

    }

}
