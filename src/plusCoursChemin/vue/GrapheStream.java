package plusCoursChemin.vue;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer; 

public class GrapheStream 
{
	private Graph graph;

	public GrapheStream( String[][] donneesB, String[][] donneesD)
	{
		System.setProperty("org.graphstream.ui", "swing");
        this.graph = new SingleGraph("Dijkstra Algorithm");
		this.graphe();
	}

	public void graphe()
	{

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
