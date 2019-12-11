package com.example.messagingapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {

    public static String UserOne = "User One: ";
    private int REQUEST_CODE_UserOne = 747;
    private ImageView userLogoImage;
    private EditText userOneMessage;
    private TextView userOneTextView;
    private Button userOneMessageButton;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor spEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences =  getSharedPreferences(Constants.SharedPrefenenceFile, Context.MODE_PRIVATE);
        userLogoImage = findViewById(R.id.user_one_image_logo);
        userOneMessage = findViewById(R.id.edit_text_user_one);
        userOneTextView = findViewById(R.id.message_user_one_text_view);
        userOneMessageButton = findViewById(R.id.user_one_message_button);

        Glide.with(this).applyDefaultRequestOptions(RequestOptions.centerCropTransform()).load(getString(R.string.user_logo))
                .into(userLogoImage);

        readSharedPreferences();


        userOneMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = userOneMessage.getText().toString().trim() + "";

                if(message.length() > 0){
                   Intent intent = new Intent(MainActivity.this, UserTwoActivity.class);

                    String currentMessage = userOneTextView.getText().toString() + UserOne+message;
                    userOneTextView.setText(currentMessage);

                    saveToShardPreferences(currentMessage);
                    intent.putExtra("my_string", message);
                    startActivityForResult(intent, REQUEST_CODE_UserOne);
                }
                else {
                    Toast.makeText(MainActivity.this, "Please enter a messsage!", Toast.LENGTH_SHORT);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_UserOne && resultCode == AppCompatActivity.RESULT_OK){
            String messageIn = data.getStringExtra("my_string");
            String current = userOneTextView.getText().toString() + "\n"+UserOne + messageIn;
            userOneTextView.setText(current);
            saveToShardPreferences(current);
        }
    }

    private void saveToShardPreferences(String message){
        spEditor = sharedPreferences.edit();
        spEditor.putString("my_string_key", message);
        spEditor.apply();
    }

    private void readSharedPreferences(){
        String itJustIn = sharedPreferences.getString("my_string_key", "Welcome");
        userOneTextView.setText(itJustIn);

    }

}
