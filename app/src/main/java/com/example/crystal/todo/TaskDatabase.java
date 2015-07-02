package com.example.crystal.todo;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Crystal on 6/30/15.
 * This class serves as the database for all tasks
 */
public class TaskDatabase {

    private ArrayList<Task> mTasks;
    private static TaskDatabase sTaskD;
    private Context mAppContext;

    private TaskDatabase(Context appcontext){
        mAppContext=appcontext;
        mTasks = new ArrayList<Task>();
        for(int i=0; i<5; i++){
            Task c=new Task();
            c.setmTitle("Task activity" + i);
            c.setmComplete(i % 2 == 0);
            mTasks.add(c);
        }
    }

    public static TaskDatabase get(Context context){
        if (sTaskD==null){
            sTaskD = new TaskDatabase(context.getApplicationContext());
        }
        return sTaskD;
    }

    public ArrayList<Task> getTasks(){
        return mTasks;}


    public Task getTask(UUID id){
        for(Task c: mTasks){
            if(c.getmId().equals(id)){
                return c;
            }
        }
        return null;
    }

    public ArrayList<Task> addTask(Task task){
        mTasks.add(task);
        return mTasks;
    }

    public ArrayList<Task> removeTask(int position){
        mTasks.remove(position);
        return mTasks;
    }

    public ArrayList<Task> editTitle(int position, String newTitle){
        Task task = mTasks.get(position);
        task.setmTitle(newTitle);
        mTasks.set(position, task);
        return mTasks;
    }

    public ArrayList<Task> remove (int position){
        mTasks.remove(position);
        return mTasks;
    }


    public ArrayList<Task> SetcompletionDate (int position, Date date){
        Task task = mTasks.get(position);
        task.setmCompletionDate(date);
        mTasks.set(position, task);
        return mTasks;
    }

    public  Date getDate (int position){
        Task task = mTasks.get(position);
        Date date = task.getmDate();
        return date;
    }

    public ArrayList<Task> isComplete (int position, Boolean complete){
        Task task = mTasks.get(position);
        task.setmComplete(true);
        mTasks.set(position, task);
        return mTasks;
    }

}



