package plusCoursChemin.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graphe 
{
	private HashMap<String,Sommet> hashSommet;
	private int nbSommets;
	private int nbArcs;
	private List<Arc> lstArcs;
	
	public Graphe ()
	{
		this.lstArcs = new ArrayList<>();
	}

	public void setNbSommets (int nbSommets)                   {this.nbSommets = nbSommets;}
	public void setNbArcs    (int nbArcs)                      {this.nbArcs = nbArcs;}
	public void setLstArcs   (ArrayList<Arc> lstArcs)          {this.lstArcs = lstArcs;}
	public void setHashSommet(HashMap<String, Sommet> hashSom) {this.hashSommet = hashSom;}

	public int            getNbSommets    ()      { return nbSommets;               }
	public int            getNbArcs       ()      { return nbArcs;                  }
	public ArrayList<Arc> getLstArcs      ()      { return new ArrayList<>(lstArcs);}

	public Sommet getSommetParIndice (int index) 
	{
		for (Sommet sommet : this.hashSommet.values()) 
		{
			if (sommet.getIndex() == index)
				return sommet;
		}
		return null;
	}

	public Sommet getSommetParNom (String nom) 
	{
		return this.hashSommet.get(nom);
	
	}

}
