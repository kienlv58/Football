package com.two.football.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.two.football.R;

/**
 * Created by TWO on 10/27/2017.
 */

public class FragmentClubVideo extends Fragment {
    private View view;

    public FragmentClubVideo() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_club_video, container, false);
        return view;
    }
}
