package cl.ciisa.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper  extends SQLiteOpenHelper {
    public static final int DATA_BASE_VERSION = 1;
    public static final String DATA_BASE_NAME = "Biblioteca";

    public dbHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String consultadrop = "DROP TABLE tbllibros";
        //db.execSQL(consultadrop);
        String consulta = "CREATE TABLE tbllibros (ID INT PRIMARY KEY NOT NULL, NOMBRE TEXT NOT NULL, EDITORIAL TEXT NOT NULL, AUTORES TEXT NOT NULL, PAGINAS INT NOT NULL)";
        db.execSQL(consulta);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
