package ca.cgagnier.lanadept;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.common.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import ca.cgagnier.lanadept.events.PlaceChangedEvent;
import ca.cgagnier.lanadept.events.PlaceClickedEvent;
import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.services.LanService;
import ca.cgagnier.lanadept.services.exceptions.NoLanException;

public class ListPlaceFragment extends ListFragment {

    private PlaceAdapter adapter;

    public ListPlaceFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Place> placeList = new ArrayList<>();

        try {
            for(PlaceSection section : LanService.getCurrent().getSelectedLan().sections) {
                placeList.addAll(section.placeList);
            }
        }
        catch (NoLanException ex)
        {

            new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.error_title)
                    .setMessage(R.string.error_no_lan_list_place)
                    .setPositiveButton(android.R.string.ok, null)
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                            startActivityForResult(intent, MainDrawer.INTENT_MAIN_ACTIVITY);
                        }
                    })
                    .show();
        }

        adapter = new PlaceAdapter((AppCompatActivity)getActivity(), placeList);
        setListAdapter(adapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEmptyText(getText(R.string.list_empty_place));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Place place = (Place)adapter.getItem(position);

        v.setSelected(true);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(getActivity(), DetailsPlaceActivity.class);
            intent.putExtra("PlaceID", place.id);
            getActivity().startActivity(intent);
        }
        else {
            EventBus.bus.post(new PlaceClickedEvent(place));
        }
    }

    @Subscribe
    public void placeChanged(PlaceChangedEvent e) {
        Place place = adapter.getItem(e.place);

        place.reservation = e.place.reservation;
        place.numero = e.place.numero;
        place.placeSection = e.place.placeSection;

        adapter.refreshList();
    }


    @Override
    public void onPause() {
        EventBus.bus.unregister(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        EventBus.bus.register(this);
        adapter.refreshList();
        super.onResume();
    }
}
