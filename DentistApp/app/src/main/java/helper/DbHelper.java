package helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static int version  = 1;
    public static String NOME_DB = "DB_DENTIST";
    public static String TABELA_USUARIOS = "usuarios";

    public DbHelper (Context context){
        super(context, NOME_DB, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_USUARIOS +
        "( id INTEGER PRIMARY KEY AUTOINCREMENT," +
        "nome TEXT NOT NULL," +
        "email TEXT NOT NULL," +
        "senha PASSWORD NOT NULL );";

        try {
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO DB","Tabela usuários criada com sucesso");
        }catch (Exception error){
            Log.i("INFO DB","Erro ao criar a Tabela usuários"+error.getMessage());

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
