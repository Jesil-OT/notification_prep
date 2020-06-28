package com.example.jesil.mynotificationprep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout mInputLayoutfirstName, mInputLayoutLastName;
    private TextView mTextViewSetError;

    String firstName, lastName;

    private NotificationManagerCompat mNotificationManagerCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputLayoutfirstName = findViewById(R.id.textInputLayout_FirstName);
        mInputLayoutLastName = findViewById(R.id.textInputLayout_lastName);
        mTextViewSetError = findViewById(R.id.textView_errorMessage);

        mNotificationManagerCompat = NotificationManagerCompat.from(this);
    }

    public void Register(View view) {
        if (!firstNameFunction() || !lastNameFunction()) {
            return;
        } else {
            //  Toast.makeText(this,  " you just Registered: " + firstName + " " + lastName, Toast.LENGTH_SHORT).show();
            registerTheUser();
        }

        alertDialogSharedPref();
    }

    private void alertDialogSharedPref() {
        final SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        boolean isFirstTimeLogin = preferences.getBoolean("show_alert", true);
        if (isFirstTimeLogin) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_cloud)
                    .setTitle(" ")
                    .setMessage(R.string.longText)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("show_alert", false);
                            editor.apply();
                        }
                    });
            builder.show();
        }
    }

    private void registerTheUser() {
        Notification notification = new NotificationCompat.Builder(this, app.CHANNEL_REGISTER)
                .setSmallIcon(R.drawable.ic_cloud)
                .setColor(Color.GREEN)
                .setContentTitle("You just registered on Buzzer.")
                .setContentText("Thank you for registering "
                        + firstName + " " + lastName + ". " + "\n")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Check out our hot and trending post in Buzzer, and" +
                                "also learn to Buzz with Friends "))
                .build();

        mNotificationManagerCompat.notify(1, notification);

    }

    private Boolean firstNameFunction() {
        firstName = mInputLayoutfirstName.getEditText().getText().toString().trim();
        if (firstName.isEmpty()) {
            mTextViewSetError.setText("Please enter a First name");
            mInputLayoutfirstName.setError("  ");
            mTextViewSetError.setVisibility(View.VISIBLE);
            mInputLayoutfirstName.requestFocus();
            return false;
        } else if (firstName.length() > 15) {
            mInputLayoutfirstName.setError("name should be less than 15 character");
            mTextViewSetError.setVisibility(View.GONE);
            return false;
        } else {
            mTextViewSetError.setVisibility(View.GONE);
            mTextViewSetError.setText(null);
            mInputLayoutfirstName.setError(null);
            return true;
        }
    }

    private Boolean lastNameFunction() {
        lastName = mInputLayoutLastName.getEditText().getText().toString().trim();
        if (lastName.isEmpty()) {
            mTextViewSetError.setText("Please enter a Last Name name");
            mInputLayoutLastName.setError("  ");
            mTextViewSetError.setVisibility(View.VISIBLE);
            mInputLayoutLastName.requestFocus();
            return false;
        } else if (lastName.length() > 15) {
            mInputLayoutLastName.setError("name should be less than 15 character");
            mTextViewSetError.setVisibility(View.GONE);
            return false;
        } else {
            mTextViewSetError.setVisibility(View.GONE);
            mTextViewSetError.setText(null);
            mInputLayoutLastName.setError(null);
            return true;
        }
    }
}
