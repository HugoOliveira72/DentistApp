package helper;
import android.content.Context;

import model.user;

public class AutenticarUser {

    public boolean valiarUsuario(String email, String senha, Context context){
        UsuarioDAO usuarioDAO = new UsuarioDAO(context);
        user usuario = usuarioDAO.buscar(email, senha);

        if(usuario.getId() == -1){
            return false;
        }
        return  true;
    }

}
