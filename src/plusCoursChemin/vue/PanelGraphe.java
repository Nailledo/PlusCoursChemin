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

        // Table des itérations Bellman (si applicable)
        if (ctrl.getDonneesIterations() != null)
        {
            JPanel panelSud = new JPanel(new GridLayout(1, 2, 10, 10));
            panelSud.add(this.creerPanelIterations(ctrl));
            panelSud.add(this.creerPanelRelaxations(ctrl));
            panelSud.setPreferredSize(new Dimension(0, 150));
            this.add(panelSud, BorderLayout.SOUTH);
        }
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
        panel.add(new JLabel("It    érations Bellman-Ford", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(new JScrollPane(table),                                        BorderLayout.CENTER);
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