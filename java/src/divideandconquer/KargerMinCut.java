package divideandconquer;
import java.io.*;
import java.util.*;
public class KargerMinCut {

    private static Map<Integer, List<Integer>> copyGraph(Map<Integer, List<Integer>> original) {
        Map<Integer, List<Integer>> copy = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : original.entrySet()) {
            copy.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return copy;
    }

    public static int runKarger(Map<Integer, List<Integer>> graph) {
        Random rand = new Random();
        while (graph.size() > 2) {
            List<Integer> keys = new ArrayList<>(graph.keySet());
            int u = keys.get(rand.nextInt(keys.size()));
            List<Integer> uEdges = graph.get(u);
            
            if (uEdges.isEmpty()) continue;
            
            int v = uEdges.get(rand.nextInt(uEdges.size()));

            // Fusionner v dans u
            List<Integer> vEdges = graph.get(v);
            uEdges.addAll(vEdges);

            // Mettre à jour les voisins
            for (Integer neighbor : vEdges) {
                List<Integer> neighborEdges = graph.get(neighbor);
                Collections.replaceAll(neighborEdges, v, u);
            }

            uEdges.removeIf(edge -> edge == u);
            graph.remove(v);
        }
        return graph.values().iterator().next().size();
    }

    public static void main(String[] args) {
        // Chemin adapté à l'arborescence pour bien trouver le fichier KargerMinCut.txt
        String filePath = "KargerMinCut.txt"; 
        Map<Integer, List<Integer>> originalGraph = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length > 0) {
                    int v = Integer.parseInt(parts[0]);
                    List<Integer> edges = new ArrayList<>();
                    for (int i = 1; i < parts.length; i++) {
                        edges.add(Integer.parseInt(parts[i]));
                    }
                    originalGraph.put(v, edges);
                }
            }
            
            int minCut = Integer.MAX_VALUE;
            // Pour n=200, il est conseillé de faire beaucoup d'itérations
            int iterations = 100; 

            System.out.println("Début des itérations...");
            for (int i = 0; i < iterations; i++) {
                int currentCut = runKarger(copyGraph(originalGraph));
                if (currentCut < minCut) {
                    minCut = currentCut;
                    System.out.println("Nouvelle coupe minimale trouvée : " + minCut);
                }
            }
            System.out.println("Résultat final : " + minCut);

        } catch (IOException e) {
            System.err.println("Erreur : Impossible de trouver le fichier " + filePath);
            System.err.println("Vérifiez que vous lancez le programme depuis le dossier : algorithms-stanford/java");
        }
    }
}

//depuis le dossier java , exectuer
//javac src/divideandconquer/KargerMinCut.java
//java -cp src divideandconquer.KargerMinCut