package plusCoursChemin;

import plusCoursChemin.metier.BellmanFord;
import plusCoursChemin.metier.ConstruireGraphe;
import plusCoursChemin.metier.Graphe;

public class Controleur 
{
	private Graphe graphe;
	private BellmanFord bellmanFord;
	
	public Controleur ()
	{
		this.graphe = ConstruireGraphe.InitGraphe("./data/graphe.data");
		this.bellmanFord = new BellmanFord(graphe,0);

		this.bellmanFord.algo();
	}
	
	public static void main(String[] args) 
	{
		new Controleur();
	}
}
