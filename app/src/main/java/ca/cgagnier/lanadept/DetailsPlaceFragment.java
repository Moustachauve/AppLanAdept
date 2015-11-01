package ca.cgagnier.lanadept;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.eventbus.*;

import ca.cgagnier.lanadept.events.PlaceClickedEvent;
import ca.cgagnier.lanadept.models.Place;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsPlaceFragment extends Fragment {

    private Place currentPlace;

    private TextView lblName;
    private TextView lblSection;
    private TextView lblStatus;


    public DetailsPlaceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details_place, container, false);

        lblName = (TextView)v.findViewById(R.id.place_name);
        lblSection = (TextView)v.findViewById(R.id.place_section);
        lblStatus = (TextView)v.findViewById(R.id.place_status);

        return v;
    }

    public void setPlace(Place place) {
        currentPlace = place;

        lblName.setText(String.format(getString(R.string.list_place_name), place.placeSection.name, place.numero));
        lblSection.setText(String.format(getString(R.string.list_place_section), place.placeSection.name));

        if(place.reservation == null)
            lblStatus.setText(R.string.place_status_free);
        else
            lblStatus.setText(R.string.place_status_reserved);
    }

    @Subscribe
    public void selectedPlaceChanged(PlaceClickedEvent e) {
        setPlace(e.place);
    }

    @Override
    public void onPause() {
        EventBus.bus.unregister(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        EventBus.bus.register(this);
        super.onResume();
    }
}
