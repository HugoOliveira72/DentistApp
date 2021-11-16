package helper;

import model.user;

public interface IUsuarioDAO {

    public  boolean salvarUsuario(user usuario );

    public boolean atualizar(user usuario);

    public user buscarPorEmailESenha(String email, String senha);

    public user buscarPorNomedeUsuario(String username);

}
