package ca.cgagnier.lanadept;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Christophe on 2015-10-31.
 */
public class ErrorDialog {

    public static void show(Context context, String text) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.error_title)
                .setMessage(text)
                .setPositiveButton(android.R.string.ok, null)
                .show();

    }
}
