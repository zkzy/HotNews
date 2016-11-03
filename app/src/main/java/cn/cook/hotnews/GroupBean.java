package cn.cook.hotnews;

import java.io.Serializable;

/**
 * Created by hasee on 2016/11/3.
 */

public class GroupBean implements Serializable{
    private boolean itemStatus;
    private String date;


    public boolean getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(boolean itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
