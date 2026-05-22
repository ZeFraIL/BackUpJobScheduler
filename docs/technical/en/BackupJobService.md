# Class Description: BackupJobService

## 1. General Information
*   **Class Name:** `BackupJobService`
*   **Type:** `JobService`
*   **Purpose:** This is the "Worker". It performs the actual file copying of the database from the private app folder to a public folder.
*   **Interactions:** Controlled by the system's `JobScheduler`. It reads from the internal database file and writes to external storage.

## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `TAG` | `String` | Logging ID. | Error and success logs. |
| `DB_NAME` | `String` | The name of the file to be backed up. | Used to find the source file. |
| `BACKUP_DIR` | `String` | The name of the folder where copies will be stored. | Used to create the destination path. |

## 3. Class Methods
### Method name: `onStartJob`
*   **Type:** `public`
*   **Return value:** `boolean` (`true` means the job is still running in another thread).
*   **What does it do:** Starts a new **Thread** (a separate path of execution) to copy the file so the main app doesn't freeze.
*   **When called:** By the system when conditions (time, network) are met.

### Method name: `backupDatabase`
*   **Type:** `private`
*   **What does it do:** 
    1. Finds the path to the current database.
    2. Creates the "DatabaseBackups" folder in the "Documents" directory.
    3. Uses `FileChannel` to transfer data from the source to the new backup file.
*   **What is important to understand:** Copying files is an "I/O operation" (Input/Output). It can be slow, which is why it MUST be in a separate thread.

## 8. Simplified Explanation
**"Explanation in simple words"**
Think of `BackupJobService` as a **"Night Janitor"**. While the office (the app) is closed, the janitor comes in, takes a folder from the filing cabinet (the database), makes a photocopy of it, and puts the copy into a safe box in the warehouse (External Storage). When finished, he leaves a note saying "I'm done".

---
**Bugs/Improvement Note:** 
1. The code uses `Environment.getExternalStoragePublicDirectory`, which is restricted in newer Android versions (Android 11+). It's better to use `MediaStore` or `getExternalFilesDir`.
2. `DB_NAME` is "my_database.db", but the database helper `HelperDB` uses "all_cars.db". This will cause the backup to fail because the file won't be found!
