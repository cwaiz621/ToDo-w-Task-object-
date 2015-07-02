package com.example.crystal.todo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Crystal on 6/30/15.
 * This class sets up the task object
 */
public class Task {

        private UUID mId;
        private String mTitle;
        private Date mDate;
        private Date mCompletionDate;


        private boolean mComplete;
        private boolean mAlert;
        private Date alertDate;
        private String mdescription;


//constructor
    public Task(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }


    public boolean ismComplete() {
        return mComplete;
    }
    public void setmComplete(boolean mComplete) {
        this.mComplete = mComplete;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    //getters and setters for entered date and completion date.
    public Date getmDate(){return  mDate;}

    public String getDate() {
        SimpleDateFormat shortDate = new SimpleDateFormat("dd/MM/yy");
        String shortenedDate = shortDate.format(mDate);
        return shortenedDate;
    }

    public void setmCompletionDate(Date mCompletionDate) {
        this.mCompletionDate = mCompletionDate;
    }

    //Setter and getters to set and get alert to do task
    public Date getAlertDate() {
        return alertDate;
    }
    public boolean ismAlert() {
        return mAlert;
    }
    public void setmAlert(boolean mAlert) {
        this.mAlert = mAlert;
    }

    public void setAlertDate(Date alertDate) {
        this.alertDate = alertDate;
    }


}
