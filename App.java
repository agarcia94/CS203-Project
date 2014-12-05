package project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

			//			for(Road road: roadNetwork.roads){
			//				road.setPointAmount();
			//				System.out.println(road.id + ": " + road.getTotalPointAmount());
			//			}

			Road mostPoints= roadNetwork.hasMostPoints();  //road with the most post points in the road network
			// System.out.println(mostP

			long startTime = System.currentTimeMillis();



			ArrayList<CellNetwork> cellNetworkConfigurations =
					mostPoints.getAllCellConfigurations(numCellTower, sigma);
			
			for(CellNetwork cn : cellNetworkConfigurations){
				System.out.println(cn);
			}
	        System.out.println("amount of networks: " + cellNetworkConfigurations.size());

			
			
			int networkId=0;
			for(CellNetwork network : cellNetworkConfigurations){
				network.setId(networkId++);
			}


			for(CellNetwork networks: cellNetworkConfigurations)
				networks.setAmountOfCoveredPoints(mostPoints);



			CellNetwork bestCellConfiguration = null;

			int bestScore = 0;
			int count = 0;
			
			
			ArrayList<Integer> removeNetworks=new ArrayList<Integer>();
			int initialCount=cellNetworkConfigurations.size();
//			int configurationsRemoved=0;
//			
//			for (int w =0; w < cellNetworkConfigurations.size(); w++) {
//			
//				CellNetwork cn= cellNetworkConfigurations.get(w);
//				for(int i = 0; i < cn.towers.size(); i++ ){		
//					for(int j = 0; j < cn.towers.size(); j++ ){ // individually compares each tower against each other
//						double distance= Math.sqrt(Math.pow(cn.towers.get(i).Xs() - cn.towers.get(j).Xs(), 2) + 
//								Math.pow(cn.towers.get(i).Ys() - cn.towers.get(j).Ys(), 2));
//
//						if(i != j){
//							if(distance < (cn.towers.get(i).radius + cn.towers.get(j).radius)){
//								RoadNetwork.deletedTowers.put(cn.id,cn);
//								removeNetworks.add(w);
//								
//							}
//						}
//
//					}
//				}
//				
//			}
//
//			for(int n=0; n < removeNetworks.size(); n++){
//			configurationsRemoved++;
//				cellNetworkConfigurations.remove(removeNetworks.get(n)); //this for loop causes massive slowed performance
//		}

			//				System.out.println(count++);
			//			System.out.println(deletedTowers.values());
			//				if(cn.pointsCovered() > bestScore){
			//					bestScore = cn.coveredPoints;
			//					bestCellConfiguration = cn;
			//
			//				}




			//			System.out.println(RoadNetwork.deletedTowers);
			//	        System.out.println("Best Score: " +  bestScore);
			//	        System.out.println("Best CellConfiguration: " +  bestCellConfiguration);

			//			bestCellConfiguration.draw();

//			long finishTime = System.currentTimeMillis();
//			try {
//				File file2 = new File(reportFilename);
//				Report re = new Report(file2);
//				re.write(RoadNetwork.deletedTowers.values(), initialCount, configurationsRemoved);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

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
