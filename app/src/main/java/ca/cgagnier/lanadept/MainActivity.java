package ca.cgagnier.lanadept;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;


public class MainActivity extends AppCompatActivity {

    TextView txtCountDownDays;
    TextView txtCountDownHours;
    TextView txtCountDownMins;
    TextView txtCountDownSecs;
    TextView txtCountDownDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        invalidateOptionsMenu();

        txtCountDownDays = (TextView)findViewById(R.id.txt_countdown_days);
        txtCountDownHours = (TextView)findViewById(R.id.txt_countdown_hours);
        txtCountDownMins = (TextView)findViewById(R.id.txt_countdown_minutes);
        txtCountDownSecs = (TextView)findViewById(R.id.txt_countdown_seconds);
        txtCountDownDate = (TextView)findViewById(R.id.txt_countdown_date);

        setTimer(new DateTime(2015, 10, 14, 12, 0));
        txtCountDownDate.setText(new DateTime(2015, 10, 14, 12, 0).toString("yyyy/MM/dd HH:mm"));
    }
;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem signIn = menu.findItem(R.id.menu_sign_in);
        MenuItem signOut = menu.findItem(R.id.menu_sign_out);

        signOut.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_sign_in) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setTimer(DateTime startingDate) {

        CountDownTimer mCountDownTimer;
        long mInitialTime = Math.abs(startingDate.getMillis() - DateTime.now().getMillis());

        mCountDownTimer = new CountDownTimer(mInitialTime, 1000) {
            @Override
            public void onFinish() {
                //mTextView.setText(DateUtils.formatElapsedTime(0));
                //mTextView.setText("Times Up!");
            }

            @Override
            public void onTick(long millisUntilFinished) {
                //time.setLength(0);
                // Use days if appropriate
//                if(millisUntilFinished > DateUtils.DAY_IN_MILLIS) {
//                    long count = millisUntilFinished / DateUtils.DAY_IN_MILLIS;
//                    txtCountDownDays.setText(String.valueOf(count));
//
//                    millisUntilFinished %= DateUtils.DAY_IN_MILLIS;
//                }

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
}
