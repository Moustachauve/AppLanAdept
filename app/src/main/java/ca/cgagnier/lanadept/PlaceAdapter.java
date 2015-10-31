package ca.cgagnier.lanadept;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import ca.cgagnier.lanadept.models.Place;

public class PlaceAdapter extends BaseAdapter {

    AppCompatActivity parentActivity;
    List<Place> placeList = new ArrayList<>();

    public PlaceAdapter(AppCompatActivity parentActivity, List<Place> placeList) {
        this.parentActivity = parentActivity;
        this.placeList.addAll(placeList);
    }

    @Override
    public int getCount() {
        return placeList.size();
    }

    @Override
    public Object getItem(int position) {
        return placeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) parentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.list_item_place, null);
        }

        return v;
    }
}
