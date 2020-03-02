package com.pike;

import java.util.Arrays;
import java.util.List;


// Algorithm to calculate how long adjacent objects are updated / replaced
// this model is use for servers but can be adjusted for bacteria
// assumptions that it takes 1 ms for replication
public class ServerBinaryList {

	boolean checkServer(List<List<Integer>> manyRacks) {
		boolean fileCopied = true;
		
		for (List<Integer> serversChk : manyRacks) {
			if	(fileCopied) {
				for (Integer serverChk : serversChk) {
					if (serverChk==0) {
						fileCopied = false;
						break;
					}
				}
			}
		}
		
		return fileCopied;
	}
	
	
	void updateServer(List<List<Integer>> manyRacks) {
		// loop through the servers once and change (copy file) from 0 to 1 
		for (int i=0; i<manyRacks.size(); i++) {
			
			List<Integer> servers = manyRacks.get(i);
			
			// has to be while loop to loop thru once for every 1 ms
			int idx = 0;
			boolean updatePrior = false;
			boolean updateNext = false;
			while (idx<servers.size()-1) {
				int priorIDX = 0;
				if (idx>0) {
					priorIDX = servers.get(idx-1);
				}
				
				int currIDX = servers.get(idx);
				int nextIDX = servers.get(idx+1);
				
				if (idx>0 && !updatePrior && priorIDX==0 && currIDX==1) {
					servers.set(idx-1, 1);
					updatePrior = true;
				} else {
					updatePrior = false;
				}
					
				if (!updateNext && currIDX==1 && nextIDX==0) {
					servers.set(idx+1, 1);
					updateNext = true;
				} else {
					updateNext = false;
				}
				idx++;
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		// Test for now, later expect user input or file extraction
		List<Integer> rack1 = Arrays.asList(1,0,0,0,1,0,1,0);
		List<Integer> rack2 = Arrays.asList(0,0,1,0,0,0,1,0);
		List<Integer> rack3 = Arrays.asList(0,0,1,0,1,0,0,1);
		List<Integer> rack4 = Arrays.asList(0,0,0,0,0,1,0,0);
		
		List<List<Integer>> racks = Arrays.asList(rack1, rack2, rack3, rack4);
		
		boolean complete = false;
		int time_ms = 0;
		ServerBinaryList sb = new ServerBinaryList();
		
		while(!complete) {
			complete = sb.checkServer(racks);
			System.out.println("("+time_ms+") "+racks);
			sb.updateServer(racks);
			time_ms++;
		}
		
		time_ms--;
		//System.out.println("All Files Copied to Every Server in "+time_ms+" ms!");
		System.out.println("All Object Replicated and Replace Adjacent Objects in "+time_ms+" ms!");

	}

}
