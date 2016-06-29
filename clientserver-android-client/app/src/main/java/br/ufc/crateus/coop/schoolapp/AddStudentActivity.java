package br.ufc.crateus.coop.schoolapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import br.ufc.crateus.coop.schoolapp.http.RequestQueueManager;
import br.ufc.crateus.coop.schoolapp.model.Student;

public class AddStudentActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText emailInput;
    private EditText courseInput;
    private Button addStudentbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        setTitle("Add Student");

        nameInput = (EditText) findViewById(R.id.nameInput);
        emailInput = (EditText) findViewById(R.id.emailInput);
        courseInput = (EditText) findViewById(R.id.courseInput);

        addStudentbutton = (Button) findViewById(R.id.addButton);
        addStudentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setName(nameInput.getText().toString());
                student.setEmail(emailInput.getText().toString());
                student.setCourse(courseInput.getText().toString());
                Log.i("AddStudent.Click", student.toJSON());
                doAddStudentRequest(student);
            }
        });
    }

    private void doAddStudentRequest(Student student) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(student.toJSON());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Student.STUDENT_API_URL, jsonObject, new Response.Listener<JSONObject>(){

                @Override
                public void onResponse(JSONObject response) {
                    Log.i("Students.POST.success",response.toString());
                    backToMain();
                }
            }, new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Students.POST.error", error.getMessage(),error);
                }
            });

            RequestQueueManager.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void backToMain() {
        Toast toast = Toast.makeText(getApplicationContext(), "Student added successfull!", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
}
