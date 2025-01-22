package com.buckshot.Controller;

import com.buckshot.View.PrimaryView;

import javafx.scene.layout.Region;

public class PrimaryController {
    private PrimaryView view;
    private int totalRounds;
    private int liveRounds;
    private int blankRounds;

    public PrimaryController() {
        view = new PrimaryView();
        
        view.setOnLiveButtonClick(this::handleLiveButtonClick);
        view.setOnBlankButtonClick(this::handleBlankButtonClick);
    }

    public Region build() {
        return view.build();
    }

    public void handleLiveButtonClick() {
        liveRounds = view.getLiveRounds();
        setTotalRounds();
    }

    public void handleBlankButtonClick() {;
        blankRounds = view.getBlankRounds();
        setTotalRounds();
    }

    private void setTotalRounds() {
        totalRounds = liveRounds + blankRounds;

        view.setTotalRounds(totalRounds);
    }

    public int getTotalRounds() {
        return totalRounds;
    }
    

}
