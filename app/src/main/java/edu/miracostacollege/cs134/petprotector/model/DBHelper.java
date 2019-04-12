package edu.miracostacollege.cs134.petprotector.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE VERSION, NAME AND TABLE NAME
    public static final String DATABASE_NAME = "PetProtector";
    private static final String DATABASE_TABLE = "Pets";
    private static final int DATABASE_VERSION = 1;


    //TASK 2: DEFINE THE FIELDS (COLUMN NAMES) FOR THE TABLE
    private static final String KEY_FIELD_ID = "_id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_PHONE = "phone";
    private static final String FIELD_IMAGE_NAME = "image_name";


    public DBHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase database){
        String table = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_NAME + " TEXT, "
                + FIELD_DESCRIPTION + " TEXT, "
                + FIELD_PHONE + " INTEGER, "
                + FIELD_IMAGE_NAME + " TEXT" + ")";
        database.execSQL (table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(database);
    }

    //********** DATABASE OPERATIONS:  ADD, UPDATE, EDIT, DELETE

    public void addPet(PetList pet) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //ADD KEY-VALUE PAIR INFORMATION FOR THE GAME NAME
        values.put(FIELD_NAME, pet.getmName());

        //ADD KEY-VALUE PAIR INFORMATION FOR THE GAME DESCRIPTION
        values.put(FIELD_DESCRIPTION, pet.getmDescription());

        //ADD KEY-VALUE PAIR INFORMATION FOR THE GAME RATING
        values.put(FIELD_PHONE, pet.getmPhone());

        //ADD KEY-VALUE PAIR INFORMATION FOR THE GAME RATING
        values.put(FIELD_IMAGE_NAME, pet.getImageName());

        // INSERT THE ROW IN THE TABLE
        long id = db.insert(DATABASE_TABLE, null, values);

        // UPDATE THE GAME WITH THE NEWLY ASSIGNED ID FROM THE DATABASE
        pet.setId(id);

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public List<PetList> getAllPets() {
        List<PetList> petList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        // A cursor is the results of a database query (what gets returned)
        Cursor cursor = database.query(
                DATABASE_TABLE,
                new String[]{KEY_FIELD_ID, FIELD_NAME, FIELD_DESCRIPTION, FIELD_PHONE, FIELD_IMAGE_NAME},
                null,
                null,
                null, null, null, null );

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()){
            do {
                PetList pet =
                        new PetList(cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getInt(3),
                                cursor.getString(4));
                petList.add(pet);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return petList;
    }


    }





