package es.rufflecol.lara.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText titleText;
    private Button buttonUpdateRecord;
    private Button buttonDeleteRecord;
    private long id;
    private SQLDatabaseController sqlDatabaseController;
    private EditText descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_record);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTextAppearance);
        setSupportActionBar(toolbar);

        sqlDatabaseController = new SQLDatabaseController(this);
        sqlDatabaseController.open();

        titleText = (EditText) findViewById(R.id.subject_edit_text);
        descriptionText = (EditText) findViewById(R.id.description_edit_text);
        buttonUpdateRecord = (Button) findViewById(R.id.button_update);
        buttonDeleteRecord = (Button) findViewById(R.id.button_delete);

        Intent intent = getIntent();
        String idFromExtra = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        id = Long.parseLong(idFromExtra);
        titleText.setText(name);
        descriptionText.setText(description);

        buttonUpdateRecord.setOnClickListener(this);
        buttonDeleteRecord.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_update:
                String title = titleText.getText().toString();
                String description = descriptionText.getText().toString();
                sqlDatabaseController.update(id, title, description);
                this.returnHome();
                break;
            case R.id.button_delete:
                sqlDatabaseController.delete(id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent homeIntent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
