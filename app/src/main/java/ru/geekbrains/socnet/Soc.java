package ru.geekbrains.socnet;

public class Soc {
    private String description; // описание
    private int picture;        // изображение
    private boolean like;       // флажок

    public Soc(String description, int picture, boolean like){
        this.description=description;
        this.picture=picture;
        this.like=like;
    }

    public String getDescription() {
        return description;
    }

    public int getPicture() {
        return picture;
    }

    public boolean isLike() {
        return like;
    }
}