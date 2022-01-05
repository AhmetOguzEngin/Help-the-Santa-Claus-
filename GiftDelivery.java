import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class GiftDelivery {
	public static int startDelivery(int totalnum, Santa deladdress, Santa mainbag){
		int totalgift = 0;
		//An optimization to omit dead ends for next iterations. 		
		int[] index = new int[totalnum];
		//Keeps levels of nodes.		
		int[] level = new int[totalnum];
		
		while(BFS(totalnum, mainbag,level)) {
			Arrays.fill(index,0);
			while(true) {
				int giftnum = DFS(deladdress,mainbag,Integer.MAX_VALUE,index, level);
				if (giftnum == 0) break;
				totalgift += giftnum;
			}
		}
		return totalgift;

	}
	//To find a valid path if it exists. If not returns false.	
	public static boolean BFS(int totalnum, Santa mainbag, int[] level){
		Arrays.fill(level, -1);
		//To keep level of mainbag at 0 
		level[mainbag.id] = 0;
		Deque<Santa> q = new ArrayDeque<Santa>();
		q.offer(mainbag);
		while(!q.isEmpty()) {
			Santa curr = q.poll();
			for(Pair<Santa,Integer> vb: curr.giftpath) {
				if(level[vb.key.id] != -1 || vb.value == 0) continue;
				level[vb.key.id] = level[curr.id] + 1;
				q.offer(vb.key);
			}
		}
		//If it can reach source, it returns true else false.		
		return level[0] != -1;
	}
	//This part is to deliver gifts over the available paths. 	
	public static int DFS(Santa deladdress, Santa current, int gift, int[] index, int[] level) {
		if(current.id == deladdress.id) return gift;
		
		while(index[current.id]<current.giftpath.size()) {
			Pair<Santa,Integer> p = current.giftpath.get(index[current.id]); 
			Santa neighbr = p.key;
			if(level[neighbr.id] == level[current.id]+1 && p.value > 0 ) {
				//To find bottleneck of the path.				
				int minflow = Math.min(gift, p.value);
				int curflow = DFS(deladdress,neighbr,minflow,index,level);

				if(curflow > 0) {
					/*
					 * After path and its bottleneck is found this part decreases the capacity of edges and 
					 * creates new reverse edges with capacity of flow. If reverse edge already exists, it just increases the capacity.
					 */
					
					if(p.residual == null){
						p.value -= curflow;
						Pair<Santa, Integer> ng = new Pair<Santa, Integer>(current,curflow);
						p.residual = ng;
						ng.residual = p;
						neighbr.giftpath.add(ng);
						return curflow;
					}
					else { 
						p.value -= curflow;
						p.residual.value +=curflow;
						return curflow;
					}

				}
			}
			//This part is to eliminate dead end problem.			
			index[current.id]++;
		}
		return 0;

	}
}
