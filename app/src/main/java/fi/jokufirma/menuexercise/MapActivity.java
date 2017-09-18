package fi.jokufirma.menuexercise;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by K1625 on 14.9.2017.
 */

public class MapActivity extends AppCompatActivity implements View.OnClickListener {
    private int note_id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Button button = (Button) findViewById(R.id.showMapButton);
        button.setOnClickListener(MapActivity.this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        Notification note = new Notification.Builder(this)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("Urgent message from menus app")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Sorry, there are no maps to display")
                .setAutoCancel(true)
                .setVisibility(Notification.VISIBILITY_PUBLIC).build();
        NotificationManager noteManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        note_id++;
        noteManager.notify(note_id, note);
    }


}

