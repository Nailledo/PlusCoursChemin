package plusCoursChemin.vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelGraphe extends JPanel 
{
    public PanelGraphe(String[][] donneesD, String[][] donneesB )
    {
        String[] colonnes = { "Sommet", "Distance" };

        this.setLayout(new GridLayout(2, 1, 10, 10));

        // Table Dijkstra
        JLabel lblDijkstra = new JLabel("Dijkstra depuis A", SwingConstants.CENTER);
        JTable tableDijkstra = new JTable(new DefaultTableModel(donneesD, colonnes));
        JPanel panelD = new JPanel(new BorderLayout());
        panelD.add(lblDijkstra, BorderLayout.NORTH);
        panelD.add(new JScrollPane(tableDijkstra), BorderLayout.CENTER);

        // Table Bellman-Ford
        JLabel lblBellman = new JLabel("Bellman-Ford depuis A", SwingConstants.CENTER);
        JTable tableBellman = new JTable(new DefaultTableModel(donneesB, colonnes));
        JPanel panelB = new JPanel(new BorderLayout());
        panelB.add(lblBellman, BorderLayout.NORTH);
        panelB.add(new JScrollPane(tableBellman), BorderLayout.CENTER);

        this.add(panelD);
        this.add(panelB);
    }
}