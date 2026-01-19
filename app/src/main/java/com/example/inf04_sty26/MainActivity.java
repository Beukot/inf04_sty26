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

    //
    // Aha, w xml jak masz obrazki to daj im adjustViewBounds na true żeby miały poprawny rozmiar
    //


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

        // tutaj zrobiłem se tablice na kości. sowwy że zrobiłem klasę ale to nie jest skomplikowane :3
        // inaczej bym musiał zrobić kilka tabelek :P
        dices = new Dice[] {
                // tutaj zresztą masz findviewbyid tylko przy tworzeniu obiektu
                new Dice(findViewById(R.id.dice1), 0, true),
                new Dice(findViewById(R.id.dice2), 0, true),
                new Dice(findViewById(R.id.dice3), 0, true),
                new Dice(findViewById(R.id.dice4), 0, true),
                new Dice(findViewById(R.id.dice5), 0, true),
        };

        rollButton = findViewById(R.id.rollButton);
        rollResultText = findViewById(R.id.rollResult);

        rollsSum = 0;

        // tu jest pętla co każdemu z imageView w tablicy daje funkcję onclick.
        for (int i = 0; i < dices.length; i++) {
            int finalI = i;       // to ci powinno podpowiedzieć, no ale poniżej masz sus funkcję co nie możesz przyjąć zmiennej "i" z pętli for

            dices[i].diceImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dices[finalI].diceAvailability) { // btw jak nie załapałeś to ten if to to samo co if (dices[finalI].diceAvailability == true)
                        view.setAlpha(0.5F); // to jest inaczej połowa przezroczystości (to F ci też raczej podpowie, oznacza on że  to jest float bo normalnie uznaje to za double)
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
                        // a no i musisz generator liczb losowych stworzyć :P
                        Random rand = new Random();
                        // w javie nextInt losuje ci int od 0 włącznie do podanej wartości bez niej (dlatego +1 jest)
                        int rollResult = rand.nextInt(6) + 1;

                        dices[i].setDiceValue(rollResult);
                        rollsSum += rollResult;
                    } else {
                        rollsSum += dices[i].diceValue;
                    }
                }

                // pamiętaj żeby zamieniać inty na stringi! :3
                rollResultText.setText(Integer.toString(rollsSum));
            }
        });
    }
}