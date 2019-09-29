package com.example.helpmecook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;

public class displayingred extends AppCompatActivity {

    Button backb;
    TextView displab;
    private static String filename ="IngredList.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayingred);

        backb=findViewById(R.id.button6);
        displab=findViewById(R.id.textView5);
        File file=null;
        BufferedReader br=null;
        file =new File(getFilesDir(),filename);
        LinkedList<String> llingred = new LinkedList<String>();
        String sfinal="";
        try
        {
            br= new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String strtemp;

            while((strtemp = br.readLine())!=null)
            {
               llingred.add(strtemp);
            }

            //String sfinal="";
            for(String str : llingred)
            {
                sfinal = sfinal+str+"\n";
            }

            displab.setText(sfinal);
            br.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        backb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(displayingred.this,ingredlist.class);
                startActivity(intent);
            }
        });
    }
}
