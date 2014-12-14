package project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Overlap {
	private ArrayList<CellNetwork> initialNetworks = new ArrayList<CellNetwork>();
	private HashSet<Integer> removalIndexes = new HashSet<Integer>();
	private int configurationsRemoved = 0;

	public Overlap(ArrayList<CellNetwork> networks){
		this.initialNetworks= networks;
	}

	public int getConfigurationsRemoved() {
		return configurationsRemoved;
	}

	public void setConfigurationsRemoved(int configurationsRemoved) {
		this.configurationsRemoved = configurationsRemoved;
	}

	public ArrayList<CellNetwork> getNetworks() {
		return initialNetworks;
	}

	public void setInitialNetworks(ArrayList<CellNetwork> initialNetworks) {
		this.initialNetworks = initialNetworks;
	}

	public HashSet<Integer> getRemovalIndexes() {
		return removalIndexes;
	}

	public void setRemovalIndexes(HashSet<Integer> removalIndexes) {
		this.removalIndexes = removalIndexes;
	}

	public void checkOverlap(){

		for (int w =0; w < initialNetworks.size(); w++) {

			CellNetwork cn= initialNetworks.get(w);
			for(int i = 0; i < cn.towers.size(); i++ ){		
				for(int j = 0; j < cn.towers.size(); j++ ){ // individually compares each tower against each other
					double distance= Math.sqrt(Math.pow(cn.towers.get(i).Xs() - cn.towers.get(j).Xs(), 2) + 
							Math.pow(cn.towers.get(i).Ys() - cn.towers.get(j).Ys(), 2));

					if(i != j){
						if(distance < (cn.towers.get(i).radius + cn.towers.get(j).radius)){
							RoadNetwork.deletedTowers.put(cn.id,cn);
//							removalIndexes.add(w);
							//w++;


						}
					}

				}
			}

		}

	}

	public void remove(){
		Collection<CellNetwork> networks= RoadNetwork.deletedTowers.values();
		for(int i=0; i < initialNetworks.size(); i++){
			for(CellNetwork network : networks){
				if(initialNetworks.get(i).id == network.id){
					removalIndexes.add(i);
					configurationsRemoved++;
				}
			}
		}
		
		Iterator<Integer> iter= removalIndexes.iterator();
		while(iter.hasNext()){
			initialNetworks.remove(iter.next());
		}


	}
}
