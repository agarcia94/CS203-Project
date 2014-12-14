package project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class App {

	public static void main(String[] args) {
		final String roadFilename = args[0];
		final String imageFilename = args[1];
		final String reportFilename = args[2];
		final int numCellTower = Integer.parseInt(args[3]);
		final double sigma = Double.parseDouble(args[4]);


		final boolean isOSM = true;


		RoadNetwork roadNetwork;
		try {
			File file = new File(roadFilename);
			roadNetwork = new RoadNetwork(file, isOSM);
			roadNetwork.draw();

			for(Road road: roadNetwork.roads)
				road.setPointAmount();

			Road mostPoints= roadNetwork.hasMostPoints();  //road with the most post points in the road network
			// System.out.println(mostP

//			long startTime = System.currentTimeMillis();



			ArrayList<CellNetwork> cellNetworkConfigurations =
					mostPoints.getAllCellConfigurations(numCellTower, sigma);

			int networkId=0;
			for(CellNetwork network : cellNetworkConfigurations){
				network.setId(networkId++);
			}


			for(CellNetwork networks: cellNetworkConfigurations)
				networks.setAmountOfCoveredPoints(mostPoints);


//			HashSet<Integer> removeNetworks=new HashSet<Integer>();
			int initialCount=cellNetworkConfigurations.size();



			int configurationsRemoved=0;



			Overlap over= new Overlap(cellNetworkConfigurations);
			over.checkOverlap();
			over.remove();
			ArrayList<CellNetwork> remainingNetworks = over.getNetworks();
			int finalCount= remainingNetworks.size();
			for(CellNetwork network : remainingNetworks){
				System.out.println(network);
			}

			System.out.println("Search: " + RoadNetwork.deletedTowers.get(1931));

			CellNetwork bestCellConfiguration = null;
			int bestScore = 0;
			for(CellNetwork cn : remainingNetworks){
				if(cn.pointsCovered() > bestScore){
					bestScore = cn.coveredPoints;
					bestCellConfiguration = cn;

				}
			}
			bestCellConfiguration.draw();
			System.out.println("Best CellConfiguration: " +  bestCellConfiguration);
			System.out.println("Initial Count:" + initialCount);
			System.out.println("Final count: " + remainingNetworks.size());




			//			System.out.println(count++);
			//			System.out.println(deletedTowers.values());





			//			System.out.println(RoadNetwork.deletedTowers);
			//	        System.out.println("Best Score: " +  bestScore);



//			long finishTime = System.currentTimeMillis();
			try {
				File file2 = new File(reportFilename);
				Report re = new Report(file2);
				re.write(RoadNetwork.deletedTowers.values(), initialCount, over.getConfigurationsRemoved());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//		System.out.println("xmax: " + Boundaries.xmax);
		//		System.out.println("xmin: " + Boundaries.xmin);
		//		System.out.println("ymax: " + Boundaries.ymax);
		//		System.out.println("ymin: " + Boundaries.ymin);


		//
		StdDraw.save(imageFilename);
	}


}
