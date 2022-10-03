import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;


public class Index {
	private HashMap<String, String> blobs;
	private File index;
	private FileWriter output;
	public Index() throws IOException
	{
		Path path= Paths.get("objects/");
		Files.createDirectories(path);
		index=new File("objects/index.txt");
		blobs=new HashMap<String,String>();

	}
	public void init() throws FileNotFoundException, IOException{
		File theDir = new File("Test/objects");
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
		Path p = Paths.get("Test");
		File index = new File(p + "/index.txt");
		index.createNewFile();
    }
	
	public void addBlobs(String fileName) throws Exception
	{
		Blob x = new Blob (fileName);
		addToFile(fileName, x.getSHA());
		
	}
	public void addToFile (String fileName, String shaString) throws IOException
	{
		output = new FileWriter(index, true);
        output.append(fileName+": "+shaString+"\n");
        blobs.put (fileName,shaString);
        output.close();
        
	}
	public boolean removeBlob(String fileName) throws IOException
	{
		if (blobs.containsKey(fileName))
		{
			File inputFile=new File("objects/index.txt");
			
			File tempFile= new File ("temp.txt");
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			String lineToRemove =fileName+": "+blobs.get(fileName);
			System.out.println (lineToRemove);
			String currentLine;
			
			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
				System.out.print(("1"));
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals(lineToRemove)) continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
			boolean successful = tempFile.renameTo(inputFile);
			File toDelete=new File (blobs.get(fileName)+".txt");
			toDelete.delete();
			blobs.remove(fileName);
			
			return true;
		}
		return false;
	}
	public static void main (String [] args) throws Exception {
		Index test = new Index();
		System.out.println(System.getProperty("user.dir"));
		test.init();
		test.addBlobs("junit.txt");
		test.addBlobs("Main2.txt");
		test.addBlobs("Main.txt");
		test.removeBlob("tst");
		test.removeBlob("tstst");
	}
	
	
}
