package de.reimerm.libgdxkotlinexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import de.reimerm.bubblefling.R;

/**
 * Created by Marius Reimer on 19-Sep-16.
 */
public class LoginHandler {

    private static final LoginHandler INSTANCE = new LoginHandler();
    private static final String TAG = "LoginHandler";
    public static int RC_SIGN_IN = 9001; // Request code to use when launching the resolution activity
    private GoogleApiClient mGoogleApiClient;
    private Context context;

    private LoginHandler() {
    }

    public static LoginHandler getInstance() {
        return INSTANCE;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void startApiClient() {
        if (mGoogleApiClient == null) {

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            PlayServiceListener playServiceListener = new PlayServiceListener();
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(playServiceListener)
                    .addOnConnectionFailedListener(playServiceListener)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }
    }

    public void login() {
        if (!isConnected()) {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

            if (context instanceof AndroidLauncher) {
                ((AndroidLauncher) context).startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        }
    }

    public void logout() {
        if (isConnected()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if (status.isSuccess()) {
                                mGoogleApiClient.disconnect();
                                Toast.makeText(context, R.string.logout_succeed, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public boolean isConnected() {
        return mGoogleApiClient != null && mGoogleApiClient.isConnected();
    }

    public void connect() {
        if (!isConnected()) {
            mGoogleApiClient.connect();
        }
    }

    private class PlayServiceListener implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        @Override
        public void onConnected(Bundle bundle) {
            Log.d(TAG, "onConnected");
        }

        @Override
        public void onConnectionSuspended(int i) {
            Log.d(TAG, "onConnectionSuspended");
            // Attempt to reconnect
            mGoogleApiClient.connect();
        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Log.d(TAG, "onConnectionFailed" + connectionResult);
        }
    }
}
