package effexor.roman.nikonovich.presentation.screens.formFilling.spinerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import effexor.roman.nikonovich.R;
import effexor.roman.nikonovich.domain.entity.entityChoose.Model;

public class ModelAdapterSpinner extends ArrayAdapter<Model> {
    private List<Model> models = new ArrayList<>();

    public ModelAdapterSpinner(@NonNull Context context, int resource, List<Model> models) {
        super(context, resource, models);
        this.models = models;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.spiner_drop_down_item, parent, false);
        TextView makeCar = row.findViewById(R.id.textChoose);
        TextView makeId = row.findViewById(R.id.textId);
        makeCar.setText(models.get(position).getModelCar());
        makeId.setText(String.valueOf(models.get(position).getIdModel()));
        return row;
    }
}
