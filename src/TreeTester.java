
import java.io.File;
import java.util.ArrayList;

public class TreeTester {
	
	public static void main(String[] args) throws Exception {
		Index i = new Index();
		i.init();
		Blob b = new Blob ("Main.txt");
		i.addBlobs("Main.txt");
		Commit c = new Commit("thing", "Jake", null);
		c.createFile();
		//System.out.println(c.getComSHA());
		
		Blob b2 = new Blob("Main2.txt");
		i.addBlobs("Main2.txt");
		Commit c2 = new Commit("hello", "Jake", c.getComSHA());
		c2.createFile();
		
		Blob b3 = new Blob("junit.txt");
		i.addBlobs("junit.txt");
		Commit c3 = new Commit("hello", "Jake", c2.getComSHA());
		c3.createFile();
		
		Blob b4 = new Blob("Main3.txt");
		i.addBlobs("Main3.txt");
		Commit c4 = new Commit("hello", "Jake", c3.getComSHA());
		c4.createFile();

//		
//		ArrayList <String> listy = new ArrayList<String>();
//		listy.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
//		listy.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
//		listy.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
//		listy.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
//		listy.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
//		File file = new File("GitCreator/Objects/dd4840f48a74c1f97437b515101c66834b59b1be");
//		Tree tree = new Tree(listy);
		//System.out.println(sha1(value));

	}

}

