package org.example.themazerunner.UI;


import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ButtonScaleEffect {

    public static void addScaleEffect(Button button) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(100), button);
        scaleIn.setFromX(1);
        scaleIn.setFromY(1);
        scaleIn.setToX(1.2);
        scaleIn.setToY(1.2);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(100), button);
        scaleOut.setFromX(1.2);
        scaleOut.setFromY(1.2);
        scaleOut.setToX(1);
        scaleOut.setToY(1);

        button.setOnMouseEntered(event -> scaleIn.play());
        button.setOnMouseExited(event -> scaleOut.play());
    }
}

