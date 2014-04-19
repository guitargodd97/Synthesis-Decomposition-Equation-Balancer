import java.util.ArrayList;

public class Compound {

	private ArrayList<String> elements;
	private int coefficient;
	private int[] subscripts;

	// Constructs a Compound
	public Compound(String compound) {
		// Initializes the coefficient
		coefficient = 1;

		// Initializes the array of element names
		elements = new ArrayList<String>();
		int start = 0;
		for (int i = 0; i < compound.length(); i++) {
			if (i < compound.length() - 1) {
				if (compound.substring(i, i + 1).compareTo("A") < 0) {
					elements.add(compound.substring(start, i));
					start = i + 1;
				}
			} else {
				if (compound.substring(i).compareTo("A") < 0) {
					elements.add(compound.substring(start, i));
					start = i + 1;
				}
			}
		}

		// Initializes the corresponding subscripts to the compound elements
		subscripts = new int[elements.size()];
		int cur = 0;
		for (int i = 0; i < compound.length(); i++) {
			if (i < compound.length() - 1) {
				if (compound.substring(i, i + 1).compareTo("A") < 0) {
					subscripts[cur] = Integer.parseInt(compound.substring(i,
							i + 1));
					cur++;
				}
			} else {
				if (compound.substring(i).compareTo("A") < 0) {
					subscripts[cur] = Integer.parseInt(compound.substring(i,
							i + 1));
					cur++;
				}
			}
		}
	}

	// Converts the Compound to a String
	public String toString() {
		String s = "";
		for (int i = 0; i < elements.size(); i++)
			s += elements.get(i) + subscripts[i];
		return coefficient + s;
	}

	// Gets the ArrayList of element names
	public ArrayList<String> getElements() {
		return elements;
	}

	// Checks if this compound has a certain element
	public boolean hasElement(String e) {
		for (int i = 0; i < elements.size(); i++) {
			if (e.equals(elements.get(i)))
				return true;
		}
		return false;
	}

	// Gets the ID of a certain element
	public int getId(String e) {
		for (int i = 0; i < elements.size(); i++) {
			if (e.equals(elements.get(i)))
				return i;
		}
		return -1;
	}

	// Gets the total amount of an element
	public int getAmount(int x) {
		return coefficient * subscripts[x];
	}

	// Gets the subscript of an element
	public int getSubscript(int x) {
		return subscripts[x];
	}

	// Sets the coefficient of the Compound
	public void setCoefficient(int coefficient) {
		this.coefficient *= coefficient;
	}
}
//© Hunter Heidenreich 2014