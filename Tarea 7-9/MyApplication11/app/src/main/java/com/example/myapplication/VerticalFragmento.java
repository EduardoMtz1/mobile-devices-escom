package com.example.myapplication;

import android.app.Fragment; import android.os.Bundle; import android.view.LayoutInflater; import android.view.View; import android.view.ViewGroup;
public class VerticalFragmento extends Fragment {
    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b) {
        return li.inflate(R.layout.vertical_fragmento, vg, false);
    }
}
