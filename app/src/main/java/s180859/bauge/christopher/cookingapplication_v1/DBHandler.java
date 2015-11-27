package s180859.bauge.christopher.cookingapplication_v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by Christopher on 24/11/2015.
 */
public class DBHandler extends SQLiteOpenHelper {

    static String TABLE_FAVORITES = "Favorites";
    static String KEY_ID = "_ID";
    // JSON Id to correspond in DB.
    static String KEY_JSON = "Jsonid";
    static String KEY_FAV = "Favorite";
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME ="FINALD";
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FAVORITES + "("+ KEY_ID + " INTEGER PRIMARY KEY, " + KEY_FAV + " INTEGER," + KEY_JSON + " TEXT"+");";
        Log.d("SQL", CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_FAVORITES);
        onCreate(db);
    }

    public void addRecipe(Recipe r){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int fav = 0;
        values.put(KEY_JSON,r.getId());
        if(r.isFavorite()){
            fav = 1;
            values.put(KEY_FAV, fav);
        }
        else{
            values.put(KEY_FAV, fav);
        }
        db.insert(TABLE_FAVORITES, null, values);
        db.close();
    }

    public int isNotUpdated(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCount= db.rawQuery("select count(*) from Favorites", null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        return count;
    }

    public int getFavorite(int inPos){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCount= db.rawQuery("select Favorite from Favorites", null);
        mCount.moveToFirst();
        int fav = 0;
            do{
                if(mCount.getPosition() == inPos){
                fav = mCount.getInt(0);
                    db.close();
                    return fav;
                }
            }
            while(mCount.moveToNext());
        db.close();
        return fav;
        }

    public void updateRecipe(Recipe r){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int fav = 0;
        if(r.isFavorite()){
            fav = 1;
            values.put(KEY_FAV, fav);
        }
        else{
            values.put(KEY_FAV, fav);
        }
        db.update(TABLE_FAVORITES,values,"Jsonid = ?", new String[]{String.valueOf(r.getId())});
        db.close();
    }
}
