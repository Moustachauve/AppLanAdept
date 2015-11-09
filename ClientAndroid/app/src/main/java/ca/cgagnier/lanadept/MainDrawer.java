package ca.cgagnier.lanadept;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import ca.cgagnier.lanadept.services.UserService;


public class MainDrawer implements NavigationView.OnNavigationItemSelectedListener {

    public static final int INTENT_MAIN_ACTIVITY = 0;
    public static final int INTENT_LIST_PLACE = 1;
    public static final int INTENT_LOGIN = 2;
    public static final int INTENT_REGISTER = 3;


    private Context context;
    private AppCompatActivity parentActivity;
    private NavigationView drawer;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    public MainDrawer(AppCompatActivity parentActivity) {
        this.parentActivity = parentActivity;
        context = parentActivity.getApplicationContext();

        drawer = (NavigationView)parentActivity.findViewById(R.id.left_drawer);
        drawer.setNavigationItemSelectedListener(this);

        updateMenuVisibility();

        drawerLayout = (DrawerLayout)parentActivity.findViewById(R.id.drawer_layout);

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        parentActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        parentActivity.getSupportActionBar().setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(
                parentActivity,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        );

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_home) {
            Intent intent = new Intent(parentActivity, MainActivity.class);
            parentActivity.startActivityForResult(intent, INTENT_MAIN_ACTIVITY);
            drawerLayout.closeDrawer(drawer);
            return true;
        }

        if (id == R.id.menu_liste_places) {
            Intent intent = new Intent(parentActivity, ListPlacesActivity.class);
            parentActivity.startActivityForResult(intent, INTENT_LIST_PLACE);
            drawerLayout.closeDrawer(drawer);
            return true;
        }

        if (id == R.id.menu_login) {
            Intent intent = new Intent(parentActivity, LoginActivity.class);
            parentActivity.startActivityForResult(intent, INTENT_LOGIN);
            drawerLayout.closeDrawer(drawer);
            return true;
        }

        if (id == R.id.menu_register) {
            Intent intent = new Intent(parentActivity, RegisterActivity.class);
            parentActivity.startActivityForResult(intent, INTENT_REGISTER);
            drawerLayout.closeDrawer(drawer);
            return true;
        }

        if(id == R.id.menu_logout) {
            UserService.getCurrent().logout();
            drawerLayout.closeDrawer(drawer);
            updateMenuVisibility();
            Toast.makeText(context, R.string.logout_done, Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    public void updateMenuVisibility() {
        Menu menu = drawer.getMenu();

        MenuItem signIn = menu.findItem(R.id.menu_login);
        MenuItem register = menu.findItem(R.id.menu_register);
        MenuItem signOut = menu.findItem(R.id.menu_logout);

        boolean isLoggedIn = UserService.getCurrent().isUserLoggedIn();
        signIn.setVisible(!isLoggedIn);
        register.setVisible(!isLoggedIn);
        signOut.setVisible(isLoggedIn);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item);
    }

    public void closeDrawers() {
        drawerLayout.closeDrawers();
    }

    public void syncState() {
        drawerToggle.syncState();
    }

}
