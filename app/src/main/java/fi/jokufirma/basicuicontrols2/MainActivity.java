package fi.jokufirma.basicuicontrols2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.login);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new String[] {"Matti","Jooseppi","Joonas","Aatu","Eetu"});
        actv.setAdapter(aa);
        final Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AutoCompleteTextView tv = (AutoCompleteTextView) findViewById(R.id.login);
                EditText et = (EditText) findViewById(R.id.password);
                String logintext = (String) tv.getText().toString();
                String passwordtext = (String)  et.getText().toString();
                String text = logintext + " " + passwordtext;
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
