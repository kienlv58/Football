package com.two.football.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.two.football.R;
import com.two.football.adapter.ReservesAdapter;
import com.two.football.model.Reserves;

import java.util.ArrayList;


public class FragmentLinkTd extends Fragment {

    private ListView lv;
    private ReservesAdapter adapter;
    private ArrayList<Reserves> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_link_td,container,false);
        lv = (ListView) view.findViewById(R.id.listReserves);
        list = new ArrayList<>();
        list.add(new Reserves("http://sample.vodobox.net/skate_phantom_flex_4k/skate_phantom_flex_4k.m3u8"));
        list.add(new Reserves("http://sample.vodobox.net/skate_phantom_flex_4k/skate_phantom_flex_4k.m3u8"));
        adapter = new ReservesAdapter(list,getContext(),R.layout.item_reserves);
        lv.setAdapter(adapter);
        return view;
    }
}
