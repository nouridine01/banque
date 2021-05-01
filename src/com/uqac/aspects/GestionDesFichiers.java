/**
 * 
 */
package com.uqac.aspects;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author primp
 *
 */
public class GestionDesFichiers {
	public static File file = new File("journalDesCoups.txt");
	
	public static void deleteFile(File file) {
		if(file.exists()) {
		   file.delete();
	   }
		
	}
	
	public static void saveData(File file, String data) {
		try {
			  
			   if (!file.exists()) {
				   file.createNewFile();
			   }
			   //String coup = "Deplacement de "+mv.xI+""+mv.yI +"à"+mv.xF+""+mv.yF; 
			   
			   FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			   BufferedWriter bw = new BufferedWriter(fw);
			   bw.write(data+"\n");
			   bw.close();
			  } catch (IOException e) {
			   e.printStackTrace();
			  }
		
		
	}
}
