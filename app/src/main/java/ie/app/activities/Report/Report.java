package ie.app.activities.Report;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;

import ie.app.R;
import ie.app.activities.Base;

public class Report extends Base {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        listView = findViewById(R.id.reportList);
//        DonationAdapter adapter = new DonationAdapter(this, app.dbManager.getAll());
        DonationAdapter adapter = new DonationAdapter(this,app.donations);
        listView.setAdapter(adapter);

        listView.setAdapter(adapter);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_report, menu);
        return true;
    }


}