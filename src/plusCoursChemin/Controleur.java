package plusCoursChemin;

import plusCoursChemin.metier.BellmanFord;
import plusCoursChemin.metier.ConstruireGraphe;
import plusCoursChemin.metier.Dijkstra;
import plusCoursChemin.metier.Graphe;

public class Controleur 
{
	private Graphe graphe;
	private BellmanFord bellmanFord;
	private Dijkstra dijkstra;

	public Controleur ()
	{
		this.graphe = ConstruireGraphe.InitGraphe("./data/graphe.data");
		this.bellmanFord = new BellmanFord(graphe,0);
		this.dijkstra = new Dijkstra(graphe, this.graphe.getSommetParIndice(0) );

		this.dijkstra.algo();
		//this.bellmanFord.algo();
	}
	
	public static void main(String[] args) 
	{
		new Controleur();
	}
}
