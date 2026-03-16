package plusCoursChemin.vue;

import javax.swing.*;
import plusCoursChemin.Controleur;

public class FrameGraphe extends JFrame 
{
	public static final int ALGO_DIJKSTRA = 0;
	public static final int ALGO_BELLMAN  = 1;

	private Controleur ctrl;

	public FrameGraphe(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setTitle("Plus Courts Chemins");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(this.creerMenuBar());
		this.add(new PanelGraphe(this.ctrl));
		this.setVisible(true);
	}

	private JMenuBar creerMenuBar()
	{
		JMenuBar  menuBar    = new JMenuBar();
		JMenu     menu       = new JMenu("Fichier");
		JMenuItem btnOuvrir  = new JMenuItem("Ouvrir un fichier .data");
		JMenuItem btnQuitter = new JMenuItem("Quitter");

		btnOuvrir.addActionListener(e ->
		{
			String chemin = this.ctrl.choisirFichier();
			if (chemin != null)
			{
				this.ctrl.init(chemin);
				this.getContentPane().removeAll();
				this.add(new PanelGraphe(this.ctrl));
				this.revalidate();
				this.repaint();
			}
		});

		btnQuitter.addActionListener(e -> System.exit(0));

		menu.add(btnOuvrir);
		menu.addSeparator();
		menu.add(btnQuitter);
		menuBar.add(menu);
		return menuBar;
	}

	public static int demanderChoixAlgorithme()
	{
		Object[] options = { "Dijkstra", "Bellman-Ford" };
		int choix = JOptionPane.showOptionDialog(
			null,
			"Choisissez l'algorithme de plus court chemin :",
			"Choix de l'algorithme",
			JOptionPane.DEFAULT_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[0]
		);

		if (choix != ALGO_BELLMAN)
			return ALGO_DIJKSTRA;

		return ALGO_BELLMAN;
	}
}