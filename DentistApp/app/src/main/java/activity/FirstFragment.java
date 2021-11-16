package activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentistapp.R;
import com.example.dentistapp.databinding.FragmentFirstBinding;

import java.io.Serializable;
import java.util.ArrayList;

import adapter.adapterConsulta;
import helper.ConsultaDAO;
import helper.UsuarioDAO;
import model.batata;
import model.consulta;
import model.user;

public class FirstFragment extends Fragment implements Serializable {

    private FragmentFirstBinding binding;
    TextView tv_Username;
    TextView tv_EmailUser;
    String NomeUsuario;

    user usuario;
    consulta cons;

    TextView tv_Hora;

    //-----------------------------------------------------------------//
    //Teste com o recycler view
    private ArrayList<batata> listaBatatas;
    private ArrayList<consulta> listaDeConsultas;
    private RecyclerView recyclerView;

    //-----------------------------------------------------------------//

    ConsultaDAO consultaDAO;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_Username = getActivity().findViewById(R.id.tv_Username);
        tv_EmailUser = getActivity().findViewById(R.id.tv_EmailUser);

        //Pegar o intent
        Intent intent = getActivity().getIntent();

        //Recupera o bundle
        Bundle infos = intent.getExtras();

        //Recupera o texto
        String valor = infos.getString("campo");
        NomeUsuario = valor;
        tv_Username.setText(NomeUsuario);

        //Buscar resto do usuário a partir do userName;
        usuario = montarUser();

        //Seta o email do usuario na tela
        tv_EmailUser.setText(usuario.getEmail().toString());

        //Inserir o nome do paciente e o email na tabela Consulta
        //para poder depois adicioar o resto quando for pra tela
        //cronsulta OU colocar todos os valores na classe consulta e depois adicionar no banco de dados

        cons = new consulta();
        cons.setNomePaciente(usuario.getNome());
        cons.setEmailPaciente(usuario.getEmail());

        //Liga com o front-end
        tv_Hora = getActivity().findViewById(R.id.tv_hora);

        //TESTE, seta o email do user no campo tv_Hora, textView grandao debaixo do RecyclerView
        tv_Hora.setText(cons.getEmailPaciente());

        //-----------------------------------------------------------------//
        //------------------TESTE COM RECYCLER VIEW------------------------//
        //-----------------------------------------------------------------//

        //Ligando front-end
        recyclerView = getActivity().findViewById(R.id.recyclerViewConsultas);
        listaBatatas = new ArrayList<>();
        listaDeConsultas = new ArrayList<>();

        /*

        try{
            listaDeConsultas = (ArrayList<consulta>)consultaDAO.buscar(usuario.getEmail());
        }catch (NullPointerException e){
            Log.i("INFO DB", "ERRO <<<<<<<<<NAO FOI POSSIVEL BUSCAR DADOS>>>>>>>\n"+e.getMessage());
        }

         */

        setConsultainfo();
        setAdapter();

    }

    private void setAdapter() {
        adapterConsulta adapter = new adapterConsulta(listaDeConsultas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setConsultainfo() {
        //listaBatatas.add(new batata("Asterix"));
        listaDeConsultas.clear();
        consultaDAO = new ConsultaDAO(getActivity().getApplicationContext());
        listaDeConsultas = (ArrayList<consulta>) consultaDAO.buscar(usuario.getEmail());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public user montarUser(){

        UsuarioDAO u = new UsuarioDAO(getActivity().getApplicationContext());
        return u.buscarPorNomedeUsuario(NomeUsuario);
    }

    public consulta montarConsulta(){
        ConsultaDAO c = new ConsultaDAO(getActivity().getApplicationContext());
        return (consulta) c.buscar(usuario.getEmail());
    }



}