import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Week2
{
	static double[][] temperatures;
	static final String CSV_PATH = "SLCDecember2020Temperatures.csv";
	static final String LINE = "--------------------------------------------------------------\n";
	static int highestDay, lowestDay;
	static double  highestTemp, lowestTemp = Double.MAX_VALUE;

	public static void main(String[] args)
	{
		temperatures = new double[31][3];
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
					temperatures[i][j++] = Double.parseDouble(sb.toString());
					sb.setLength(0);
					continue;
				}
				if (c == '\n')
				{
					temperatures[i][j] = Double.parseDouble(sb.toString());
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
			if (temperatures[i][1] > highestTemp)
			{
				highestTemp = temperatures[i][1];
				highestDay = i;
			}
			if (temperatures[i][2] < lowestTemp)
			{
				lowestTemp = temperatures[i][2];
				lowestDay = i;
			}
			for (int j = 0; j < temperatures[i].length; j++)
			{
				printAndStore(Double.toString(temperatures[i][j]), sb);
				if (j < temperatures[i].length-1)
					printAndStore("\t", sb);
				else
				{
					printAndStore("\t", sb);
					printAndStore(Double.toString(temperatures[i][j-1] - temperatures[i][j]), sb);
				}
			}
			printAndStore("\n", sb);
		}
		printAndStore("December Highest Temperature: 12/" +
				temperatures[highestDay][0] + 
				": " + highestTemp + " Average Hi: " +
				(temperatures[highestDay][1] + temperatures[highestDay][2])/2.0 + "\n", sb);

		printAndStore("December Lowest Temperature: 12/" +
				temperatures[lowestDay][0] + 
				": " + lowestTemp + " Average Lo: " +
				(temperatures[lowestDay][1] + temperatures[lowestDay][2])/2.0 + "\n", sb);
		for (int i = 0; i < temperatures.length; i++)
		{
			printAndStore(Double.toString(temperatures[i][0]) + " Hi " + getRepeatedCharString('+', (int)temperatures[i][1]) + "\n",sb);
			printAndStore("   Lo " + getRepeatedCharString('-', (int)temperatures[i][2]) + "\n",sb);
		}

	}

	static String getRepeatedCharString(char c, int nChars)
	{
		char[] chars = new char[nChars];
		for (int i = 0; i < chars.length; i++)
			chars[i] = c;
		return new String(chars);
	}
	static void printAndStore(String str, StringBuilder sb)
	{
		sb.append(str);
		System.out.print(str);
	}
}
