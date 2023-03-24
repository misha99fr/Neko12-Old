package ru.dimon6018.neko11.workers;

import static ru.dimon6018.neko11.ui.fragments.NekoLand.CHAN_ID;
import static ru.dimon6018.neko11.workers.NekoWorker.DEBUG_NOTIFICATION;
import ru.dimon6018.neko11.ui.fragments.NekoLand;
import ru.dimon6018.neko11.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class NekoToyWorker extends Worker {

    private static final String TAG = "NekoToyWorker";

    public NekoToyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Result state;
        Context context = getApplicationContext();
        final PrefState prefs = new PrefState(context);
        try {
            Log.i(TAG, "Success");
            NekoWorker.notifyCat(context, NekoWorker.getExistingCat(prefs), context.getString(R.string.meow_meow));
            state = Result.success();
        } catch (Exception e) {
            Log.e(TAG, "Error in worker method, see:" + e);
            state = Result.failure();
        }
        prefs.setToyState(0);
        return state;
    }
    public static void scheduleToyWork(Context context) {
        Log.i(TAG, "Let's schedule work for toy");

        int toymin = 10;
        int toymax = 60;
        int toydelayrandom = new Random().nextInt(toymax - toymin + 1) + toymin;

        OneTimeWorkRequest workToyRequest =
                new OneTimeWorkRequest.Builder(NekoToyWorker.class)
                        .addTag("TOYWORK")
                        .setInitialDelay(toydelayrandom, TimeUnit.MINUTES)
                        .build();

        WorkManager.getInstance().enqueue(workToyRequest);

        if (NekoLand.DEBUG_NOTIFICATIONS) {
            NotificationManager noman = context.getSystemService(NotificationManager.class);
            noman.notify(DEBUG_NOTIFICATION, new Notification.Builder(context)
                    .setSmallIcon(R.drawable.stat_icon)
                    .setContentTitle(String.format("Work scheduled in %d min", (toydelayrandom)))
                    .setContentText("Work for toy success scheduled")
                    .setPriority(Notification.PRIORITY_MIN)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .setChannelId(CHAN_ID)
                    .setShowWhen(true)
                    .build());
        }
    }
    public static void stopToyWork() {
        WorkManager.getInstance().cancelAllWorkByTag("TOYWORK");
    }
}
