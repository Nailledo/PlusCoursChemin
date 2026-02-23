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
        // this.dijkstra.algo();
        // this.bellmanFord.algo();


        // Données Dijkstra
        int[]      distD           = this.dijkstra.getDistances();
        String[][] donneesDijkstra = new String[this.graphe.getNbSommets()][2];
        for (int i = 0; i < this.graphe.getNbSommets(); i++)
        {
            donneesDijkstra[i][0] = this.graphe.getSommetParIndice(i).getNom();
            donneesDijkstra[i][1] = (distD[i] == Integer.MAX_VALUE) ? "+INFINI" : String.valueOf(distD[i]);
        }

        // Données Bellman-Ford
        int[]      distB              = this.bellmanFord.getDistances();
        String[][] donneesBellmanFord = new String[this.graphe.getNbSommets()][2];
        for (int i = 0; i < this.graphe.getNbSommets(); i++)
        {
            donneesBellmanFord[i][0] = this.graphe.getSommetParIndice(i).getNom();
            donneesBellmanFord[i][1] = (distB[i] == Integer.MAX_VALUE) ? "+INFINI" : String.valueOf(distB[i]);
        }

        new FrameGraphe(donneesDijkstra, donneesBellmanFord );
    }

    public static void main(String[] args) 
    {
        new Controleur();
    }
}