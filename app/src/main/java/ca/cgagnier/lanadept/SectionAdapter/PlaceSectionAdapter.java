package ca.cgagnier.lanadept.SectionAdapter;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ca.cgagnier.lanadept.models.PlaceSection;

public class PlaceSectionAdapter extends BaseAdapter {

    List<ListItem> items = new ArrayList<>();

    public PlaceSectionAdapter(AppCompatActivity activity, List<PlaceSection> placeSectionList) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    private void populateFromSection(PlaceSection section) {

    }
}
