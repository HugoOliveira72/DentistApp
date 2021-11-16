package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dentistapp.R;

import helper.UsuarioDAO;
import model.user;

public class Cadastro extends AppCompatActivity {


    private EditText campoSenha;
    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoConfirmaSenha;
    private Button botaoCadastrar;
    private user usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telacadastro);

            //Pega os valores no layout
            campoNome = findViewById(R.id.editTextNomeTelaCadastro);
            campoEmail = findViewById(R.id.editTextEmailTelaCadastro);
            campoSenha = findViewById(R.id.editTextPasswordTelaCadastro);
            campoConfirmaSenha = findViewById(R.id.editTextPasswordConfirmTelaCadastro);
            botaoCadastrar = findViewById(R.id.buttonCadastrar);

            botaoCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (validarNome(campoNome)) {
                        if (validarEmail(campoEmail)) {
                            if (validarSenha(campoSenha)) {
                                if (validarConfirmaSenha(campoConfirmaSenha)) {
                                    if (validarSenhasIguais(campoSenha, campoConfirmaSenha)) {
                                        if (cadastrarUser()) {
                                            finish();
                                            Toast.makeText(Cadastro.this, "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Cadastro.this, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(Cadastro.this, "Senhas não conferem", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(Cadastro.this, "Confirme a senha!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Cadastro.this, "Insira a Senha!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Cadastro.this, "Insira o Email!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Cadastro.this, "Insira o Nome!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    public boolean validarNome(EditText campoNome){

        if(campoNome.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validarEmail(EditText campoEmail){

        if(campoEmail.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validarSenha(EditText campoSenha){


        if(campoSenha.getText().toString().isEmpty()) {
            return false;
        }
        return true;

    }

    public boolean validarConfirmaSenha(EditText campoConfirmaSenha){

        if(campoConfirmaSenha.getText().toString().isEmpty()) {
            return false;
        }
        return true;

    }

    public boolean validarSenhasIguais(EditText campoSenha,EditText campoConfirmaSenha){

        String senha = campoSenha.getText().toString();
        String confirmaSenha = campoConfirmaSenha.getText().toString();

        if(senha.equals(confirmaSenha)) {
            return true;
        }
        return false;
    }

    public boolean cadastrarUser(){
        usuario = new user();
        usuario.setNome(campoNome.getText().toString());
        usuario.setEmail(campoEmail.getText().toString());
        usuario.setPassword(campoSenha.getText().toString());

        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());

        return usuarioDAO.salvarUsuario(usuario);

    }



}