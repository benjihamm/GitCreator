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
		total = new ArrayList<String>();
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
	
	public void writeToFile() throws Exception {
		File f = new File("Objects/"+ sha1(listToString()));
		FileWriter fw = new FileWriter(f); 
		fw.append("tree : " + parent + "\n");
		for (int i = 0; i < list.size();i++) {
				if (list.get(i).substring(0,2).equals("*d")) {
					traverse(parent, list.get(i).substring(10));
					//System.out.println(parent);
				}
				else if(list.get(i).substring(0,2).equals(("*e"))){
					traverse(parent, list.get(i).substring(9));
					Blob blobby = new Blob (list.get(i).substring(9));
				}
				else {
					fw.append("blob : " +getSHA(list.get(i))+ " " + getFilename(list.get(i))+ "\n");
				}
		}
		for(int i = 0; i < total.size(); i++) {
			fw.append(total.get(i) + "\n");
			//System.out.println(total.get(i));
		}
		fw.close();
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
	
	public boolean treeExists(String parent) throws IOException {
		BufferedReader br2 = new BufferedReader(new FileReader("Objects/"+parent));
		if(br2.readLine().substring(7) != "null") {
			return true;
		}
		return false;
	}
	
	public void traverse(String parent, String filename) throws IOException {
		String s = "";
		Scanner sc = new Scanner(new File ("Objects/"+parent));
		if(treeExists(parent)) {
		    s = sc.next(); 
		    //s = sc.next();
			while(sc.hasNext()) {
				String current = sc.next();
				if (current.substring(current.length()).equals(filename)) {
					total.add(s); //adding the line
					//break;
				}
				else {
					total.add(current);
				}
			}
		}
		else {
			s = sc.next(); 
			while(sc.hasNext()) {
				String current = sc.next();
				if (!current.contains(filename)) {
					total.add(current);
				}
		}
		traverse(s.substring(8), filename);
	}
	}
	


	
}
