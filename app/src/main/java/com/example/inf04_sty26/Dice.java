package com.example.inf04_sty26;

import android.widget.ImageView;

public class Dice {
    public ImageView diceImageView;
    public int diceImageSource;
    public int diceValue;
    public boolean diceAvailability;

    public Dice(ImageView image, int value, boolean availability) {
        diceImageView = image;
        diceValue = value;
        diceAvailability = availability;
        setDiceValue(value);
    }

    public void setDiceValue(int value) {
        diceValue = value;

        switch (value) {
            case 0:
                diceImageSource = R.drawable.kosc0;
                break;
            case 1:
                diceImageSource = R.drawable.kosc1;
                break;
            case 2:
                diceImageSource = R.drawable.kosc2;
                break;
            case 3:
                diceImageSource = R.drawable.kosc3;
                break;
            case 4:
                diceImageSource = R.drawable.kosc4;
                break;
            case 5:
                diceImageSource = R.drawable.kosc5;
                break;
            case 6:
                diceImageSource = R.drawable.kosc6;
                break;
        }

        diceImageView.setImageResource(diceImageSource);
    }
}
