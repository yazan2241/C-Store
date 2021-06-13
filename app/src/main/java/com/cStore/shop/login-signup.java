//////////////////////////////////////////////////////\\\\\\\\\\\\هاد ملف الجافا login 
package com.cStore.shop;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.appcompat.app.AppCompatActivity;



class Login extends AppCompatActivity {
    private TextView t1;
    private Button btl;
    private EditText tu,tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tu = findViewById(R.id.email);
        tp = findViewById(R.id.pass);
/*
        t1 = findViewById(R.id.textViewSignUp);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitySignUp();
            }
        });
*/



        btl = findViewById(R.id.buLogIn);
        btl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityHome();
            }
        });



    }
    /*
    public  void openActivitySignUp(){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
*/
    public  void openActivityHome(){

        if(TextUtils.isEmpty(tu.getText()) && TextUtils.isEmpty(tp.getText()))
        {
            tu.setError("Enter Username");
            tp.setError("Enter Password");
            tu.requestFocus();
            tp.requestFocus();
        }
        else
        {
            String stru =  tu.getText().toString();
            String strp = tp.getText().toString();
            if(stru.matches("zeina") && strp.matches("admin"))
            {
                String str = "You have logged in successfully";
                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else
            {
                String str1 = "Type correct username or Password";
                Toast.makeText(getApplicationContext(),str1,Toast.LENGTH_SHORT).show();
            }

        }
    }

}
/*
//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\///////////////////////////// هاد ملف ال Signup
package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Signup extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btn = findViewById(R.id.sgnextbutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitySignUp();
            }
        });

    }

    public void openActivitySignUp() {
        Intent intent = new Intent(this, BabyDetails.class);
        startActivity(intent);
    }
}
/*
////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\ هاد spinner
 Spinner mySpinner = findViewById(R.id.spinnerBG);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(BabyDetails.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bg));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\هاد الانتقال
 Button btn = findViewById(R.id.nextbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityLogin();

            }
        });

///////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\هاد ادخال تاريخ الولادة بكل نافذة منبثقة
        mDisplayDate = findViewById(R.id.textViewDob);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        BabyDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };
/////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\
*/
