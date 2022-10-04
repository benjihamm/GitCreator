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
	public String sha;
	public String parent;
	private ArrayList<String> total;
	//private String s;
	
	public Tree (ArrayList<String> listy, String parenty) throws Exception
	{
		parent = parenty;
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
		for (String s : list) {
			str+=s + "\n";
		}
		return str;
	}
	
	public void writeToFile() throws IOException {
		File f = new File("Objects/"+ sha1(listToString()));
		FileWriter writer = new FileWriter(f); 
		for (String str : list) {
			if (str.substring(0,9).equals("*deleted*")) {
				traverse(parent, str.substring(10));
			}
			if (!str.equals("")) {
			writer.append("blob : " +getSHA(str)+ " " + getFilename(str)+"\n");
			}
		}
		writer.append("tree : " + parent);
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
	
	public void traverse(String parent, String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Objects/"+parent));
		String s = br.readLine(); 
		while(br.ready()) {
			String current = br.readLine();
			if (current.contains(filename)) {
				total.add(s);
				break;
			}
			else {
				total.add(current);
			}
		}
		traverse(s.substring(7), filename);
	}
	

	
}
