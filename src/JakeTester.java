//import static org.junit.jupiter.api.Assertions.*;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import git.Index;
//
////import Git.Commit;
//
//
//class JakeTester {
//
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//		
//		Path p = Paths.get("junit.txt");
//        try {
//            Files.writeString(p, "example", StandardCharsets.ISO_8859_1);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//        	System.out.println(e.toString());
////            e.printStackTrace();
//        }
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//		File myObj = new File("jnuit.txt"); 
//	    if (myObj.delete()) { 
//	      System.out.println("Deleted the file.");
//	    } else {
//	      System.out.println("Failed to delete the file.");
//	    } 
//	}
//
//	@Test
//	void testBlob() throws Exception
//	{
//		Blob b = new Blob("junit.txt"); 
//		File file2 = new File("Objects/c3499c2729730a7f807efb8676a92dcb6f8a3f8f");
//		assertTrue(file2.exists());
//	}
//	
//	@Test 
//	void testInit() throws Exception 
//	{
//		Index i = new Index();
//		File file = new File("index");
//		assertTrue(file.exists());
//		Path path = Paths.get("Objects");
//		assertTrue(Files.exists(path)); 
//	}
//	
//	@Test
//	void testAdd() throws Exception 
//	{
//		Index i2 = new Index();
//		Blob b = new Blob("junit.txt"); 
//		i2.addBlobs("junit.txt");
//		File file3 = new File("objects/c3499c2729730a7f807efb8676a92dcb6f8a3f8f");
//		assertTrue(file3.exists());
//	}
//	
//	@Test
//	void testRemove() throws Exception
//	{
//		Index i3 = new Index();
//		Blob b = new Blob("junit.txt"); 
//		i3.addBlobs("junit.txt");
//		File file4 = new File("objects/c3499c2729730a7f807efb8676a92dcb6f8a3f8f");
//		i3.removeBlob("junit.txt");
//	}
//		/*
//		 * 1. index file is correct
//		 * 2. does objects/[hash] file exist
//		 * 3. are contents of objects/[hash] correct
//		 */
//	
//	@Test
//	void test() throws Exception {
//		ArrayList <String> listy = new ArrayList<String>();
//		listy.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
//		listy.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
//		listy.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
//		listy.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
//		listy.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
//		File file = new File("objects/dd4840f48a74c1f97437b515101c66834b59b1be");
//		Tree tree = new Tree(listy);
////		//tree.createIndexFile();
////		//assertTrue(file.exists());
////		Scanner s = new Scanner(file);
////		assertTrue(s.nextLine().equals("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f"));
////		assertTrue(s.nextLine().equals("blob : 01d82591292494afd1602d175e165f94992f6f5f"));
////		assertTrue(s.nextLine().equals("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83"));
////		assertTrue(s.nextLine().equals("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b"));
////		assertTrue(s.nextLine().equals("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976"));
//		
//	}
//	
////	@Test
////	void commitTest() throws IOException {
////		Commit test1 = new Commit("objects/d30ba5f7fcb23557ff87fc4e0f32bf5370f154cd", "good", "Jake", null);
////		test1.createFile();
////		test1.getDate();
////	}
//	
//	
//
//}
