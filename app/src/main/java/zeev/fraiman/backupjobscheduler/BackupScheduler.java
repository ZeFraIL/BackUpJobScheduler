package zeev.fraiman.backupjobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.util.Calendar;

public class BackupScheduler {

    private static final int JOB_ID = 1;
    private static final String TAG = "BackupScheduler";

    public static void scheduleBackup(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(context, BackupJobService.class);

        long interval = 24 * 60 * 60 * 1000L;
        long startMillis = getNextExecutionTime();

        JobInfo.Builder jobInfoBuilder = new JobInfo.Builder(JOB_ID, componentName)
                .setMinimumLatency(startMillis - System.currentTimeMillis())
                .setPeriodic(interval)
                .setPersisted(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            jobInfoBuilder.setRequiresBatteryNotLow(true);
        }

        JobInfo jobInfo = jobInfoBuilder.build();
        if (jobScheduler.schedule(jobInfo) == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Backup job scheduled for 01:00");
        } else {
            Log.e(TAG, "Error planning backup job");
        }
    }

    private static long getNextExecutionTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 1); // 01:00
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        return calendar.getTimeInMillis();
    }
}
