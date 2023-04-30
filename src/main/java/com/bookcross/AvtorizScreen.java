package com.bookcross;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AvtorizScreen extends AppCompatActivity {

    Button enter;//кнопка войти в профиль
    Button registr1;//кнопка зарегистрироваться
    EditText login;
    EditText password;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avtoriz);

        databaseHelper = new DatabaseHelper(this);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        enter = (Button) findViewById(R.id.enter1);

        registr1 = findViewById(R.id.registr);
        registr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AvtorizScreen.this, RegistrScreen.class));
            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String log = login.getText().toString();
                String pass = password.getText().toString();
                
                if (log.equals("") || pass.equals(""))
                    Toast.makeText(AvtorizScreen.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();

                else {
                    Boolean check = databaseHelper.checkLoginPassword(log, pass);

                    if (check == true){
                        Toast.makeText(AvtorizScreen.this, "Avtoriz successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ProfilScreen.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AvtorizScreen.this, "Invalid", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
