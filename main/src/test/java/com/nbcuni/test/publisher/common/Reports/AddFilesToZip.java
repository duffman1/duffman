package com.nbcuni.test.publisher.common.Reports;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class AddFilesToZip {

	public void addFilesToZip(File zipFile, List<File> files) throws IOException {
              
		//create a temp file
		File tempFile = File.createTempFile(zipFile.getName(), null);
              
		//delete the temp file (necessary)
		tempFile.delete();

		boolean renameOk = zipFile.renameTo(tempFile);
		if (!renameOk) {
			throw new RuntimeException("Failed to rename the file " + zipFile.getAbsolutePath() + " to " + tempFile.getAbsolutePath());
		}
		byte[] buf = new byte[1024];
        
		ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
        
		ZipEntry entry = zin.getNextEntry();
		while (entry != null) {
			String name = entry.getName();
			boolean notInFiles = true;
			for (File f : files) {
				if (f.getName().equals(name)) {
					notInFiles = false;
					break;
				}
			}
			if (notInFiles) {
				
				//add ZIP entry to output stream
				out.putNextEntry(new ZipEntry(name));
				
				//transfer bytes from the ZIP file to the output file
				int len;
				while ((len = zin.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
			}
			entry = zin.getNextEntry();
		}
		
		//close the streams       
		zin.close();
		
		//compress the files
		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).exists()) {
				InputStream in = new FileInputStream(files.get(i));
			
				//add ZIP entry to output stream
				out.setLevel(5);
				out.putNextEntry(new ZipEntry(files.get(i).getName()));
			
				//transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
			
				//complete the entry
				out.closeEntry();
				in.close();
			}
			else {
				System.out.println("Could not find file to zip titled '" + files.get(i).getPath());
			}
		}
		
		//complete the ZIP file
		out.close();
		tempFile.delete();
       
   }
}