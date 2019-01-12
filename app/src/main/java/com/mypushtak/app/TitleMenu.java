package com.mypushtak.app;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class TitleMenu extends ExpandableGroup<SubTitle> {

    private String imageUrl;

    public TitleMenu(String title, List<SubTitle> items, String imageUrl) {
        super(title, items);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

