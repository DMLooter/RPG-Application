package chat.main;

import java.util.ArrayList;

/**
 * Class used for holding multiple pieces of information in line with each
 * other. Each is represented in an ArrayList that holds all three objects in
 * the same order. Like a hashmap for three values.
 * 
 * @author Michael
 *
 * @param <T>
 * @param <E>
 * @param <G>
 */
public class MultiArray<T, E, G> {
	ArrayList<T> Type1;
	ArrayList<E> Type2;
	ArrayList<G> Type3;

	public MultiArray() {
		this(10);
	}

	public MultiArray(int i) {
		Type1 = new ArrayList<T>(i);
		Type2 = new ArrayList<E>(i);
		Type3 = new ArrayList<G>(i);
	}

	public void remove(T t, E e, G g) {
		Type1.remove(t);
		Type2.remove(e);
		Type3.remove(g);
	}

	public void add(T t, E e, G g) {
		Type1.add(t);
		Type2.add(e);
		Type3.add(g);
	}

	public T get1(int i) {
		return Type1.get(i);
	}

	public E get2(int i) {
		return Type2.get(i);
	}

	public G get3(int i) {
		return Type3.get(i);
	}

	public void add1(T t) {
		Type1.add(t);
	}

	public void add2(E e) {
		Type2.add(e);
	}

	public void add3(G g) {
		Type3.add(g);
	}

	public void set1(int i, T t) {
		Type1.set(i, t);
	}

	public void set2(int i, E e) {
		Type2.set(i, e);
	}

	public void set3(int i, G g) {
		Type3.set(i, g);
	}

	public int find(T t) {
		for (int i = 0; i < Type1.size(); i++) {
			if (Type1.get(i).equals(t)) {
				return i;
			}
		}
		return -1;
	}

	public int size() {
		int T = Type1.size();
		int E = Type2.size();
		int G = Type3.size();
		int max;
		if (T >= E) {
			if (T >= G) {
				max = T;
			} else {
				max = G;
			}
		} else {
			if (E >= G) {
				max = E;
			} else {
				max = G;
			}
		}
		return max;
	}
}
