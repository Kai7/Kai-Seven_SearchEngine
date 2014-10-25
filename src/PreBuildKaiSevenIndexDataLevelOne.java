import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.StringTokenizer;

public class PreBuildKaiSevenIndexDataLevelOne
{
	public static KaiSevenIndex[] Index_kaiSeven;
	public static int EndOfKaiSevenIndex;
	public static int TheLastNewsNo;
	
	public static void main(String[] args)
	{
		long StarTime = System.currentTimeMillis();
		
		Scanner NewsInput = null;
		try
		{
			NewsInput = new Scanner(new FileInputStream("News.txt"));
//			NewsInput = new Scanner(new FileInputStream("LittleNews.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.print("input data error!");
			System.exit(0);
		}
		
		Index_kaiSeven = new KaiSevenIndex[100000];
		EndOfKaiSevenIndex = 0;
		TheLastNewsNo = 0;
		
		buildKaiSevenIndex(NewsInput);
		NewsInput.close();
		
		PrintWriter NewsOutput = null;
		try
		{
			NewsOutput = new PrintWriter(new FileOutputStream("KaiSevenIndexData-Level-One.txt"));
//			NewsOutput = new PrintWriter(new FileOutputStream("LittleKaiSevenIndexData-Level-One.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.print("onput data error!");
			System.exit(0);
		}
		
		NewsOutput.println(TheLastNewsNo);
		NewsOutput.println(EndOfKaiSevenIndex);
		NewsOutput.println();
		int TempForHitPageNum;
		for(int i=1; i<=EndOfKaiSevenIndex; i++)
		{
			NewsOutput.println(Index_kaiSeven[i].getKeyWord());
			TempForHitPageNum = Index_kaiSeven[i].getRelativePageNum();
			NewsOutput.println(TempForHitPageNum);
			NewsOutput.println();
		}
		NewsOutput.close();

		long EndTime = System.currentTimeMillis();
		double TotalTime = (EndTime - StarTime) / 1000.0;
		System.out.println("It takes " + TotalTime + " sec to build Index-Data-Level-One");
	}
	
	private static void buildKaiSevenIndex(Scanner Input)
	{
		String TempHandlingLine = null;
		StringTokenizer HandlingLine = null;
		String HandlingToken = null;
//		String WhatIsDelimiter_1 = " .,':?!\"()"
		String WhatIsDelimiter_2 = " .,':?!\"()-$`@#%&/;=_0123456789*+";

		while(Input.hasNextLine())
		{
			TheLastNewsNo++;
//			System.out.println("times: " + TheLastNewsNo);
			TempHandlingLine = Input.nextLine();
			HandlingLine = new StringTokenizer(TempHandlingLine, WhatIsDelimiter_2);
			while(HandlingLine.hasMoreTokens())
			{
				HandlingToken = HandlingLine.nextToken();
				if(isIgnoreString(HandlingToken))
					continue;
				handleToken(HandlingToken);
			}
			TempHandlingLine = Input.nextLine();
			HandlingLine = new StringTokenizer(TempHandlingLine, WhatIsDelimiter_2);
			while(HandlingLine.hasMoreTokens())
			{
				HandlingToken = HandlingLine.nextToken();
				if(isIgnoreString(HandlingToken))
					continue;
				handleToken(HandlingToken);
			}
			Input.nextLine();
			Input.nextLine();
			Input.nextLine();
			Input.nextLine();
			Input.nextLine();
			Input.nextLine();
		}
	}
	
	private static void handleToken(String theToken)
	{
		int WhereTheTokenIs = binarySearchForIndex(theToken, 1, EndOfKaiSevenIndex);
		if(WhereTheTokenIs > 0)
			Index_kaiSeven[WhereTheTokenIs].riseRelativePageNum();
		else
		{
			EndOfKaiSevenIndex++;
			insertionToKaiSevenIndex(theToken, (-WhereTheTokenIs));
		}
	}
	
	private static int binarySearchForIndex(String theSearchToken, int front, int rear)
	{
		int middle = 0;
		
		while(front <= rear)
		{
			middle = (front + rear) / 2;
			if(Index_kaiSeven[middle].getKeyWord().equals(theSearchToken))
				return middle;
			else
			{
				if(Index_kaiSeven[middle].getKeyWord().compareTo(theSearchToken)<0)
					front = middle + 1;
				else
					rear = middle -1;					
			}
		}
		
		return (-front);
	}
	
	private static void insertionToKaiSevenIndex(String theToken, int toIndex)
	{
		for(int i=EndOfKaiSevenIndex; i>toIndex; i--)
		{
			Index_kaiSeven[i] = Index_kaiSeven[i-1];
		}
		Index_kaiSeven[toIndex] = new KaiSevenIndex(theToken);
		Index_kaiSeven[toIndex].riseRelativePageNum();
	}
	
	private static boolean isIgnoreString(String theString)
	{
		if(theString.equals("the") 
				|| theString.equals("that")
				|| theString.equals("who")
				|| theString.equals("he")
				|| theString.equals("their")
				|| theString.equals("it")
				|| theString.equals("its")
				|| theString.equals("of")
				|| theString.equals("off")
				|| theString.equals("for")
				|| theString.equals("from")
				|| theString.equals("after")
				|| theString.equals("about")
				|| theString.equals("a") 
				|| theString.equals("an")
				|| theString.equals("to")
				|| theString.equals("at")
				|| theString.equals("and")
				|| theString.equals("but")
				|| theString.equals("with")
				|| theString.equals("no")
				|| theString.equals("not")
				|| theString.equals("may")
				|| theString.equals("can")
				|| theString.equals("is")
				|| theString.equals("are")
				|| theString.equals("as")
				|| theString.equals("has")
				|| theString.equals("have")
				|| theString.equals("was")
				|| theString.equals("be")
				|| theString.equals("will")
				|| theString.equals("in")
				|| theString.equals("on")
				|| theString.equals("up")
				|| theString.equals("out")
				|| theString.equals("by")
				|| theString.equals("his")
				|| theString.equals("more")
				|| theString.equals("over")
				|| theString.equals("s")
				|| theString.equals("u")
				|| theString.equals("t")
				|| theString.equals("n"))
			return true;
		else
			return false;
	}

}