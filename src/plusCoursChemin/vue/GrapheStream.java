package plusCoursChemin.vue;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer; 
import plusCoursChemin.Controleur;

public class GrapheStream 
{
	private Graph graph;
	private Controleur ctrl;

	public GrapheStream( Controleur ctrl )
	{
		this.ctrl = ctrl;
		System.setProperty("org.graphstream.ui", "swing");
        this.graph = new SingleGraph("Dijkstra Algorithm");
		this.graphe();
	}

	public void graphe()
	{
		// graph.addNode("A").setAttribute("ui.label", "A");

		String[][] donneesB = this.ctrl.getDonneesB();

		for (int cpt = 0; cpt < donneesB.length; cpt++) 
		{
			graph.addNode( donneesB[cpt][0] );

		}
	}

	private void addWeightedEdge(String from, String to, double weight) 
    {
        Edge edge = graph.addEdge(from + "-" + to, from, to, false);
        edge.setAttribute("weight", weight);
        edge.setAttribute("ui.label", String.valueOf(weight));
    }

	public ViewPanel display()
	{
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();
		return viewer.addDefaultView(false); 
	}
}
