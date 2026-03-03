package plusCoursChemin.vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelGraphe extends JPanel 
{
	private GrapheStream graphe;

    public PanelGraphe(String[][] donneesD, String[][] donneesB )
    {
		this.graphe = new GrapheStream(donneesB, donneesD);

        String[] colonnes = { "Sommet", "Distance" };

        this.setLayout(new GridLayout(2, 1, 10, 10));

        // Table Dijkstra
        JLabel lblDijkstra   = new JLabel("Dijkstra depuis A", SwingConstants.CENTER);
        JTable tableDijkstra = new JTable(new DefaultTableModel(donneesD, colonnes));

		JPanel panelDroite = new JPanel(new GridLayout(1, 2));
		JPanel panelGauche = new JPanel(new BorderLayout());

        JPanel panelD = new JPanel(new BorderLayout());

        panelD.add(lblDijkstra, BorderLayout.NORTH);
        panelD.add(new JScrollPane(tableDijkstra), BorderLayout.CENTER);

        // Table Bellman-Ford
        JLabel lblBellman = new JLabel("Bellman-Ford depuis A", SwingConstants.CENTER);
        JTable tableBellman = new JTable(new DefaultTableModel(donneesB, colonnes));
        JPanel panelB = new JPanel(new BorderLayout());
        panelB.add(lblBellman, BorderLayout.NORTH);
        panelB.add(new JScrollPane(tableBellman), BorderLayout.CENTER);

        panelDroite.add(panelD, BorderLayout.NORTH);
        panelDroite.add(panelB, BorderLayout.SOUTH);

		panelGauche.add ( this.graphe.display() );

		this.add ( panelDroite, BorderLayout.EAST );
		this.add ( panelGauche, BorderLayout.WEST );

	}
}