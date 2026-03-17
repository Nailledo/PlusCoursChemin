# Plus Court Chemin

Application Java pour trouver le plus court chemin dans un graphe.

## Algorithmes implémentés

- **Dijkstra** : Pour les graphes avec poids positifs
- **Bellman-Ford** : Pour les graphes avec poids négatifs

## Compilation

```bash
javac -cp "lib/*" -d bin @compile.list
```

## Exécution

Sur Linux/Mac :
```bash
./run.sh
```

Sur Windows :
```bash
run.bat
```

## Structure

- `src/` : Code source Java
- `bin/` : Fichiers compilés
- `lib/` : Dépendances externes
- `data/` : Fichiers de données pour les graphes