package com.mypushtak.app;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    public static int APP_REQUEST_CODE = 99;
    PhoneNumber phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        printKeyHash();

        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken != null) {
            Toast.makeText(this, "already entered", Toast.LENGTH_SHORT).show();
            //Handle Returning User
        } else {
            //Handle new or logged out user
        }
    }

    private void printKeyHash() {
        try {
            PackageInfo info=getPackageManager().getPackageInfo("com.mypushtak.app",
                    PackageManager.GET_SIGNATURES);
            for(Signature signature:info.signatures){
                MessageDigest md=MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KEYHASH", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void loginstart(View view)
    {
        final Intent intent = new Intent(getBaseContext(), AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.CODE); // or .ResponseType.TOKEN
        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
                Log.e("error", String.valueOf(loginResult.getError()));
//                Toast.makeText(this, ""+loginResult.getError(), Toast.LENGTH_SHORT).show();
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
//                Toast.makeText(this, ""+toastMessage, Toast.LENGTH_SHORT).show();
            } else {
                if (loginResult.getAccessToken() != null) {

                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                    gotocollectphoneno();
//                    Toast.makeText(this, ""+toastMessage, Toast.LENGTH_SHORT).show();
                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0,10));
                }

                // If you have an authorization code, retrieve it from
                // loginResult.getAuthorizationCode()
                // and pass it to your server and exchange it for an access token.

                // Success! Start your next activity...
                if (phoneNumber != null) {
                    String phoneNumberString = phoneNumber.toString();
                    Toast.makeText(this, ""+phoneNumberString, Toast.LENGTH_SHORT).show();
                }
                Intent i1=new Intent(this,LoginActivity.class);
                startActivity(i1);
            }

            // Surface the result to your user in an appropriate way.
            Toast.makeText(
                    this,
                    toastMessage,
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void gotocollectphoneno()
    {
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                // Get Account Kit ID
                String accountKitId = account.getId();

                // Get phone number
                phoneNumber = account.getPhoneNumber();
                if (phoneNumber != null) {
                    String phoneNumberString = phoneNumber.toString();
                }


                // Get email
                String email = account.getEmail();
            }

            @Override
            public void onError(final AccountKitError error) {
                // Handle Error
            }
        });
    }


    public void logoutstart(View view)
    {
        AccountKit.logOut();
        Toast.makeText(this, "logged out sucessfully!", Toast.LENGTH_SHORT).show();
    }
}
