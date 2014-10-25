import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main
{
	public static KaiSevenIndex[] Index_kaiSeven;
	public static int EndOfKaiSevenIndex;
	public static int TheLastNewsNo;
	public static String[][] NewsMappingTable;
	
	public static void main(String[] args)
	{
		
		long StarTime = System.currentTimeMillis();
		
		Scanner LevelTwoDataInput = null;
		try
		{
			LevelTwoDataInput = new Scanner(new FileInputStream("KaiSevenIndexData-Level-Two.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("input file error!");
			System.exit(0);
		}
		
		
		TheLastNewsNo = LevelTwoDataInput.nextInt();
		LevelTwoDataInput.nextLine();
		EndOfKaiSevenIndex = LevelTwoDataInput.nextInt();
		LevelTwoDataInput.nextLine();
		LevelTwoDataInput.nextLine();
		Index_kaiSeven = new KaiSevenIndex[EndOfKaiSevenIndex + 1];
				
		buildKaiSevenIndex(LevelTwoDataInput);
		LevelTwoDataInput.close();
//		inspectionBuildKaiSevenIndex();
		
		NewsMappingTable = new String[EndOfKaiSevenIndex + 1][3];
		buildNewsMappingTable();
		
		long EndTime = System.currentTimeMillis();
		double TotalTime = (EndTime - StarTime) / 1000.0;
		System.out.println("It takes " + TotalTime + " sec to build KaiSevenIndex and NewsMappingTable");
		
		SearchEngineWindow SearchEngine = new SearchEngineWindow();
		SearchEngine.setLocation(400, 50);
		SearchEngine.setVisible(true);
		
	}
	
	public static void buildKaiSevenIndex(Scanner Input)
	{
		String TempKeyWord = null;
		int TempRelativePageNum;
		int TempDestinationPageNo;
		int TempPositionNum;
		int TempPositionNo;
		for(int i = 1; i <= EndOfKaiSevenIndex; i++)
		{
			TempKeyWord = Input.nextLine();
			TempRelativePageNum = Input.nextInt();
			Input.nextLine();
			Index_kaiSeven[i] = new KaiSevenIndex(TempKeyWord, TempRelativePageNum);
			Index_kaiSeven[i].setRelativePageNum(TempRelativePageNum);
			for(int j=0; j <= TempRelativePageNum - 1; j++)
			{
				TempDestinationPageNo = Input.nextInt();
				TempPositionNum = Input.nextInt();
				Index_kaiSeven[i].structorPositionOfDestinationPage(j, TempPositionNum);
				Index_kaiSeven[i].setDestinationPageIndexOn(TempDestinationPageNo, j);
				Index_kaiSeven[i].setPositionNumForDestinationIndexOn(j, TempPositionNum);
				for(int k=2; k <= TempPositionNum + 1; k++)
				{
					TempPositionNo = Input.nextInt();
					Index_kaiSeven[i].setPositionNoForDestinationPageIndexOn(j, k, TempPositionNo);
				}
				Input.nextLine();
			}
			Input.nextLine();
		}
	}
	
	public static void buildNewsMappingTable()
	{
		Scanner NewsMappingDataInput = null;
		try
		{
			NewsMappingDataInput = new Scanner(new FileInputStream("NewsMappingTable.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("input data error");
			System.exit(0);
		}
		int Index = 1;
		while(NewsMappingDataInput.hasNextLine())
		{
			NewsMappingTable[Index][0] = NewsMappingDataInput.nextLine();
			NewsMappingTable[Index][1] = NewsMappingDataInput.nextLine();
			NewsMappingTable[Index][2] = NewsMappingDataInput.nextLine();
			NewsMappingDataInput.nextLine();
			Index++;
		}
		NewsMappingDataInput.close();
	}
	
	public static KaiSevenStringLinkListNode linearSearch(String[] theKeywordSet)
	{
		Scanner LinearSearchInput = null;
		try
		{
			LinearSearchInput = new Scanner(new FileInputStream("News.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("input data error");
			System.exit(0);
		}
		
		int KeywordNum = theKeywordSet.length;
		boolean[] isHit = new boolean[KeywordNum];
		for(int i=0; i < KeywordNum; i++)
			isHit[i] = false;
		boolean isTotalHit = true;
		
		String TempHandlingLine1 = null;
		String TempHandlingLine2 = null;
		StringTokenizer HandlingLine = null;
		String HandlingToken = null;
//		String WhatIsDelimiter_1 = " .,':?!\"()"
		String WhatIsDelimiter_2 = " .,':?!\"()-$`@#%&/;=_0123456789*+";
		
		KaiSevenStringLinkListNode StartNode = new KaiSevenStringLinkListNode();
		KaiSevenStringLinkListNode EndNode = StartNode;
		int NewsNo = 1;
		while(LinearSearchInput.hasNextLine())
		{
			TempHandlingLine1 = LinearSearchInput.nextLine();
			TempHandlingLine2 = LinearSearchInput.nextLine();
			for(int i=0; i < KeywordNum; i++)
			{				
				String hitKeyword = theKeywordSet[i];				
				HandlingLine = new StringTokenizer(TempHandlingLine1, WhatIsDelimiter_2);
				while(HandlingLine.hasMoreTokens())
				{
					HandlingToken = HandlingLine.nextToken();
					if(HandlingToken.equals(hitKeyword))
					{
						isHit[i] = true;
						break;
					}
				}
				if(isHit[i])
					continue;
				
				HandlingLine = new StringTokenizer(TempHandlingLine2, WhatIsDelimiter_2);
				while(HandlingLine.hasMoreTokens())
				{
					HandlingToken = HandlingLine.nextToken();
					if(HandlingToken.equals(hitKeyword))
					{
						isHit[i] = true;
						break;
					}
				}
				if(isHit[i])
					continue;
				else
					break;
			}
			
			for(int i=0; i < KeywordNum; i++)
				if(!isHit[i])
				{
					isTotalHit = false;
					break;
				}
			
			LinearSearchInput.nextLine();
			LinearSearchInput.nextLine();
			LinearSearchInput.nextLine();
			LinearSearchInput.nextLine();
			LinearSearchInput.nextLine();
			LinearSearchInput.nextLine();
			if(isTotalHit)
			{
				String[] theHitPageInformation = NewsMappingTable[NewsNo];
				EndNode.increaseNextStringNode(theHitPageInformation);
				EndNode = EndNode.getNextStringNode();
			}
			
			for(int i=0; i < KeywordNum; i++)
				isHit[i] = false;
			isTotalHit = true;	
			
			NewsNo++;
		}
		
		LinearSearchInput.close();
		return StartNode;
	}
	
	public static KaiSevenStringLinkListNode binarySearch(String[] theKeywordSet)
	{
		String hitKeyword = theKeywordSet[0];
		
		int front = 1;
		int rear = EndOfKaiSevenIndex;
		int middle = 0;
		int theHitIndex = 0;
		boolean isHit = false;
		
		while(front <= rear)
		{
			middle = (front + rear) / 2;
			if(Index_kaiSeven[middle].getKeyWord().equals(hitKeyword))
			{
				theHitIndex = middle;
				isHit = true;
				break;
			}
			else
			{
				if(Index_kaiSeven[middle].getKeyWord().compareTo(hitKeyword)<0)
					front = middle + 1;
				else
					rear = middle -1;					
			}
		}		
		
		if(!isHit)
			theHitIndex = (-front);
		
		KaiSevenStringLinkListNode StartNode = new KaiSevenStringLinkListNode();
		KaiSevenStringLinkListNode EndNode = StartNode;
		if(theHitIndex < 0)
			return StartNode;
		
		int theHitPageNo;
		int theHitPageNum = Index_kaiSeven[theHitIndex].getRelativePageNum();
		for(int i=0; i < theHitPageNum; i++)
		{
			theHitPageNo = Index_kaiSeven[theHitIndex].getDestinationPageIndexOn(i);
			String[] theHitPageInformation = NewsMappingTable[theHitPageNo];
			EndNode.increaseNextStringNode(theHitPageInformation);
			EndNode = EndNode.getNextStringNode();
		}
		
		return StartNode;
	}
	
	/*
	private static void inspectionBuildKaiSevenIndex()
	{
		PrintWriter InspectionDataOutput = null;
		try
		{
			InspectionDataOutput = new PrintWriter(new FileOutputStream("KaiSevenIndexData-Test.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.print("onput data error!");
			System.exit(0);
		}
		
		InspectionDataOutput.println(TheLastNewsNo);
		InspectionDataOutput.println(EndOfKaiSevenIndex);
		InspectionDataOutput.println();
		int TempForHitPageNum;
		int TempForHitPageNo;
		int TempPositionNumForHitPageNo;
		for(int i=1; i<=EndOfKaiSevenIndex; i++)
		{
			InspectionDataOutput.println(Index_kaiSeven[i].getKeyWord());
			TempForHitPageNum = Index_kaiSeven[i].getRelativePageNum();
			InspectionDataOutput.println(TempForHitPageNum);
			
			for(int j=0; j<TempForHitPageNum; j++)
			{
				TempForHitPageNo = Index_kaiSeven[i].getDestinationPageIndexOn(j);
				InspectionDataOutput.print(TempForHitPageNo + " ");
				TempPositionNumForHitPageNo = Index_kaiSeven[i].getPositionNumIndexOn(j);
				InspectionDataOutput.print(TempPositionNumForHitPageNo + " ");
				for(int k=2; k<=TempPositionNumForHitPageNo+1; k++)
				{
					InspectionDataOutput.print(Index_kaiSeven[i].getPositionNoIndexOn(j, k) + " ");
				}
				InspectionDataOutput.println();
			}

			InspectionDataOutput.println();
		}
		
		InspectionDataOutput.close();
	}
	*/
}
