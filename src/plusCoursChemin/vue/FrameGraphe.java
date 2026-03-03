package plusCoursChemin.vue;

import javax.swing.*;
import java.awt.*;

public class FrameGraphe extends JFrame 
{
    private PanelGraphe panelGraphe;

    public FrameGraphe(String[][] donneesD, String[][] donneesB)
    {
        this.setTitle("Plus Courts Chemins");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        this.panelGraphe = new PanelGraphe(donneesD, donneesB );
        this.add(panelGraphe);

        this.setVisible(true);
    }
}