package com.two.football.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.R;
import com.two.football.adapter.HighlightAdapter;
import com.two.football.model.Highlight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TWO on 10/27/2017.
 */

public class VideoPlusActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private List<Highlight> list;
    private HighlightAdapter adapter;
    private DatabaseReference reference;
    private Intent intent;
    private ImageView imgBack;
    private TextView tvNameTool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reference = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_video_favorite);
        //getSupportActionBar().hide();
        initView();
        initVideo();
    }

    private void initVideo() {
        list = new ArrayList<>();
        reference.child("Tournament").child("Premier League").child("videos").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Highlight highlight = dataSnapshot.getValue(Highlight.class);
                list.add(highlight);

                adapter = new HighlightAdapter(VideoPlusActivity.this, list);

                listView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.lv_video_favorite);

        tvNameTool = (TextView) findViewById(R.id.tv_name_toolbar);
        tvNameTool.setText("Video nổi bật");
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent = new Intent(VideoPlusActivity.this, PlayVideoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title",list.get(position).getTitle());
        bundle.putString("link",list.get(position).getUrlVideo());
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
