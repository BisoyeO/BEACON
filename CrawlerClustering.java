import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.io.PrintWriter;
import java.text.DecimalFormat;
class Untitled {
	public static void main(String[] args)  throws FileNotFoundException {
		BufferedReader br = null;   
		String line="";
		try{
			br = new BufferedReader(new FileReader("CrawlerMalwareData.txt"));
			while ((line = br.readLine()) != null) {
				double  add=0;
				String[] tweetLine = line.split("\\s+");
				System.out.print(tweetLine[0]+"\t"+tweetLine[1]+"\t");
				for(int a=2;a<tweetLine.length;a++)
					add+=Double.parseDouble(tweetLine[a]);
				System.out.println(add);
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
	}
}