package com.bensonsworkwear.bensons.fragment;

import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bensonsworkwear.bensons.R;

public class Tab3 extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab3, container, false);
    }
}
