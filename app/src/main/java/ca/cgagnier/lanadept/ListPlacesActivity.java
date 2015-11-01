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
import android.widget.ArrayAdapter;
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
