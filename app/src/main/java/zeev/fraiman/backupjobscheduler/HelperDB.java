package zeev.fraiman.backupjobscheduler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class HelperDB extends SQLiteOpenHelper {

    public static final String DB_FILE="all_cars.db";

    public static final String TABLE_CARS="Cars";
    public static final String CODE="Code";
    public static final String COMPANY="Company";
    public static final String MODEL="Model";
    public static final String YEAR="Year";
    public static final String PRICE="Price";



    public HelperDB(Context context) {
        super(context, DB_FILE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String st="CREATE TABLE IF NOT EXISTS "+TABLE_CARS+" ( ";
        st+=CODE+" TEXT, "+COMPANY+" TEXT, "+MODEL+" TEXT, "+YEAR+" INTEGER, "+PRICE+" REAL);";
        db.execSQL(st);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
