package com.mypushtak.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.mypushtak.app.Bean.ConstantUrl;
import com.mypushtak.app.Bean.MySignleton;
import com.mypushtak.app.Bean.PrefUtil;
import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.ProductFullView;
import com.mypushtak.app.Singleton.ProfileDetails;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignIn extends AppCompatActivity{
    SignInButton google;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=1;
    LoginButton loginButton;
    CallbackManager callbackManager;
    EditText email,password;
    ImageView showVisible;
    ImageView showVisibleoff;
    Button signIn;
    ProgressBar progressBar;
    private ProfileDetails pf=new ProfileDetails();
    private  PrefUtil prefUtil=new PrefUtil(SignIn.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_sign_in);
        google=findViewById(R.id.google);
        callbackManager = CallbackManager.Factory.create();
        progressBar=findViewById(R.id.signin_progress);
        loginButton=findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        showVisible=findViewById(R.id.showvisible);
        showVisibleoff=findViewById(R.id.showvisibleoff);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signIn=findViewById(R.id.signin);
        progressBar.setVisibility(View.GONE);



        showVisibleoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                showVisible.setVisibility(View.VISIBLE);
                showVisibleoff.setVisibility(View.GONE);
            }
        });

        showVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                showVisibleoff.setVisibility(View.VISIBLE);
                showVisible.setVisibility(View.GONE);
            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals(""))
                    password.setError("cant be blank");
                else if(password.getText().toString().equals(""))
                    password.setError("cant be blank");
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    try {
                        String pass=getMD5(password.getText().toString().trim());
                        signInAuthenticate(email.getText().toString().trim(),pass);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }


                }
            }
        });




        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {


                        String accessToken = loginResult.getAccessToken().getToken();

                        // save accessToken to SharedPreference
                        PrefUtil prefUtil=new PrefUtil(SignIn.this);
                        prefUtil.saveAccessToken(accessToken);

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject jsonObject,
                                                            GraphResponse response) {

                                        // Getting FB User Data
                                        Bundle facebookData = getFacebookData(jsonObject);


                                    }
                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,first_name,last_name,email,gender");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }


                    @Override
                    public void onCancel () {
                        Log.d("abcd", "Login attempt cancelled.");
                    }

                    @Override
                    public void onError (FacebookException e){
                        e.printStackTrace();
                        Log.d("abcd", "Login attempt failed.");
                        deleteAccessToken();
                    }
                }
        );



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("abcd","in click");
                signIn();

            }
        });


    }

    private void deleteAccessToken() {
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null){
                    //User logged out
                    PrefUtil prefUtil=new PrefUtil(SignIn.this);
                    prefUtil.clearToken();
                    //LoginManager.getInstance().logOut();
                }
            }
        };
    }

    private void signInAuthenticate(final String emailData, final String passwordData) {

        String url=ConstantUrl.URL+"user_signin/"+emailData;


        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            JSONArray jsonArray=new JSONArray(response);


                            for (int i=0;i<jsonArray.length(); i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                int id=jsonObject.optInt("id");
                                String email=jsonObject.optString("emailid");
                                String password=jsonObject.optString("password");
                                String alternative_email=jsonObject.optString("alternative_email");
                                String avatar=jsonObject.optString("avatar");
                                Long number=jsonObject.optLong("mobile");
                                String name=jsonObject.optString("first_name");

                                ProfileDetails profileDetails=new ProfileDetails(id,email,password,alternative_email,avatar,number,name);

                                if (emailData.equalsIgnoreCase(email)&&passwordData.equalsIgnoreCase(password))
                                {
                                    Log.d("paaaaaa",""+pf.getEmail());
                                    prefUtil.saveFacebookUserInfo("","",emailData,passwordData);
                                    progressBar.setVisibility(View.GONE);
                                    Intent j=new Intent(SignIn.this,MainActivity.class);
                                    startActivity(j);
                                }
                                else
                                {

                                    Log.d("abcdefg",""+emailData+"  "+email+"    "+passwordData+"   "+password);
                                    Log.d("paaaalaa",""+pf.getEmail());
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(),"Enter Correct Email or Password",Toast.LENGTH_SHORT).show();
                                }


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("MarketingError",error.toString());
                error.printStackTrace();

            }
        });
        MySignleton.getInstance(getApplicationContext()).addToRequestqueue(stringRequest);








    }




    public void signInFetch(String url) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            JSONArray jsonArray=new JSONArray(response);


                            for (int i=0;i<jsonArray.length(); i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                int id=jsonObject.optInt("id");
                                String email=jsonObject.optString("emailid");
                                String password=jsonObject.optString("password");
                                String alternative_email=jsonObject.optString("alternative_email");
                                String avatar=jsonObject.optString("avatar");
                                Long number=jsonObject.optLong("mobile");
                                String name=jsonObject.optString("first_name");

                                ProfileDetails profileDetails=new ProfileDetails(id,email,password,alternative_email,avatar,number,name);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("MarketingError",error.toString());
                error.printStackTrace();

            }
        });
        MySignleton.getInstance(getApplicationContext()).addToRequestqueue(stringRequest);
    }





    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Log.d("abcd","in on activity");
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Log.d("abcd",""+account.getEmail()+"  "+account.getId()+"  "+account.getDisplayName()+"   "+account.getFamilyName()+"    "+account.getServerAuthCode()+"   "+account.getPhotoUrl()+"  "+account.getAccount());
            Log.d("unique123",""+account.getPhotoUrl()+"     "+account.getId());

            String email_id=account.getEmail();
            String first_name=account.getGivenName();
            String last_name=account.getFamilyName();
