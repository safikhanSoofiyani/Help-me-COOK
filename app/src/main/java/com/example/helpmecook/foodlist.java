package com.example.helpmecook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class foodlist extends AppCompatActivity {

    Button enterbutton , backbutton, deleteb, displayb;
    EditText foodtext , primtext , sectext;
    String sfood , sprimingred , ssecingred , sfinal;
    private String filename="FoodList.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);

        //initializing the widgets with their id from layout
        enterbutton = findViewById(R.id.enter);
        backbutton = findViewById(R.id.back);
        foodtext = findViewById(R.id.fooditem);
        primtext = findViewById(R.id.pimingred);
        sectext = findViewById(R.id.secingred);
        deleteb  = findViewById(R.id.button);
        displayb=findViewById(R.id.button3);

        //setting button event listeners

        enterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sfood = foodtext.getText().toString();
                sprimingred = primtext.getText().toString();
                ssecingred = sectext.getText().toString();
                sfinal = sfood + "," + sprimingred + "," + ssecingred ;
                //Initializing out to access the file
                FileOutputStream out=null;
                try {
                    //opening the file in append mode. the file is saved in internal storage
                    out=openFileOutput(filename, Context.MODE_APPEND);
                    out.write(sfinal.getBytes());
                    out.write("\n".getBytes());
                    out.close();
                    Toast.makeText(foodlist.this,"Saved Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent =  new Intent(foodlist.this , MainActivity.class);
                    startActivity(intent);


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(foodlist.this,"Sorry. Please try after some time", Toast.LENGTH_LONG).show();
                    Intent intent =  new Intent(foodlist.this , MainActivity.class);
                    startActivity(intent);

                }
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(foodlist.this , MainActivity.class);
                startActivity(intent);
            }
        });

        deleteb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file=null;
                try
                {
                    file=new File(getFilesDir(),filename);
                    file.delete();
                    Toast.makeText(foodlist.this,"Deleting successfully",Toast.LENGTH_SHORT).show();
                }
                catch(Exception e)
                {
                    Toast.makeText(foodlist.this,"Deleting error",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        displayb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(foodlist.this,displayfood.class);
                startActivity(intent);
            }
        });

    }
}
