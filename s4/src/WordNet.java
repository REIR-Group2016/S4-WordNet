package s4.src;

import edu.princeton.cs.algs4.*;

import java.util.*;

/**
 * Created by raquelitarosaguilar on 03/10/2016.
 */
public class WordNet {

    private SAP sap;
    private Object[] mSortedList;
    private HashMap<Integer, String> synsets;
    private HashMap<Integer, Bag<Integer>> hypernym;


    /*****************************************************************
     * constructor takes the name of the two input files
     *****************************************************************/
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        this.synsets = new HashMap<Integer, String>();
        this.hypernym = new HashMap<Integer, Bag<Integer>>();
        synsetsInput(synsets);
        sap = new SAP(hypernymsInput(hypernyms));
        mSortedList = this.synsets.entrySet().toArray();
        Arrays.sort(mSortedList, 0, mSortedList.length, new Comparator<Object>() {
            @Override
            @SuppressWarnings("unchecked")
            public int compare(Object o1, Object o2) {
                Map.Entry<Integer, String> l = (Map.Entry<Integer, String>) o1;
                Map.Entry<Integer, String> r = (Map.Entry<Integer, String>) o2;
                return l.getValue().compareTo(r.getValue());
            }
        });
    }

    /*****************************************************************
     * A helper method that reads a synsets file from standard input and
     * and checks if it is valid CSV
     *****************************************************************/
    private void synsetsInput(String synset) {

        // read from standard input
        In filename = new In(synset);

        while (!filename.isEmpty()) {
            String[] lines = filename.readLine().split(",");
            synsets.put(Integer.parseInt(lines[0]), lines[1]);
        }
    }

    /*****************************************************************
     * A helper method that reads a hypernyms file from standard input
     *****************************************************************/
    private Digraph hypernymsInput(String hypernyms) {

        Digraph digraph = new Digraph(synsets.size());

        // read from standard input
        In filename = new In(hypernyms);

        while (!filename.isEmpty()) {
            String[] lines = filename.readLine().split(",");
            int synsetID = Integer.parseInt(lines[0]);
            for (int i = 1; i < lines.length; i++) {
                int id = Integer.valueOf(lines[i]);
                digraph.addEdge(synsetID, id);

                Bag<Integer> bagOfHypernyms;
                if (hypernym.containsKey(synsetID)) {
                    bagOfHypernyms = hypernym.get(synsetID);
                } else {
                    bagOfHypernyms = new Bag<Integer>();
                }
                bagOfHypernyms.add(id);
                hypernym.put(synsetID, bagOfHypernyms);
            }
        }

        isRootedOrCycle(digraph);
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
        return synsets.values();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return synsets.containsValue(word);
    }

    /*****************************************************************
     * Finds the distance between nouns
     * distance between nounA and nounB (defined below)
     *****************************************************************/
    @SuppressWarnings("unchecked")
    public int distance(String nounA, String nounB) {
        // Lets throw an exception if worda are not a noun
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("Invalid nouns");

        /*int keya = -1;
        int keyb = -1;
        for (Map.Entry<Integer, String> entry : synsets.entrySet()) {
            if (entry.getValue().compareTo(nounA) == 0) {
                keya = entry.getKey();
            }
            if (entry.getValue().compareTo(nounB) == 0) {
                keyb = entry.getKey();
            }
        }*/

        Comparator<Object> comparator = new Comparator<Object>() {
            @Override
            @SuppressWarnings("unchecked")
            public int compare(Object o1, Object o2) {
                Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) o1;
                String noun = (String) o2;
                return entry.getValue().compareTo(noun);
            }
        };
        int keya = Arrays.binarySearch(mSortedList, nounA, comparator);
        int keyb = Arrays.binarySearch(mSortedList, nounB, comparator);

        keya = ((Map.Entry<Integer, String>) mSortedList[keya]).getKey();
        keyb = ((Map.Entry<Integer, String>) mSortedList[keyb]).getKey();

        return sap.length(keya, keyb);
    }

    /*****************************************************************
     * a synset (second field of synsets.txt) that is the common
     * ancestor of nounA and nounB
     *****************************************************************/
    @SuppressWarnings("unchecked")
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("Invalid nouns");

        /*int keya = -1;
        int keyb = -1;
        for (Map.Entry<Integer, String> entry : synsets.entrySet()) {
            if (entry.getValue().compareTo(nounA) == 0) {
                keya = entry.getKey();
            }
            if (entry.getValue().compareTo(nounB) == 0) {
                keyb = entry.getKey();
            }
        }*/

        Comparator<Object> comparator = new Comparator<Object>() {
            @Override
            @SuppressWarnings("unchecked")
            public int compare(Object o1, Object o2) {
                Map.Entry<Integer, String> entry = (Map.Entry<Integer, String>) o1;
                String noun = (String) o2;
                return entry.getValue().compareTo(noun);
            }
        };
        int keya = Arrays.binarySearch(mSortedList, nounA, comparator);
        int keyb = Arrays.binarySearch(mSortedList, nounB, comparator);

        keya = ((Map.Entry<Integer, String>) mSortedList[keya]).getKey();
        keyb = ((Map.Entry<Integer, String>) mSortedList[keyb]).getKey();

        return synsets.get(sap.ancestor(keya, keyb));
    }

    /*****************************************************************
     * Test client
     *****************************************************************/
    public static void main(String[] args) {

        // Change path to your local path
        WordNet wordNet = new WordNet("./wordnet_input/synsets.txt", "./wordnet_input/hypernyms100K.txt");
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

