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

import model.user;

public class Login extends AppCompatActivity {

    private user usuario;
    private EditText campoEmail;
    private EditText campoSenha;
    private Button botaoEntrar;

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
                        usuario = procurausuario();
                        if(usuario.getNome().equals("Errado")){
                            Toast.makeText(Login.this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
                        }else{
                            AbrirTelaPrincipal(view);
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

    public user procurausuario() {
        user u;
        List<user> lista = MainActivity.listaUsers;

        for (int i = 0; i < lista.size(); i++) {
            u = lista.get(i);
            if (u.getEmail().equals(campoEmail.getText().toString()) && u.getPassword().equals(campoSenha.getText().toString())) {
                return u;
            }
        }
        u = new user();
        u.setNome("Errado");
        return u;
    }

}