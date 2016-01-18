package com.fos.homeworkadapters.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Vanya Mihova on 9.11.2015 г..
 */
public class ArticleModel {

    String title;
    int imageResource;
    int countPointers;
    int countComments;


    public ArticleModel(String title, int imageResource, int countPointers, int countComments) {
        this.title = title;
        this.imageResource = imageResource;
        this.countPointers = countPointers;
        this.countComments = countComments;
    }


    public String getTitle() {
        return title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getCountPointers() {
        return countPointers;
    }

    public int getCountComments() {
        return countComments;
    }


    public void increasePoints() {
        countPointers++;
    }
    public void decreasePoints() {
        if(countPointers > 0)
            countPointers--;
    }


    public void setCountComments(int countComments) {
        this.countComments = countComments;
    }
}
