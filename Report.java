package project;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Report {

	//Print most points
	//Print best cell config 
	//Prefromance Time
	File file; 

	
	
    public Report(File file) throws IOException {
        // TODO Auto-generated constructor stub
    	this.file=file;
    	if(!file.exists()){
			file.createNewFile();
		}
    }

    public void add(String message) {
        // TODO Auto-generated method stub

    }

    public void write(Collection<CellNetwork> deletedConfigurations, int initialCount, int amountDeleted) throws IOException {
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		int count= deletedConfigurations.size();

		bw.write("Deleted Configurations: ");
		bw.newLine();
		for(CellNetwork cn : deletedConfigurations){
			bw.write(cn.toString());
			
		}
		bw.write("Total initial configurations: " + initialCount);
		bw.newLine();
		bw.write("Amount of deleted configurations: " + count);
		bw.newLine();
		bw.write("Final amount of configurations: " + (initialCount - count));
		bw.flush();
		bw.close();

		

	}
    }
