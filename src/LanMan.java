import java.io.File;
import java.util.Scanner;


public class LanMan
{
	
	
	public static void terminalMode(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine())
		{
			System.out.format("Enter path to list: ");
			String line = sc.nextLine();
			File[] files = Tools.getContents(new File(line)).toArray(new File[] {});
			for (int i=0;i<files.length; i++)
			{
				String md5=Tools.md5ToString(Tools.getMD5(files[i]));
//				String md5 = files[i].getAbsolutePath();
				System.out.format("%d/%d: %s %s\n", i, files.length, md5, files[i]);
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		terminalMode(args);
	}

}
