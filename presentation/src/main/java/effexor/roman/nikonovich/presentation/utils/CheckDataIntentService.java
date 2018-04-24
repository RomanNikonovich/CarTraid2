package effexor.roman.nikonovich.presentation.utils;

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
import effexor.roman.nikonovich.data.utils.ParseUrl;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class CheckDataIntentService extends IntentService {
    private NotificationManager notificationManager;
    private static final String NEW_CAR = "Новых машин: ";
    private static final String NAME_APP = "АвтоТрейдер";
    public static final int NOTIFICATION_ID = 577523414;
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
                            int countNewCar = 0;
                            for (SearchNet searchDB : searchs) {
                                RealmList<VehicleNet> carsList = ParseUrl.getCars(searchDB.getUrlSearch());
                                sendNotification(carsList.size());
                                if (carsList.size() != 0) {
                                    searchDB.getListVehicleNet().retainAll(carsList);
                                    if (searchDB.getListVehicleNet().size() != 0) {
                                       /* for (VehicleNet vehicle : searchDB.getListVehicleNet())
                                            vehicle.setNew(false);*///удалить
                                        carsList.removeAll(searchDB.getListVehicleNet());
                                        if (carsList.size() != 0) {
                                            for (VehicleNet vehicleNet : carsList)
                                                vehicleNet.setNew(true);
                                            searchDB.getListVehicleNet().addAll(carsList);
                                            countNewCar += carsList.size();
                                        }
                                    } else {
                                        searchDB.getListVehicleNet().addAll(carsList);
                                    }
                                }
                            }
                           /* if (preferences.getBoolean(NOTIF, true)) {
                                if (countNewCar != 0)
                                    sendNotification(countNewCar);
                            }*/

                        }
                    } catch (IOException e) {
                    }
                }
            });
        }
    }


    private void sendNotification(int count) {
        NotificationCompat.Builder builder = new NotificationCompat
                .Builder(getApplicationContext())
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(NAME_APP)
                .setContentText(NEW_CAR + count)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(NotifPI.getPI(getApplicationContext()))
                .setAutoCancel(true);
        if (preferences.getBoolean(SOUND, true)) {
            builder.setDefaults(Notification.DEFAULT_SOUND);
        }
        notificationManager.notify(NOTIFICATION_ID,
                builder.build());

    }
}
