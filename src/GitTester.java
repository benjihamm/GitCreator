import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GitTester {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCommit() throws Exception {
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
		Commit c3 = new Commit("Lets go", "Jake", c2.getComSHA());
		c3.createFile();
		
		Blob b4 = new Blob("Main3.txt");
		i.addBlobs("Main3.txt");
		Commit c4 = new Commit("Comp sci", "Jake", c3.getComSHA());
		c4.createFile();
		
		Blob b5 = new Blob("Main4.txt");
		i.addBlobs("Main4.txt");
		Commit c5 = new Commit("commit 5", "Jake", c4.getComSHA());
		c5.createFile();

		
	}

}