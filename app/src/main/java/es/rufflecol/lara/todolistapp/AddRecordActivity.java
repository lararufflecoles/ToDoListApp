package es.rufflecol.lara.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddRecordActivity extends AppCompatActivity implements OnClickListener {

    private Button buttonAddRecord;
    private SQLDatabaseController sqlDatabaseController;
    private EditText subjectEditText;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTextAppearance);
        setSupportActionBar(toolbar);

        subjectEditText = (EditText) findViewById(R.id.subject_edit_text);
        descriptionEditText = (EditText) findViewById(R.id.description_edit_text);
        buttonAddRecord = (Button) findViewById(R.id.add_record);

        sqlDatabaseController = new SQLDatabaseController(this);
        sqlDatabaseController.open();
        buttonAddRecord.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:
                final String name = subjectEditText.getText().toString().toUpperCase();
                final String description = descriptionEditText.getText().toString().toUpperCase();
                sqlDatabaseController.insert(name, description);

                Intent main = new Intent(AddRecordActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
            default:
                break;
        }
    }
}