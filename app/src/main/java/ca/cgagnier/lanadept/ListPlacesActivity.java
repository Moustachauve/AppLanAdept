package ca.cgagnier.lanadept;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.models.PlaceSection;
import ca.cgagnier.lanadept.services.LanService;
import ca.cgagnier.lanadept.services.exceptions.NoLanException;

public class ListPlacesActivity extends AppCompatActivity {

    private MainDrawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_places);

        List<Place> placeList = new ArrayList<>();

        try {
            for(PlaceSection section : LanService.getCurrent().getSelectedLan().sections) {
                placeList.addAll(section.placeList);
            }
        }
        catch (NoLanException ex)
        {

            new AlertDialog.Builder((Context)this)
                    .setTitle(R.string.error_title)
                    .setMessage(R.string.error_no_lan)
                    .setPositiveButton(android.R.string.ok, null)
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivityForResult(intent, MainDrawer.INTENT_MAIN_ACTIVITY);
                        }
                    })
                    .show();
        }

        PlaceAdapter adapter = new PlaceAdapter(this, placeList);

        ListView listView = (ListView)findViewById(R.id.list_places);
        listView.setAdapter(adapter);

        View v = getLayoutInflater().inflate(R.layout.list_empty_place, (ViewGroup)findViewById(R.id.main_container));

        listView.setEmptyView(v);

        drawer = new MainDrawer(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawer.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawer.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawer.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (MainDrawer.INTENT_LOGIN) : {
                if (resultCode == AppCompatActivity.RESULT_OK) {
                    Toast.makeText(getBaseContext(), R.string.login_done, Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case (MainDrawer.INTENT_REGISTER) : {
                if (resultCode == AppCompatActivity.RESULT_OK) {
                    Toast.makeText(getBaseContext(), R.string.register_done, Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }

        drawer.updateMenuVisibility();
    }

}
