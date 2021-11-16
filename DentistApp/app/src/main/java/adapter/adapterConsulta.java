package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentistapp.R;

import java.util.ArrayList;

import model.batata;
import model.consulta;

public class adapterConsulta extends RecyclerView.Adapter<adapterConsulta.MyViewHolder>{

    private ArrayList<consulta> listaconsultas;
    private ArrayList<batata> listadeBatatas;


    public adapterConsulta(ArrayList<consulta> listaconsultas){
        this.listaconsultas = listaconsultas;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView DataText;
        private TextView MotivoText;
        private TextView HoraText;

        public MyViewHolder(final View view){
            super(view);
            DataText = view.findViewById(R.id.tv_Data_ListItem);
            MotivoText = view.findViewById(R.id.tv_Motivo_ListItem);
            HoraText = view.findViewById(R.id.tv_Hora_ListItem);

        }
    }

    @NonNull
    @Override
    public adapterConsulta.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull adapterConsulta.MyViewHolder holder, int position) {
        //
        String Datas = listaconsultas.get(position).getData();
        String Motivos = listaconsultas.get(position).getMotivo();
        String Horarios = listaconsultas.get(position).getHora();
        holder.DataText.setText(Datas);
        holder.MotivoText.setText(Motivos);
        holder.HoraText.setText(Horarios);
        //
    }

    @Override
    public int getItemCount() {
        return listaconsultas.size();
    }
}