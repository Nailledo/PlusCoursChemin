package plusCoursChemin.vue;

import plusCoursChemin.Controleur;
import plusCoursChemin.metier.Arc;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import java.util.ArrayList;

public class GrapheStream 
{
	private Graph      graph;
	private Controleur ctrl;

	// Définition du style CSS pour GraphStream
	private String styleSheet = 
		"node {" +
		"   text-size: 14px;" +   
		"   text-alignment:center; " + 
		"   text-color:white;  "  +  
		"   text-style: bold;" +      
		"   size: 15px;" +           
		"}" +
		"edge {" +
		"   text-size: 16px;" +     
		"   text-style: bold;" +      
		"}";

	public GrapheStream(Controleur ctrl)
	{
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		System.setProperty("org.graphstream.ui", "swing");
		this.ctrl  = ctrl;
		this.graph = new SingleGraph("Graphe");
		
		// Application de la feuille de style au graphe
		this.graph.setAttribute("ui.stylesheet", styleSheet);
		this.graph.addAttribute("ui.quality");
		this.graph.addAttribute("ui.antialias");
		
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
		Viewer viewer = new Viewer(this.graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();
		View view = viewer.addDefaultView(false);
		return view;
	}
}