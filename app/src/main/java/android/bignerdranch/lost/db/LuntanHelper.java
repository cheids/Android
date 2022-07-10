package android.bignerdranch.lost.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LuntanHelper extends SQLiteOpenHelper {

    public static final String CREATE_USER = "create table Luntan ("
            + "id integer primary key autoincrement, "
            + "user_id text, "
            + "content text, "
            + "pic text, "
            + "time text, "
            + "leixing text, "
            + "biaoti text, "
            + "fabuzhe text, "
            + "username text, "
            + "head_url text, "
            + "zan text)";

    public LuntanHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}

