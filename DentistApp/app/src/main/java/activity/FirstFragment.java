package activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.dentistapp.R;
import com.example.dentistapp.databinding.FragmentFirstBinding;

import java.io.Serializable;

import helper.UsuarioDAO;
import model.user;

public class FirstFragment extends Fragment implements Serializable {

    private FragmentFirstBinding binding;
    TextView tv_Username;
    TextView tv_EmailUser;
    String Nome;
    user usuario;

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
        Nome = valor;
        tv_Username.setText(Nome);

        //Buscar resto do usuário a partir do userName;
        usuario = montarUser();

        //
        tv_EmailUser.setText(usuario.getEmail().toString());


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public user montarUser(){

        UsuarioDAO u = new UsuarioDAO(getActivity().getApplicationContext());
        return u.buscarPorNomedeUsuario(Nome);
    }




}