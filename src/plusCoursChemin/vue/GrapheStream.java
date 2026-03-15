package plusCoursChemin.vue;

import plusCoursChemin.Controleur;
import plusCoursChemin.metier.Arc;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.util.DefaultMouseManager;

import java.util.ArrayList;

public class GrapheStream 
{
    private Graph      graph;
    private Controleur ctrl;

    // --- Définition du style CSS pour GraphStream ---
    private String styleSheet = 
        "node {" +
        "   text-size: 18px;" +       // Police plus grande pour les sommets
        "   text-style: bold;" +      // Texte en gras
        "   size: 15px;" +            // Taille du point grossie un peu pour équilibrer
        "}" +
        "edge {" +
        "   text-size: 16px;" +       // Police plus grande pour les poids des arcs
        "   text-style: bold;" +      // Texte en gras
        "   arrow-size: 12px, 8px;" + // Flèche plus visible sur les arcs orientés
        "}";

    public GrapheStream(Controleur ctrl)
    {
        System.setProperty("org.graphstream.ui", "swing");
        this.ctrl  = ctrl;
        this.graph = new SingleGraph("Graphe");
        
        // Application de la feuille de style au graphe
        this.graph.setAttribute("ui.stylesheet", styleSheet);
        
        this.construireGraphe();
    }

    private void construireGraphe()
    {
        ArrayList<Arc> arcs = this.ctrl.getGraphe().getLstArcs();

        for (Arc arc : arcs)
        {
            String src   = arc.getSource().getNom();
            String dest  = arc.getDest().getNom();
            int    poids = arc.getCout();

            if (this.graph.getNode(src) == null)
                this.graph.addNode(src).setAttribute("ui.label", src);
            if (this.graph.getNode(dest) == null)
                this.graph.addNode(dest).setAttribute("ui.label", dest);

            String idArete = src + "-" + dest;
            if (this.graph.getEdge(idArete) == null)
            {
                Edge edge = this.graph.addEdge(idArete, src, dest, true);
                edge.setAttribute("weight",   poids);
                edge.setAttribute("ui.label", String.valueOf(poids));
            }
        }
    }

    public View display()
	{
		Viewer viewer = new Viewer(this.graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		viewer.enableAutoLayout();
		View view = viewer.addDefaultView(false);
		return view;
	}
}