package com.example.crystal.todo;

import android.app.Fragment;
import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Crystal on 6/30/15.
 * This class serves as the view of the to do list
 */
public class ToDoFragment extends Fragment{

    private Task task;
    private EditText AddTask;
    private Button Submit;
    private CheckBox title;
    private String tempTask;
    private TextView test;
    private ArrayList<Task> mTasklist;
    private ListView taskl;
    private static final String TAG = "ToDoFragmant";



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreat Bundle Called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.todo_fragment,parent,false);

        AddTask=(EditText)v.findViewById(R.id.AddTask);
        test=(TextView)v.findViewById(R.id.DateMod);
        taskl =(ListView) v.findViewById(R.id.tasklist);


        //setting adapter
        //taskl.setAdapter(new customadapter(getActivity().getApplication(), mTasklist));
        taskl.setAdapter(new customadapter(getActivity()));


        //new task created when button clicked
        Submit=(Button)v.findViewById(R.id.button);
        Submit.setBackgroundResource(R.drawable.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new Task();
                String temp = AddTask.getText().toString();
                task.setmTitle(temp);
                TaskDatabase.get(getActivity()).addTask(task);
                ((customadapter) taskl.getAdapter()).notifyDataSetChanged();
                Log.d(TAG, "listview updated");
                Toast.makeText(getActivity(), AddTask.getText(), Toast.LENGTH_SHORT).show();
                AddTask.clearFocus();
                AddTask.setText("");
            }
        });
/*
        AddTask.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ( keyCode == KeyEvent.KEYCODE_ENTER) {
                    task = new Task();
                    String temp=AddTask.getText().toString();
                    task.setmTitle(temp);
                    TaskDatabase.get(getActivity()).addTask(task);
                    ((customadapter) taskl.getAdapter()).notifyDataSetChanged();
                    Log.d(TAG, "listview updated");
                    AddTask.clearFocus();
                    AddTask.setText(""); //why does this return another item?
                    return true;
                }
                return false;
            }
        });
*/


        /*Task t= new Task();
        t =  (Task)(taskl.getAdapter()).getItem(position);
        TaskDatabase.get(getActivity()).editTitle(position,"boo");
        ((customadapter) taskl.getAdapter()).notifyDataSetChanged();*/

       // ((customadapter) taskl.getAdapter())


        return v;
    }




}
