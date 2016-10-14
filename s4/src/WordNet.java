package s4.src;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Queue;

/**
 * Created by raquelitarosaguilar on 03/10/2016.
 */
public class WordNet {

    private SAP sap;
    private SeparateChainingHashST<Integer, String> synsets;
    private SeparateChainingHashST<String, Queue<Integer>> hypernym;


    /*****************************************************************
     * constructor takes the name of the two input files
     *****************************************************************/
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException("Input is NULL");
        }

        this.synsets = new SeparateChainingHashST<Integer, String>();
        this.hypernym = new SeparateChainingHashST<String, Queue<Integer>>();
        synsetsInput(synsets);
        Digraph graph = hypernymsInput(hypernyms);
        isRootedOrCycle(graph);
        sap = new SAP(graph);
    }

    /*****************************************************************
     * A helper method that reads a synsets file from standard input and
     * and checks if it is valid CSV
     *****************************************************************/
    private void synsetsInput(String synset) {

        In filename = new In(synset);

        while (filename.hasNextLine()) {

            String[] lines = filename.readLine().split(",");
            String key = lines[0].trim();
            String value = lines[1].trim();
            int synsetID = Integer.parseInt(key);

            String[] nounsList = value.split(" ");
            synsets.put(synsetID, value);


            for (int i = 0; i < nounsList.length; i++) {
                String thisNoun = nounsList[i].trim();

                if (!hypernym.contains(thisNoun)) {
                    hypernym.put(thisNoun, new Queue<Integer>());
                }
                hypernym.get(thisNoun).enqueue(synsetID);
            }
        }
    }

    /*****************************************************************
     * A helper method that reads a hypernyms file from standard input
     *****************************************************************/
    private Digraph hypernymsInput(String hypernyms) {

        Digraph digraph = new Digraph(synsets.size());

        In filename = new In(hypernyms);

        while (filename.hasNextLine()) {

            String[] lines = filename.readLine().split(",");
            String key = lines[0].trim();
            int synsetID = Integer.parseInt(key);

            for (int i = 1; i < lines.length; i++) {
                int index = Integer.parseInt(lines[i].trim());
                digraph.addEdge(synsetID, index);
            }
        }
        return digraph;
    }

    /*****************************************************************
     * A helper method that checks if a graph has a directed
     * cycle or not
     ****************************************************************
     * @param digraph*/
    private void isRootedOrCycle(Digraph digraph) {
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        if (directedCycle.hasCycle()) {
            throw new IllegalArgumentException("Graph is not acyclic");
        }
        int root = 0;
        for (int i = 0; i < digraph.V(); i++) {
            if (!digraph.adj(i).iterator().hasNext())
                root++;
        }
        if (root != 1)
            throw new IllegalArgumentException("Graph is not rooted");
    }

    /*****************************************************************
     * Returns all the nouns in the Wordnet
     *****************************************************************/
    public Iterable<String> nouns() {
        return hypernym.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return hypernym.contains(word);
    }

    /*****************************************************************
     * Finds the distance between nouns
     * distance between nounA and nounB (defined below)
     *****************************************************************/
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) && isNoun(nounB)) {
            throw new IllegalArgumentException();
        }

        Queue<Integer> a = hypernym.get(nounA);
        Queue<Integer> b = hypernym.get(nounB);

        return this.sap.length(a, b);
    }

    /*****************************************************************
     * a synset (second field of synsets.txt) that is the common
     * ancestor of nounA and nounB
     *****************************************************************/
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) && isNoun(nounB)) {
            throw new IllegalArgumentException();
        }

        Queue<Integer> a = hypernym.get(nounA);
        Queue<Integer> b = hypernym.get(nounB);

        return synsets.get(sap.ancestor(a, b));
    }

    /*****************************************************************
     * Test client
     *****************************************************************/
    public static void main(String[] args) {

        // Change path to your local path
        WordNet wordNet = new WordNet("./wordnet_input/synsets6.txt", "./wordnet_input/hypernyms6TwoAncestors.txt");
        //WordNet wordNet = new WordNet(args[0], args[1]);

        System.out.println("Type in two nouns");

        while (!StdIn.isEmpty()) {

            String v = StdIn.readLine();
            String w = StdIn.readLine();

            if (!wordNet.isNoun(v)) {
                StdOut.println(v + " is not in the WordNet");
                continue;
            }

            if (!wordNet.isNoun(w)) {
                StdOut.println(w + " is not in the WordNet");
                continue;
            }

            int dist = wordNet.distance(v, w);
            String ancestor = wordNet.sap(v, w);
            StdOut.printf("Distance = %d, ancestor = %s\n", dist, ancestor);
        }
    }
}

