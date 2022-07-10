package android.bignerdranch.lost.utils;

import android.bignerdranch.lost.bean.Luntan;
import android.bignerdranch.lost.db.LuntanHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LuntanDBUtils {
    public static final String DB_NAME = "lost.db";

    public static final int VERSION = 1;

    private static LuntanDBUtils sqliteDB;

    private SQLiteDatabase db;

    private LuntanDBUtils(Context context) {
        LuntanHelper dbHelper = new LuntanHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }


    public synchronized static LuntanDBUtils getInstance(Context context) {
        if (sqliteDB == null) {
            sqliteDB = new LuntanDBUtils(context);
        }
        return sqliteDB;
    }


    public void delete(Context context,String id) {
        LuntanHelper dbHelper = new LuntanHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getReadableDatabase();
        db.delete("Luntan", "id=?", new String[] { id });
    }

    /**
     * 修改信息
     * @param context
     * @param userBean
     */
    public void change(Context context, Luntan userBean) {
        LuntanHelper dbHelper = new LuntanHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", userBean.getId());
        values.put("user_id", userBean.getUser_id());
        values.put("content", userBean.getContent());
        values.put("pic", userBean.getPic());
        values.put("time", userBean.getTime());
        values.put("leixing", userBean.getLeixing());
        values.put("biaoti", userBean.getBiaoti());
        values.put("fabuzhe", userBean.getFabuzhe());
        values.put("zan", userBean.getZan());
        values.put("username", userBean.getUsername());
        values.put("head_url", userBean.getHead_url());
        db.update("Luntan", values, "id=?", new String[]{userBean.getId()+""});
    }

    /**
     * 插入数据
     * @param user
     */
    public void insert(Luntan user) {
        try {
            db.execSQL("insert into Luntan(user_id,content,pic,time,leixing,biaoti,fabuzhe,username,head_url,zan) values(?,?,?,?,?,?,?,?,?,?) ", new String[]{
                    user.getUser_id(),
                    user.getContent(),
                    user.getPic(),
                    user.getTime(),
                    user.getLeixing(),
                    user.getBiaoti(),
                    user.getFabuzhe(),
                    user.getUsername(),
                    user.getHead_url(),
                    user.getZan(),
            });
        } catch (Exception e) {
            Log.d("校园资讯插入数据报错", e.getMessage().toString());
        }
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<Luntan> findAll(){
        List<Luntan> shopAddressList = new ArrayList<>();
        Cursor cursor = db
                .query("Luntan", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do{
                Luntan shopAddress = new Luntan();
                shopAddress.setId(cursor.getInt(cursor.getColumnIndex("id")));
                shopAddress.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
                shopAddress.setContent(cursor.getString(cursor.getColumnIndex("content")));
                shopAddress.setPic(cursor.getString(cursor.getColumnIndex("pic")));
                shopAddress.setTime(cursor.getString(cursor.getColumnIndex("time")));

                shopAddress.setLeixing(cursor.getString(cursor.getColumnIndex("leixing")));
                shopAddress.setBiaoti(cursor.getString(cursor.getColumnIndex("biaoti")));
                shopAddress.setFabuzhe(cursor.getString(cursor.getColumnIndex("fabuzhe")));

                shopAddress.setZan(cursor.getString(cursor.getColumnIndex("zan")));
                shopAddress.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                shopAddress.setHead_url(cursor.getString(cursor.getColumnIndex("head_url")));
                shopAddressList.add(shopAddress);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return shopAddressList;
    }


    public List<Luntan> loadByName(String name){
        List<Luntan>shopAddressList=new ArrayList<Luntan>();
        Cursor cursor=db.query("Luntan",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                if(cursor.getString(cursor.getColumnIndex("leixing")).indexOf(name)!=-1){
                    Luntan shopAddress = new Luntan();
                    shopAddress.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    shopAddress.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
                    shopAddress.setContent(cursor.getString(cursor.getColumnIndex("content")));
                    shopAddress.setPic(cursor.getString(cursor.getColumnIndex("pic")));
                    shopAddress.setTime(cursor.getString(cursor.getColumnIndex("time")));

                    shopAddress.setLeixing(cursor.getString(cursor.getColumnIndex("leixing")));
                    shopAddress.setBiaoti(cursor.getString(cursor.getColumnIndex("biaoti")));
                    shopAddress.setFabuzhe(cursor.getString(cursor.getColumnIndex("fabuzhe")));

                    shopAddress.setZan(cursor.getString(cursor.getColumnIndex("zan")));
                    shopAddress.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                    shopAddress.setHead_url(cursor.getString(cursor.getColumnIndex("head_url")));
                    shopAddressList.add(shopAddress);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return shopAddressList;
    }
}

