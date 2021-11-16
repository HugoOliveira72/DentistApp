package helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.consulta;
import model.user;

public class ConsultaDAO implements IConsultaDAO{
    private final SQLiteDatabase escrita;
    private final SQLiteDatabase leitura;



    public ConsultaDAO(Context context){
        DbHelper db = new DbHelper(context);
        this.escrita = db.getWritableDatabase();
        this.leitura = db.getReadableDatabase();
    }


    public boolean salvarConsulta(consulta cons){
        ContentValues cv = new ContentValues();
        cv.put("nomepaciente",cons.getNomePaciente());
        cv.put("email",cons.getEmailPaciente());
        cv.put("motivo",cons.getMotivo());
        cv.put("data",cons.getData());
        cv.put("hora",cons.getHora());


        try {
            escrita.insert(DbHelper.TABELA_CONSULTA, null, cv);
            Log.i("INFO DB", "Consulta cadastrada com sucesso");

        }catch (Exception e){
            Log.i("INFO DB", "Erro ao cadastrar"+e.getMessage());
            return false;

        }

        return true;
    }


    public boolean atualizarConsulta(consulta cons){
        ContentValues cv = new ContentValues();
        cv.put("nomepaciente",cons.getNomePaciente());
        cv.put("email",cons.getEmailPaciente());
        cv.put("motivo",cons.getMotivo());
        cv.put("data",cons.getData());
        cv.put("hora",cons.getHora());

        try {
            String[] args = {String.valueOf(cons.getId())};
            escrita.update(DbHelper.TABELA_CONSULTA,cv,"id=?",args);
            Log.i("INFO DB", "Consulta atualizado com sucesso");

        }catch (Exception e){
            Log.i("INFO DB", "Erro ao atualizar"+e.getMessage());
            return false;
        }

        return true;
    }

    public List<consulta> buscar(String email){

        List<consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM "+DbHelper.TABELA_CONSULTA + " WHERE email=?;";
        String[] arg = {email};

        Cursor cursor = leitura.rawQuery(sql,arg);

        while(cursor.moveToNext()){
            consulta consult = new consulta();
            int id_consulta = cursor.getInt(cursor.getColumnIndex("id_consulta"));
            String nomeUser = cursor.getString(cursor.getColumnIndex("nomePaciente"));
            String emailUser = cursor.getString(cursor.getColumnIndex("email"));
            String motivo = cursor.getString(cursor.getColumnIndex("motivo"));
            String data = cursor.getString(cursor.getColumnIndex("data"));
            String hora = cursor.getString(cursor.getColumnIndex("hora"));

            consult.setId(id_consulta);
            consult.setNomePaciente(nomeUser);
            consult.setEmailPaciente(emailUser);
            consult.setMotivo(motivo);
            consult.setData(data);
            consult.setHora(hora);

            consultas.add(consult);

        }

        return consultas;
    }

    public List<consulta> buscarPorData(String DATA){

        List<consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM "+DbHelper.TABELA_CONSULTA + " WHERE data=?;";
        String[] arg = {DATA};

        Cursor cursor = leitura.rawQuery(sql,arg);

        while(cursor.moveToNext()){
            consulta consult = new consulta();
            int id_consulta = cursor.getInt(cursor.getColumnIndex("id_consulta"));
            String nomeUser = cursor.getString(cursor.getColumnIndex("nomePaciente"));
            String emailUser = cursor.getString(cursor.getColumnIndex("email"));
            String motivo = cursor.getString(cursor.getColumnIndex("motivo"));
            String data = cursor.getString(cursor.getColumnIndex("data"));
            String hora = cursor.getString(cursor.getColumnIndex("hora"));

            consult.setId(id_consulta);
            consult.setNomePaciente(nomeUser);
            consult.setEmailPaciente(emailUser);
            consult.setMotivo(motivo);
            consult.setData(data);
            consult.setHora(hora);

            consultas.add(consult);

        }

        return consultas;
    }

    public boolean deletarConsulta(consulta consult){

        try {
            String[] args = {String.valueOf(consult.getId())};
            escrita.delete(DbHelper.TABELA_CONSULTA,"id_consulta=?",args);
            Log.i("INFO DB", "Consulta deletada com sucesso");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao deletar consulta"+e.getMessage());
            return false;
        }

        return true;
    }
}
