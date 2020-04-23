package com.example.storeandretrieveimageswithsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ShowImagesActivity extends AppCompatActivity {

    private DatabaseHandler objectDatabaseHandler;
    private RecyclerView objRV;
     private RVAdapter objeRVA;
     private Button shoebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);

        try{
            objRV=findViewById(R.id.RVImages);
            shoebtn=findViewById(R.id.showimagesBtn);
            objectDatabaseHandler=new DatabaseHandler(this);
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


    public void getData(View view)
    {
        try{
             objeRVA=new RVAdapter(objectDatabaseHandler.getAllImageData());
             objRV.setHasFixedSize(true);

             objRV.setLayoutManager(new LinearLayoutManager(this));
             objRV.setAdapter(objeRVA);
        }
        catch (Exception e)
        {
            Toast.makeText(this,"getData"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
