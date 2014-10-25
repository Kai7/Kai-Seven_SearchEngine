import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class PreBuildNewsMappingTable
{
	public static void main(String[] args)
	{
		long StarTime = System.currentTimeMillis();
		
		Scanner NewsInput = null;
		try
		{
			NewsInput = new Scanner(new FileInputStream("News.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.print("input data error!");
			System.exit(0);
		}
		
		PrintWriter NewsOutput = null;
		try
		{
			NewsOutput = new PrintWriter(new FileOutputStream("NewsMappingTable.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.print("output data error!");
			System.exit(0);
		}
		
		int NewsNumber = 1;
		while(NewsInput.hasNextLine())
		{
			NewsOutput.println(NewsInput.nextLine());
			NewsInput.nextLine();
			NewsOutput.println(NewsInput.nextLine());
			NewsOutput.println(NewsNumber);
			NewsOutput.println();
			NewsInput.nextLine();
			NewsInput.nextLine();
			NewsInput.nextLine();
			NewsInput.nextLine();
			NewsInput.nextLine();
			NewsNumber++;
		}
		
		NewsInput.close();
		NewsOutput.close();
		
		long EndTime = System.currentTimeMillis();
		double TotalTime = (EndTime - StarTime) / 1000.0;
		System.out.println("It takes " + TotalTime + " sec to build Mapping-Table");
	}

}
