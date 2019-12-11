package com.example.messagingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class UserTwoActivity extends AppCompatActivity {
    public static String UserTwo = "User Two: ";
    private int REQUEST_CODE_UserTwo = 757;
    private ImageView userLogoImage;
    private EditText userTwoMessage;
    private TextView userTwoTextView;
    private Button userTwoMessageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_two);
        userLogoImage = findViewById(R.id.user_two_image_logo);
        userTwoMessage = findViewById(R.id.edit_text_user_two);
        userTwoTextView = findViewById(R.id.message_user_two_text_view);
        userTwoMessageButton = findViewById(R.id.user_two_message_button);

        Glide.with(this).applyDefaultRequestOptions(RequestOptions.centerCropTransform()).load(getString(R.string.user_logo))
                .into(userLogoImage);

        String messageReceieved = getIntent().getStringExtra("my_string");

        userTwoTextView.setText("From User One:" + messageReceieved);

        userTwoMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userTwoTextView.getText().toString().length() > 0){
                    Intent replyIntent = new Intent(UserTwoActivity.this, MainActivity.class);
                    replyIntent.putExtra("my_string", userTwoTextView.getText().toString());
                    setResult(AppCompatActivity.RESULT_OK, replyIntent);
                    finish();
                } else {
                    Toast.makeText(UserTwoActivity.this, "Please Enter a Message.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
