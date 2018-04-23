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
import java.util.Collection;
import java.util.List;

import effexor.roman.nikonovich.R;
import effexor.roman.nikonovich.domain.entity.choose.Model;

public class ModelAdapterSpinner extends ArrayAdapter<Model> {
    private List<Model> models = new ArrayList<>();
    private LayoutInflater flater;

    public ModelAdapterSpinner(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void addAll(@NonNull Collection<? extends Model> collection) {
        super.addAll(collection);
        this.models.clear();
        this.models.addAll(collection);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getViewItem(position, convertView, parent);
    }

    private View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if (rowView == null) {
            viewHolder = new ViewHolder();
            flater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = flater.inflate(R.layout.spiner_drop_down_item, parent, false);

            viewHolder.titleSearch = rowView.findViewById(R.id.textChoose);
            viewHolder.idSearch = rowView.findViewById(R.id.textId);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        viewHolder.titleSearch.setText(models.get(position).getModelCar());
        viewHolder.idSearch.setText(String.valueOf(models.get(position).getIdModel()));
        return rowView;
    }

    private View getViewItem(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if (rowView == null) {
            viewHolder = new ViewHolder();
            flater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = flater.inflate(R.layout.spinner_item, parent, false);

            viewHolder.titleSearch = rowView.findViewById(R.id.textChoose);
            viewHolder.idSearch = rowView.findViewById(R.id.textId);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        viewHolder.titleSearch.setText(models.get(position).getModelCar());
        viewHolder.idSearch.setText(String.valueOf(models.get(position).getIdModel()));
        return rowView;
    }

    private class ViewHolder {
        TextView titleSearch;
        TextView idSearch;
    }
}
