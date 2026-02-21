package plusCoursChemin;

import plusCoursChemin.metier.Arc;
import plusCoursChemin.metier.ConstruireGraphe;
import plusCoursChemin.metier.Graphe;


public class Controleur 
{
	// private Graphe graphe;
	
	// public Controleur ()
	// {
	// 	this.graphe = ConstruireGraphe.InitGraphe("../data/graphe.data");
	// }
	
	public static void main(String[] args) 
	{
		Graphe graphe = ConstruireGraphe.InitGraphe("./data/graphe.data");
		
		System.out.println("nb Arcs" + graphe.getNbArcs());
		System.out.println("nbSommets " + graphe.getNbSommets());

		for (Arc arc : graphe.getLstArcs()) 
		{
			System.out.println("Arc de " + arc.getSource().getNom() + " à "+ arc.getDest().getNom() + " en " + arc.getCout());
		}
		//new Controleur();
	}
}
