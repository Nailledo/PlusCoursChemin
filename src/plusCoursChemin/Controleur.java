package plusCoursChemin;

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

    public Controleur()
    {
        this.graphe      = ConstruireGraphe.InitGraphe("./data/graphe.data");
        this.dijkstra    = new Dijkstra(graphe, this.graphe.getSommetParIndice(0));
        this.bellmanFord = new BellmanFord(graphe, 0);

		// CUI
        this.dijkstra.algo();
        this.bellmanFord.algo();       

        new FrameGraphe( this );
    }

    public static void main(String[] args) 
    {
        new Controleur();
    }

	public String[][] getDonneesB()
	{
		return this.bellmanFord.getDonneesB();
	}
	public String[][] getDonneesD()
	{
		return this.dijkstra.getDonneesD();
	}
}