package activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import helper.RecyclerItemClickListener;
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

    //-----------------------------------------------------------------//
    Button btn_Excluir;

    //-----------------------------------------------------------------//
    private consulta consultaSelecionada = new consulta();

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
        //tv_Hora = getActivity().findViewById(R.id.tv_hora);

        //TESTE, seta o email do user no campo tv_Hora, textView grandao debaixo do RecyclerView
        //tv_Hora.setText(cons.getEmailPaciente());

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

        //---------------------------------------------------------------------
        // ADICIONA EVENTO ON CLICK NO RECYCLER VIEW

        //btn_Excluir = getActivity().findViewById(R.id.btn_Delete_ListItem);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity().getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Pegar a movimentação selecionada
                consultaSelecionada = listaDeConsultas.get(position);
                exibirDialog();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }
        ));
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

    @Override
    public void onStart() {

        setConsultainfo();
        setAdapter();
        super.onStart();
    }

    private void exibirDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Excluir consulta");
        alert.setMessage("Você realemente deseja excluir a consulta");

        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                consultaDAO = new ConsultaDAO(getActivity().getApplicationContext());
                //Toast.makeText(getActivity(),"TESTE:"+consultaSelecionada.getId(), Toast.LENGTH_SHORT).show();
                if(consultaDAO.deletarConsulta(consultaSelecionada)){
                    Toast.makeText(getActivity(),"Consulta excluida com sucesso!", Toast.LENGTH_SHORT).show();
                    setConsultainfo();
                    setAdapter();
                }else{
                    Toast.makeText(getActivity(), "Erro ao excluir consulta", Toast.LENGTH_SHORT).show();
                }


            }
        });
        alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
            }
        });
        alert.create().show();
    }





}