import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.StringTokenizer;

public class PreBuildKaiSevenIndexDataLevelTwo
{
	public static KaiSevenIndex[] Index_kaiSeven;
	public static int EndOfKaiSevenIndex;
	public static int TheLastNewsNo;
	
	public static void main(String[] args)
	{
		long StarTime = System.currentTimeMillis();
		
		Scanner LevelOneDataInput = null;
		try
		{
			LevelOneDataInput = new Scanner(new FileInputStream("KaiSevenIndexData-Level-One.txt"));
//			LevelOneDataInput = new Scanner(new FileInputStream("LittleKaiSevenIndexData-Level-One.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("input file error!");
			System.exit(0);
		}
		
		TheLastNewsNo = LevelOneDataInput.nextInt();
		LevelOneDataInput.nextLine();
		EndOfKaiSevenIndex = LevelOneDataInput.nextInt();

		LevelOneDataInput.nextLine();
		LevelOneDataInput.nextLine();
		Index_kaiSeven = new KaiSevenIndex[EndOfKaiSevenIndex + 1];
				
		preBuildKaiSevenIndex(LevelOneDataInput);
		LevelOneDataInput.close();
		
//		-------------------------------------------------------------------------
		
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
		
				
		buildKaiSevenIndex(NewsInput);
//		System.out.println("level 2 build finish");
		NewsInput.close();
		
		PrintWriter NewsOutput = null;
		try
		{
			NewsOutput = new PrintWriter(new FileOutputStream("KaiSevenIndexData-Level-Two.txt"));
//			NewsOutput = new PrintWriter(new FileOutputStream("LittleKaiSevenIndexData-Level-Two.txt"));
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
		int TempForHitPageNo;
		int TempPositionNumForHitPageNo;
		for(int i=1; i<=EndOfKaiSevenIndex; i++)
		{
			NewsOutput.println(Index_kaiSeven[i].getKeyWord());
			TempForHitPageNum = Index_kaiSeven[i].getRelativePageNum();
			NewsOutput.println(TempForHitPageNum);
			
			for(int j=0; j<TempForHitPageNum; j++)
			{
				TempForHitPageNo = Index_kaiSeven[i].getDestinationPageIndexOn(j);
				NewsOutput.print(TempForHitPageNo + " ");
				TempPositionNumForHitPageNo = Index_kaiSeven[i].getPositionNumIndexOn(j);
				NewsOutput.print(TempPositionNumForHitPageNo + " ");
				for(int k=2; k<=TempPositionNumForHitPageNo+1; k++)
				{
					NewsOutput.print(Index_kaiSeven[i].getPositionNoIndexOn(j, k) + " ");
				}
				NewsOutput.println();
			}

			NewsOutput.println();
		}
		NewsOutput.close();
		
		long EndTime = System.currentTimeMillis();
		double TotalTime = (EndTime - StarTime) / 1000.0;
		System.out.println("It takes " + TotalTime + " sec to build Index-Data-Level-Two");
	}
	
	private static void preBuildKaiSevenIndex(Scanner Input)
	{
		String TempKeyWord = null;
		int TempPageNum = 0;
		for(int i=1; i<=EndOfKaiSevenIndex; i++)
		{
			TempKeyWord = Input.nextLine();
			TempPageNum = Input.nextInt();
			Input.nextLine();
			Input.nextLine();
			Index_kaiSeven[i] = new KaiSevenIndex(TempKeyWord, TempPageNum, 20);
		}
		
//		System.out.println("level 1 build finish");
	}
	
	private static void buildKaiSevenIndex(Scanner Input)
	{
		String TempHandlingLine = null;
		StringTokenizer HandlingLine = null;
		String HandlingToken = null;
//		String WhatIsDelimiter_1 = " .,':?!\"()";
		String WhatIsDelimiter_2 = " .,':?!\"()-$`@#%&/;=_0123456789*+";
		int NewsNo = 0;
		
		while(Input.hasNextLine())
		{
			NewsNo++;
//			System.out.println("times: " + NewsNo);
			TempHandlingLine = Input.nextLine();
			int TempPositionNo = 1;
			HandlingLine = new StringTokenizer(TempHandlingLine, WhatIsDelimiter_2);
			while(HandlingLine.hasMoreTokens())
			{
				HandlingToken = HandlingLine.nextToken();
				if(isIgnoreString(HandlingToken))
					continue;
				handleToken(HandlingToken, NewsNo, TempPositionNo);
				TempPositionNo++;
			}
			TempHandlingLine = Input.nextLine();
			HandlingLine = new StringTokenizer(TempHandlingLine, WhatIsDelimiter_2);
			while(HandlingLine.hasMoreTokens())
			{
				HandlingToken = HandlingLine.nextToken();
				if(isIgnoreString(HandlingToken))
					continue;
				handleToken(HandlingToken, NewsNo, TempPositionNo);
				TempPositionNo++;
			}
			Input.nextLine();
			Input.nextLine();
			Input.nextLine();
			Input.nextLine();
			Input.nextLine();
			Input.nextLine();
		}
	}
	
	private static void handleToken(String theToken, int NewsNo, int thePositionNo)
	{
		int WhereTheTokenIs = binarySearchForIndex(theToken, 1, EndOfKaiSevenIndex);
		Index_kaiSeven[WhereTheTokenIs].updateKaiSevenPosting(NewsNo, thePositionNo);
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
				|| theString.equals("new")
				|| theString.equals("one")
				|| theString.equals("two")
				|| theString.equals("says")
				|| theString.equals("said")
				|| theString.equals("s")
				|| theString.equals("u")
				|| theString.equals("t")
				|| theString.equals("n"))
			return true;
		else
			return false;
	}

}