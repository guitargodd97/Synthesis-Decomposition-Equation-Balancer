import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// Program Intro
		JOptionPane
				.showMessageDialog(
						null,
						"Welcome to the Synthesis/Decomposition Checmical Equation Balancer.\nEnter in your reactants and products and a balanced equation will be output.");

		// Initializes the ArrayLists
		ArrayList<Compound> reactants = new ArrayList<Compound>();
		ArrayList<Compound> products = new ArrayList<Compound>();

		// Collects the reactants
		String s = JOptionPane.showInputDialog("How many reactants?", "1");
		int r = Integer.parseInt(s);
		while (r > 0) {
			reactants.add(new Compound(JOptionPane.showInputDialog(
					"Enter in a reactant. (Ex. C1O2).", "O2")));
			r--;
		}

		// Collects the products
		s = JOptionPane.showInputDialog("How many products?", "1");
		r = Integer.parseInt(s);
		while (r > 0) {
			products.add(new Compound(JOptionPane.showInputDialog(
					"Enter in a product. (Ex. C1O2).", "O2")));
			r--;
		}

		// Balances the equation
		boolean finished = false;
		while (!finished) {
			finished = true;
			for (int i = 0; i < reactants.size(); i++) {
				ArrayList<String> eR = reactants.get(i).getElements();
				for (int id = 0; id < eR.size(); id++) {
					int saved = -1;
					int loco = -1;
					for (int index = 0; index < products.size(); index++) {
						if (products.get(index).hasElement(eR.get(id))) {
							saved = index;
							loco = products.get(index).getId(eR.get(id));
							index = products.size();
						}
					}
					if (saved >= 0) {
						if (!(reactants.get(i).getAmount(id) == products.get(
								saved).getAmount(loco))) {
							if ((reactants.get(i).getAmount(id)
									% products.get(saved).getAmount(loco) == 0)
									|| (products.get(saved).getAmount(loco)
											% reactants.get(i).getAmount(id) == 0)) {
								if (reactants.get(i).getAmount(id) > products
										.get(saved).getAmount(loco)) {
									int x = reactants.get(i).getAmount(id)
											/ products.get(saved).getAmount(
													loco);
									products.get(saved).setCoefficient(x);
								} else {
									int x = products.get(saved).getAmount(loco)
											/ reactants.get(i).getAmount(id);
									reactants.get(i).setCoefficient(x);
								}
								finished = false;
							} else {
								int x = reactants.get(i).getSubscript(id);
								int y = products.get(saved).getSubscript(loco);
								reactants.get(i).setCoefficient(y);
								products.get(saved).setCoefficient(x);
								finished = false;
							}
						}
					}
				}
			}

			for (int i = 0; i < products.size(); i++) {
				ArrayList<String> eP = products.get(i).getElements();
				for (int id = 0; id < eP.size(); id++) {
					int saved = -1;
					int loco = -1;
					for (int index = 0; index < reactants.size(); index++) {
						if (reactants.get(index).hasElement(eP.get(id))) {
							saved = index;
							loco = reactants.get(index).getId(eP.get(id));
							index = reactants.size();
						}
					}
					if (saved >= 0) {
						if (!(products.get(i).getAmount(id) == reactants.get(
								saved).getAmount(loco))) {
							if ((products.get(i).getAmount(id)
									% reactants.get(saved).getAmount(loco) == 0)
									|| (reactants.get(saved).getAmount(loco)
											% products.get(i).getAmount(id) == 0)) {
								if (products.get(i).getAmount(id) > reactants
										.get(saved).getAmount(loco)) {
									int x = products.get(i).getAmount(id)
											/ reactants.get(saved).getAmount(
													loco);
									reactants.get(saved).setCoefficient(x);
								} else {
									int x = reactants.get(saved)
											.getAmount(loco)
											/ products.get(i).getAmount(id);
									products.get(i).setCoefficient(x);
								}
								finished = false;
							} else {
								int x = products.get(i).getSubscript(id);
								int y = reactants.get(saved).getSubscript(loco);
								products.get(i).setCoefficient(y);
								reactants.get(saved).setCoefficient(x);
								finished = false;
							}
						}
					}
				}
			}
		}

		// Outputs the equation
		String equation = "";
		for (int i = 0; i < reactants.size(); i++) {
			equation += reactants.get(i);
			if (i < reactants.size() - 1)
				equation += " + ";
			else
				equation += " = ";
		}
		for (int i = 0; i < products.size(); i++) {
			equation += products.get(i);
			if (i < products.size() - 1)
				equation += " + ";
		}
		JOptionPane.showMessageDialog(null, "Your balanced equation is: \n"
				+ equation);
	}
}
//© Hunter Heidenreich 2014