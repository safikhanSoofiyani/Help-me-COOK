package com.example.helpmecook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button foodlist,ingredlist,generating;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing the buttons with their respective id present in layout
        foodlist=findViewById(R.id.foodlistb);
        ingredlist=findViewById(R.id.ingredlistb);
        generating=findViewById(R.id.generateb);

        //assigning event listeners to each button

        foodlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the activity that contains the code for entering a food item into the
                //file
                Intent intent = new Intent( MainActivity.this, foodlist.class);
                startActivity(intent);
            }
        });

        ingredlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the activity that contains the code for entering an ingredient
                //into the file
                Intent intent1 = new Intent(MainActivity.this , ingredlist.class);
                startActivity(intent1);
            }
        });

        generating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the activity that contains the code for generating the food
                //item and displaying onto the screen
                Intent intent2 = new Intent(MainActivity.this , generatefood.class);
                startActivity(intent2);

            }
        });



    }
}
