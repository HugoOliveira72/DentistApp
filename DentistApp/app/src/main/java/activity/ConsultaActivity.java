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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConsultaActivity extends AppCompatActivity {

    //Motivo
    private String motivo;

    //DATA
    EditText etData;
    DatePickerDialog.OnDateSetListener setListener;
    public String Data;

    //Hora
    EditText Tempo;
    TimePickerDialog timePickerDialog;
    int horaAtual;
    int minutoAtual;
    public String Hora;

    Calendar calendar = Calendar.getInstance();

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

        //Pegar o valor no edittext e colocar no atributo data
        this.Data = etData.getText().toString();


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

        //Pegar o valor no edittext e colocar no atributo hora
        this.Hora = Tempo.getText().toString();

        //---------------------------------------------------------------------//
    }

    public void teste(View view){
        Toast.makeText(ConsultaActivity.this, "Data:"+Data+"\nHora:"+Hora, Toast.LENGTH_SHORT).show();
    }

}