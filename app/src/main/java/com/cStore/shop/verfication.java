package com.cStore.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class verfication extends AppCompatActivity {
private EditText a;
public String code2,recode;
SignUpActivity sign=new SignUpActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfication);
        a=findViewById(R.id.verification);
//sign.cc(code2);
code2= SignUpActivity.code;
System.out.println("uiihih"+code2);
    }
    public void nextsign(View view){
        recode=a.getText().toString();

        if(recode.equals(code2)){
        Intent intent2 = new Intent(verfication.this , MainActivity.class);
        startActivity(intent2);
    }else{
            Toast.makeText(getApplicationContext(),"رمز التحقق غير صحيح",Toast.LENGTH_SHORT).show();
        }

    }
}
