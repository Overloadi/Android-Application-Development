package fi.jokufirma.listviewexercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview = (ListView) findViewById(R.id.listView);
        String[] phones = new String[]{
                "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu",
                "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu"
        };
        final ArrayList<String> list = new ArrayList<String>();
        for (int i=0; i < phones.length; ++i) {
            list.add(phones[i]);
        }
        PhoneArrayAdapter adapter = new PhoneArrayAdapter(this,list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phone = list.get(position);
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
    });
    }

}
