package fi.jokufirma.launchmap;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button button;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.buttonShowMap);
        button.setOnClickListener(MainActivity.this);

    }

     @Override
     public void onClick(View view) {
         EditText et1 = (EditText) findViewById(R.id.latitudeText);
         EditText et2 = (EditText) findViewById(R.id.longitudeText);
         String string1 = et1.getText().toString();
         String string2 = et2.getText().toString();
         double lat = Double.parseDouble(string1);
         double lng = Double.parseDouble(string2);
         Intent intent = new Intent(Intent.ACTION_VIEW);
         intent.setData(Uri.parse("geo:" + lat + "," + lng));
         startActivity(intent);
     }
}
