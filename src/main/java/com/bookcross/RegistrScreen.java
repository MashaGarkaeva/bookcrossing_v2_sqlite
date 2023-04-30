package com.bookcross;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrScreen extends AppCompatActivity {
    EditText reg_login;
    EditText reg_password;
    EditText reg_fio;
    Button enter2;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);
        reg_login = findViewById(R.id.reg_login);
        reg_password = findViewById(R.id.reg_password);
        reg_fio = findViewById(R.id.reg_fio);
        enter2 = (Button) findViewById(R.id.enter2);

        databaseHelper = new DatabaseHelper(this);

        enter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = reg_login.getText().toString();
                String password = reg_password.getText().toString();
                String fio = reg_fio.getText().toString();

                if (login.equals("") || password.equals("") || fio.equals(""))
                    Toast.makeText(RegistrScreen.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();

                else {
                        Boolean checkLogin = databaseHelper.checkLogin(login);

                        if (checkLogin == false) {
                            Boolean insert = databaseHelper.insertData(login, password);

                            if (insert == true) {
                                Toast.makeText(RegistrScreen.this, "SingUp succesfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), AvtorizScreen.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegistrScreen.this, "SingUp failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegistrScreen.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
}
