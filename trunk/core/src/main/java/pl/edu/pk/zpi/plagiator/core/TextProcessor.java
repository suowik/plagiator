package pl.edu.pk.zpi.plagiator.core.extractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class TextProcessor {

  public void extract(File file) throws IOException {
		
		File temp = File.createTempFile("file", ".txt", file.getParentFile());
		String charset = "UTF-8";
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));

		PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(temp), charset));
		try {
		for (String line; (line = reader.readLine()) != null;) {
			
			int counter = 0;
			for( int i=0; i<line.length(); i++ ) {
			    if( line.charAt(i) == '\"' ) {
			        counter++;
			    } 
			}
			
			if( counter != 0 && (counter % 2) != 0){
				//obsluga cytatow na >1 linii
			}
			
			line = line.replaceAll("\".*?\"", "");
			 line = line.replaceAll("[^A-Za-z0-9 ]", "");
			 line = line.replaceAll("\\b[\\w']{1,2}\\b", "");
			 line = line.replaceAll("\\s{2,}", " ");
			 
			 writer.println(line);
		}
		
		} finally {
		
		reader.close();
		writer.close();
		}
	}
	
}
