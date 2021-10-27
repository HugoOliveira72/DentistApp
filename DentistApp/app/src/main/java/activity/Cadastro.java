package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dentistapp.R;

import model.user;

public class Cadastro extends AppCompatActivity {

    private user usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telacadastro);

    }

    public void ChecarCampos(View view){

        //Pega os valores no layout
        EditText campoNome = findViewById(R.id.editTextNomeTelaCadastro);
        EditText campoEmail = findViewById(R.id.editTextEmailTelaCadastro);
        EditText campoSenha = findViewById(R.id.editTextPasswordTelaCadastro);
        EditText campoConfirmaSenha = findViewById(R.id.editTextPasswordConfirmTelaCadastro);

        if(validarNome(campoNome)){
            if(validarEmail(campoEmail)){
                if(validarSenha(campoSenha)){
                    if(validarConfirmaSenha(campoConfirmaSenha)){
                        if(validarSenhasIguais(campoSenha,campoConfirmaSenha)){
                            usuario = new user();
                            usuario.setNome(campoNome.getText().toString());
                            usuario.setEmail(campoEmail.getText().toString());
                            usuario.setPassword(campoSenha.getText().toString());
                            MainActivity.listaUsers.add(usuario);
                            finish();
                            Toast.makeText(this,"Cadastro efetuado com sucesso",Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(this,"Senhas não conferem",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this,"Confirme a senha!",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this,"Insira a Senha!",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"Insira o Email!",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Insira o Nome!",Toast.LENGTH_SHORT).show();
        }
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






}