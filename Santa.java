import java.util.ArrayList;


public class Santa {
	//Santa represents the nodes. It has two children named bag and vehicle. These three classes are created to increase the intelligibility of project.
	ArrayList<Pair<Santa,Integer>> giftpath;
	int id;
	public Santa(int id) {
		this.giftpath = new ArrayList<Pair<Santa,Integer>>();
		this.id = id;
	}
	
}
