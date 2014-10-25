
public class KaiSevenPosting
{
	private int RelativePageNum;
	private int[][] DestinationPage;
	
	public KaiSevenPosting()
	{
		RelativePageNum = 0;
		DestinationPage = null;
	}
	
	public KaiSevenPosting(int theRelativePageNum)
	{
		RelativePageNum = 0;
		DestinationPage = new int[theRelativePageNum][];
	}
	
	public KaiSevenPosting(int theRelativePageNum, int theKeywordPositionNum)
	{
		RelativePageNum = 0;
		DestinationPage = new int[theRelativePageNum][theKeywordPositionNum + 2];
	}
	
	public void structorPositionOfDestinationPage(int theDestinationPageIndex, int thePositionNum)
	{
		DestinationPage[theDestinationPageIndex] = new int[thePositionNum + 2];
	}
	
	public int getRelativePabeNum()
	{
		return RelativePageNum;
	}
	
	public int getDestinationPageIndexOn(int theIndex)
	{
		return DestinationPage[theIndex][0];
	}
	
	public int getPositionNoIndexOn(int theFirstIndex, int theSecondIndex)
	{
		return DestinationPage[theFirstIndex][theSecondIndex];
	}
	
	public int getPositionNumIndexOn(int theIndex)
	{
		return DestinationPage[theIndex][1];
	}
	
	public void riseRelativePabeNum()
	{
		RelativePageNum++;
	}
	
	public void increaseDestinationPage(int thePageNo, int thePositionNo)
	{
		DestinationPage[RelativePageNum][0] = thePageNo;
		DestinationPage[RelativePageNum][1] = 1;
		DestinationPage[RelativePageNum][2] = thePositionNo;
		RelativePageNum++;
	}
	
	public void updateDestinationPage(int thePageIndex, int thePositionNo)
	{
		DestinationPage[thePageIndex][1]++;
		DestinationPage[thePageIndex][DestinationPage[thePageIndex][1] + 1] = thePositionNo;
	}
	
	public void setRelativePageNum(int theRelativePageNum)
	{
		RelativePageNum = theRelativePageNum;
	}
	
	public void setDestinationPageIndexOn(int theDestinationPage, int theIndex)
	{
		DestinationPage[theIndex][0] = theDestinationPage;
	}
	
	public void setPositionNumForDestinationIndexOn(int theDestinationPageIndex, int theRelativePageNum)
	{
		DestinationPage[theDestinationPageIndex][1] = theRelativePageNum;
	}
	
	public void setPositionNoForDestinationPageIndexOn(int theDestinationIndex, int theIndex, int thePositionNo)
	{
		DestinationPage[theDestinationIndex][theIndex] = thePositionNo;
	}
}
