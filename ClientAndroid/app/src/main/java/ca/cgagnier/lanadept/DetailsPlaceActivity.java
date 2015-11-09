package ca.cgagnier.lanadept;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.services.PlaceService;

public class DetailsPlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_place);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        long placeID = intent.getLongExtra("PlaceID", -1);
        DetailsPlaceFragment fragment = (DetailsPlaceFragment)getFragmentManager().findFragmentById(R.id.fragment);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Intent backIntent = new Intent(this, ListPlacesActivity.class);
            this.startActivity(backIntent);
        }


        Place place = PlaceService.getCurrent().getById(placeID);

        getSupportActionBar().setTitle(String.format(getString(R.string.list_place_name), place.placeSection.name, place.numero));
        fragment.setPlace(place);

    }
}
