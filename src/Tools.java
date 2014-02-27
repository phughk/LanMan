import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Tools
{
	
	public static String md5ToString(byte[] digest)
	{
		String ret="";
		for (byte b: digest)
		{
			int i = (b<<4);
			ret+=Integer.toString(i, 16);
		}
		return ret;
	}
	
	public static byte[] getMD5(File f) throws IOException
	{
		MessageDigest md = null;
		try
		{
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (InputStream is = Files.newInputStream(Paths.get(f.getAbsolutePath()))) {
		  DigestInputStream dis = new DigestInputStream(is, md);
		  /* Read stream to EOF as normal... */
		}
		return md.digest();
	}
	
	/**
	 * Get (only) files in given directory. This is achieved without recursion, so no worry about nesting.
	 * @param dir Directory to list
	 * @return list of all child files in directory and subdirectories (excluding directories and links)
	 */
	public static Set<File> getContents(File dir)
	{
		Set<File> list = new HashSet<>();
		Set<File> dirs = new HashSet<>();
		
		if (dir.isDirectory())
		{
			dirs.add(dir);
			while (!dirs.isEmpty())
			{
				// Select directory, add contents, iterate
				// Select
				dir = dirs.iterator().next();
				dirs.remove(dir);
				// add
				File[] contents = dir.listFiles();
				//System.err.println("Dir: "+dir);
				if (contents!=null) // Null if permission denied sometimes
				{
					for (File f: contents)
					{
						if (f.isFile()) list.add(f);
						else if (f.isDirectory()) dirs.add(f);
						else
						{
							// throw new RuntimeException(String.format("File %s is neither file nor folder (probably a link)", f));
						}
					}
				}
				// iterate
			}
		}
		else
		{
			throw new IllegalArgumentException("Expected directory as parameter");
		}
		
		return list;
	}
}
