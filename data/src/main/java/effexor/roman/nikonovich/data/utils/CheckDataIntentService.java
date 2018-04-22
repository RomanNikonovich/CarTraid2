package effexor.roman.nikonovich.data.utils;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;

import java.io.IOException;

import effexor.roman.nikonovich.data.entity.vehicleNet.SearchNet;
import effexor.roman.nikonovich.data.entity.vehicleNet.VehicleNet;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class CheckDataIntentService extends IntentService {
    private NotificationManager notificationManager;
    private static final String NEW_CAR = "Новых машин: ";
    public static final int NOTIFICATION_ID = 1547895327;
    private static final String UNIQ_ACTION = "effexor.roman.nikonovich";
    private static final String NOTIF = "notif";
    private static final String SOUND = "sound";
    private final String TABLE_NAME = "setting_table";
    private SharedPreferences preferences;

    public static void startCheckDataIntentService(Context context) {
        Intent intent = new Intent(context, CheckDataIntentService.class);
        intent.setAction(UNIQ_ACTION);
        context.startService(intent);
    }

    public CheckDataIntentService() {
        super("CheckDataIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getSharedPreferences(TABLE_NAME, MODE_PRIVATE);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    try {
                        RealmResults<SearchNet> searchs = realm.where(SearchNet.class).findAll();
                        if (searchs.size() != 0) {
                            for (SearchNet searchDB : searchs) {
                                RealmList<VehicleNet> carsList = ParseUrl.getCars(searchDB.getUrlSearch());
                                if (preferences.getBoolean(NOTIF, true))
                                    sendNotification(searchDB.getNameSearch(), carsList.size());
                                if (carsList.size() != 0) {
                                    searchDB.getListVehicleNet().retainAll(carsList);
                                    if (searchDB.getListVehicleNet().size() != 0) {
                                        for (VehicleNet vehicle : searchDB.getListVehicleNet())
                                            vehicle.setNew(false);
                                        carsList.removeAll(searchDB.getListVehicleNet());
                                        if (carsList.size() != 0) {
                                            for (VehicleNet vehicleNet : carsList)
                                                vehicleNet.setNew(true);
                                            searchDB.getListVehicleNet().addAll(carsList);
                                            //посчитать кол-во машин
                                            /*sendNotification(searchDB.getNameSearch(), carsList.size());*/
                                        }
                                    } else {
                                        searchDB.getListVehicleNet().addAll(carsList);
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                    }
                }
            });
        }
    }


    private void sendNotification(String title, int count) {
        Notification notification;
        if (preferences.getBoolean(SOUND, true)) {
            notification = new NotificationCompat
                    .Builder(getApplicationContext())
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle(title)
                    .setContentText(NEW_CAR + count)
                    .setWhen(System.currentTimeMillis())
                 /*   .setContentIntent(NotifPI)*/
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setAutoCancel(true)
                    .build();
        } else {
            notification = new NotificationCompat
                    .Builder(getApplicationContext())
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle(title)
                    .setContentText(NEW_CAR + count)
                  /*  .setContentIntent()*/
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .build();
        }
        notificationManager.notify(NOTIFICATION_ID,
                notification);

    }
}
