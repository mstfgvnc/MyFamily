package mgoyun.com.mgoyun;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Veritabanidil extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="DİL";
    public static final int DATABASE_VERSİON=1;
    public static final String KİŞİLER_TABLE="DİLLER";

    public static final String  ROW_ID="id";
    public static final String  ROW_NAME="dil";

    public Veritabanidil(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSİON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + KİŞİLER_TABLE + "("+ROW_ID+ " INTEGER PRIMARY KEY, "+ROW_NAME+" TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + KİŞİLER_TABLE);
        onCreate(db);
    }

    public void Ekle(String language,Integer i) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(ROW_ID,i);
        cv.put(ROW_NAME,language.trim());
        db.insert(KİŞİLER_TABLE,null,cv);
        db.close();

    }
    public String gettext(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        String y=null;
        Cursor cursor=db.rawQuery("select * from DİLLER where id=?",new String[] {String.valueOf(id)});
        if(cursor.moveToNext()) {
            y = cursor.getString(1);
        }
        return y;
    }

    public Integer deletedata(Integer id){
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete(KİŞİLER_TABLE,"id=?",new String[]{id.toString()});

    }


}
