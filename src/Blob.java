import java.io.File;
// code taken from http://www.java2s.com/example/java/security/get-sha1-hash-for-file.html
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Blob {
	String sha;
	
    public Blob (String fileName) throws Exception {
        File read = new File(fileName);
        sha=getSHA1(read);
        
        
        File write = new File("Objects/"+sha+".txt");
        write.createNewFile();
        InputStream input = new FileInputStream(read);
        OutputStream output = new FileOutputStream(write);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
               output.write(buffer, 0, length);
               
        }
        output.close();
        input.close();

        
     
    }//from  www.jav a  2s  . c o  m
   /* public File getFile(String fileName)
    {
    	return fileName.;     
    }
    */
    public static String getSHA1(File file) throws IOException {
        

        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            FileInputStream fis = new FileInputStream(file);
            byte[] dataBytes = new byte[1024];

            int nread = 0;

            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
            ;

            byte[] mdbytes = md.digest();

            //convert the byte to hex format
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            return sb.toString();
           

        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
    public  String getSHA()
    {
    	return sha;
    }
   
}

