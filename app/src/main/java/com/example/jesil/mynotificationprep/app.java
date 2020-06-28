package com.example.jesil.mynotificationprep;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;

/* created by
 *    Jesil toborowei
 *       2020*/

public class app extends Application {
    public static final String CHANNEL_REGISTER = "channel1";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channelRegister = new NotificationChannel(CHANNEL_REGISTER,
                    "Buzzer ",
                    NotificationManager.IMPORTANCE_HIGH);
            channelRegister.setLightColor(Color.RED);
            channelRegister.setDescription(" You just registered to buzzer");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channelRegister);
        }
    }
}
