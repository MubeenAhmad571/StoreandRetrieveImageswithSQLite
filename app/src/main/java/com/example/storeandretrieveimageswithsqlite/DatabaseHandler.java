package com.example.storeandretrieveimageswithsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper
{

    Context context;
    private static String DATABASe_NAME = "mydb.db";
    private static int DATABASE_VERSION = 1;
    private static String createTableQuery = "create table imageInfo (imageName TEXT" + ",image BLOB)";
    private ByteArrayOutputStream ObjectByteArrayOutputStream;
    private byte[] imageINBytes;


    public DatabaseHandler(Context context) {
        super(context, DATABASe_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(createTableQuery);
            Toast.makeText(context, "Table created successfully inside our database", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(context, "Table not created successfully" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void storeImage(ModelClass objectModelClass) {
        try {
            SQLiteDatabase objectSQLiteDatabase = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = objectModelClass.getImage();

            ObjectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, ObjectByteArrayOutputStream);

            imageINBytes = ObjectByteArrayOutputStream.toByteArray();
            ContentValues ObjectContentValues = new ContentValues();

            ObjectContentValues.put("imageName", objectModelClass.getImageName());
            ObjectContentValues.put("image", imageINBytes);
            long checkIFQueryRuns = objectSQLiteDatabase.insert("imageInfo", null, ObjectContentValues);

            if (checkIFQueryRuns != 0) {
                Toast.makeText(context, "Data added into out table", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Fails to add Data", Toast.LENGTH_LONG).show();
                objectSQLiteDatabase.close();
            }

        } catch (Exception e) {
            Toast.makeText(context, "storeImage" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public ArrayList<ModelClass> getAllImageData()
    {
        try
        {

            SQLiteDatabase objevtSqlitedatabase = this.getReadableDatabase();
            ArrayList<ModelClass> objectModelClass = new ArrayList<>();
            Cursor objectCusrsor = objevtSqlitedatabase.rawQuery("select * from imageInfo", null);

            if (objectCusrsor.getCount() != 0)
            {
                while (objectCusrsor.moveToNext())
                {
                    String nameOfImage = objectCusrsor.getString(0);
                    byte[] imageBytes = objectCusrsor.getBlob(1);

                    Bitmap objeBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    objectModelClass.add(new ModelClass(nameOfImage, objeBitmap));
                }
                return objectModelClass;
            }
            else
                {
                Toast.makeText(context, "No Values exits in Database", Toast.LENGTH_LONG).show();
                return null;
            }
        } catch (Exception e)
        {
            Toast.makeText(context, "getAllImageData" + e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }

    }
}


