package plusCoursChemin;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import plusCoursChemin.metier.Arc;
import plusCoursChemin.metier.BellmanFord;
import plusCoursChemin.metier.ConstruireGraphe;
import plusCoursChemin.metier.Dijkstra;
import plusCoursChemin.metier.Graphe;
import plusCoursChemin.vue.FrameGraphe;

public class Controleur 
{
	private Graphe      graphe;
	private BellmanFord bellmanFord;
	private Dijkstra    dijkstra;
	private FrameGraphe frame;

	public Controleur()
	{
		String chemin = this.choisirFichier();
		if (chemin != null)
		{
			this.init(chemin);
			this.frame = new FrameGraphe(this);
		}
	}

	public void init(String chemin)
	{
		this.graphe = ConstruireGraphe.InitGraphe(chemin);
		this.lancerAlgo();
	}

	public void lancerAlgo()
	{
		if (this.aArcNegatif())
		{
			System.out.println("Arc négatif détecté → Bellman-Ford");
			this.bellmanFord = new BellmanFord(this.graphe, 0);
			this.bellmanFord.algo();
			this.dijkstra = null;
		}
		else
		{
			int choix = FrameGraphe.demanderChoixAlgorithme();

			if (choix == FrameGraphe.ALGO_BELLMAN)
			{
				System.out.println("Aucun arc négatif --> Bellman-Ford");
				this.bellmanFord = new BellmanFord(this.graphe, 0);
				this.bellmanFord.algo();
				this.dijkstra = null;
			}
			else
			{
				System.out.println("Aucun arc négatif --> Dijkstra");
				this.dijkstra = new Dijkstra(this.graphe, this.graphe.getSommetParIndice(0));
				this.dijkstra.algo();
				this.bellmanFord = null;
			}
		}
	}

	public String choisirFichier()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("./data"));
		chooser.setFileFilter(new FileNameExtensionFilter("Data files", "data"));
		int result = chooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION)
			return chooser.getSelectedFile().getAbsolutePath();

		return null;
	}

	private boolean aArcNegatif()
	{
		for (Arc arc : this.graphe.getLstArcs())
			if (arc.getCout() < 0)
				return true;
		return false;
	}

	public static void main(String[] args) { new Controleur(); }

	public Graphe      getGraphe()                { return this.graphe;                          }
	public boolean     aCircuitAbsorbant    ()    { return ( this.bellmanFord != null ? this.bellmanFord.aCircuitAbsorbant() : false ); }

	public String[][]  getDonneesIterations()     { return (this.bellmanFord != null) ? this.bellmanFord.getDonneesIterations()  : (this.dijkstra != null ? this.dijkstra.getDonneesIterations()  : null); }
	public String[]    getColonnesIterations()    { return (this.bellmanFord != null) ? this.bellmanFord.getColonnesIterations() : (this.dijkstra != null ? this.dijkstra.getColonnesIterations() : null); }
	public String[][]  getDonneesB()              { return (this.bellmanFord != null) ? this.bellmanFord.getDonneesB()           : null; }
	public String[][]  getDonneesD()              { return (this.dijkstra    != null) ? this.dijkstra.getDonneesD()              : null; }
	public String[][]  getDonneesRelaxations()    { return (this.bellmanFord != null) ? this.bellmanFord.getDonneesRelaxations() : null; }

}