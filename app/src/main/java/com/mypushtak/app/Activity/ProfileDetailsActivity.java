package com.mypushtak.app.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mypushtak.app.R;
import com.mypushtak.app.Singleton.ProfileDetails;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class ProfileDetailsActivity extends AppCompatActivity {

    Button change_password, save;

    Toolbar mToolbar;
    //    private ImageView back_button;
    TextView mTextview, upload_profile;

    EditText email, name, alternate_email, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        change_password = findViewById(R.id.details_change_password);
        mToolbar = findViewById(R.id.profile_details_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTextview = findViewById(R.id.change_password);
        mTextview.setText("PROFILE");
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        alternate_email = findViewById(R.id.alternate_email);
        contact = findViewById(R.id.contact_number);
        upload_profile=findViewById(R.id.upload_profilepic);
        save=findViewById(R.id.save_profile);
//        back_button=findViewById(R.id.navigationnn);

        ProfileDetails pd = new ProfileDetails();

        email.setText(pd.getEmail());
        name.setText(pd.getFirst_name());
        alternate_email.setText(pd.getAlternative_email());
        contact.setText("" + pd.getNumber());
        //upload_profile.setText("" + pd.getAvatar());

        upload_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 12);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaildata = email.getText().toString().trim();
                String namedata = name.getText().toString().trim();
                String alternate_emaildata = alternate_email.getText().toString().trim();
                String contactData = contact.getText().toString().trim();
                String avatar = upload_profile.getText().toString().trim();
            }
        });


//        //*********************SENT TO PREVIOUS ACTIVITY*********************************************************************8
//        back_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                sendToBooksActivity();
//            }
//        });


        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileDetailsActivity.this, ChangePassword.class);
                startActivity(i);
                finish();
            }
        });
    }


    private void sendToBooksActivity() {
        Intent i = new Intent(ProfileDetailsActivity.this, Fictionbooks.class);
        startActivity(i);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12 && resultCode == RESULT_OK && data != null) {


            Uri uriImage = data.getData();
            CropImage.activity()                  //// start picker to get image for cropping and then use the image in cropping activity
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {                                 //GET CROP RESULT

            Log.d("unique", "done");
            CropImage.ActivityResult result = CropImage.getActivityResult(data);                        //DATA=CROPPED PHOTO
            Log.d("unique", "done2");
            if (resultCode == RESULT_OK) {     //CHECK WHETHER PIC CROPPED OR NOT

                Uri resultUri = result.getUri();
                upload_profile.setText(resultUri.toString());

            }
        }
    }


}
