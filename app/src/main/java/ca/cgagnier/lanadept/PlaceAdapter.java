package ca.cgagnier.lanadept;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.text.TextDirectionHeuristic;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.repositories.exceptions.NotFoundException;

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

        Place place = (Place)getItem(position);
        TextView placeName = (TextView)v.findViewById(R.id.place_name);
        TextView placeSection = (TextView)v.findViewById(R.id.place_section);
        TextView placeStatus = (TextView)v.findViewById(R.id.place_status);

        placeName.setText(String.format(parentActivity.getString(R.string.list_place_name), place.placeSection.name, place.numero));
        placeSection.setText(String.format(parentActivity.getString(R.string.list_place_section), place.placeSection.name));

        if(place.reservation == null)
            placeStatus.setBackgroundResource(R.color.placeFree);
        else
            placeStatus.setBackgroundResource(R.color.placeReserved);

        return v;
    }

    public Place getItem(Place place) {
        for(Place currPlace : placeList) {
            if(currPlace.id == place.id)
                return currPlace;
        }

        throw new NotFoundException();
    }

    public void refreshList() {
        notifyDataSetChanged();
    }
}
