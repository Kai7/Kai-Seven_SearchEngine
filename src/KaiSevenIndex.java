
public class KaiSevenIndex
{
	private String KeyWord;
	private KaiSevenPosting KeyWordPosting;
	
	public KaiSevenIndex()
	{
		KeyWord = null;
		KeyWordPosting = new KaiSevenPosting();
	}
	
	public KaiSevenIndex(String theKeyWord)
	{
		KeyWord = theKeyWord;
		KeyWordPosting = new KaiSevenPosting();
	}
	
	public KaiSevenIndex(String theKeyWord, int theRelativePageNum)
	{
		KeyWord = theKeyWord;
		KeyWordPosting = new KaiSevenPosting(theRelativePageNum);
	}
	
	public KaiSevenIndex(String theKeyWord, int theRelativePageNum, int theKeywordPositionNum)
	{
		KeyWord = theKeyWord;
		KeyWordPosting = new KaiSevenPosting(theRelativePageNum, theKeywordPositionNum);
	}
	
	public void structorPositionOfDestinationPage(int theDestinationPageIndex, int thePositionNum)
	{
		KeyWordPosting.structorPositionOfDestinationPage(theDestinationPageIndex, thePositionNum);
	}
	
	public String getKeyWord()
	{
		return KeyWord;
	}
	
	public int getRelativePageNum()
	{
		return KeyWordPosting.getRelativePabeNum();
	}
	
	public int getDestinationPageIndexOn(int theIndex)
	{
		return KeyWordPosting.getDestinationPageIndexOn(theIndex);
	}
	
	public int getPositionNumIndexOn(int theIndex)
	{
		return KeyWordPosting.getPositionNumIndexOn(theIndex);
	}
	
	public int getPositionNoIndexOn(int theFirstIndex, int theSecondIndex)
	{
		return KeyWordPosting.getPositionNoIndexOn(theFirstIndex, theSecondIndex);
	}
	
	public void updateKaiSevenPosting(int thePageNo, int thePositionNo)
	{
		try
		{
			int thePageIndex = KeyWordPosting.getRelativePabeNum() - 1;
//			System.out.println("thePageNo: " + thePageNo + "  thePositionNo: " + thePositionNo);
			if(thePageIndex < 0)
				KeyWordPosting.increaseDestinationPage(thePageNo, thePositionNo);
			else
			{
				if(KeyWordPosting.getDestinationPageIndexOn(thePageIndex) == thePageNo)
				{
//					System.out.println("update");
					KeyWordPosting.updateDestinationPage(thePageIndex, thePositionNo);
				}
				else
				{
//					System.out.println("increase");
					KeyWordPosting.increaseDestinationPage(thePageNo, thePositionNo);
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println(e);
			System.out.println(KeyWord);
			System.out.println(KeyWordPosting.getRelativePabeNum());
			System.exit(0);
		}
	}
	
	public void setKeyWord(String theKeyWord)
	{
		KeyWord = theKeyWord;
	}
	
	public void setRelativePageNum(int theRelativePageNum)
	{
		KeyWordPosting.setRelativePageNum(theRelativePageNum);
	}
	
	public void riseRelativePageNum()
	{
		KeyWordPosting.riseRelativePabeNum();
	}
	
	public void setDestinationPageIndexOn(int theDestinationPage, int theIndex)
	{
		KeyWordPosting.setDestinationPageIndexOn(theDestinationPage, theIndex);
	}
	
	public void setPositionNumForDestinationIndexOn(int theDestinationPageIndex, int theRelativePageNum)
	{
		KeyWordPosting.setPositionNumForDestinationIndexOn(theDestinationPageIndex, theRelativePageNum);
	}
	
	public void setPositionNoForDestinationPageIndexOn(int theDestinationIndex, int theIndex, int thePositionNo)
	{
		KeyWordPosting.setPositionNoForDestinationPageIndexOn(theDestinationIndex, theIndex, thePositionNo);
	}
}