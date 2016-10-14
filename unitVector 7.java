import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.io.PrintWriter;
import java.text.DecimalFormat;
/*
K-means clustering
dr agra wall to get the tweet data set
*/
class Untitled { //read from a file and write the feat vector to the file
	/*ArrayList<Integer> unigram = new ArrayList<Integer>(Collections.nCopies(95, 0));
	public static void unigram(String strTweet, int rating){
		for(char ch:strTweet.toCharArray()){
			if(ch<' '||ch>'~') //continue if its not one of the 95 chars in our unigram
				continue;
			//increments the chars index in the array
			unigram.set(((int)ch)-32, (unigram.get(((int)ch)-32))+1);
		}

	}*/
	public static void main(String[] args) throws FileNotFoundException {
		BufferedReader br = null;     
		ArrayList<Integer> unigram = new ArrayList<Integer>(Collections.nCopies(95, 0));
		double strSize = 0;
		int chr = 32;
		double norm = 0;
		int tweetRating;
		//output file
		PrintWriter outputFile = new PrintWriter("tweetvector.csv");
		PrintWriter tv = new PrintWriter(new File("tweetVector.csv"));
		StringBuilder sb = new StringBuilder();
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		sb.append("vector");
		sb.append(',');
		sb.append("rating");
		sb.append('\n');

		//string that eventually gets read in from a file
		String strTweet;
		String line="";
		
		try{
		br = new BufferedReader(new FileReader("tweets_result.csv"));
		
		
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			strSize = 0;
			String[] tweetLine = line.split(",");// use comma as separator
			strTweet=tweetLine[1];
			tweetRating = Integer.parseInt(tweetLine[2]);
			strSize += strTweet.length();//gets the size of the line
			for(char ch:strTweet.toCharArray()){
				if(ch<' '||ch>'~') //continue if its not one of the 95 chars in our unigram
					continue;
				//increments the chars index in the array
				unigram.set(((int)ch)-32, (unigram.get(((int)ch)-32))+1);
			}
			chr=32;
			norm = 0;
			for(int a : unigram){
				norm +=(Math.pow((a/strSize), 2));
				//System.out.println(a/strSize);
			}
			//System.out.println(norm);

			norm=Math.sqrt(norm);
			//write result
			for(int a : unigram){
				//sb.append(Double.toString((a/strSize))));
				sb.append(String.format("%.2f",((a/strSize)/norm)));
				//System.out.println(norm+" "+a/strSize);
				sb.append(" ");
				chr++;
			}
			sb.append(',');
			sb.append(Integer.toString(tweetRating));
			sb.append('\n');
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

		tv.write(sb.toString());
		tv.close();
		//outputFile.print("-vector 1.0-");
		outputFile.close();
		
	}
}