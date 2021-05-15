package mgoyun.com.mgoyun;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.IOException;

public  class Veritabani extends SQLiteOpenHelper {
    public Veritabani(Context context) {
        super(context, "veri.db", null  , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table resimler(id integer primary key,img blob not null,yol text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists resimler");
    }
    public Boolean insertimage(String x, Integer i){
        SQLiteDatabase db=this.getWritableDatabase();
        try {
            FileInputStream fs=new FileInputStream(x);

            byte [] imgbyte = new byte[fs.available()];

            fs.read(imgbyte);

            ContentValues contentValues=new ContentValues();
            contentValues.put("id",i);
            contentValues.put("img",imgbyte);
            contentValues.put("yol",x.trim());
            // contentValues.put("img1",imgbyte1);
            db.insert("resimler",null,contentValues);
            fs.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
    public Bitmap getimage(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        Bitmap bt=null;
        Cursor cursor=db.rawQuery("select * from resimler where id=?",new String[] {String.valueOf(id)});
        if(cursor.moveToNext()){
            byte [] imag=cursor.getBlob(1);
            bt= BitmapFactory.decodeByteArray(imag,0,imag.length);
        }
        return bt;
    }



    public String gettext(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        String y=null;
        Cursor cursor=db.rawQuery("select * from resimler where id=?",new String[] {String.valueOf(id)});
        if(cursor.moveToNext()) {
            y = cursor.getString(2);
        }
        return y;
    }

    public Integer deletedata(Integer id){
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete("resimler","id=?",new String[]{id.toString()});

    }

}