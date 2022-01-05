import java.util.Comparator;

public class Pair<T1,T2> implements Comparator<Pair<T1,T2>> {
	//Each pair represents an edge.
	Santa key;
	Integer value;
	Pair<Santa,Integer> residual;
	public Pair(Santa key, Integer value) {
		this.key = key;
		this.value = value;
	}
	@Override
	public int compare(Pair<T1,T2> o1, Pair<T1,T2> o2) {
		return o1.value-o2.value;
	}

	
	
}
