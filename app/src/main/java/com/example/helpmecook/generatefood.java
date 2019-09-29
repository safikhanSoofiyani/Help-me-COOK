package com.example.helpmecook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Random;
import java.util.StringTokenizer;

public class generatefood extends AppCompatActivity {

    Button satisfiedb , notsatisfiedb ;
    TextView fooddisplay;
    private final String filenameFood = "FoodList.txt";
    private final String filenameIngred = "IngredList.txt";
    LinkedList<String> lingred = new LinkedList<String>();
    LinkedList<String> lfood = new LinkedList<String>();

    int countForPrim;   //counter for primary ingredient
    int countForSec;    //counter for secondary ingredient
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generatefood);

        //initializing the widgets by their id in layout
        satisfiedb = findViewById(R.id.satis);
        notsatisfiedb = findViewById(R.id.nop);
        fooddisplay =findViewById(R.id.printfood);

        File file1 = null;
        File file2 = null;
        BufferedReader bfood = null;
        BufferedReader bingred = null;
        //int finalPresent = 0; //set this only when the item is to be selected finally
        file1 = new File(getFilesDir(), filenameFood);
        file2 = new File(getFilesDir(), filenameIngred);

        try {

            bfood = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
            //FIRST WE WILL READ THE ENTIRE FOOD ITEMS FILE INTO A LINKED LIST
            String sfoodtemp;
            while ((sfoodtemp = bfood.readLine()) != null) {
                lfood.add(sfoodtemp);
            }
            /*for(String str :lfood)
              fooddisplay.setText( str);*/

            bingred = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
            //NEXT WE WILL READ THE ENTIRE INGREDIENTS FILE INTO A LINKED LIST
            String singredtemp;
            while ((singredtemp = bingred.readLine()) != null) {
                lingred.add(singredtemp);
            }
            bfood.close();
            bingred.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        catch(Exception e)
        {
            Toast.makeText(generatefood.this, (CharSequence) e,Toast.LENGTH_SHORT).show();
        }

        try{
            int mainCount = 0;
            while (mainCount < 90) {
                countForPrim=-1;
                countForSec=-1;
                // NOW WE GENERATE A RANDOM INTEGER TO CHOOSE FROM THE FOODS LIST HERE
                Random rand = new Random();
                int randomValue = rand.nextInt(lfood.size());
                //Toast.makeText(generatefood.this,Integer.toString(randomValue),Toast.LENGTH_SHORT).show();
                //AFTER GENERATING A RANDOM INTEGER IN THE GIVEN RANGE, WE WILL STORE THE CORRESPONDING
                //RECORD INTO VARIABLES
                String chooseTemp = lfood.get(randomValue);
                StringTokenizer str = new StringTokenizer(chooseTemp, ",");
                String chooseFood = str.nextToken();
                String choosePrimaryIngred = str.nextToken();
                String chooseSecondaryIngred = str.nextToken();

                int flag = 0;  //set this when primary ingredient is present

                //NOW THE STRING IS SPLIT INTO RESPECTIVE COMPONENTS
                //WE WILL NOW SPLIT THE INGREDIENTS FILE ITERATIVELY AND SEARCH IF THE PRIMARY INGREDIENT
                //IS PRESENT OR NOT

                for (int i = 0; i < lingred.size(); i++) {
                    String stringIngred = lingred.get(i);
                    //System.out.println(lingred.size());
                    StringTokenizer str1 = new StringTokenizer(stringIngred, ",");
                    String ingredName = str1.nextToken();
                    //int ingredCount = Integer.parseInt(str1.nextToken());
                    countForPrim++;
                    if (choosePrimaryIngred.equals(ingredName)) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0)  //if primary ingredient is not present then continue
                {
                    mainCount++;
                    continue;
                }

                //NOW AFTER SEARCHING FOR PRIMARY INGREDIENT IF THE PRIMARY INGREDIENT IS NOT PRESENT
                //THEN WE HAVE TO GENERATE A RANDOM INTEGER AGAIN ELSE WE SEARCH FOR SECONDARY
                //INGREDIENT


                int finalPresent=0;
                if (flag == 1) {
                    for (int i = 0; i < lingred.size(); i++) {
                        //SEARCHING FOR SECONDARY INGREDIENT
                        String stringIngred = lingred.get(i);
                        StringTokenizer str2 = new StringTokenizer(stringIngred, ",");
                        String ingredName = str2.nextToken();
                        //int ingredCount = Integer.parseInt(str1.nextToken());
                        countForSec++;
                        if (chooseSecondaryIngred.equals(ingredName)) {
                            finalPresent = 1;
                            break;
                        }
                    }
                }

                if (finalPresent == 0) //if the secondary ingredient is not present then continue
                {
                    mainCount++;
                    continue;
                }

                if (finalPresent == 1) {
                    fooddisplay.setText(chooseFood);
                    //int choice = sc.nextInt();
                    break;

                }

            }

            if(mainCount==90)
            {
                Toast.makeText(generatefood.this,"Sorry nothing from your available ingredients can be cooked ",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(generatefood.this,MainActivity.class);
                startActivity(intent);

            }
        }

        catch (NumberFormatException e)
        {
            Toast.makeText(generatefood.this,"Sorry try after sometime(number format)",Toast.LENGTH_SHORT).show();
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            Toast.makeText(generatefood.this,"Sorry try after sometime(array out of bounds)",Toast.LENGTH_SHORT).show();
        }
        catch (ArrayStoreException e)
        {
            Toast.makeText(generatefood.this,"Sorry try after sometime(array store)",Toast.LENGTH_SHORT).show();
        }
        catch (IndexOutOfBoundsException e)
        {
            Toast.makeText(generatefood.this,"Sorry try after sometime(index of bounds)",Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(generatefood.this, (CharSequence) e,Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


        //assigning the event listeners
        satisfiedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    String strttemp = lingred.get(countForPrim);
                    StringTokenizer strr1 = new StringTokenizer(strttemp, ",");
                    String ingredName1 = strr1.nextToken();
                    String strtemp11 = strr1.nextToken();
                    int ingredCount = Integer.parseInt(strtemp11);
                    ingredCount--;
                    if (ingredCount == 0) {
                        lingred.remove(countForPrim);
                        if (countForPrim < countForSec)
                            countForSec--;
                    } else {
                        String ingredCount1 = Integer.toString(ingredCount);
                        String sfinal = ingredName1 + "," + ingredCount1;
                        lingred.remove(countForPrim);
                        lingred.add(countForPrim, sfinal);
                    }

                    String strtemp = lingred.get(countForSec);
                    StringTokenizer strr2 = new StringTokenizer(strtemp, ",");
                    String ingredName = strr2.nextToken();
                    String strrtemp = strr2.nextToken();
                    int ingredCount2 = Integer.parseInt(strrtemp);
                    ingredCount2--;
                    if (ingredCount2 == 0) {
                        lingred.remove(countForSec);

                    } else {
                        String ingredCount12 = Integer.toString(ingredCount2);
                        String sfinal = ingredName + "," + ingredCount12 ;
                        lingred.remove(countForSec);
                        lingred.add(countForSec, sfinal);
                    }


                    FileOutputStream fis = null;

                        fis = openFileOutput(filenameIngred, Context.MODE_PRIVATE);
                        for (String strr : lingred) {
                            fis.write(strr.getBytes());
                            fis.write("\n".getBytes());

                        }
                        fis.close();

                     /*catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(generatefood.this, "Updation error", Toast.LENGTH_SHORT).show();
                    }*/

                    Toast.makeText(generatefood.this, "updated successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(generatefood.this, MainActivity.class);
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Toast.makeText(generatefood.this, (CharSequence) e,Toast.LENGTH_SHORT).show();
                }

            }
        });

        notsatisfiedb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if not satisfied call this activity again
                Toast.makeText(generatefood.this, "New item displayed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(generatefood.this , generatefood.class);
                startActivity(intent);

            }
        });


    }
}
