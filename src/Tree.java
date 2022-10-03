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
		for (String str : list) {
			if (!str.equals("")) {
			writer.append("blob : " +getSHA(str)+ " " + getFilename(str)+"\n");
			}
		}
		writer.append("tree: " + sha1(listToString()));
		writer.close();
	}
	public String getSHA(String str) {
		return str.substring(str.length()-40);
	}
	
	public String getFilename(String str) {
		return str.substring(0,str.length()-42);
	}
	
	public String getTreefilename() throws IOException {
		return sha1(listToString());
	}
	
	

	

	
	
	
	
	
}
