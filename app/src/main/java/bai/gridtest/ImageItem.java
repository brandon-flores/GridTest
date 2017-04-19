package bai.gridtest;

import android.graphics.Bitmap;

/**
 * Created by next on 4/19/2017.
 */

public class ImageItem {
    private int image_id;
    private String title;

    public ImageItem(int image_id, String title) {
        super();
        this.image_id = image_id;
        this.title = title;
    }

    public int getImageID() {
        return image_id;
    }

    public void setImageID(int image_id) {
        this.image_id = image_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}