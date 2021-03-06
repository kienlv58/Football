package com.two.football.view.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.two.football.adapter.ClubRecycleAdapter;
import com.two.football.adapter.HomeVideoAdapter;
import com.two.football.model.Club;
import com.two.football.R;
import com.two.football.model.MatchHome;
import com.two.football.model.Video;
import com.two.football.view.activity.ClubActivity;
import com.two.football.adapter.MatchAdapter;
import com.two.football.view.activity.VideoPlusActivity;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by TWO on 10/23/2017.
 */

public class FragmentHome extends Fragment implements View.OnClickListener {
    private View view;
    private ArrayList<MatchHome> matches;
    private ViewPager viewPager;
    private CircleIndicator indicator;
    private ImageView btnLeft, btnRight;
    private int id;
    private Button btnViewClub, btnVideoPlus;
    private MatchAdapter adapter;
    private Handler handler;
    private int delay = 3000;
    private int page = 0;
    private RecyclerView recyclerVideo;
    private List<Video> videos;
    private HomeVideoAdapter videoAdapter;
    private RecyclerView recyclerClub;
    private List<Club> clubs;
    private ClubRecycleAdapter clubAdapter;
    private DatabaseReference mReference;
    private LinearLayout llHomeLive, llVideoNb, llinforClub;

    Runnable runnable = new Runnable() {
        public void run() {
            if (adapter.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            viewPager.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };

    public FragmentHome() {
        handler = new Handler();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_layout, container, false);
        mReference = FirebaseDatabase.getInstance().getReference();
        initView();
        initMatch();
        initVideo();
        initClub();
        return view;

    }

    private void initClub() {
        clubs = new ArrayList<>();
        mReference.child("Club").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Club club = dataSnapshot.getValue(Club.class);
                club.setId(dataSnapshot.getKey());
                clubs.add(club);
                clubAdapter = new ClubRecycleAdapter(getContext(), clubs);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                recyclerClub.setLayoutManager(layoutManager);
                recyclerClub.setAdapter(clubAdapter);
                indicator.setViewPager(viewPager);
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

    public void initVideo(){
        videos = new ArrayList<>();
        mReference.child("Tournament").child("Premier League").child("videos").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Video video = dataSnapshot.getValue(Video.class);
                videos.add(video);
                videoAdapter = new HomeVideoAdapter(getContext(), videos);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                recyclerVideo.setLayoutManager(layoutManager);
                recyclerVideo.setAdapter(videoAdapter);

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

    private void initMatch() {
        matches = new ArrayList<>();
        matches.clear();
        mReference.child("Tournament").child("Premier League").child("ltd").child("0").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MatchHome matchHome = dataSnapshot.getValue(MatchHome.class);
                matches.add(matchHome);
                adapter = new MatchAdapter(getContext(), matches);
                viewPager.setAdapter(adapter);
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

        indicator.setViewPager(viewPager);
    }

    private void initView() {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        indicator = (CircleIndicator) view.findViewById(R.id.idicator);

        recyclerVideo = (RecyclerView) view.findViewById(R.id.recycle_video);
        recyclerClub = (RecyclerView) view.findViewById(R.id.recycle_club);

        btnLeft = (ImageView) view.findViewById(R.id.btn_left);
        btnRight = (ImageView) view.findViewById(R.id.btn_right);

        btnViewClub = (Button) view.findViewById(R.id.btn_view_club);
        btnVideoPlus = (Button) view.findViewById(R.id.btn_video_plus);

        llHomeLive = (LinearLayout) view.findViewById(R.id.ll_home_live);
        llVideoNb = (LinearLayout) view.findViewById(R.id.ll_video_nb);
        llinforClub = (LinearLayout) view.findViewById(R.id.ll_infor_club);

        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnViewClub.setOnClickListener(this);
        btnVideoPlus.setOnClickListener(this);

        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (getActivity().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        int newHeight = height - (actionBarHeight * 2);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llHomeLive.getLayoutParams();
        params.height = newHeight/3;
        llHomeLive.setLayoutParams(params);

        LinearLayout.LayoutParams paramss = (LinearLayout.LayoutParams) llinforClub.getLayoutParams();
        paramss.height = newHeight/3;
        llinforClub.setLayoutParams(paramss);

        LinearLayout.LayoutParams paramsss = (LinearLayout.LayoutParams) llVideoNb.getLayoutParams();
        paramsss.height = newHeight/3;
        llVideoNb.setLayoutParams(paramsss);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left:
                id = viewPager.getCurrentItem();
                if (id==0){
                    id = 2;
                } else {
                    id--;
                }
                viewPager.setCurrentItem(id);
                break;
            case R.id.btn_right:
                id = viewPager.getCurrentItem();
                if (id==3){
                    id = 0;
                } else {
                    id++;
                }
                viewPager.setCurrentItem(id);
                break;

            case R.id.btn_view_club:
                Intent intent = new Intent(getContext(), ClubActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_video_plus:
                Intent intent1 = new Intent(getContext(), VideoPlusActivity.class);
                startActivity(intent1);
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }
}
