package activity;

import android.view.View;
import android.widget.TextView;
import com.example.dentistapp.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class calendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final TextView diaDoMes;
    private final CalendarAdapter.OnItemListener onItemListener;

    public calendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        diaDoMes = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(getAdapterPosition(), (String) diaDoMes.getText());
    }
}
