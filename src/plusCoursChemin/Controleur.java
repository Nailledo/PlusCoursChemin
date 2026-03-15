package plusCoursChemin;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import plusCoursChemin.metier.Arc;
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
        String chemin = this.choisirFichier();
        this.graphe = ConstruireGraphe.InitGraphe(chemin);
        this.lancerAlgo();
        new FrameGraphe(this);
    }

    public void lancerAlgo()
    {
        if (this.aArcNegatif())
        {
            System.out.println("Arc négatif détecté → Bellman-Ford");
            this.bellmanFord = new BellmanFord(this.graphe, 0);
            this.bellmanFord.algo();
            this.dijkstra = null;
        }
        else
        {
            System.out.println("Aucun arc négatif → Dijkstra");
            this.dijkstra = new Dijkstra(this.graphe, this.graphe.getSommetParIndice(0));
            this.dijkstra.algo();
            this.bellmanFord = null;
        }
    }

    public String choisirFichier()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("./"));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Data files", "data");
        chooser.setFileFilter(filter);

        chooser.showOpenDialog(null);

        return chooser.getSelectedFile().getAbsolutePath();
    }

    private boolean aArcNegatif()
    {
        for (Arc arc : this.graphe.getLstArcs())
            if (arc.getCout() < 0)
                return true;
        return false;
    }
    
    public static void main(String[] args) 
    {
        new Controleur();
    }

	public String[][] getDonneesB()
	{
        if (this.bellmanFord != null) return this.bellmanFord.getDonneesB();
		return null;
	}
	public String[][] getDonneesD()
	{
        if (this.dijkstra != null) return this.dijkstra.getDonneesD();
		return null;
	}
public Graphe getGraphe() { return this.graphe; }
}
