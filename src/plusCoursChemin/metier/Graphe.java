package plusCoursChemin.metier;

import java.util.ArrayList;
import java.util.List;

public class Graphe 
{
	private int nbSommets;
	private int nbArcs;
	private List<Arc> lstArcs;
	
	public Graphe ()
	{
		this.lstArcs = new ArrayList<>();
	}

	public void setNbSommets(int nbSommets)         {this.nbSommets = nbSommets;}
	public void setNbArcs   (int nbArcs)            {this.nbArcs = nbArcs;}
	public void setLstArcs  (ArrayList<Arc> lstArcs){this.lstArcs = lstArcs;}

	public int getNbSommets    () {return nbSommets;}
	public int getNbArcs       () {return nbArcs;}
	public ArrayList<Arc> getLstArcs() {return new ArrayList<>(lstArcs);}

	

	
}
