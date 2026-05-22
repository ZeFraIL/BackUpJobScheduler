# Class Description: BackupScheduler

## 1. General Information
*   **Class Name:** `BackupScheduler`
*   **Type:** Normal Class (Utility)
*   **Purpose:** This class acts as a "Planner". It tells the Android system when and how the backup should be executed.
*   **Interactions:** It interacts with `MainActivity` (to get started) and `BackupJobService` (which it schedules).

## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `JOB_ID` | `int` | A unique identification number for this specific job (set to 1). | Used to register the job in the system. |
| `TAG` | `String` | Used for identification in the system logs. | Used in `Log.d` to show scheduling success. |

## 3. Class Methods
### Method name: `scheduleBackup`
*   **Type:** `public static`
*   **Return value:** `void`
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `context` | `Context` | The environment data required to access system services. |
*   **What does it do:** 
    1. Creates a `ComponentName` pointing to `BackupJobService`.
    2. Calculates the time until the next 1:00 AM.
    3. Uses `JobInfo.Builder` to set rules: run every 24 hours, persist after reboot, and require any network.
    4. Submits the request to the system `JobScheduler`.
*   **When called:** Manually from `MainActivity.onCreate()`.
*   **What is important to understand:** `setPersisted(true)` requires a special permission in the `AndroidManifest.xml` (RECEIVE_BOOT_COMPLETED) to work after a phone restart.

### Method name: `getNextExecutionTime`
*   **Type:** `private static`
*   **Return value:** `long` (timestamp in milliseconds)
*   **What does it do:** Calculates exactly how many milliseconds from "now" it will be 1:00 AM. If it's already past 1:00 AM today, it targets 1:00 AM tomorrow.

## 7. General Logic
1. Calculate the target time (1:00 AM).
2. Pack all requirements (Network, Battery, Time) into a "Job" package.
3. Hand the package to the Android System's "Secretary" (`JobScheduler`).

## 8. Simplified Explanation
**"Explanation in simple words"**
Think of `BackupScheduler` as an **"Alarm Clock"**. You aren't the one waking up to do the work; you are just setting the alarm for the **"Worker"** (`BackupJobService`) to wake up at 1:00 AM and do the job only if the phone is charging or has internet.
