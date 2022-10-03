import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

//import git.Tree;

public class Tree {
	private static ArrayList<String> list;
	private String sha;
	//private String s;
	
	public Tree (ArrayList<String> listy) throws Exception
	{
		list = listy;
        writeToFile();
        
    }

	public static String sha1(String s) throws IOException {
		String sha1 = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(s.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}
		return sha1;
	}

	public String listToString() {
		String str = "";
		for (String s : list) {//create string with all lines in array
			str+=s + "\n";
		}
		return str;
	}
	
	public void writeToFile() throws IOException {
		File f = new File("Objects/"+ sha1(listToString()));
		FileWriter writer = new FileWriter(f); 
//		for(String str: list) {
//		  writer.write(str + System.lineSeparator());
//		}
//		writer.close();

		for (String str : list) {
			if (!str.equals("")) {
			//issue is that @index 1 of inside index is a space, should fix later
			String sha = str.substring(str.length()-40);
			String fname = str.substring(0,str.length()-42);
			writer.append("blob : " +sha+ " " +fname+"\n");
			}
		}
		writer.append("tree: " + sha);
		writer.close();
		
	}
	
	public String getTreefilename() throws IOException {
		return sha1(listToString());
	}
	
	

	
//	public static void main (String[]args) throws Exception {
//		ArrayList <String> listy = new ArrayList<String>();
//		listy.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
//		listy.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
//		listy.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
//		listy.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
//		listy.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
//		File file = new File("GitCreator/Objects/dd4840f48a74c1f97437b515101c66834b59b1be");
//		Tree tree = new Tree(listy);
//		tree.writeToFile();
//		//System.out.println(sha1(value));
//	}
	

	
	
	
	
	
}
