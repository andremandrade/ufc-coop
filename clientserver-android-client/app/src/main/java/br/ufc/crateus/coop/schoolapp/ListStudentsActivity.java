package br.ufc.crateus.coop.schoolapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import br.ufc.crateus.coop.schoolapp.http.RequestQueueManager;
import br.ufc.crateus.coop.schoolapp.model.Student;

public class ListStudentsActivity extends AppCompatActivity {

    private ListView studentsList;
    private ArrayAdapter<String> listAdapter;
    private List<String> studentsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);
        setTitle("List Students");
        studentsList = (ListView) findViewById(R.id.studentsListView);

        studentsData = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, studentsData);

        studentsList.setAdapter(listAdapter);
    }

    @Override
    protected void onResume() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Student.STUDENT_API_URL, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.i("Students.GET.success", response.toString());
                List<Student> students = new ArrayList<Student>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        students.add(Student.fromJSON(response.getJSONObject(i).toString()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                loadStudentsOnList(students);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Students.GET.error", error.getMessage(), error);
            }
        });

        RequestQueueManager.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);

        super.onResume();
    }

    private void loadStudentsOnList(List<Student> students) {
        for (Student student : students) {
            studentsData.add(student.getName() + " - " + student.getEmail());
        }
        listAdapter.notifyDataSetChanged();
    }

}
