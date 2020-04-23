package com.example.storeandretrieveimageswithsqlite;

import android.graphics.Bitmap;

public class ModelClass {

    String imageName;
    Bitmap image;

    public ModelClass(String imageName, Bitmap image) {
        this.imageName = imageName;
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
