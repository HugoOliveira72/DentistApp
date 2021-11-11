package activity;

import android.os.Build;
import android.os.Bundle;

import com.example.dentistapp.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentistapp.databinding.ActivityPrincipalBinding;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.user;

public class Principal extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private AppBarConfiguration appBarConfiguration;
    private ActivityPrincipalBinding binding;


    //-------------------------------
    private TextView mesAnoText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initWidgets();
        selectedDate = LocalDate.now();
        setMesView();

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //textoNome = findViewById(R.id.textviewNome);
        //usuario = (user) getIntent().getSerializableExtra("usuario");

        //atualizarFragment();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        mesAnoText = findViewById(R.id.textViewMesAno);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMesView() {
        mesAnoText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> diasNoMes = diasNoMesArray(selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(diasNoMes,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> diasNoMesArray(LocalDate date) {
        ArrayList<String> diasNoMesArray = new ArrayList<>();
        YearMonth anoMes = YearMonth.from(date);
        int diasNoMes = anoMes.lengthOfMonth();
        LocalDate primeiroDoMes = selectedDate.withDayOfMonth(1);
        int diaDaSemana = primeiroDoMes.getDayOfWeek().getValue();

        for( int i=1; i<= 42; i++){
            if (i < diaDaSemana || i > diasNoMes + diaDaSemana){
                diasNoMesArray.add("");

            }else{
                diasNoMesArray.add(String.valueOf(i - diaDaSemana));
            }
        }
        return diasNoMesArray;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*
    public void atualizarFragment(){
        textoNome.setText("Usuário: "+usuario.getNome());
    }

     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void mesAnteriorAction(View view){
        selectedDate = selectedDate.minusMonths(1);
        setMesView();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void mesSeguinteAction(View view){
        selectedDate = selectedDate.plusMonths(1);
        setMesView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText) {
        if(dayText.equals("")){
            String message = "Selected Date" + dayText + monthYearFromDate(selectedDate);
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        }
    }
}