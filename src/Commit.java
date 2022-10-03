import java.io.*;
import java.util.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class Commit {
	
	public String parent;
	public String pointer;
	private Tree pTree;
	private String cTree;
	private String summary;
	private String date;
	private String author;
	private String pValue;
	private String comsha1;
	private ArrayList <String>list;
	
	public Commit (String changeLog,String authorName, String parentPointer) throws Exception
	{
		parent = parentPointer;
		author=authorName;
		summary=changeLog;
		date= getDate();
		comsha1 = getSHA1(summary + date + author);
		Tree t = new Tree(list(), findPrevious());
		//t = pTree;
		pointer = t.getTreefilename();
		createFile();
		PrintWriter p = new PrintWriter ("Objects/index.txt");
		p.print("");
		p.close();
	}
	
//	public String getTreeName() throws IOException {
//		return pTree.getTreefilename();
//	}
	
	
	public String getSHA1(String s) {
		String value = s;
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
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			date = dtf.format(now);  
			return date;
	 }
	 
	 public String findPrevious() throws IOException {
		 if (parent != null) {
			 BufferedReader br = new BufferedReader(new FileReader("Objects/"+ parent));
			 return br.readLine();
		 }
		 return "null";
	 }
	 
	 public void createFile () throws IOException
	 {
		 File file=new File ("Objects/"+comsha1);
		 FileWriter writer=new FileWriter(file);
		 //pValue = findPrevious();
		if(parent != null) {
				BufferedReader br = new BufferedReader(new FileReader("Objects/"+parent));
				String s = br.readLine() +"\n"+ br.readLine();
				br.readLine();
				String s2 = br.readLine() + "\n" + br.readLine() + "\n" + br.readLine();
				br.close();
				PrintWriter p = new PrintWriter ("Objects/"+ parent);
				p.write(s +"\n"+ comsha1 + "\n"+ s2);
				p.close();
			}
			if(pointer == null) {
				pointer = "null";
			}
			pValue = "null";
		 writer.append (pointer +"\n"+parent+"\n"+pValue+"\n"+author+"\n"+date+"\n"+summary);
		 writer.close();
	 }
	 
	 public String getComSHA() {
		 return comsha1;
	 }
	 
	 public ArrayList <String> list() throws NoSuchAlgorithmException, IOException{
		 BufferedReader s = new BufferedReader(new FileReader("Objects/index.txt"));
		 ArrayList<String> list = new ArrayList<String>();
		 while (s.ready()){
		     list.add(s.readLine());
		 }
		 s.close();
		 return list;
	 }
	 
	 //pValue is the previous commit file, parent is the previous commit sha, pointer is the current tree
	 
	 public void deleteFile(String filename) throws IOException {
		 File f = new File("Objects/index.txt");
		 BufferedWriter fw = new BufferedWriter(new FileWriter(f));
		 fw.append("*deleted*" + filename);
		 fw.close();
	 }
	 
	 
	
}
