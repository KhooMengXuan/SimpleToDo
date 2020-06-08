package sg.edu.rp.c346.id18016446.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd;
    Button btnClear;
    ListView listView;
    Spinner spnAddRemove;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        listView = findViewById(R.id.listViewTask);
        spnAddRemove = findViewById(R.id.spinner);
        btnDelete = findViewById(R.id.buttonDelete);

        final ArrayList<String> taskList;
        taskList = new ArrayList<String>();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList);

        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                taskList.add(task);
                adapter.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                if (task.isEmpty()) {
                    taskList.clear();
                    adapter.notifyDataSetChanged();
                } else {
                    etTask.setText("");
                }
            }
        });

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        etTask.setText("");
                        etTask.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etTask.setText("");
                        etTask.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taskList.size() != 0) {
                    int pos = Integer.parseInt(etTask.getText().toString());
                    if (pos < taskList.size()) {
                        taskList.remove(pos);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong Index number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
