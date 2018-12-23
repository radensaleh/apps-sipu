package com.example.rdsaleh.sipu_polindra.introSlide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.rdsaleh.sipu_polindra.R;

import agency.tango.materialintroscreen.SlideFragment;

public class SlideKopen extends SlideFragment {
    private CheckBox checkBox;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.custom_kopen, container, false);

        return view;
    }

    @Override
    public int backgroundColor() {
        return R.color.colorKopen1;
    }

    @Override
    public int buttonsColor() {
        return R.color.colorKopen2;
    }

}
