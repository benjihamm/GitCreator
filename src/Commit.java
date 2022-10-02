import java.io.File;
import java.io.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;

public class Commit {
	
	public String parent;
	public String pointer;
	private File pTree;
	private String summary;
	private String date;
	private String author;
	private String pValue;
	private String comsha1;
	private ArrayList <String>list;
	public Commit (String changeLog,String authorName, String parentPointer) throws Exception
	{
		pointer=null;
		author=authorName;
		summary=changeLog;
		date= getDate().toString();
		BufferedReader br = new BufferedReader(new FileReader("commit.txt"));
		String parent = br.readLine();
		list.add(parent);
		BufferedReader b = new BufferedReader(new FileReader("index.txt"));
		String index = "";
		while ((index = br.readLine()) != null) {
			list.add(index);
		}
		Tree t = new Tree(list);
	}
	
	public String getSHA1() {
		String value = "";
		value += summary;
		value += date;
		value += author;
		value += parent;
		String sha1 = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(value.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e){
			e.printStackTrace();
		}
		return sha1;
	}
	
	 public String getDate()
	 {
		 Calendar c = Calendar.getInstance();
		 int mYear = c.get(Calendar.YEAR);
	   	 int mMonth = c.get(Calendar.MONTH);
	   	 int mDay = c.get(Calendar.DAY_OF_MONTH);
		 date = mMonth +"/"+ mDay +"/"+ mYear;
		 return date;
	 }
	 public void createFile () throws IOException
	 {
		 File file=new File ("Objects/"+getSHA1());
		 comsha1 = getSHA1();
		 FileWriter writer=new FileWriter(file);
		 writer.append (pValue +"\n"+parent+"\n"+pointer+"\n"+author+"\n"+date+"\n"+summary);
		 writer.close();
		 
	 }
	 

//	 public static void main (String[]args) throws Exception {
//		Commit test1 = new Commit("objects/e30674de6cab9269db9c374e0fe0618a2b560e06", "good", "Jake", null);
//		test1.createFile();
//		test1.getDate();
//	 }
	 
	 
	
}
