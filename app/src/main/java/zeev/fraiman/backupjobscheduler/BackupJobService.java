package zeev.fraiman.backupjobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class BackupJobService extends JobService {

    private static final String TAG = "BackupJobService";
    private static final String DB_NAME = "my_database.db"; // Название вашей БД
    private static final String BACKUP_DIR = "DatabaseBackups"; // Папка для бэкапов

    @Override
    public boolean onStartJob(JobParameters params) {
        new Thread(() -> {
            backupDatabase();
            jobFinished(params, false);
        }).start();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }

    private void backupDatabase() {
        File dbFile = getDatabasePath(DB_NAME);
        File backupDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), BACKUP_DIR);

        if (!backupDir.exists()) {
            if (!backupDir.mkdirs()) {
                Log.e(TAG, "Error creating backup directory");
                return;
            }
        }

        String backupFileName = "backup_" + System.currentTimeMillis() + ".db";
        File backupFile = new File(backupDir, backupFileName);

        try (FileChannel src = new FileInputStream(dbFile).getChannel();
             FileChannel dst = new FileOutputStream(backupFile).getChannel()) {
            dst.transferFrom(src, 0, src.size());
        } catch (IOException e) {
            Toast.makeText(this, "error in backup", Toast.LENGTH_SHORT).show();
        }
    }
}
