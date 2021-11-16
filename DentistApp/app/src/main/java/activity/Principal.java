package activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.dentistapp.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentistapp.databinding.ActivityPrincipalBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import adapter.adapterConsulta;
import adapter.adapterConsulta;
import helper.UsuarioDAO;
import model.batata;
import model.consulta;
import model.user;

public class Principal extends AppCompatActivity implements Serializable {

    private AppBarConfiguration appBarConfiguration;
    private ActivityPrincipalBinding binding;

    //
    user usuario;
    consulta Consulta = new consulta();

    //
    TextView tv_Usernome;

    //
    Button btn_Teste;

    //
    private consulta consultaSelecionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);

        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //FAB
        binding.fabAdicionarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Principal.this, "USUARIO:"+Consulta.getNomePaciente()+"\nDATA:"+Consulta.getData(), Toast.LENGTH_SHORT).show();
                abrirTelaConsulta(view);
            }
        });

        btn_Teste = findViewById(R.id.btn_Delete_ListItem);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void abrirTelaConsulta(View view){
        startActivity(new Intent(this,ConsultaActivity.class));
    }

    public void ExibirDialog(View view){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Excluir consulta");
            alert.setMessage("Você realemente deseja excluir a consulta");
            alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Excluirconsulta();
                    Toast.makeText(Principal.this, "Consulta excluida com sucesso!", Toast.LENGTH_SHORT).show();
                }
            });
            alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(Principal.this, "!--------------!", Toast.LENGTH_SHORT).show();
                }
            });
            alert.create().show();
    }

    public void Excluirconsulta(){

    }



}