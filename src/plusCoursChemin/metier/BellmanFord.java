package plusCoursChemin.metier;

import java.util.ArrayList;

public class BellmanFord 
{
    private Graphe            graphe;
    private int               numSommetSource;
    private int[]             distances;
    private boolean           circuitAbsorbant;
    private ArrayList<int[]>  historique;
    private ArrayList<String> relaxations;

    public BellmanFord(Graphe g, int numSommetSource)
    {
        this.graphe           = g;
        this.numSommetSource  = numSommetSource;
        this.circuitAbsorbant = false;
        this.historique       = new ArrayList<>();
        this.relaxations      = new ArrayList<>();
    }

    public void algo()
    {
        int            nbSommets = this.graphe.getNbSommets();
        ArrayList<Arc> lstArcs   = this.graphe.getLstArcs();

        // Initialisation
        distances = new int[nbSommets];
        for (int i = 0; i < nbSommets; i++)
            distances[i] = Integer.MAX_VALUE;
        distances[this.numSommetSource] = 0;
        this.historique.add(distances.clone());

        // Relaxations
        for (int i = 0; i < nbSommets - 1; i++)
        {
            for (Arc arc : lstArcs)
            {
                int source = arc.getSource().getIndex();
                int dest   = arc.getDest().getIndex();
                int cout   = arc.getCout();

                if (distances[source] != Integer.MAX_VALUE &&
                    distances[source] + cout < distances[dest])
                {
                    distances[dest] = distances[source] + cout;

                    // Enregistrement de la relaxation
                    this.relaxations.add(
                        "Itération " + (i+1) + " | " +
                        arc.getSource().getNom() + " -> " + arc.getDest().getNom() +
                        " : d(" + arc.getDest().getNom() + ") = " + distances[dest]
                    );
                }
            }
            this.historique.add(distances.clone());
        }

        // Détection circuit absorbant
        for (Arc arc : lstArcs)
        {
            int source = arc.getSource().getIndex();
            int dest   = arc.getDest().getIndex();
            int cout   = arc.getCout();
            if (distances[source] != Integer.MAX_VALUE &&
                distances[source] + cout < distances[dest])
            {
                this.circuitAbsorbant = true;
                System.out.println("Circuit absorbant détecté !");
            }
        }
    }

    public String[][] getDonneesRelaxations()
    {
        String[][] donnees = new String[this.relaxations.size()][1];
        for (int i = 0; i < this.relaxations.size(); i++)
            donnees[i][0] = this.relaxations.get(i);
        return donnees;
    }

    public String[][] getDonneesIterations()
    {
        int        nbSommets = this.graphe.getNbSommets();
        String[][] donnees   = new String[this.historique.size()][nbSommets + 1];
        for (int i = 0; i < this.historique.size(); i++)
        {
            donnees[i][0] = (i == 0) ? "Initialisation" : "Itération " + i;
            int[] dist = this.historique.get(i);
            for (int j = 0; j < nbSommets; j++)
                donnees[i][j + 1] = (dist[j] == Integer.MAX_VALUE) ? "+∞" : String.valueOf(dist[j]);
        }
        return donnees;
    }

    public String[] getColonnesIterations()
    {
        int      nbSommets = this.graphe.getNbSommets();
        String[] colonnes  = new String[nbSommets + 1];
        colonnes[0] = "";
        for (int i = 0; i < nbSommets; i++)
            colonnes[i + 1] = "d(" + this.graphe.getSommetParIndice(i).getNom() + ")";
        return colonnes;
    }

    public boolean    aCircuitAbsorbant()   { return this.circuitAbsorbant; }
    public int[]      getDistances()        { return this.distances;        } 

    public String[][] getDonneesB()
    {
        String[][] donnees = new String[this.graphe.getNbSommets()][2];
        for (int i = 0; i < this.graphe.getNbSommets(); i++)
        {
            donnees[i][0] = this.graphe.getSommetParIndice(i).getNom();
            donnees[i][1] = (distances[i] == Integer.MAX_VALUE) ? "+∞" : String.valueOf(distances[i]);
        }
        return donnees;
    }
}