package com.mchavez.pettabbed;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import javax.mail.AuthenticationFailedException;

public class Email extends AppCompatActivity {

    private TextInputLayout user;
    private TextInputLayout pass;
    private TextInputLayout body;
    private String recipient;
    private Button btn_enviar;
    Toolbar toolbar3;

    private ProgressBar pbEnviando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        user = (TextInputLayout) findViewById(R.id.username);
        pass = (TextInputLayout) findViewById(R.id.password);
        body = (TextInputLayout) findViewById(R.id.mensaje);
        btn_enviar = findViewById(R.id.btn_enviar);
        recipient = "aerocmc@gmail.com";

        pbEnviando = findViewById(R.id.pb_enviando);

        establishToolbar();

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(user.getEditText().getText())){
                    pbEnviando.setVisibility(View.VISIBLE);
                    new AsyncTask<Void, Void, String>(){

                        @Override
                        protected String doInBackground(Void... voids) {
                            try {
                                JavaMail sender = new JavaMail(user.getEditText().getText().toString(),
                                        pass.getEditText().getText().toString());
                                sender.sendMail("Contacto Coursera", body.getEditText().getText().toString(),
                                        "aerocmc@gmail.com",
                                        recipient);
                            }catch (AuthenticationFailedException authEx){
                                return "error"; //getString(R.string.message_error);
                            } catch (Exception e) {
                                return "otro error"; //e.getMessage();
                            }
                            return "Exito"; //getString(R.string.message_success);
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            super.onPostExecute(s);
                            pbEnviando.setVisibility(View.GONE);
                            //btnEnviar.setVisibility(View.VISIBLE);
                            Toast.makeText(Email.this, s, Toast.LENGTH_SHORT).show();
                            if (s.equals("Exito")){
                                body.getEditText().setText("");
                                user.getEditText().setText("");
                                pass.getEditText().setText("");
                            }
                        }
                    }.execute();
                } else {
                    Toast.makeText(Email.this, "facepalm", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void establishToolbar() {
        Toolbar toolbar3 = (Toolbar) findViewById(R.id.toolbar_contacto);
        setSupportActionBar(toolbar3);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}