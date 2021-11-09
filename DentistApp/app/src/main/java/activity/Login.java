package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dentistapp.R;

import java.util.List;

import helper.AutenticarUser;
import model.user;

public class Login extends AppCompatActivity {

    private user usuario;
    private EditText campoEmail;
    private EditText campoSenha;
    private Button botaoEntrar;
    private AutenticarUser autenticar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telalogin);
    }

    public void ChecarEIr(View view) {
        campoEmail = findViewById(R.id.editTextEmailTelaLogin);
        campoSenha = findViewById(R.id.editTextPasswordTelaLogin);
        botaoEntrar = findViewById(R.id.buttonEntrarLoginTela);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarEmail(campoEmail)) {
                    if (validarSenha(campoSenha)) {

                        autenticar = new AutenticarUser();
                        String email, senha;
                        email = campoEmail.getText().toString();
                        senha = campoSenha.getText().toString();

                        if(autenticar.valiarUsuario(email,senha,getApplicationContext())){
                            AbrirTelaPrincipal(view);
                            Toast.makeText(Login.this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Login.this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Login.this, "Insira a senha", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Insira o e-mail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void AbrirTelaPrincipal(View view) {
        startActivity(new Intent(this, Principal.class));

        /*Intent intent = new Intent(this,Principal.class);
        intent.putExtra("usuario",usuario);
        startActivity(intent);
        */

    }

    public boolean validarEmail(EditText campoEmail) {

        if (campoEmail.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validarSenha(EditText campoSenha) {

        if (campoSenha.getText().toString().isEmpty()) {
            return false;
        }
        return true;

    }

}