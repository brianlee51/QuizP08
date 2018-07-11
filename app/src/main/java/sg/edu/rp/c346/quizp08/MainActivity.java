package sg.edu.rp.c346.quizp08;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge;
    Button btnSave;
    Spinner spnClass;
    String name="";
    int selected = 0;
    Integer age = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etAge = findViewById(R.id.editTextAge);
        btnSave = findViewById(R.id.buttonSave);
        spnClass = findViewById(R.id.spinner);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                age =  Integer.parseInt(etAge.getText().toString());
                Toast toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        spnClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = spnClass.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("theName",name);
        prefEdit.putInt("theAge",age);
        prefEdit.putInt("thePos",selected);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String name = prefs.getString("theName", "");
        Integer age = prefs.getInt("theAge",0);
        int position = prefs.getInt("thePos",0);
        etAge.setText(age+"");
        etName.setText(name);
        spnClass.setSelection(position);
    }


}
