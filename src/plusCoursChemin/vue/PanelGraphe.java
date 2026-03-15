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

        this.add((JComponent) new GrapheStream(ctrl).display(), BorderLayout.CENTER);
        this.add(this.creerPanelTables(ctrl),                   BorderLayout.EAST);
    }

    private JPanel creerPanelTables(Controleur ctrl)
    {
        String[] colonnes = { "Sommet", "Distance" };

        JPanel panelD = new JPanel(new BorderLayout());
        panelD.add(new JLabel("Dijkstra",     SwingConstants.CENTER), BorderLayout.NORTH);
        if ( ctrl.getDonneesD() != null )
            panelD.add(new JScrollPane(new JTable(new DefaultTableModel(ctrl.getDonneesD(), colonnes))), BorderLayout.CENTER);
        else
        {
            panelD.add ( new JLabel("Le graphe à des arcs négatifs "), BorderLayout.CENTER );
        }

        JPanel panelB = new JPanel(new BorderLayout());
        if ( ctrl.getDonneesB() != null )
        {
            panelB.add(new JLabel("Bellman-Ford", SwingConstants.CENTER), BorderLayout.NORTH);
            panelB.add(new JScrollPane(new JTable(new DefaultTableModel(ctrl.getDonneesB(), colonnes))), BorderLayout.CENTER);
        }
        else
        {
            panelB.add ( new JLabel("Le graphe à des arcs négatifs ") );
            panelB.add ( new JLabel("On utilise Bellman-Ford ") );
        }
      
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.add(panelD);
        panel.add(panelB);
        panel.setPreferredSize(new Dimension(200, 0));
        return panel;
    }
}