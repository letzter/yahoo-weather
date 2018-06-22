package pt.stelvio.weather.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

import pt.stelvio.weather.R;
import pt.stelvio.weather.pojos.ForecastCondition;

/**
 * Created by stelv on 6/18/2018.
 */

public class ForecastDayAdapter extends RecyclerView.Adapter<ForecastDayAdapter.ViewHolder> {

    private ArrayList<ForecastCondition> forecastConditions;

    public ForecastDayAdapter(ArrayList<ForecastCondition> forecastConditions) {
        this.forecastConditions = forecastConditions;
    }

    public void replaceWith(Collection<ForecastCondition> forecastConditions) {
        this.forecastConditions.clear();
        this.forecastConditions.addAll(forecastConditions);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        this.forecastConditions.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_day_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.weekday.setText(forecastConditions.get(position).getDay());
        holder.weekdayMaxTemp.setText(forecastConditions.get(position).getHigh());
        holder.weekdayMinTemp.setText(forecastConditions.get(position).getLow());
    }

    @Override
    public int getItemCount() {
        return forecastConditions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView weekday;
        TextView weekdayMaxTemp;
        TextView weekdayMinTemp;

        ViewHolder(View view) {
            super(view);
            weekday = view.findViewById(R.id.forecast_weekday);
            weekdayMaxTemp = view.findViewById(R.id.forecast_max_temp);
            weekdayMinTemp = view.findViewById(R.id.forecast_min_temp);
        }
    }
}
