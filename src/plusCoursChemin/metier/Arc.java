package plusCoursChemin.metier;

public class Arc 
{
	private Sommet source;
	private Sommet dest;
	private int cout;

	public Arc(Sommet source, Sommet dest, int cout) 
	{
		this.source = source;
		this.dest = dest;
		this.cout = cout;
	}

	
	public Sommet getSource() {return source;}
	public Sommet getDest()   {return dest;}
	public int    getCout()   {return cout;}
	
	public void setSource(Sommet source){this.source = source;}
	public void setDest  (Sommet dest)  {this.dest = dest;}
	public void setCout  (int cout)     {this.cout = cout;}
}
