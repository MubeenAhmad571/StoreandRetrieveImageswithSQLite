package com.example.storeandretrieveimageswithsqlite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText imageDetailsET;
    private ImageView objectImageView;

    private static final int PICK_IMAGE_REQUEST=100;
    private Uri imageFilePath;
    private Bitmap imageToStore;

    DatabaseHandler objectDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectTOXML();
    }


    public void connectTOXML()
    {
        try {
            imageDetailsET=findViewById(R.id.imageNameET);
            objectImageView=findViewById(R.id.image);

            objectDatabaseHandler=new DatabaseHandler(this);
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this,"connectTOXML"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void chooseImage(View objectView)
    {
        try {
            Intent objectIntent= new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,PICK_IMAGE_REQUEST);
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this,"chooseImage"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
            {
                imageFilePath=data.getData();
                imageToStore= MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);

                objectImageView.setImageBitmap(imageToStore);

            }
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this,"onActivityResult"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void storeImage(View view)
    {
        try {
            if(!imageDetailsET.getText().toString().isEmpty() && objectImageView.getDrawable()!=null && imageToStore!=null)
            {
                objectDatabaseHandler.storeImage(new ModelClass(imageDetailsET.getText().toString(),imageToStore));
            }
            else if (!imageDetailsET.getText().toString().isEmpty() )
            {
                Toast.makeText(MainActivity.this,"Please select image name",Toast.LENGTH_LONG).show();
            }
            else if(objectImageView.getDrawable()!=null)
            {
                Toast.makeText(MainActivity.this,"Please select image",Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this,"storeImage"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void moveToshowActivity(View view)
    {
        startActivity(new Intent(MainActivity.this,ShowImagesActivity.class ));
    }
}
