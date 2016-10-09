package s4;

import edu.princeton.cs.algs4.*;
import java.util.*;

/**
 * Created by raquelitarosaguilar on 03/10/2016.
 */
public class Outcast {

    // constructor takes a WordNet object
    public Outcast(WordNet wordNet){
        return;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns){

    }

    /** The following test client takes from the command line the name of a synset file,
     * the name of a hypernym file, followed by the names of outcast files, and prints out an outcast in each file
     * This test client was recieved from http://coursera.cs.princeton.edu/algs4/assignments/wordnet.html
     */

    public static void main(String[] args){
        WordNet wordNet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordNet);

        for(int t = 2; t < args.length; t++){
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            System.out.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
