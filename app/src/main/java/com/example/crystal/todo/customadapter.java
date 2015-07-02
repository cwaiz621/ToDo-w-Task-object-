package com.example.crystal.todo;

import android.content.Context;
import android.graphics.Color;
import android.nfc.Tag;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Crystal on 6/30/15.
 * This class represents the model for the To Do list
 */
public class customadapter extends ArrayAdapter<Task> {

    private final Context context;
    private final ArrayList<Task> tasks;
    private static final String TAG = "cadapter";
    Integer pos;



    //constructor of the adapter
    public customadapter(Context context) {
        super(context, R.layout.listlayout, TaskDatabase.get(context).getTasks());
        this.context = context;
        //this.tasks=values;
        this.tasks=TaskDatabase.get(context).getTasks();
    }


    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        Task taskitem=getItem(position);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //initializing views
        rowView = inflater.inflate(R.layout.listlayout, parent, false);
        rowView.setTag(new Integer(position));

        final TextView task = (TextView) rowView.findViewById(R.id.Task);
        task.setText(taskitem.getmTitle());

        final EditText taskeditor = (EditText) rowView.findViewById(R.id.editor);
//        taskeditor.setText(task.getText());
        taskeditor.setTag(new Integer(position));

        final TextView datmod = (TextView) rowView.findViewById(R.id.DateMod);
        datmod.setText("entered: " + taskitem.getDate());

        final CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.complete);
        checkBox.setTag(new Integer(position));


        final ViewSwitcher TitleEdit = (ViewSwitcher) rowView.findViewById(R.id.vSwitcher);
        TitleEdit.setTag(new Integer(position));

        Button del = (Button) rowView.findViewById(R.id.delete);
        del.setTag(new Integer(position));


        //When a textview task title is pressed, textview changes to edit text to enable title change
        TitleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText taskeditor = (EditText) v.findViewById(R.id.editor);
                TextView task = (TextView) v.findViewById(R.id.Task);
                taskeditor.setText(task.getText());
                TitleEdit.showNext();
                taskeditor.requestFocus();


                taskeditor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        TitleEdit.showPrevious();

                    }
                });

            }

        });

        //When enter is pressed, task title is changed and view switcher switches back to textview
        taskeditor.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                ViewSwitcher view = (ViewSwitcher) taskeditor.getParent();
                if ((keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Integer temp = (Integer) v.getTag();
                    TextView task = (TextView) view.findViewById(R.id.Task);
                    String tempTitle = taskeditor.getText().toString();
                    TaskDatabase.get(context).editTitle(temp, String.valueOf(tempTitle));
                    task.setText(tasks.get(temp).getmTitle());
                    Toast.makeText(getContext(), task.getText(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, String.valueOf("edittask" + temp));
                    TitleEdit.reset();

                    return true;
                }
                return false;
            }

        });

        //When button is pressed, item is deleted
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = (Integer) v.getTag();
                Toast.makeText(getContext(), task.getText(),Toast.LENGTH_SHORT );
                TaskDatabase.get(context).removeTask(pos);
                notifyDataSetChanged();
            }
        });

        //When item is checked disable row item, and set completion date. When item is unchecked, enable row item and reset the creation date
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                Integer temp = (Integer) checkBox.getTag();
                if (checkBox.isChecked()) {
                    Date currentDate = new Date();
                    Log.d(TAG, String.valueOf(temp));
                    //Integer pos = (Integer) task.getTag();
                    //TaskDatabase.get(context).SetcompletionDate((pos, currentDate);
                    String completionDate = dateFormat.format(currentDate);
                    TaskDatabase.get(context).isComplete(temp, true);
                    TaskDatabase.get(context).SetcompletionDate(temp, currentDate);
                    task.setText(tasks.get(temp).getmTitle());
                    datmod.setText("completed: " + completionDate);
                    datmod.setTextColor(Color.GRAY);
                    task.setTextColor(Color.GRAY);
                    TitleEdit.setClickable(false);
                }else{
                    TitleEdit.setClickable(true);
                    Date date = TaskDatabase.get(context).getDate(temp);
                    TaskDatabase.get(context).isComplete(temp, false);
                    String enteredDate = dateFormat.format(date);
                    datmod.setText("entered: " + enteredDate);
                    datmod.setTextColor(Color.BLACK);
                    task.setTextColor(Color.BLACK);
                }

            }
        });


        //debugging purposes
        Integer temp = (Integer) rowView.getTag();
        Log.d(TAG,String.valueOf("end roview" +temp));

        return rowView;
    }


}
