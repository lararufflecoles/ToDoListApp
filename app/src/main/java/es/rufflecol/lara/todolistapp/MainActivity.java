package es.rufflecol.lara.todolistapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SQLDatabaseController sqlController;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTextAppearance);
        setSupportActionBar(toolbar);

        sqlController = new SQLDatabaseController(this);
        sqlController.open();
        listView = (ListView) findViewById(R.id.list_view);

        Cursor cursor = sqlController.fetch();
        String[] from = new String[]{SQLDatabaseHelper.ID, SQLDatabaseHelper.SUBJECT, SQLDatabaseHelper.DESCRIPTION}; // Mapping the Strings...
        int[] to = new int[]{R.id.id, R.id.title, R.id.description}; // ...to the TextView IDs

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {

                TextView idTextView = (TextView) view.findViewById(R.id.id);
                String id = idTextView.getText().toString();

                TextView titleTextView = (TextView) view.findViewById(R.id.title);
                String title = titleTextView.getText().toString();

                TextView descriptionTextView = (TextView) view.findViewById(R.id.description);
                String description = descriptionTextView.getText().toString();

                Intent modifyIntent = new Intent(MainActivity.this, ModifyRecordActivity.class);
                modifyIntent.putExtra("id", id);
                modifyIntent.putExtra("title", title);
                modifyIntent.putExtra("description", description);
                startActivity(modifyIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_record) {
            Intent add_record = new Intent(this, AddRecordActivity.class);
            startActivity(add_record);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}