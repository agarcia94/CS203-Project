package hw2;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class App {

    public static void main(String[] args) {
//        final String roadFilename = args[0];
//        final String imageFilename = args[1];
//        final String reportFilename = args[3];
//        final int numCellTower = Integer.parseInt(args[4]);
//        final double sigma = Double.parseDouble(args[5]);

        final boolean isOSM = true;

        File file = new File("text.txt");
        
        HashMap<Long, Point> nodes = new HashMap<Long, Point>();
        
        OSM reader = new OSM();
       // reader.main();
        reader.OSM2Text(new File("map.xml"), new File("text.txt"));
        reader.extractBoundaries(new File("map.xml"));
        reader.extractNodes(new File("map.xml"));
        reader.extractWays(new File("map.xml"), reader.extractNodes(new File("map.xml")));
        
        for(Road value : reader.roads.values()){
        	System.out.println(value.segments);
        	value.draw();
        }
        
        
       

//        try {
//			RoadNetwork rn = new RoadNetwork(file, false);
//			//System.out.println(rn.allPoints);
//			//System.out.println(rn.roads);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        	
        	
				
			
		
        

        /*
         * Get a list of all of the cellular network configurations
         */

//        ArrayList<CellNetwork> cellNetworkConfigurations =
//                roadNetwork.getAllCellConfigurations(numCellTower, sigma);
//        CellNetwork bestCellConfiguration = null;
//        
//        double bestScore = 0.0;
//        for (CellNetwork cn : cellNetworkConfigurations) {
//            // do test on cn to see if cn's score is better than bestScore
//            // if it is update bestCellConfiguration = cn
//            bestCellConfiguration = cn;
//        }
//
//        bestCellConfiguration.draw();
//
//        /*
//         * Everything is done
//         * 
//         */
//
//        StdDraw.save(imageFilename);
//        Report report = 
//                new Report(new java.io.File(reportFilename));
//        report.write();
    }
}
