package plusCoursChemin.vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import plusCoursChemin.Controleur;

public class PanelGraphe extends JPanel 
{
	private GrapheStream graphe;
	private Controleur ctrl;

    public PanelGraphe( Controleur ctrl )
    {
		this.ctrl = ctrl;
		this.graphe = new GrapheStream( this.ctrl );

        String[] colonnes = { "Sommet", "Distance" };

        this.setLayout(new GridLayout(2, 1, 10, 10));

        // Table Dijkstra
        JLabel lblDijkstra   = new JLabel("Dijkstra depuis A", SwingConstants.CENTER);
        JTable tableDijkstra = new JTable(new DefaultTableModel(this.ctrl.getDonneesD(), colonnes));

		JPanel panelDroite = new JPanel(new GridLayout(1, 2));
		JPanel panelGauche = new JPanel(new GridLayout(1, 2));

        JPanel panelD = new JPanel(new BorderLayout());

        panelD.add(lblDijkstra, BorderLayout.NORTH);
        panelD.add(new JScrollPane(tableDijkstra), BorderLayout.CENTER);

        // Table Bellman-Ford
        JLabel lblBellman = new JLabel("Bellman-Ford depuis A", SwingConstants.CENTER);
        JTable tableBellman = new JTable(new DefaultTableModel(this.ctrl.getDonneesB(), colonnes));
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