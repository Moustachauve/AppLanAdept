package ca.cgagnier.lanadept;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.common.eventbus.*;

import ca.cgagnier.lanadept.events.PlaceChangedEvent;
import ca.cgagnier.lanadept.events.PlaceClickedEvent;
import ca.cgagnier.lanadept.models.Place;
import ca.cgagnier.lanadept.models.User;
import ca.cgagnier.lanadept.services.PlaceService;
import ca.cgagnier.lanadept.services.ReservationService;
import ca.cgagnier.lanadept.services.UserService;
import ca.cgagnier.lanadept.services.exceptions.PlaceReservedException;
import ca.cgagnier.lanadept.services.exceptions.TooManyReservationException;
import ca.cgagnier.lanadept.services.exceptions.UserNotLoggedInException;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsPlaceFragment extends Fragment {

    private Place currentPlace;

    private LinearLayout mainLayout;
    private LinearLayout actionLayout;

    private TextView lblName;
    private TextView lblSection;
    private TextView lblStatus;
    private TextView lblNotConnected;

    private Button btnReserve;
    private Button btnCancel;


    public DetailsPlaceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details_place, container, false);

        mainLayout = (LinearLayout)v.findViewById(R.id.place_detail_layout);
        actionLayout = (LinearLayout)v.findViewById(R.id.place_logged_in);

        lblName = (TextView)v.findViewById(R.id.place_name);
        lblSection = (TextView)v.findViewById(R.id.place_section);
        lblStatus = (TextView)v.findViewById(R.id.place_status);
        lblNotConnected = (TextView)v.findViewById(R.id.place_not_logged_in);

        btnReserve = (Button)v.findViewById(R.id.btn_place_reserve);
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    User loggedInUser = UserService.getCurrent().getLoggedInUser();
                    ReservationService.getCurrent().reserve(loggedInUser, currentPlace);
                    refreshPlace();
                    EventBus.bus.post(new PlaceChangedEvent(currentPlace));
                }
                catch (UserNotLoggedInException ex) {
                    ErrorDialog.show(getActivity(), getString(R.string.error_not_logged_in));
                }
                catch (TooManyReservationException ex) {
                    ErrorDialog.show(getActivity(), getString(R.string.error_too_many_reservation));
                }
                catch (PlaceReservedException ex) {
                    ErrorDialog.show(getActivity(), getString(R.string.error_place_reserved));
                }
            }
        });

        btnCancel = (Button)v.findViewById(R.id.btn_place_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    User loggedInUser = UserService.getCurrent().getLoggedInUser();
                    if(currentPlace.reservation.user.id != loggedInUser.id) {
                        ErrorDialog.show(getActivity(), getString(R.string.error_place_cancel_not_owner));
                    }
                    else {
                        ReservationService.getCurrent().cancel(currentPlace.reservation);
                        refreshPlace();
                        EventBus.bus.post(new PlaceChangedEvent(currentPlace));
                    }
                }
                catch (UserNotLoggedInException ex) {
                    ErrorDialog.show(getActivity(), getString(R.string.error_not_logged_in));
                }
                catch (NullPointerException ex) {
                    refreshPlace();
                }
            }
        });

        mainLayout.setVisibility(View.INVISIBLE);

        return v;
    }

    public void refreshPlace() {
        if(currentPlace == null)
            return;

        Place place = PlaceService.getCurrent().getById(currentPlace.id);
        setPlace(place);
    }

    public void setPlace(Place place) {
        currentPlace = place;

        lblName.setText(String.format(getString(R.string.list_place_name), place.placeSection.name, place.numero));
        lblSection.setText(String.format(getString(R.string.list_place_section), place.placeSection.name));

        if(place.reservation == null)
            lblStatus.setText(R.string.place_status_free);
        else
            lblStatus.setText(R.string.place_status_reserved);

        if(UserService.getCurrent().isUserLoggedIn()) {
            lblNotConnected.setVisibility(View.GONE);
            actionLayout.setVisibility(View.VISIBLE);

            btnCancel.setVisibility(View.GONE);

            if(place.reservation == null) {
                btnReserve.setVisibility(View.VISIBLE);
            }
            else {
                btnReserve.setVisibility(View.GONE);

                try {
                    if (place.reservation.user.id == UserService.getCurrent().getLoggedInUser().id) {
                        btnCancel.setVisibility(View.VISIBLE);
                    }
                }
                catch (UserNotLoggedInException ex) {}
            }
        }
        else {
            lblNotConnected.setVisibility(View.VISIBLE);
            actionLayout.setVisibility(View.GONE);
        }

        mainLayout.setVisibility(View.VISIBLE);
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
