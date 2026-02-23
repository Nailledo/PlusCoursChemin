package plusCoursChemin.metier;

import java.util.ArrayList;
import java.util.HashSet;

public class Dijkstra 
{
    private Graphe g;
    private Sommet s;

    // w c'est la matrice des coûts, deja effctuée dans Arc.getCout()
    public Dijkstra(Graphe g, Sommet s)
    {
        this.g = g;
        this.s = s;
    }

    public void algo()
    {
        int[] distances = new int[this.g.getNbSommets()];

        // Initialisation
        for (int i = 0; i < distances.length; i++)
            distances[i] = Integer.MAX_VALUE;

        distances[this.s.getIndex()] = 0;
        // E = ensemble des sommets déjà visités
        HashSet<Sommet> E = new HashSet<>();

        // F = ensemble des sommets du graphe
        ArrayList<Sommet> F = new ArrayList<>(  );
        for (int i = 0; i < this.g.getNbSommets(); i++)
            F.add(this.g.getSommetParIndice(i));

        while (!F.isEmpty())
        {
            // on choisit le sommet avec la plus petite valeur de d
            Sommet u       = null;
            int    minDist = Integer.MAX_VALUE;
            for (Sommet v : F)
            {
                if (distances[v.getIndex()] < minDist)
                {
                    minDist = distances[v.getIndex()];
                    u = v;
                }
            }

            if (u == null) break;

            F.remove(u);
            E.add(u);

            for (Arc arc : this.g.getLstArcs())
            {
                Sommet voisin = null;
                int cout = 0;

                if (arc.getSource().equals(u))
                {
                    voisin = arc.getDest();
                    cout   = arc.getCout();
                }

                if (voisin != null && F.contains(voisin))
                {
                    if (distances[u.getIndex()] != Integer.MAX_VALUE &&
                        distances[voisin.getIndex()] > distances[u.getIndex()] + cout)
                    {
                        distances[voisin.getIndex()] = distances[u.getIndex()] + cout;
                    }
                }
            }
        }

        // Affichage des résultats
        System.out.println("Distances depuis le sommet " + this.s.getNom() + " :");
        for (int i = 0; i < this.g.getNbSommets(); i++)
        {
            Sommet v    = this.g.getSommetParIndice(i);
            String dist = (distances[i] == Integer.MAX_VALUE) ? "+infini" : String.valueOf(distances[i]);
            System.out.println("Le sommet " + v.getNom() + " est à " + dist + " de " + this.s.getNom() );
        }
    }
}