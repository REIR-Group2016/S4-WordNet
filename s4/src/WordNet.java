package s4.src;

import edu.princeton.cs.algs4.*;
import java.util.*;

/**
 * Created by raquelitarosaguilar on 03/10/2016.
 */
public class WordNet {

    private SAP sap;
    private HashMap<Integer, String> synsID;
    private HashMap<String, Bag<Integer>> nounID;


    /*****************************************************************
     * constructor takes the name of the two input files
     *****************************************************************/
    public WordNet(String synsets, String hypernyms){

        this.synsID = new HashMap<>();
        this.nounID = new HashMap<>();
        synsetsInput(synsets);
        hypernymsInput(hypernyms);
    }

    /*****************************************************************
     * A helper method that reads a synsets file from standard input and
     * and checks if it is valid CSV
     *****************************************************************/
    private void synsetsInput(String synset){

        // read from standard input
        In filename = new In(synset);
        int iter = 0;

        // while the file contain some lines
        while (!filename.isEmpty()){
            String[] lines = filename.readLine().split(",");

            for (String string  : lines[1].split(" ")){
                Bag<Integer> words = new Bag<>();

                if(nounID.containsKey(string)){
                    words = nounID.get(string);
                }
                words.add(Integer.parseInt(lines[0]));
                nounID.put(string, words);
            }

//            for(String noun : nouns){
//                if(nounID.get(noun) == null){
//                    nounID.put(noun, new Bag<Integer>());
//                }
//                nounID.get(noun).add(synsetID);
//            }
        }
        iter++;
    }

    /*****************************************************************
     * A helper method that reads a hypernyms file from standard input
     *****************************************************************/
    private Digraph hypernymsInput(String hypernyms){
        Digraph digraph = new Digraph(synsID.size());

        // read from standard input
        In filename = new In(hypernyms);

        while (!filename.isEmpty()){
            String[] lines = filename.readLine().split(",");
            int synsetID = Integer.parseInt(lines[0]);
            for(int i = 1; i < lines.length; i++){
                int id = Integer.valueOf(lines[i]);
                digraph.addEdge(synsetID, id);
            }
        }

        isCycle(digraph);
        return digraph;
    }

    /*****************************************************************
     * A helper method that checks if a graph has a directed
     * cycle or not
     ****************************************************************
     * @param digraph*/
    private void isCycle(Digraph digraph){
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        if (directedCycle.hasCycle()){
            throw new IllegalArgumentException("This graph has a cycle in it!");
        }
    }

    /*****************************************************************
     * Returns all the nouns in the net
     *****************************************************************/
    // return all WordNet nouns
    public Iterable<String> nouns() {
        // keySet() returns the a view of the keys contained in this map
        return nounID.keySet();
    }

    // is the word a WordNet nound?
    public boolean isNoun(String word){
    	return nounID.containsKey(word);
    }

    /*****************************************************************
     * Finds the distance between nouns
     * distance between nounA and nounB (defined below)
     *****************************************************************/
    public int distance(String nounA, String nounB){
        // Lets throw an exception if word is not a noun
        if(isNoun(nounA) || isNoun(nounB)) {
            return sap.length(nounID.get(nounA), nounID.get(nounB));
        }else{
            throw new IllegalArgumentException("Invalid: both words must be noun");
        }
    }

    /*****************************************************************
     * a synset (second field of synsets.txt) that is the common
     * ancestor of nounA and nounB
     *****************************************************************/
    public String sap(String nounA, String nounB) {

        // Lets throw an exception if word is not a noun
        if (isNoun(nounA) || isNoun(nounB)) {
            return synsID.get(sap.ancestor(nounID.get(nounA), nounID.get(nounB)));
        }else{
            throw new IllegalArgumentException("Invalid: both words must be noun");
        }
    }

    /*****************************************************************
     * Test client
     *****************************************************************/
    public static void main(String[] args){

        WordNet wordNet = new WordNet("*/synsets.txt", "*/hypernyms.txt");
        //WordNet wordNet = new WordNet(args[0], args[1]);


        while (!StdIn.isEmpty()){
            String v = StdIn.readLine();
            String w = StdIn.readLine();

            if(!wordNet.isNoun(v)){
                StdOut.println(v + " is not in the WordNet");
                continue;
            }

            if(!wordNet.isNoun(w)){
                StdOut.println(w + " is not in the WordNet");
                continue;
            }

            int dist = wordNet.distance(v, w);
            String ancestor = wordNet.sap(v, w);
            StdOut.printf("Distance = %d, ancestor = %s\n", dist, ancestor);
        }

    }

}
