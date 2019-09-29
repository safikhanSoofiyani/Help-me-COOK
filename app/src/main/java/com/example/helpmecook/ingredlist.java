package com.example.helpmecook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class ingredlist extends AppCompatActivity {

    Button backb , enterb , deleteb,displayb;
    EditText ingredn , unitsc;
    String singred , sunits , sfinal;
    private final String filename = "IngredList.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredlist);

        //initialising the widgets by their id in layout
        enterb=findViewById(R.id.enterbutton);
        backb=findViewById(R.id.backbutton);
        ingredn=findViewById(R.id.ingredname);
        unitsc=findViewById(R.id.unitscount);
        deleteb=findViewById(R.id.button2);
        displayb=findViewById(R.id.button5);


        //assigning button listeners
        enterb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singred = ingredn.getText().toString();
                sunits = unitsc.getText().toString();
                int unitsCount = Integer.parseInt(sunits);
                sfinal = singred + "," + sunits ;

                FileOutputStream temp = null;
                FileOutputStream out =null;
                File file =null;
                BufferedReader inpIngred = null;
                try {
                    temp=openFileOutput(filename, Context.MODE_APPEND);
                    temp.close();

                    //NOW WE ARE GOING TO READ THE INGREDIENTS FILE INTO A LIST AND CHECKING WHETHER
                    //THE ENTERED INGREDIENT IS PRESENT OR NOT. IF THE INGREDIENT IS ALREADY PRESENT
                    //THEN JUST INCREMENT IT. ELSE IF THE INGREDIENT IS NOT PRESENT THEN
                    //ADD THE INGREDIENT INTO THE FILE

                    LinkedList<String> lingred= new LinkedList<String>();
                    file=new File(getFilesDir(),filename);
                    inpIngred=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    String strtemp;
                    while((strtemp = inpIngred.readLine()) != null)
                    {
                        lingred.add(strtemp);
                    }
                    inpIngred.close();
                    int flag=0; //set this flag if the element is found.
                    for(int i=0;i<lingred.size();i++)
                    {
                        strtemp=lingred.get(i);
                        StringTokenizer str =new StringTokenizer(strtemp,",");
                        String ingredName = str.nextToken();
                        int ingredCount = Integer.parseInt(str.nextToken());
                        if(ingredName.equals(singred))
                        {
                            //IF THE INGREDIENT IS ALREADY PRESENT THEN SET THE FLAG AND
                            //JUST REMOVE THE PREVIOUS ELEMENT FROM THE LIST, INCREMENT THE COUNT
                            //AND THEN ADD THE STRING BACK INTO THE LIST AND FINALLY WRITE THE LIST BACK TO THE FILE
                            flag=1;
                            lingred.remove(i);
                            ingredCount+=unitsCount;
                            String ingredCounttemp = Integer.toString(ingredCount);
                            String sfinal1 = ingredName + "," +ingredCounttemp ;
                            lingred.add(i,sfinal1);
                            out=openFileOutput(filename,Context.MODE_PRIVATE);
                            for(int j=0;j<lingred.size();j++)
                            {
                                out.write((lingred.get(j)).getBytes());
                                out.write("\n".getBytes());
                            }
                            out.close();
                            break;

                        }
                    }

                    FileOutputStream outfile = null;

                    if(flag==0)
                    {
                        outfile= openFileOutput(filename,Context.MODE_APPEND);
                        outfile.write(sfinal.getBytes());
                        outfile.write("\n".getBytes());
                        outfile.close();
                    }
                    Toast.makeText(ingredlist.this,"Saved Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ingredlist.this,MainActivity.class);
                    startActivity(intent);


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ingredlist.this,"Sorry try after sometime",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ingredlist.this,MainActivity.class);
                    startActivity(intent);
                }



            }
        });

        backb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ingredlist.this , MainActivity.class);
                startActivity(intent);
            }
        });

        deleteb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file=null;
                file=new File(getFilesDir(),filename);
                file.delete();
                Toast.makeText(ingredlist.this,"Deleted Successfully",Toast.LENGTH_SHORT).show();
            }
        });

        displayb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ingredlist.this,displayingred.class);
                startActivity(intent);
            }
        });


    }
}
