package ie.app.activities.Donate;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ie.app.R;
import ie.app.activities.Base;
import ie.app.api.DonationApi;
import ie.app.services.FirstService;
import ie.app.models.Donation;
import ie.app.main.DonationApp;

public class Donate extends Base {

    public static final String TAG = Donate.class.getSimpleName();
    private Button donateButton ;
    private RadioGroup paymentMethod ;
    private ProgressBar progressBar ;
    private NumberPicker amountPicker ;

    private EditText amountValue ;

    private TextView totalTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Log.e(TAG, "Bug Create");
        } else {
            Log.e(TAG, "Welcome Donate App");
        }
        setContentView(R.layout.activity_donate);

        donateButton = findViewById(R.id.donateButton);
        paymentMethod = findViewById(R.id.paymentMethod);
        progressBar = findViewById(R.id.progressBar);
        amountPicker = findViewById(R.id.amountPicker);
        amountValue = findViewById((R.id.amountValue));
        totalTextView = findViewById(R.id.totalValue);

        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(1000);
        amountPicker.setValue(500);
        progressBar.setMax(app.target);
        progressBar.setProgress(app.totalDonated);
        totalTextView.setText("$" + app.totalDonated);
        amountValue.setHint("$0");
        amountValue.setText("");
//        Intent ServiceIntent = new Intent(this, FirstService.class);
//        startService(ServiceIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG, "OnResume start");
        new GetAllTask(this).execute("");
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_donate, menu);
        return true;
    }
    @SuppressLint("SetTextI18n")
    public void donateButtonPressed(View view) {
        Donation donation = checkedDonate();

        if (donation != null) {
            app.newDonation(donation);
            Log.v("Donate", "Donate Pressed! with amount " + donation.getAmount() + ", method: " +
                    donation.getMethod());
            Log.v("Donate","Total Donate: " + app.totalDonated);
            progressBar.setProgress(app.totalDonated);
            amountValue.setHint("$0");
            amountValue.setText("");
            amountPicker.setValue(donation.getAmount());
            totalTextView.setText("$" + app.totalDonated);
        }

    }

    public Donation checkedDonate() {
        int amount = amountPicker.getValue();
        String textAmount = amountValue.getText().toString();

        if (!textAmount.trim().equals("")) {
            amount = Integer.parseInt(textAmount);
        }
        int radioId = paymentMethod.getCheckedRadioButtonId();
        String method = "";
        if (radioId == R.id.PayPal) {
            method = "PayPal";
        }
        if (radioId == R.id.Direct) {
            method = "Direct";
        }
        if (method.equals("")) {
            Toast.makeText(this, "Please select method donation", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (amount > (app.target - app.totalDonated)) {
            amount = app.target - app.totalDonated;
        }
        return new Donation(amount, method);
    }

    private class GetAllTask extends AsyncTask<String, Void, List<Donation>> {
        protected ProgressDialog dialog;
        protected Context context;
        public GetAllTask(Context context)
        {
            this.context = context;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("Donate","onPreExecute start " );
            this.dialog = new ProgressDialog(context, 1);
            this.dialog.setMessage("Retrieving Donations List");
            this.dialog.show();
        }
        @Override
        protected List<Donation> doInBackground(String... params) {
            try {
                Log.e("donate", "Donation App Getting All Donations");
                return (List<Donation>) DonationApi.getAll((String) params[0]);
            }
            catch (Exception e) {
                Log.e("donate", "ERROR : " + e);
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(List<Donation> result) {
            super.onPostExecute(result);
            //use result to calculate the totalDonated amount here
            progressBar.setProgress(app.totalDonated);
            totalTextView.setText("$" + app.totalDonated);
            Log.e("Donate","onPostExecute start" );
            if (dialog.isShowing())
                dialog.dismiss();
        }
    }

}