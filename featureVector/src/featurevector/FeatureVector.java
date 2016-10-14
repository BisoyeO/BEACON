/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package featurevector;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import com.opencsv.CSVReader;

/**
 *
 * @author Bisoye
 */
public class FeatureVector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        BufferedReader br = null;
        PrintWriter outFile = new PrintWriter("Evolution Vector.txt", "UTF-8");
        ArrayList<Double> unigram = new ArrayList<Double>(Collections.nCopies(95, 0.0));
        double strSize = 0;
        int chr = 32;
        double norm = 0;
        int tweetRating;
        try {
            CSVReader reader = new CSVReader(new FileReader("Evolution Text Data (4 Instances).csv"));
            String[] nextLine;
            reader.readNext();//gets rid of the title line
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                //System.out.println(nextLine[1] + nextLine[2]);
                for (char ch : nextLine[2].toCharArray()) {
                    if (ch < ' ' || ch > '~') //continue if its not one of the 95 chars in our unigram
                    {
                        continue;
                    }
                    unigram.set(((int) ch) - 32, (unigram.get(((int) ch) - 32)) + 1);//increments the chars index in the array
                }
                for (int a = 0; a < 95; a++) {
                    //System.out.println(((char)a)+" "+unigram.get(a)+" "+nextLine[2].length());
                    unigram.set(a, unigram.get(a) / (nextLine[2]).length());
                    //System.out.println(a/strSize);
                }
                
                //print out the unigram
                System.out.print(nextLine[0] + " "+nextLine[1]+" ");
                outFile.print(nextLine[0] + "\t"+nextLine[1]+"\t");
                for (double a : unigram) {
                    System.out.print(a + " ");
                    outFile.print(a + "\t");
                }
                System.out.println();
                outFile.println();
                for (int a = 0; a < 95; a++) {
                    unigram.set(a, 0.0);
                    //System.out.println(a/strSize);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        outFile.close();
    }

}
