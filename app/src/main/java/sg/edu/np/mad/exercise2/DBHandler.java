package sg.edu.np.mad.exercise2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "practical6DB.db";

    public static final String TABLE_USER = "User";
    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FOLLOWED = "followed";



    public DBHandler(Context context){
        super(context,"practical6.db",null,2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                +COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_FOLLOWED + " TEXT " + ")";
        db.execSQL(CREATE_USER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void addUser(user user) {
        ContentValues values = userTransfer(user);


        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public ContentValues userTransfer(user user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.name);
        values.put(COLUMN_DESCRIPTION, user.description);
        values.put(COLUMN_FOLLOWED, user.followed);
        return values;
    }

    public ArrayList<user> getUsers() {
        ArrayList<user> users = new ArrayList<>();

        String querySelectAll = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(querySelectAll, null);

        while (cursor.moveToNext()) {
            user user = new user("hi","hi",1,true);
            user.id = cursor.getInt(0);
            user.name = cursor.getString(1);
            user.description = cursor.getString(2);
            user.followed =  cursor.getInt(3) != 0;
            users.add(user);
            /*users.add(new user(cursor.getString(1), cursor.getString(2), cursor.getInt(0), Boolean.parseBoolean(cursor.getString(3))));*/
        }


        return users;
    }

    public void updateUser(user user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_USER, userTransfer(user), COLUMN_ID+"=?", new String[]{Integer.toString(user.id)});
    }



}