//
//            PrefUtil prefUtil=new PrefUtil(SignIn.this);
//            prefUtil.saveFacebookUserInfo(first_name,
//                    last_name,email_id,"");

            String url=ConstantUrl.URL+"google_signin/"+email_id+"/"+first_name+"/"+last_name;

            checkSignIn(url,email_id,first_name,last_name);

        } catch (ApiException e) {
            Log.w("abcd", "signInResult:failed code=" + e.getStatusCode()+e.getMessage().toString());

        }
    }

    private Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();

        try {
            String id = object.getString("id");
            URL profile_pic;
            try {
                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));



            String email=object.getString("email");
            String first_name=object.getString("first_name");
            String last_name=object.getString("last_name");

//            prefUtil.saveFacebookUserInfo(object.getString("first_name"),
//                    object.getString("last_name"),object.getString("email"), "");
//
//
//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//            String first_name= prefs.getString("fb_first_name","");
//            String last_name= prefs.getString("fb_last_name","");
//            String email= prefs.getString("fb_email","");

            String url=ConstantUrl.URL+"google_signin/"+email+"/"+first_name+"/"+last_name;

            checkSignIn(url,email,first_name,last_name);

            Log.d("superunique",""+first_name+"  "+last_name+"  "+email);
        } catch (Exception e) {
            Log.d("abcd", "BUNDLE Exception : "+e.toString());
        }

        return bundle;
    }

    private void checkSignIn(String url, final String email, final String first_name, final String last_name) {
        Log.d("final shared",""+url);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String url=ConstantUrl.URL+"user_signin/"+email;

                        Log.d("final shared",""+url+"  "+response.getBytes().toString());
                        signInFetchForIntegration(url,first_name,last_name,email);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("MarketingError",error.toString());
                error.printStackTrace();

            }
        });
        MySignleton.getInstance(getApplicationContext()).addToRequestqueue(stringRequest);

    }

    private void signInFetchForIntegration(String url, final String first_name, final String last_name, final String email) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            JSONArray jsonArray=new JSONArray(response);


                            for (int i=0;i<jsonArray.length(); i++)
                            {
                                Log.d("last mein",""+response.getBytes().toString());
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String password=jsonObject.optString("password");
                                Log.d("final pshared",""+password);
                                prefUtil.saveFacebookUserInfo(first_name,last_name,email,password);

                                Log.d("final shared",""+password);







                                Intent intent=new Intent(SignIn.this,MainActivity.class);
                                startActivity(intent);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("MarketingError",error.toString());
                error.printStackTrace();

            }
        });
        MySignleton.getInstance(getApplicationContext()).addToRequestqueue(stringRequest);
    }

    public static String getMD5(String data) throws NoSuchAlgorithmException
    {
        MessageDigest messageDigest=MessageDigest.getInstance("MD5");

        messageDigest.update(data.getBytes());
        byte[] digest=messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(Integer.toHexString((int) (b & 0xff)));
        }
        return sb.toString();
    }

}
