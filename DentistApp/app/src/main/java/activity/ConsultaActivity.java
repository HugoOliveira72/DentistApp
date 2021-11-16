package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dentistapp.R;

import java.util.Calendar;

import helper.ConsultaDAO;
import model.consulta;

public class ConsultaActivity extends AppCompatActivity {

    //Motivo
    EditText et_motivo;
    String motivo;

    //DATA
    EditText etData;
    DatePickerDialog.OnDateSetListener setListener;
    private String Data;

    //Hora
    EditText Tempo;
    TimePickerDialog timePickerDialog;
    int horaAtual;
    int minutoAtual;
    private String Hora;

    //Pega instancia do calendário
    Calendar calendar = Calendar.getInstance();

    //NomeUsuario
    //private String Username;

    //EMAIL
   // private String Email;

    //CONSULTA
    private consulta cons = new consulta();

    //BUTTON
    Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        //---------------------------------------------------------------------//
        //DATE TIME PICKER
        etData = findViewById(R.id.et_Data);


        final int ano = calendar.get(Calendar.YEAR);
        final int mes = calendar.get(Calendar.MONTH);
        final int dia = calendar.get(Calendar.DAY_OF_MONTH);

        etData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ConsultaActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int ano, int mes, int dia) {
                                mes = mes+1;
                                String data = dia+"/"+mes+"/"+ano;
                                etData.setText(data);
                                Data = data;
                            }
                        },
                        ano,
                        mes,
                        dia);
                datePickerDialog.show();
            }
        });




        //---------------------------------------------------------------------//
        //POPUP TIME PICKER

        Tempo = findViewById(R.id.et_Time);
        Tempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                horaAtual = calendar.get(Calendar.HOUR_OF_DAY);
                minutoAtual = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(ConsultaActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourofDay, int minutes) {
                        String horario = (hourofDay + ":" + minutes);
                        Tempo.setText(horario);
                        Hora = horario;
                    }
                },horaAtual,minutoAtual,true);
                timePickerDialog.show();
            }
        });

        //Pegar os valores e define para os atributos ConsultActivy

        this.Data = etData.getText().toString();
        this.Hora = Tempo.getText().toString();
        et_motivo = findViewById(R.id.editTextMotivoBox);
        motivo = et_motivo.getText().toString();

        //---------------------------------------------------------------------//
        //Consulta

        //---------------------------------------------------------------------//

    }

    public void teste(View view){
        //Distribui os valores para os atributos da classe consulta

        cons.setMotivo(et_motivo.getText().toString());
        cons.setData(Data);
        cons.setHora(Hora);

        Toast.makeText(ConsultaActivity.this, "Usuario:"+cons.getNomePaciente().toString()+"\nMotivo:"+cons.getMotivo().toString()+"\nData:"+cons.getData().toString()
                +"\nHora:"+cons.getHora().toString(),Toast.LENGTH_SHORT).show();

        /*
                "\n==================\n" +
                "Atributos da classe:\n" +
                "Usuario:"+cons.getNomePaciente()+"\n" +
                "Email:"+cons.getEmailPaciente()+"\n" +
                "Motivo:"+cons.getMotivo()+"\n" +
                "Data:"+cons.getData()+"\n" +
                "Hora:"+cons.getHora()+"\n" , Toast.LENGTH_SHORT).show();
        */
    }

    public boolean cadastrarConsulta(){
        cons.setMotivo(et_motivo.getText().toString());
        cons.setData(Data);
        cons.setHora(Hora);

        ConsultaDAO consultaDAO = new ConsultaDAO(getApplicationContext());

        return consultaDAO.salvarConsulta(cons);
    }

    public void continuarAction(View view){
        if(cadastrarConsulta()){
            Toast.makeText(ConsultaActivity.this, "Consulta cadastrada com sucesso!:",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ConsultaActivity.this, "Erro ao cadastrar consulta!:",Toast.LENGTH_SHORT).show();
        }
    }




}