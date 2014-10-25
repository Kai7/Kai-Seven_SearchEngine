import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
//import javax.swing.JTextPane;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchEngineWindow extends JFrame implements ActionListener
{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 620;

	public static final int NUMBER_OF_CHAR = 25;

	private JPanel Panel_Search;
	private JScrollPane ScrolPane_Textarea;
	private JLabel Label_Logo;
	private JLabel Label_Message;
	private JTextField TextField_Context;
	private JButton Button_Search;
	private JButton Button_Quit;
	private JRadioButton RadioButton_Linear;
	private JRadioButton RadioButton_Binary;
	private ButtonGroup ButtonGroup_SearchMethod;
	private JTextArea TextArea_Result;

	public SearchEngineWindow()
	{
		super("kai-Seven_SearchEngine");
		setSize(WIDTH, HEIGHT);
		setResizable(false);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		Panel_Search = new JPanel();
		Panel_Search.setBounds(0, 0, 500, 125);
		Panel_Search.setLayout(null);

		Label_Logo = new JLabel("Welcome to Kai-Seven Search Engine");
		Label_Logo.setBounds(10, 5, 500, 25);
		Label_Message = new JLabel("");
		Label_Message.setBounds(10, 95, 500, 25);
		TextField_Context = new JTextField(NUMBER_OF_CHAR);
		TextField_Context.setBounds(10, 35, 280, 25);
		Button_Search = new JButton("Search");
		Button_Search.setBounds(300, 35, 90, 25);
		Button_Search.addActionListener(this);
		Button_Quit = new JButton("Quit");
		Button_Quit.setBounds(400, 35, 85, 25);
		Button_Quit.addActionListener(this);

		ButtonGroup_SearchMethod = new ButtonGroup();
		RadioButton_Linear = new JRadioButton("Sequential Search", true);
		RadioButton_Linear.setBounds(10, 65, 200, 25);
		RadioButton_Binary = new JRadioButton("Use Inverted Index");
		RadioButton_Binary.setBounds(220, 65, 200, 25);
		ButtonGroup_SearchMethod.add(RadioButton_Linear);
		ButtonGroup_SearchMethod.add(RadioButton_Binary);

		TextArea_Result = new JTextArea(10, 10);
		TextArea_Result.setEditable(false);
		ScrolPane_Textarea = new JScrollPane(TextArea_Result);
		ScrolPane_Textarea.setBounds(10, 130, 475, 450);

		Panel_Search.add(Label_Logo);
		Panel_Search.add(TextField_Context);
		Panel_Search.add(Button_Search);
		Panel_Search.add(Button_Quit);
		Panel_Search.add(RadioButton_Linear);
		Panel_Search.add(RadioButton_Binary);
		Panel_Search.add(Label_Message);

		setLayout(null);
		add(Panel_Search);
		add(ScrolPane_Textarea);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("Quit"))
			System.exit(0);
		else if (e.getActionCommand().equals("Search"))
		{
			Button_Search.setEnabled(false);
			TextArea_Result.setText("");
			String theKeyword = TextField_Context.getText();
			if (theKeyword.equals(""))
				Label_Message.setText("Please input the keyword!");
			else
				searchTheKeyword(theKeyword);
			TextField_Context.requestFocus();
			TextField_Context.selectAll();
			Button_Search.setEnabled(true);
		} 
		else
			Label_Logo.setText("Error");
	}

	public void searchTheKeyword(String theKeyword)
	{
		long SearchStart = System.currentTimeMillis();
		
		String[] theKeywordSet = theKeyword.split(" ");
		
		if(theKeywordSet.length > 2)
			Label_Message.setText("We not support ...");
		
		KaiSevenStringLinkListNode ResultStartNode;
		if (ButtonGroup_SearchMethod.getSelection() == RadioButton_Linear.getModel())
			ResultStartNode = Main.linearSearch(theKeywordSet);
		else
			ResultStartNode = Main.binarySearch(theKeywordSet);
		
		if(ResultStartNode.getNextStringNode() == null)
			TextArea_Result.setText("The keyword is not fount!");
		else
			printTheResult(ResultStartNode);
		
		long SearchEnd = System.currentTimeMillis();
		double TotalTime = (SearchEnd - SearchStart) / 1000.0;
		Label_Message.setText("It takes " + TotalTime + " sec to search");
	}
	
	public void printTheResult(KaiSevenStringLinkListNode theStartNode)
	{
		String[] TempPrintData;
		int DataColumnNum;
		while(!(theStartNode.getNextStringNode() == null))
		{
			TempPrintData = theStartNode.getNextStringNode().getDataField();
			DataColumnNum = TempPrintData.length;
			for(int i=0; i < DataColumnNum - 1; i++)
				TextArea_Result.append(TempPrintData[i] + "\n");
			TextArea_Result.append("\n");
			theStartNode = theStartNode.getNextStringNode();
		}
	}
}