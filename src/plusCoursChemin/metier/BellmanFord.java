package plusCoursChemin.metier;

import java.util.ArrayList;

public class BellmanFord 
{
	private int numSommetSource;
	private Graphe graphe;

	public BellmanFord (Graphe g, int numSommetSource)
	{
		this.graphe = g;
		this.numSommetSource = numSommetSource;	
	}

	public void algo()
	{
		int[]  distances = new int[this.graphe.getNbSommets()];
		ArrayList<Arc> lstArcs = new ArrayList<>(this.graphe.getLstArcs());
		Arc	arc;
		int source, dest, cout;
		String nomSommet;

		for (int i = 0; i < distances.length; i++) 
			distances[i] = Integer.MAX_VALUE;

		distances[this.numSommetSource] = 0;	

		for (int i = 0; i < this.graphe.getNbSommets()-1; i++) 
			for (int j = 0; j < this.graphe.getNbArcs(); j++) 
			{
				arc = lstArcs.get(j);
				source = arc.getSource().getIndex();
				dest   = arc.getDest().getIndex();
				cout   = arc.getCout();

				if (distances[source] != Integer.MAX_VALUE &&
					distances[source] + cout < distances[dest])  
				{
					distances[dest] = distances[source] + cout;
				}
			}
			
		for (int j = 0; j < this.graphe.getNbArcs(); j++) 
		{
			arc = lstArcs.get(j);
			source = arc.getSource().getIndex();
			dest   = arc.getDest().getIndex();
			cout   = arc.getCout();

			if (distances[source] != Integer.MAX_VALUE &&
				distances[source] + cout < distances[dest])  
			{
				System.out.println("Le graphe contient des boucle négatives");
			}
		}

		for (int i = 0; i < this.graphe.getNbSommets() ; i++) 
		{
			nomSommet = this.graphe.getSommetParIndice(i).getNom();

			System.out.println("Le sommet " + nomSommet + " est à " + distances[i] + " de " + this.numSommetSource );
		}	
	}




}
