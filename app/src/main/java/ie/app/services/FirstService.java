package ie.app.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class FirstService extends Service {
    public FirstService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("FirstService", "Service da dc khoi tao");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("FirstService", "Service da dc dong");
    }
}