package com.example.helpmecook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class displayfood extends AppCompatActivity {
    TextView displayfood;
    Button backb;
    private static String filename = "FoodList.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayfood);

        displayfood=findViewById(R.id.disp);
        backb=findViewById(R.id.button4);
        LinkedList<String> llfood = new LinkedList<String>();
        File file=null;
        BufferedReader br= null;
        String sfinal="";
        file=new File(getFilesDir(),filename);
        try {
            br= new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String strtemp;
            while((strtemp=br.readLine())!= null)
            {
                llfood.add(strtemp);
            }

            for(String str : llfood)
            {
                StringTokenizer str1 = new StringTokenizer(str,",");
                sfinal = sfinal+str1.nextToken()+"  (which require "+str1.nextToken()+" and "+str1.nextToken()+")\n";
            }
            displayfood.setText(sfinal);
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        backb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(displayfood.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
