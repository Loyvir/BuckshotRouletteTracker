package com.buckshot.View;


import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PrimaryView {
    private Runnable onLiveButtonClick;
    private Runnable onBlankButtonClick;
    private int liveRounds;
    private int blankRounds;
    private int totalRounds;
    private BorderPane root;
    private List<Boolean> selectedStates; 

    public PrimaryView() {
    }

    public Region build() {
        root = new BorderPane();
        root.setTop(roundsContainer());

        String css = this.getClass().getResource("/styles.css").toExternalForm();
        root.getStylesheets().add(css);

        return root;
    }

    private Node selectLiveRounds() {
        VBox vbox = new VBox(5);
        Label label = new Label("Select Live Rounds");
        ToggleGroup liveToggleGroup = new ToggleGroup();
        HBox radioButtonsBox = new HBox(10);

        label.setId("live-rounds-label");
        radioButtonsBox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
    
        for (int i = 1; i <= 5; i++) {
            RadioButton radioButton = new RadioButton(String.valueOf(i));
            radioButton.setToggleGroup(liveToggleGroup);
            radioButton.setUserData(i);
            radioButtonsBox.getChildren().add(radioButton);
        }
    
        liveToggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                liveRounds = (int) newToggle.getUserData();
                if (onLiveButtonClick != null) {
                    onLiveButtonClick.run();
                    root.setCenter(trackerDiaplay());
                }
            }
        });
    
        vbox.getChildren().addAll(label, radioButtonsBox);
        return vbox;
    }
    

    private Node selectBlankRounds() {
        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);
    
        Label label = new Label("Select Blank Rounds");
        ToggleGroup blankToggleGroup = new ToggleGroup();
    
        HBox radioButtonsBox = new HBox(10);
        radioButtonsBox.setAlignment(Pos.CENTER);
        label.setId("blank-rounds-label");
    
        for (int i = 1; i <= 5; i++) {
            RadioButton radioButton = new RadioButton(String.valueOf(i));
            radioButton.setToggleGroup(blankToggleGroup);
            radioButton.setUserData(i);
            radioButtonsBox.getChildren().add(radioButton);
        }
    
        blankToggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                blankRounds = (int) newToggle.getUserData();
                if (onBlankButtonClick != null) {
                    onBlankButtonClick.run();
                    root.setCenter(trackerDiaplay());
                }
            }
        });
    
        vbox.getChildren().addAll(label, radioButtonsBox);
        return vbox;
    }

    private Node roundsContainer() {
        VBox vbox = new VBox();

        vbox.getChildren().add(selectLiveRounds());
        vbox.getChildren().add(selectBlankRounds());

        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER);


        return vbox;
    }

    private Node trackerDiaplay() {
        VBox vbox = new VBox();
        HBox labelHbox = new HBox();
        Label liveLabel = new Label("Live " + liveRounds);
        Label blankLabel = new Label("Blank " + blankRounds);
    
        liveLabel.setId("live-rounds-label");
        blankLabel.setId("blank-rounds-label");
    
        labelHbox.getChildren().addAll(liveLabel, blankLabel);
        vbox.getChildren().add(labelHbox);
    
        for (int i = 0; i < totalRounds; i++) {
            final int index = i; // Capture the current index
            Label roundLabel = new Label("Round " + (i + 1));
    
            HBox hbox = new HBox();
            RadioButton live = new RadioButton("Live");
            RadioButton blank = new RadioButton("Blank");
            ToggleGroup group = new ToggleGroup();
            double chance = ((double) liveRounds / (liveRounds + blankRounds)) * 100;
            Label chanceForLiveRound = new Label("Chance for live round: " + Math.round(chance) + "%");
    
            live.setToggleGroup(group);
            blank.setToggleGroup(group);
    
            // Restore the selection state
            if (selectedStates.get(index) != null) {
                if (selectedStates.get(index)) {
                    live.setSelected(true);
                } else {
                    blank.setSelected(true);
                }
            }
    
            // Update the state when a radio button is selected
            live.setOnAction(e -> {
                if (liveRounds == 0) {
                    live.setSelected(false);
                    return;
                }
    
                // Check if there was a previous selection and increment the respective count
                if (selectedStates.get(index) != null) {
                    if (selectedStates.get(index)) {
                        // If it was previously live, do nothing (already selected)
                        return;
                    } else {
                        // If it was previously blank, increment blankRounds
                        blankRounds++;
                    }
                }
    
                selectedStates.set(index, true); // Mark this round as live selected
                liveRounds--; // Decrease liveRounds
                root.setCenter(trackerDiaplay()); // Rebuild the display
            });
    
            blank.setOnAction(e -> {
                if (blankRounds == 0) {
                    blank.setSelected(false);
                    return;
                }
    
                // Check if there was a previous selection and increment the respective count
                if (selectedStates.get(index) != null) {
                    if (selectedStates.get(index)) {
                        // If it was previously live, increment liveRounds
                        liveRounds++;
                    } else {
                        // If it was previously blank, do nothing (already selected)
                        return;
                    }
                }
    
                selectedStates.set(index, false); // Mark this round as blank selected
                blankRounds--; // Decrease blankRounds
                root.setCenter(trackerDiaplay()); // Rebuild the display
            });
    
            hbox.getChildren().addAll(roundLabel, live, blank, chanceForLiveRound);
            hbox.setAlignment(Pos.CENTER);
            hbox.setSpacing(10);
    
            vbox.getChildren().add(hbox);
        }
    
        vbox.setAlignment(Pos.CENTER);
        labelHbox.setAlignment(Pos.CENTER);
        labelHbox.setSpacing(10);
        vbox.setSpacing(5);
    
        return vbox;
    }
    

    public void setOnLiveButtonClick(Runnable onButtonClick) {
        onLiveButtonClick = onButtonClick;
    }
    public void setOnBlankButtonClick(Runnable onButtonClick) {
        onBlankButtonClick = onButtonClick;
        
    }

    public void setLiveRounds(int rounds) {
        liveRounds = rounds;
    }

    public void setBlankRounds(int rounds) {
        blankRounds = rounds;
    }

    public int getLiveRounds() {
        return liveRounds;
    }

    public int getBlankRounds() {
        return blankRounds;
    }

    public void setTotalRounds(int totalRounds) {
        this.totalRounds = totalRounds;
        selectedStates = new ArrayList<>(totalRounds);
        for (int i = 0; i < totalRounds; i++) {
            selectedStates.add(null); // null means no selection
        }
    }


    
}
