package com.fos.homeworkadapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vanya Mihova on 9.11.2015 г..
 */
public class CommentsActivity extends AppCompatActivity {

    List<String> allCommentStrings = new ArrayList<>();
    ListView listComments;
    EditText editText;
    Button btnSend;
    Button btnBack;
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        listComments = (ListView) findViewById(R.id.list_view_comments);
        editText = (EditText) findViewById(R.id.edit_text_add_comment);
        btnSend = (Button) findViewById(R.id.btn_send_comment);
        btnBack = (Button) findViewById(R.id.btn_go_back);
        btnSend.setOnClickListener(mBtnSendOnClickListener);
        btnBack.setOnClickListener(mBtnBackClickListener);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, allCommentStrings);
        listComments.setAdapter(adapter);
    }




    private View.OnClickListener mBtnSendOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!TextUtils.isEmpty(editText.getText())) {
                allCommentStrings.add(editText.getText().toString());
                editText.setText("");
                adapter.notifyDataSetChanged();
            }
        }
    };


    private View.OnClickListener mBtnBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("countComment", allCommentStrings.size());
            returnIntent.putExtra("itemPosition", getIntent().getExtras().getInt("itemPosition"));
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    };


}
