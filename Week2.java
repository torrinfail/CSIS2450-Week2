import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Week2
{
	static int[][] temperatures;
	static final String CSV_PATH = "SLCDecember2020Temperatures.csv";
	static final String LINE = "--------------------------------------------------------------\n";

	public static void main(String[] args)
	{
		temperatures = new int[31][3];
		FileReader csvFile = null;
		StringBuilder sb = new StringBuilder();
		try
		{
			csvFile = new FileReader(CSV_PATH);
			int c = 0, i = 0, j = 0;
			while ((c = csvFile.read()) != -1)
			{
				if (c == ',')
				{
					temperatures[i][j++] = Integer.parseInt(sb.toString());
					sb.setLength(0);
					continue;
				}
				if (c == '\n')
				{
					temperatures[i][j] = Integer.parseInt(sb.toString());
					sb.setLength(0);
					i++;
					j = 0;
					continue;
				}
				sb.append((char)c);
			}
		}
		catch (IOException e){}
		sb.setLength(0);

		printAndStore(LINE, sb);
		printAndStore("December 2020: Temperatures in Utah\n", sb);
		printAndStore(LINE, sb);
		printAndStore("Day\tHigh\tLow\tVariance\n", sb);
		printAndStore(LINE, sb);


		for (int i = 0; i < temperatures.length; i++)
		{
			for (int j = 0; j < temperatures[i].length; j++)
			{
				printAndStore(Integer.toString(temperatures[i][j]), sb);
				if (j < temperatures[i].length-1)
					printAndStore("\t", sb);
				else
				{
					printAndStore("\t", sb);
					printAndStore(Integer.toString(temperatures[i][j-1] - temperatures[i][j]), sb);
				}
			}
			printAndStore("\n", sb);
		}
	}

	static void printAndStore(String str, StringBuilder sb)
	{
		sb.append(str);
		System.out.print(str);
	}
}
