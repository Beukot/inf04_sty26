package com.example.inf04_sty26;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public Dice[] dices;
    public Button rollButton;
    public TextView rollResultText;
    public int rollsSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dices = new Dice[] {
                new Dice(findViewById(R.id.dice1), 0, true),
                new Dice(findViewById(R.id.dice2), 0, true),
                new Dice(findViewById(R.id.dice3), 0, true),
                new Dice(findViewById(R.id.dice4), 0, true),
                new Dice(findViewById(R.id.dice5), 0, true),
        };

        rollButton = findViewById(R.id.rollButton);
        rollResultText = findViewById(R.id.rollResult);

        rollsSum = 0;

        for (int i = 0; i < dices.length; i++) {
            int finalI = i;
            dices[i].diceImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dices[finalI].diceAvailability) {
                        view.setAlpha(0.5F);
                        dices[finalI].diceAvailability = false;
                    } else {
                        view.setAlpha(1);
                        dices[finalI].diceAvailability = true;
                    }
                }
            });
        }

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollsSum = 0;

                for (int i = 0; i < dices.length; i++) {
                    if (dices[i].diceAvailability) {
                        Random rand = new Random();
                        int rollResult = rand.nextInt(6) + 1;

                        dices[i].setDiceValue(rollResult);
                        rollsSum += rollResult;
                    } else {
                        rollsSum += dices[i].diceValue;
                    }
                }

                rollResultText.setText(Integer.toString(rollsSum));
            }
        });
    }
}