package plusCoursChemin.metier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ConstruireGraphe 
{
	public static Graphe InitGraphe(String fichier)
	{
		Scanner scLigne;
		Graphe graphe = new Graphe();
		HashMap<String, Sommet> hashSommet = new HashMap<>();
		ArrayList<Arc> lstArc = new ArrayList<>();
		String ligne,nomSource, nomDest;
		Sommet source, dest;
		int indexSommet,cout; 
		Arc arc;

		
		try {
			Scanner sc = new Scanner(new FileInputStream(fichier), StandardCharsets.UTF_8.name());
			
			indexSommet = 0;
			while (sc.hasNextLine()) 
			{
				ligne = sc.nextLine();
				if ( ligne.startsWith("#") )
					continue;

				if (ligne.trim().isEmpty())
					continue;
				
				scLigne = new Scanner(ligne);
				scLigne.useDelimiter("\\t");
				
				nomSource = scLigne.next();
				nomDest = scLigne.next();
				cout = scLigne.nextInt();

				if (!hashSommet.containsKey(nomSource))
				{
					hashSommet.put(nomSource, new Sommet(indexSommet, nomSource));
					indexSommet ++;
				}

				if (!hashSommet.containsKey(nomDest))
				{
					hashSommet.put(nomDest, new Sommet(indexSommet, nomDest));
					indexSommet ++;
				}

				source = hashSommet.get(nomSource);
				dest   = hashSommet.get(nomDest);

				arc = new Arc(source,dest,cout);
				lstArc.add(arc);

				scLigne.close();
			}

			sc.close();

			graphe.setHashSommet(hashSommet);
			graphe.setNbSommets( hashSommet.size());
			graphe.setNbArcs(lstArc.size());
			graphe.setLstArcs(lstArc);

		} catch (FileNotFoundException e) {
			System.out.println("fichier non trouvée");
		}

		return graphe;
	}

}
