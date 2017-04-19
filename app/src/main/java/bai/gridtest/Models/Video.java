package bai.gridtest.Models;

/**
 * Created by brand on 4/15/2017.
 */

public class Video {
    private int id;
    private String name;

    public Video(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Video() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
