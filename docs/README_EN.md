# 📱 Android App Documentation (LEVEL 10/10)

________________________________________
🧾 General Information
**Project Name:**
BackUpJobScheduler
**Author(s):**
Zeev Fraiman
**Date:**
May 22, 2026
**Language:**
Java
**Development Environment:**
Android Studio
**Android Version (minSdk / targetSdk):**
28 / 35
________________________________________
🎯 Project Goal
•	**What problem does the app solve:** Automates the creation of backup copies for a local SQLite database.
•	**Why is this task important:** Prevents user data loss in case of app or device failure. Backups are performed in the background at a scheduled time.
•	**Target Audience:** Android developers looking for a reliable background data preservation mechanism.
________________________________________
📌 App Requirements
**Functional Requirements**
•	Schedule a backup task upon app launch.
•	Execute database copying in the background every 24 hours.
•	Save the database file to the public documents directory.
**Non-functional Requirements**
•	**Performance:** Minimal system impact by running the task during low-activity hours (01:00 AM).
•	**Usability:** The process is fully automated and requires no user intervention.
•	**Reliability:** Uses the system `JobScheduler` to guarantee task execution even after device reboots.
________________________________________
🧠 General Architecture
•	**Chosen Approach:**
–	Component-based structural approach (Service-Oriented).
•	**Why this approach was chosen:** `JobScheduler` is the standard and efficient way to schedule background tasks in Android, balancing battery consumption and work execution.
•	**Main System Components:**
–	`MainActivity`: Entry point that initiates the scheduler.
–	`BackupScheduler`: Class for configuring and launching `JobInfo`.
–	`BackupJobService`: Service that performs the actual file copying.
–	`HelperDB`: Class for managing the SQLite database.
________________________________________
🧩 UML Diagram (Required)
`[MainActivity]` –> `[BackupScheduler]`
`[BackupScheduler]` –> `[BackupJobService]` (via JobScheduler API)
`[BackupJobService]` –> `[HelperDB]` (database file access)
`[BackupJobService]` –> `[External Storage]` (saving the copy)

**Explanation:**
- Separation of concerns: scheduling logic is separated from execution logic.
- Scalability: Easy to add new types of background tasks without changing the main UI.
________________________________________
🧩 Detailed Class Description
📌 **Class: MainActivity**
**Role:** Main activity of the app.
**Responsibility:** Initializing the UI and starting the job scheduler.
**Main Methods:**
- `onCreate()` — sets the layout and calls `BackupScheduler.scheduleBackup()`.
**Interaction with other classes:** Calls the static method of `BackupScheduler`.

📌 **Class: BackupJobService**
**Role:** Background service (JobService).
**Why it is used:** Allows the system to perform work in the background based on defined conditions (network, charging).
**Responsibility:** Copying the DB file in a separate thread.
**Main Methods:**
- `onStartJob()` — starts a thread for copying.
- `backupDatabase()` — implements FileChannel transfer logic.

📌 **Class: BackupScheduler**
**Role:** Task configurator.
**Responsibility:** Setting the periodicity (24 hours) and conditions (network availability) for the JobScheduler.
________________________________________
🔄 App Workflow Diagram
**Scenario:**
1. User opens the app.
2. `MainActivity` asks `BackupScheduler` to schedule the work.
3. The Android system queues the task.
4. At 01:00 AM (or when conditions are met), the system starts `BackupJobService`.
5. The service creates a thread, copies `all_cars.db` to the `Documents/DatabaseBackups` folder.
6. The service notifies the system that the work is finished.
________________________________________
🎨 UI/UX Analysis
•	**Why the interface is made this way:** Minimalistic (Empty Activity) because the main function is background-oriented.
•	**Principles used:**
–	Simplicity (one action at startup).
–	Logic (user is not distracted by technical processes).
•	**What can be improved:** Add a screen with a list of already created backups and a manual start option.
________________________________________
⚙️ Threading
**Describe:**
- Used: `Thread` (inside `BackupJobService`).
•	**Why this method was chosen:** Simple file copying does not require complex reactive programming; a basic thread is sufficient to free the Main UI Thread.
•	**Prevention of:**
–	Freezes (ANR): I/O operation is moved out of the main thread.
–	Memory leaks: Short-lived thread that ends with the task.
________________________________________
💾 Data Management
•	**Where data is stored:** SQLite (internal storage) and backup copies (external storage).
•	**Why this method was chosen:** SQLite is the standard for structured data; external storage allows user access to backups.
•	**Ensuring:**
–	Preservation: Using `FileChannel` for fast and safe copying.
–	Correctness: Checking folder existence before writing.
________________________________________
🌐 Networking
•	Not present in the current version. Cloud synchronization is planned.
________________________________________
🔐 Security (Basic Level)
•	**Sensitive data:** Car data.
•	**Protection:** Android's standard sandbox for the main DB. Backups are saved in Documents.
________________________________________
🧪 Testing
•	**Tests available:**
–	Unit tests (ExampleUnitTest).
–	UI tests (ExampleInstrumentedTest).
•	**What is checked:** Basic context functionality and mathematical operations.
________________________________________
🐞 Error Handling
•	**Anticipated errors:** Directory creation errors, I/O errors (IOException).
•	**App reaction:** Logging via `Log.e` and showing a `Toast` in case of failure.
________________________________________
⚡ Performance
•	**Optimizations:** Use of `setPeriodic` to prevent too frequent starts. `setRequiresBatteryNotLow` (for API 26+) to save power.
•	**Bottlenecks:** Read/write speed for very large DB sizes.
________________________________________
🚀 Expansion Possibilities
•	Adding backup encryption.
•	Uploading backups to Google Drive / Dropbox.
•	Interface for restoring data from a backup.
________________________________________
📊 Project Self-Assessment
**Criterion | Rating (1–10)**
---|---
Architecture | 9
Code | 9
UI/UX | 5 (minimalist)
Reliability | 10
Overall Level | 8
________________________________________
🏁 Conclusion
•	**Best achievement:** Reliable background task scheduling.
•	**Difficulties:** Working with external file paths in newer Android versions.
•	**Skills acquired:** Deep understanding of `JobScheduler` and filesystem operations.
________________________________________
📎 Appendices
•	Diagrams (in the text above).
•	[Repository link]
