package com.mypushtak.app;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class TitleFragment extends Fragment {

    View rootView;

    @BindView(R.id.title_name)
    TextView titleName;

    @BindView(R.id.main_content)
    LinearLayout mainContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_title, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void setTitle(String title) {
        titleName.setText(title);
        if (title.equalsIgnoreCase("google")){
            mainContent.setBackgroundColor(getResources().getColor(R.color.red_icon_color));
        } else if (title.equalsIgnoreCase("Motorola")){
            mainContent.setBackgroundColor(getResources().getColor(R.color.green_icon_color));
        } else if (title.equalsIgnoreCase("Samsung")){
            mainContent.setBackgroundColor(getResources().getColor(R.color.yellow_icon_color));
        } else if (title.equalsIgnoreCase("Lenevo")){
            mainContent.setBackgroundColor(getResources().getColor(R.color.blue_icon_color));
        }

    }
}
