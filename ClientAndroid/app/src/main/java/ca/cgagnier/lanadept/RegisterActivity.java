package ca.cgagnier.lanadept;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ca.cgagnier.lanadept.services.UserService;
import ca.cgagnier.lanadept.services.exceptions.InvalidEmailException;
import ca.cgagnier.lanadept.services.exceptions.InvalidLoginException;
import ca.cgagnier.lanadept.services.exceptions.InvalidNameException;
import ca.cgagnier.lanadept.services.exceptions.InvalidPasswordConfirmationException;
import ca.cgagnier.lanadept.services.exceptions.InvalidPasswordException;
import ca.cgagnier.lanadept.services.exceptions.UserAlreadyLoggedInException;


public class RegisterActivity extends AppCompatActivity {

    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mPasswordConfirmationView;
    private EditText mFullNameView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordConfirmationView = (EditText) findViewById(R.id.password_confirmation);
        mFullNameView = (EditText) findViewById(R.id.full_name);

        final Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        mFullNameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    attemptRegister();
                    handled = true;
                }
                return handled;
            }
        });
    }

    public void attemptRegister() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mPasswordConfirmationView.setError(null);
        mFullNameView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String passwordConfirmation = mPasswordConfirmationView.getText().toString();
        String fullName = mFullNameView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check if the user entered a full name.
        if (TextUtils.isEmpty(fullName.trim())) {
            mFullNameView.setError(getString(R.string.error_field_required));
            focusView = mFullNameView;
            cancel = true;
        }

        // Check if the user entered a password confirmation.
        if (TextUtils.isEmpty(passwordConfirmation.trim())) {
            mPasswordConfirmationView.setError(getString(R.string.error_field_required));
            focusView = mPasswordConfirmationView;
            cancel = true;
        }
        else if(!password.equals(passwordConfirmation)) {
            mPasswordConfirmationView.setError(getString(R.string.error_incorrect_password_confirmation));
            focusView = mPasswordConfirmationView;
            cancel = true;
        }

        // Check if the user entered a password.
        if (TextUtils.isEmpty(password.trim())) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }
        else if(password.length() < 4) {
            mPasswordView.setError(getString(R.string.error_password_too_short));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check if the user entered an email.
        if (TextUtils.isEmpty(email.trim())) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }
        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
            mEmailView.setError(getString(R.string.error_email_invalid));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password, passwordConfirmation, fullName);
            mAuthTask.execute((Void) null);
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final String mPasswordConfirmation;
        private final String mFullName;

        UserLoginTask(String email, String password, String passwordConfirmation, String fullName) {
            mEmail = email;
            mPassword = password;
            mPasswordConfirmation = passwordConfirmation;
            mFullName = fullName;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                // Simulate network access.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return false;
            }

            try {
                UserService.getCurrent().register(mEmail, mPassword, mPasswordConfirmation, mFullName);
            }
            catch (InvalidEmailException ex) {
                ErrorDialog.show(getApplicationContext(), getString(R.string.error_email_invalid));
            }
            catch (InvalidPasswordException ex) {
                ErrorDialog.show(getApplicationContext(), getString(R.string.error_incorrect_password));
            }
            catch (InvalidPasswordConfirmationException ex) {
                ErrorDialog.show(getApplicationContext(), getString(R.string.error_incorrect_password_confirmation));
            }
            catch (InvalidNameException ex) {
                ErrorDialog.show(getApplicationContext(), getString(R.string.error_name_invalid));
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                setResult(AppCompatActivity.RESULT_OK);
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

