package ca.cgagnier.lanadept;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;

import ca.cgagnier.lanadept.services.DevService;
import ca.cgagnier.lanadept.services.UserService;


public class MainActivity extends AppCompatActivity {

    private static boolean isFirstTime = true;

    TextView txtCountDownDays;
    TextView txtCountDownHours;
    TextView txtCountDownMins;
    TextView txtCountDownSecs;
    TextView txtCountDownDate;
    Menu menu;

    MainDrawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isFirstTime) {
            isFirstTime = false;
            DevService.getCurrent().ResetEverything();
        }

        drawer = new MainDrawer(this);

        txtCountDownDays = (TextView)findViewById(R.id.txt_countdown_days);
        txtCountDownHours = (TextView)findViewById(R.id.txt_countdown_hours);
        txtCountDownMins = (TextView)findViewById(R.id.txt_countdown_minutes);
        txtCountDownSecs = (TextView)findViewById(R.id.txt_countdown_seconds);
        txtCountDownDate = (TextView)findViewById(R.id.txt_countdown_date);

        setTimer(new DateTime(2015, 10, 14, 12, 0));
        txtCountDownDate.setText(new DateTime(2015, 10, 14, 12, 0).toString("yyyy/MM/dd HH:mm"));
    }

    private void setTimer(DateTime startingDate) {

        CountDownTimer mCountDownTimer;
        long mInitialTime = Math.abs(startingDate.getMillis() - DateTime.now().getMillis());

        mCountDownTimer = new CountDownTimer(mInitialTime, 1000) {
            @Override
            public void onFinish() {
                txtCountDownDate.setText(R.string.message_lan_started);
            }

            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = (long)Math.floor(millisUntilFinished / 1000);
                long minutes = (long)Math.floor(seconds / 60);
                long hours = (long)Math.floor(minutes / 60);
                long days = (long)Math.floor(hours / 24);

                hours = hours - (days * 24);
                minutes = minutes - (days * 24 * 60) - (hours * 60);
                seconds = seconds - (days * 24 * 60 * 60) - (hours * 60 * 60) - (minutes * 60);

                txtCountDownDays.setText(String.format("%02d", days));
                txtCountDownHours.setText(String.format("%02d", hours));
                txtCountDownMins.setText(String.format("%02d", minutes));
                txtCountDownSecs.setText(String.format("%02d", seconds));
            }
        }.start();
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
}
