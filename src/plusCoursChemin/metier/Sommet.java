package plusCoursChemin.metier;

public class Sommet 
{
	private int index;
	private String nom;


	public Sommet(int index, String nom) 
	{
		this.index = index;
		this.nom = nom;
	}

	public int    getIndex()          { return index; }
	public String getNom()            { return nom;   }

	public void   setIndex(int index) { this.index = index; }
	public void   setNom(String nom)  { this.nom   = nom;   }


}
