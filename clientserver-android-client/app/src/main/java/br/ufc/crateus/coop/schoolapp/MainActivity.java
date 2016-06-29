package br.ufc.crateus.coop.schoolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public static final String LIST_STUDENTS = "List Students";
    public static final String ADD_STUDENT = "Add Student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        String[] names = new String[]{LIST_STUDENTS, ADD_STUDENT};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String clickedItem = (String) adapterView.getItemAtPosition(i);
                if(clickedItem.equals(LIST_STUDENTS))
                    openStudentsList();
                else if(clickedItem.equals(ADD_STUDENT))
                    openAddStudent();

            }
        });
    }

    private void openAddStudent() {
        Intent addStudentIntent = new Intent(this, AddStudentActivity.class);
        startActivity(addStudentIntent);
    }

    private void openStudentsList() {
        Intent listStudentsIntent = new Intent(this, ListStudentsActivity.class);
        startActivity(listStudentsIntent);
    }
}
