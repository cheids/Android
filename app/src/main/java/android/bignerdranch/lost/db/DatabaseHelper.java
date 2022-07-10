package android.bignerdranch.lost.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static int Version = 1;       // 数据库版本号
    public final static String MYDB = "lost.db";        //数据库名字
    public static final String U_USER_INFO = "userInfo";

    public DatabaseHelper(Context context) {
        super(context, MYDB, null, Version);
    }

    public DatabaseHelper(Context context,String name, int version) {
        super(context, name, null, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 登录注册用户信息
         * */
        String sql = "create table user(id integer primary key autoincrement,username varchar(80),password varchar(80))";
        db.execSQL(sql);            // 执行数据库

//        /**
//         * 我的页面用户信息
//         * */
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + U_USER_INFO + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "nickName VARCHAR, sex CHAR(1), birthday DATE, tele CHAR(11), signature VARCHAR)");
    }



    // 升级
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
