package com.life.notificationtest;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

/**
 * Created by lixfe on 2015/3/13.
 */
public class NotificationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);
    }
}
