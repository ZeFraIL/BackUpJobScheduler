# Class Description: HelperDB

## 1. General Information
*   **Class Name:** `HelperDB`
*   **Type:** `SQLiteOpenHelper`
*   **Purpose:** This class manages the creation and structure of the app's local database.
*   **Interactions:** Used by other parts of the app to save or retrieve data about cars.

## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `DB_FILE` | `String` | The filename of the database on disk ("all_cars.db"). | System uses this to create the file. |
| `TABLE_CARS` | `String` | The name of the table inside the DB. | In SQL queries. |
| `CODE`, `MODEL`... | `String` | Names of the columns (Price, Year, etc.). | In SQL queries. |

## 3. Class Methods
### Method name: `onCreate`
*   **Type:** `public`
*   **What does it do:** Runs an **SQL** (Structured Query Language) command to create the "Cars" table if it doesn't exist yet.
*   **When called:** The very first time the app tries to access the database.

## 8. Simplified Explanation
**"Explanation in simple words"**
Think of `HelperDB` as the **"Architect"** and **"Librarian"** of a library. The Architect decides how many shelves (tables) there are and what labels (columns) are on the books. The Librarian makes sure the library is built correctly before any books are put inside.
