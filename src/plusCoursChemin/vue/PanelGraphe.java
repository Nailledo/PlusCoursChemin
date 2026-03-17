package plusCoursChemin.vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import plusCoursChemin.Controleur;

public class PanelGraphe extends JPanel 
{
	public PanelGraphe(Controleur ctrl)
	{
		this.setLayout(new BorderLayout(10, 10));
		this.add(this.creerPanelControle(ctrl),                BorderLayout.NORTH);
		this.add((JComponent) new GrapheStream(ctrl).display(), BorderLayout.CENTER);
		this.add(this.creerPanelTables(ctrl),                   BorderLayout.EAST);

		if (ctrl.getDonneesIterations() != null)
		{
			JPanel panelSud;
			if (ctrl.getDonneesRelaxations() != null)
			{
				panelSud = new JPanel(new GridLayout(1, 2, 10, 10));
				panelSud.add(this.creerPanelIterations(ctrl));
				panelSud.add(this.creerPanelRelaxations(ctrl));
			}
			else
			{
				panelSud = new JPanel(new BorderLayout());
				panelSud.add(this.creerPanelIterations(ctrl), BorderLayout.CENTER);
			}
			panelSud.setPreferredSize(new Dimension(0, 150));
			this.add(panelSud, BorderLayout.SOUTH);
		}

	}

	private JPanel creerPanelControle(Controleur ctrl)
	{
		JPanel panelGlobal = new JPanel(new BorderLayout(5, 5));

		JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelControles.add(new JLabel("Sommet source :"));

		String[] nomsSommets = ctrl.getNomsSommets();
		JComboBox<String> comboSommets = new JComboBox<>(nomsSommets);
		comboSommets.setSelectedIndex(ctrl.getSommetSourceIndex());
		panelControles.add(comboSommets);

		JButton btnRecalculer = new JButton("Recalculer");
		btnRecalculer.addActionListener(e ->
		{
			ctrl.recalculerDepuisSource(comboSommets.getSelectedIndex());
			Window fenetre = SwingUtilities.getWindowAncestor(this);
			if (fenetre instanceof FrameGraphe)
				((FrameGraphe) fenetre).rafraichirContenu();
		});
		panelControles.add(btnRecalculer);

		panelGlobal.add(panelControles, BorderLayout.NORTH);

		if (ctrl.aCircuitAbsorbant())
			panelGlobal.add(new JLabel("Circuit absorbant détectée !"), BorderLayout.SOUTH);

		return panelGlobal;
	}

	private JPanel creerPanelTables(Controleur ctrl)
	{
		String[] colonnes = { "Sommet", "Distance" };

		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));        

		if (ctrl.getDonneesD() != null)
		{
			JPanel panelD = new JPanel(new BorderLayout());
			panelD.add(new JLabel("Dijkstra", SwingConstants.CENTER), BorderLayout.NORTH);
			panelD.add(new JScrollPane(new JTable(new DefaultTableModel(ctrl.getDonneesD(), colonnes))), BorderLayout.CENTER);
			panel.add(panelD);

		}
		else
		{
			JPanel panelB = new JPanel(new BorderLayout());
			panelB.add(new JLabel("Bellman-Ford", SwingConstants.CENTER), BorderLayout.NORTH);
			panelB.add(new JScrollPane(new JTable(new DefaultTableModel(ctrl.getDonneesB(), colonnes))), BorderLayout.CENTER);
			panel.add(panelB);

		}
		panel.setPreferredSize(new Dimension(200, 0));
		return panel;
	}

	private JPanel creerPanelIterations(Controleur ctrl)
	{
		JTable table = new JTable(new DefaultTableModel(
			ctrl.getDonneesIterations(),
			ctrl.getColonnesIterations()
		));

		JPanel panel = new JPanel(new BorderLayout());
		String nomAlgo = (ctrl.getDonneesD() != null) ? "Dijkstra" : "Bellman-Ford";
		panel.add(new JLabel("Itérations " + nomAlgo, SwingConstants.CENTER), BorderLayout.NORTH);
		panel.add(new JScrollPane(table),                                       BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(0, 150));
		return panel;
	}

	private JPanel creerPanelRelaxations(Controleur ctrl)
	{
		JTable table = new JTable(new DefaultTableModel(
			ctrl.getDonneesRelaxations(),
			new String[]{ "Relaxations effectuées" }
		));

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("Ordre des relaxations", SwingConstants.CENTER), BorderLayout.NORTH);
		panel.add(new JScrollPane(table),                                      BorderLayout.CENTER);
		return panel;
	}
}