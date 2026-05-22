# Class Description: MainActivity

## 1. General Information
*   **Class Name:** `MainActivity`
*   **Type:** Activity
*   **Purpose:** This is the entry point of the application. Its main responsibility is to initialize the app and trigger the background job scheduling process.
*   **Interactions:** It calls the `BackupScheduler` class to set up the backup task.

## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `TAG` | `String` | A label used for logging (printing messages to the console for the developer). | Used in `Log.d` or `Log.e` to identify where the message came from. |

## 3. Class Methods
### Method name: `onCreate`
*   **Type:** `protected`
*   **Return value:** `void` (returns nothing)
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `savedInstanceState` | `Bundle` | Contains data from a previous session (if the app was closed and reopened). |
*   **What does it do:** 
    1. Calls the parent class version of `onCreate`.
    2. Sets the visual layout using `setContentView(R.layout.activity_main)`.
    3. Calls `BackupScheduler.scheduleBackup(this)` to start the logic for background backups.
*   **When called:** Automatically by the Android system when the app starts.
*   **What is important to understand:** This method should be fast. If you put heavy work here, the screen will stay white or freeze.

## 4. Lifecycle
*   **onCreate():** Called when the activity is first created. This is where we set up the "skeleton" of our screen and start the backup timer.

## 5. Interface Interaction (UI)
*   **Elements:** Uses `activity_main.xml`.
*   **Connection:** The connection is made via `setContentView`. No specific buttons are handled here because the app works automatically in the background.

## 6. Interaction with other components
*   **BackupScheduler:** `MainActivity` passes its "Context" (information about the app's current state) to `BackupScheduler` so it can register the background job.

## 7. General Logic
When the user clicks the app icon, `MainActivity` wakes up, loads the main screen, and immediately tells the system: "Hey, please make sure to back up our database at 1:00 AM."

## 8. Simplified Explanation
**"Explanation in simple words"**
Think of `MainActivity` as the **"Power Button"** on a coffee machine. When you press it, the machine lights up and automatically checks if it needs to start its cleaning cycle (the backup task). The button doesn't make the coffee itself; it just starts the process.

---
**Code Improvement Note:** Currently, `MainActivity` schedules the backup every time it opens. In a production app, it might be better to check if the job is already scheduled to avoid redundant calls, although `JobScheduler` handles this gracefully by updating the existing job.
