package fi.jokufirma.menuexercise;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class InfoActivity extends AppCompatActivity implements View.OnClickListener {
    private int note_id = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Button button = (Button) findViewById(R.id.showInfoButton);
        button.setOnClickListener(InfoActivity.this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    // Luo ilmoituksen, kun painetaan nappia
    @Override
    public void onClick(View v) {
        Notification note = new Notification.Builder(this)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("Urgent message from menus app")
                .setContentText("Sorry, no information found")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setVisibility(Notification.VISIBILITY_PUBLIC).build();
        NotificationManager noteManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        note_id++;
        noteManager.notify(001, note);
    }
}
