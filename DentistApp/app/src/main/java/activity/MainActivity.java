package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dentistapp.R;

import java.util.ArrayList;
import java.util.List;

import model.user;

public class MainActivity extends AppCompatActivity {

    public static List<user> listaUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void AbrirTelaCadastro(View view){
        startActivity(new Intent(this, Cadastro.class));
    }

    public void AbrirTelaLogin(View view){
        startActivity(new Intent(this, Login.class));
    }
}