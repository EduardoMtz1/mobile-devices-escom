package com.example.myapplication;

import android.os.*;
import android.support.v4.app.Fragment;
import android.view.*;

public class Tab2 extends Fragment {
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
    }

    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle bn) {
        return li.inflate(R.layout.tab2, vg, false);
    }
}
