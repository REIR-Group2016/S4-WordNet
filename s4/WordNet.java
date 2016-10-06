package s4;

import edu.princeton.cs.algs4.*;
import java.util.*;

/**
 * Created by raquelitarosaguilar on 03/10/2016.
 */
public class WordNet {

    private boolean noun;			   // True if a noun, else false
	private boolean[] marked;          // marked[v] = has v been marked in dfs?
    private int[] pre;                 // pre[v]    = preorder  number of v
    private int[] post;                // post[v]   = postorder number of v
    private int[] distTo;      		   // distTo[v] = length of shortest s->v path
	
	// constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms){

    }

    // return all WordNet nouns
    public Iterable<String> nouns();

    // is the word a WordNet nound?
    public boolean isNoun(String word){
        // TODO: I put in the noun boolean value for this but I am now not sure how to use it.
    	
    	return ;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB){
        return ;
    }

    // a synset (second field of synsets.txt) that is the common
    // ancestor of nounA and nounB
    public String sap(String nounA, String nounB){
        return :
    }

    /**
     * Test client
     * Do a unit testing of this class
     */
    public static void main(String[] args){

    }



}
