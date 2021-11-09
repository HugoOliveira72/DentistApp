package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import model.user;

public class UsuarioDAO implements  IUsuarioDAO{

    private SQLiteDatabase escrita;
    private SQLiteDatabase leitura;

    public UsuarioDAO(Context context){
        DbHelper db = new DbHelper(context);
        this.escrita = db.getWritableDatabase();
        this.leitura = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(user usuario) {
        ContentValues cv = new ContentValues();
        cv.put("nome",usuario.getNome());
        cv.put("email",usuario.getEmail());
        cv.put("senha",usuario.getPassword());

        try {
            escrita.insert(DbHelper.TABELA_USUARIOS, null, cv);
            Log.i("INFO DB", "Usuario cadastrado com sucesso");

        }catch (Exception e){
            Log.i("INFO DB", "Erro ao cadastrar"+e.getMessage());
            return false;

        }

        return true;
    }

    @Override
    public boolean atualizar(user usuario) {

        ContentValues cv = new ContentValues();
        cv.put("nome",usuario.getNome());
        cv.put("email",usuario.getEmail());
        cv.put("senha",usuario.getPassword());

        try {
            String[] args = {String.valueOf(usuario.getId())};
            escrita.update(DbHelper.TABELA_USUARIOS,cv,"id=?",args);
            Log.i("INFO DB", "Usuario cadastrado com sucesso");

        }catch (Exception e){
            Log.i("INFO DB", "Erro ao cadastrar"+e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public user buscar(String email, String senha) {
        String sql = "SELECT * FROM "+DbHelper.TABELA_USUARIOS+" WHERE email=? AND senha=?";
        String[] args = {email, senha};

        Cursor c = leitura.rawQuery(sql,args);

        user usu = new user();
        usu.setId(-1);

        if(c.getCount() != 0 ){
            c.moveToFirst();
            int id = c.getInt(c.getColumnIndex("id"));
            String nome_user= c.getString(c.getColumnIndex("nome"));
            String email_user = c.getString(c.getColumnIndex("senha"));

            usu.setId(id);
            usu.setNome(nome_user);
            usu.setEmail(email_user);

        }

        return usu;
    }
}
