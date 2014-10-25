
public class KaiSevenStringLinkListNode
{
	private String[] DataField;
	private KaiSevenStringLinkListNode NextStringNode;
	
	public KaiSevenStringLinkListNode()
	{
		DataField = null;
		NextStringNode = null;
	}
	
	public KaiSevenStringLinkListNode(int theDataSize)
	{
		DataField = new String[theDataSize];
		NextStringNode = null;
	}
	
	public KaiSevenStringLinkListNode(String[] theDataSource)
	{
		DataField = theDataSource;
		NextStringNode = null;
	}
	
	public KaiSevenStringLinkListNode(KaiSevenStringLinkListNode theNextStringNode)
	{
		DataField = null;
		NextStringNode = theNextStringNode;
	}
	
	public KaiSevenStringLinkListNode(String[] theDataSource, KaiSevenStringLinkListNode theNextStringNode)
	{
		DataField = theDataSource;
		NextStringNode = theNextStringNode;
	}	
	
	public KaiSevenStringLinkListNode getNextStringNode()
	{
		return NextStringNode;
	}
	
	public String[] getDataField()
	{
		return DataField;
	}
	
	public void setNextStringNode(KaiSevenStringLinkListNode theNextNode)
	{
		NextStringNode = theNextNode;
	}
	
	public void setDataField(String[] theDataSource)
	{
		DataField = theDataSource;
	}
	
	public void increaseNextStringNode(String[] theDataSource)
	{
		KaiSevenStringLinkListNode theNextNode;
		theNextNode = new KaiSevenStringLinkListNode(theDataSource);
		NextStringNode = theNextNode;
	}
}
