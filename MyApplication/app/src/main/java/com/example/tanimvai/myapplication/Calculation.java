package com.example.tanimvai.myapplication;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;

import static android.widget.Toast.*;
import static java.lang.Math.abs;

public class Calculation extends Activity  {

    int height,width;
    Bitmap bitmap;
    int[][] dataSet;
    int pointx=0,pointy=0,avgx,avgy,x1=0,y1=0;
    int radius=65;
    int flag=0,count1=0,count2=0;
    int input;



    public Calculation(Bitmap bitMap,int imageheitht,int imagewidth,int in){

        bitmap = bitMap;
        height=imageheitht;
        width=imagewidth;
        dataSet = new int[width+10][height+10];
        input=in;
        MainActivity.headcount=0;
        Log.i("Appest", height + " h "+width + "in" + input);
        setValue();
        calculateCenter();


    }

    //set value in dataset
    public void setValue()
    {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int pixels = bitmap.getPixel(x, y);
                int red = Color.red(pixels);
                int blue = Color.blue(pixels);
                int green = Color.green(pixels);

                dataSet[x][y] = 0;

                if ((red < 100 && blue < 80 && green < 70)) {

                    dataSet[x][y] = 1;
                    //Log.i("Apptest", x + " " + y);
                }

            }
        }

    }

    // calculate center
    private void calculateCenter() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (dataSet[i][j] == 1) {


                    // CHeck the sum for every pixel
                    int sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;

                    for (int n = i; n < i + radius + radius/10 && n < width; n++) {
                        sum1 = sum1 + dataSet[n][j];
                    }

                    for (int n = i; n > i - (radius + radius/10) && n > 0; n--) {
                        sum2 = sum2 + dataSet[n][j];
                    }

                    for (int n = j; n < j + radius + radius/10 && n < height; n++) {
                        sum3 = sum3 + dataSet[i][n];
                    }

                    for (int n = j; n > j - (radius + radius/10) && n > 0; n--) {
                        sum4 = sum4 + dataSet[i][n];
                    }

                    // determine the center
                    //Log.i("Apptest",sum1+" " + sum2 + " " + sum3 + " sum4" );
                    if (sum1 >= radius && sum2 >= radius && sum3 >= radius && sum4 >= radius) {

                        //if (Math.abs(pointx - i) >= radius/5 && Math.abs(pointy - j) >= radius/5 )
                        {

                            //if (flag == 1)
                            {

                                int check1=i,check2=j;
                                int scount=0;
                                int ssum1=0,ssum2=0;
                                for(int a=i;Math.abs(check1-a)<=radius/5;a++)
                                {
                                    for(int b=j;Math.abs(check2-b)<=radius/5;b++)
                                    {


                                        int l1 = 0, l2 = 0, l3 = 0, l4 = 0;
                                        for (int k = a; k < a + (radius + (radius/10)) && k < width; k++) {

                                            l1 = l1 + dataSet[k][b];
                                        }

                                        for (int k = a; k > a - (radius + (radius/10)) && k > 0; k--) {
                                            l2 = l2 + dataSet[k][b];
                                        }
                                        for (int k = b; k < b + (radius + (radius/10)) && k < height; k++) {
                                            l3 = l3 + dataSet[a][k];
                                        }
                                        for (int k = b; k > b - (radius + (radius/10)) && k > 0; k--) {
                                            l4 = l4 + dataSet[a][k];
                                        }
                                        //System.out.println(l1+ " " + l2 + " " + l3 + " " + l4);
                                        if(l1 > radius && l2 > radius && l3 > radius && l4 > radius)
                                        {
                                            scount++;
                                            ssum1+=a;
                                            ssum2+=b;
                                            check1=a;
                                            check2=b;


                                            for (int k = a; k < a + (radius + (radius/10)) && k < width; k++) {
                                                dataSet[k][b]=0;
                                            }

                                            for (int k = a; k > a - (radius + (radius/10)) && k > 0; k--) {
                                                dataSet[k][b]=0;
                                            }
                                            for (int k = b; k < b + (radius + (radius/10)) && k < height; k++) {
                                                dataSet[a][k]=0;
                                            }
                                            for (int k = b; k > b - (radius + (radius/10)) && k > 0; k--) {
                                                dataSet[a][k]=0;
                                            }
                                        }
                                    }
                                    for(int b=j;Math.abs(check2-b)<=radius/5;b--)
                                    {


                                        int l1 = 0, l2 = 0, l3 = 0, l4 = 0;
                                        for (int k = a; k < a + (radius + (radius/10)) && k < width; k++) {
                                            l1 = l1 + dataSet[k][b];
                                        }

                                        for (int k = a; k > a - (radius + (radius/10)) && k > 0; k--) {
                                            l2 = l2 + dataSet[k][b];
                                        }
                                        for (int k = b; k < b + (radius + (radius/10)) && k < height; k++) {
                                            l3 = l3 + dataSet[a][k];
                                        }
                                        for (int k = b; k > b - (radius + (radius/10)) && k > 0; k--) {
                                            l4 = l4 + dataSet[a][k];
                                        }
                                        //System.out.println(l1+ " " + l2 + " " + l3 + " " + l4);
                                        if(l1 > radius && l2 > radius && l3 > radius && l4 > radius)
                                        {
                                            scount++;
                                            ssum1+=a;
                                            ssum2+=b;
                                            check1=a;
                                            check2=b;
                                            //System.out.println(a + " ab "+b);
                                            //System.out.println(ssum1 + " s " + ssum2);

                                            for (int k = a; k < a + (radius + (radius/10)) && k < width; k++) {
                                                dataSet[k][b]=0;
                                            }

                                            for (int k = a; k > a - (radius + (radius/10)) && k > 0; k--) {
                                                dataSet[k][b]=0;
                                            }
                                            for (int k = b; k < b + (radius + (radius/10)) && k < height; k++) {
                                                dataSet[a][k]=0;
                                            }
                                            for (int k = b; k > b - (radius + (radius/10)) && k > 0; k--) {
                                                dataSet[a][k]=0;
                                            }
                                        }
                                    }
                                }
                                if(scount>0)
                                {
                                    avgx = ssum1/scount;
                                    avgy = ssum2/scount;
                                }


                                //avgx = x1 / count1;
                                //avgy = y1 / count1;


                                int area=0;


                                
                                // call function to determine area of head

                                //Log.i("Apptest", "area = " + area);
                                
                                area = calculationArea(avgx,avgy);
                                
                                area = area + radius*scount;

                                //Toast.makeText(this , area + " area ", Toast.LENGTH_LONG).show();

                                
                                int standardArea = 3*radius*radius;

                                //Log.i("Apptest", "area = " + area + " sta " + standardArea);


                                if(100*area/standardArea >= 85)
                                {
                                    MainActivity.headcount++;
                                    function(avgx,avgy);
                                    Log.i("Apptest", MainActivity.headcount + " area vs standard area : " + 100 * area / standardArea);
                                    //Toast.makeText(this , "area vs standard area : " + 100*area/standardArea, Toast.LENGTH_LONG).show();

                                }

                                
                                //count1 = 1;
                                //x1 = i;
                                //y1 = j;
                            }
                            //pointx = i;
                            //pointy = j;


                        }
                        /*else if (Math.abs(pointx - i) <= radius/5 && Math.abs(pointy - j) <= radius/5) {
                            x1 = x1 + i;
                            y1 = y1 + j;
                            count1++;
                            pointx=i;
                            pointy=j;
                            flag = 1;

                        }*/

                        for (int n = i; n < i + radius && n < width; n++) {
                            dataSet[n][j] = 0;

                        }
                        for (int n = i; n > i - (radius) && n > 0; n--) {
                            dataSet[n][j] = 0;

                        }
                        for (int n = j; n < j + radius && n < height ; n++) {
                            dataSet[i][n] = 0;

                        }
                        for (int n = j; n > j - (radius) && n > 0; n--) {
                            dataSet[i][n] = 0;

                        }


                    }
                }


            }
        }

        /*if(count1>0)
        {
            avgx = x1 / count1;
            avgy = y1 / count1;
        }

        int area=0;

        // call function to determine area of head
        //Log.i("Apptest", "area = " + area);

        area = calculationArea(avgx,avgy);

        area = area + radius*count1;

        //Toast.makeText(this , area + " area ", Toast.LENGTH_LONG).show();

        int standardArea = 3*radius*radius;

        //Log.i("Apptest", "asa2 = " + area + " abs2 " + standardArea + " hsa2 " + MainActivity.headcount);

        if(100*area/standardArea >= 85)
        {
            MainActivity.headcount++;
            Log.i("Apptest", MainActivity.headcount + " area vs standard area : " + 100 * area / standardArea);
            //Toast.makeText(this , "area vs standard area : " + 100*area/standardArea, Toast.LENGTH_LONG).show();

        }

        //tts = new TextToSpeech(this, this);
        //speakOut();

        //textView.setText(" h: " + MainActivity.headcount);

        //Toast.makeText(this,MainActivity.headcount + " " ,Toast.LENGTH_LONG).show();

        Log.i("Apptest", "asa = " + area + " abs " + standardArea + " hsa " + MainActivity.headcount);*/

    }

    public int calculationArea(int avg1, int avg2)
    {
        //Log.i("Apptest", avg1 + " avg" + avg2);
        int m=avg1-radius,n=avg2-radius;
        int[][] cp = new int[radius*3][radius*3];
        int area=0,k,l;
        for (int st = radius; st > 0; st--) {

            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;
            

            for (l = avg2; l > avg2 - radius; l--) {

                k = (int) solveQuadratic(1, -2 * avg1,
                        l * l - 2 * avg2 * l - t, 1);
                
                if(k>0 && l>0)
                {
                    area += dataSet[k][l];
                    cp[k-m][l-n] = -1;
                }
                
                
            }

        }
        

        for (int st = radius; st > 0; st--) {

            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;
            

            for (l = avg2; l > avg2 - radius; l--) {

                k = (int) solveQuadratic(1, -2 * avg1,
                        l * l - 2 * avg2 * l - t, 0);

                if (k > 0 && l > 0) {
                    if (cp[k-m][l-n] != -1) {
                        area += dataSet[k][l];
                        cp[k-m][l-n] = -1;
                    }
                   
                }


            }

        }
        

        for (int st = radius; st > 0; st--) {

            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;
            // System.out.println(avg1+ " " + avg2);

            for (l = avg2; l < avg2 + radius; l++) {

                k = (int) solveQuadratic(1, -2 * avg1,
                        l * l - 2 * avg2 * l - t, 1);
                // System.out.println(k + " l " +l);
                if (k < 0) {
                    break;
                }

                if (k > 0 && l > 0) {
                    if (cp[k-m][l-n] != -1) {
                        area += dataSet[k][l];
                        cp[k-m][l-n] = -1;
                    }
                }



            }

        }
     

        for (int st = radius; st > 0; st--) {

            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;
            

            for (l = avg2; l < avg2 + radius; l++) {

                k = (int) solveQuadratic(1, -2 * avg1,
                        l * l - 2 * avg2 * l - t, 0);

                if (k > 0 && l > 0) {
                    if (cp[k-m][l-n] != -1) {
                        area += dataSet[k][l];
                        cp[k-m][l-n] = -1;
                    }
                }

            }

        }
       

        for (int st = radius; st > 0; st--) {
            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;

            
            for (k = avg1; k > avg1 - radius; k--) {

                l = (int) solveQuadratic(1, -2 * avg2,
                        k * k - 2 * avg1 * k - t, 1);
                if (k > 0 && l > 0) {
                    if (cp[k-m][l-n] != -1) {
                        area += dataSet[k][l];
                        cp[k-m][l-n] = -1;
                    }
                }

                
               
            }
        }
      

        for (int st = radius; st > 0; st--) {
            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;

           
            for (k = avg1; k > avg1 - radius; k--) {

                l = (int) solveQuadratic(1, -2 * avg2,
                        k * k - 2 * avg1 * k - t, 0);
                if (k > 0 && l > 0) {
                    if (cp[k-m][l-n] != -1) {
                        area += dataSet[k][l];
                        cp[k-m][l-n] = -1;
                    }
                }

            }
        }
        
        for (int st = radius; st > 0; st--) {
            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;

          
            for (k = avg1; k < avg1 + radius; k++) {

                l = (int) solveQuadratic(1, -2 * avg2,
                        k * k - 2 * avg1 * k - t, 1);
                
                if (k > 0 && l > 0) {
                    if (cp[k-m][l-n] != -1) {
                        area += dataSet[k][l];
                        cp[k-m][l-n] = -1;
                    }
                }

            }
        }
       

        for (int st = radius; st > 0; st--) {
            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;

            
            for (k = avg1; k < avg1 + radius; k++) {

                l = (int) solveQuadratic(1, -2 * avg2,
                        k * k - 2 * avg1 * k - t, 0);
                if (k > 0 && l > 0) {
                    if (cp[k-m][l-n] != -1) {
                        area += dataSet[k][l];
                        cp[k-m][l-n] = -1;
                    }
                }

            }
        }
        //Log.i("Apptest", "asa");
        
        return area;
    }

    public double solveQuadratic(double a, double b, double c, int ck) {
        double D = b * b - 4 * a * c;
        // System.out.println(b+ " " + D + " D ");

        if (D < 0)
            return -1; // complex solution
        double m = (-b + Math.sqrt(D)) / (2 * a);
        double n = (-b - Math.sqrt(D)) / (2 * a);
        if (ck == 1)
            return m;
        else {
            return n;
        }

    }

    // the head circle set in 0

    private void function(int avg1 , int avg2) {
        int k,l;
        for (int st = radius; st > 0; st--) {


            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;


            for (l = avg2; l > avg2 - radius; l--) {

                k = (int) solveQuadratic(1, -2 * avg1,
                        l * l - 2 * avg2 * l - t, 1);
                // System.out.println(k + " l " +l);
                if (k < 0 || l<0) {
                    break;
                }

                dataSet[k][l]=0;


            }

        }


        for (int st = radius; st > 0; st--) {

            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;


            for (l = avg2; l > avg2 - radius; l--) {

                k = (int) solveQuadratic(1, -2 * avg1,
                        l * l - 2 * avg2 * l - t, 0);

                if (k < 0 || l<0) {
                    break;
                }

                dataSet[k][l]=0;

            }

        }


        for (int st = radius; st > 0; st--) {

            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;


            for (l = avg2; l < avg2 + radius; l++) {

                k = (int) solveQuadratic(1, -2 * avg1,
                        l * l - 2 * avg2 * l - t, 1);

                if (k < 0 || l<0) {
                    break;
                }

                dataSet[k][l]=0;

            }

        }


        for (int st = radius; st > 0; st--) {

            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;


            for (l = avg2; l < avg2 + radius; l++) {

                k = (int) solveQuadratic(1, -2 * avg1,
                        l * l - 2 * avg2 * l - t, 0);
                // System.out.println(k + " l " +l);
                if (k < 0 || l<0) {
                    break;
                }

                dataSet[k][l]=0;
            }

        }


        for (int st = radius; st > 0; st--) {
            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;

            for (k = avg1; k > avg1 - radius; k--) {

                l = (int) solveQuadratic(1, -2 * avg2,
                        k * k - 2 * avg1 * k - t, 1);

                if (l < 0 || k<0) {
                    break;
                }
                dataSet[k][l]=0;
            }
        }


        for (int st = radius; st > 0; st--) {
            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;

            for (k = avg1; k > avg1 - radius; k--) {

                l = (int) solveQuadratic(1, -2 * avg2,
                        k * k - 2 * avg1 * k - t, 0);

                if (l < 0 || k<0) {
                    break;
                }

                dataSet[k][l]=0;

            }
        }


        for (int st = radius; st > 0; st--) {
            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;


            for (k = avg1; k < avg1 + radius; k++) {

                l = (int) solveQuadratic(1, -2 * avg2,
                        k * k - 2 * avg1 * k - t, 1);

                if (l < 0) {
                    break;
                }
                dataSet[k][l]=0;

            }
        }


        for (int st = radius; st > 0; st--) {
            int t = (avg1 + st) * (avg1 + st) - 2
                    * (avg1 + st) * avg1 + avg2 * avg2
                    - 2 * avg2 * avg2;

            for (k = avg1; k < avg1 + radius; k++) {

                l = (int) solveQuadratic(1, -2 * avg2,
                        k * k - 2 * avg1 * k - t, 0);

                if (l < 0) {
                    break;
                }
                dataSet[k][l]=0;

            }
        }
    }
}

