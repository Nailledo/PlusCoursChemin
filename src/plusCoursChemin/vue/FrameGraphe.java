package plusCoursChemin.vue;

import javax.swing.*;
import plusCoursChemin.Controleur;

public class FrameGraphe extends JFrame 
{
    private PanelGraphe panelGraphe;
	private Controleur  ctrl;

    public FrameGraphe( Controleur ctrl )
    {
		this.ctrl = ctrl;

        this.setTitle("Plus Courts Chemins");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        this.panelGraphe = new PanelGraphe( this.ctrl );
        this.add(panelGraphe);

        this.setVisible(true);
    }
}