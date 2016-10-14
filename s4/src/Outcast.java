package s4.src;

import edu.princeton.cs.algs4.*;

/**
 * Created by raquelitarosaguilar on 03/10/2016.
 */
public class Outcast {

    private WordNet wordNet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordNet) {
        this.wordNet = wordNet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        
    	String[] lines = new String[nouns.length];
    	
    	for(int i = 0; i < nouns.length; i++)
    	{
    		//lines[i] = nouns[i].split(",");
    		// Type mismatch, cannot convert
    		// Cannot Split and cannot quite figure out what I need to do to get it done!
    		// If finished this would make outcast properly remove the first number so it could be
    		// Considered a noun, though another split for char will also be needed for the input
    		// We are using.
    	}
    	
    	
    	String outcast = null;
        int totalDistance = 0; //Save value for adding up all the distances
        int outcastDistance = 0; //Distance of current outcast

        for (int i = 0; i < nouns.length; i++) {
            // Check distance to every point from nouns[i] and add it all together?
            // Compare that to outcast and if greater, make outcast be that string.

            if (outcast == null) {
                // a first case just to make it not null and enable comparisons
                outcast = nouns[0];
            }
            for (int k = 0; k < nouns.length; k++) {
                totalDistance += wordNet.distance(lines[i], lines[k]);
            }
            if (outcastDistance > totalDistance) {
                outcastDistance = totalDistance;
                outcast = nouns[i];
            }
            totalDistance = 0; // reset to 0.
        }
        return outcast;
    }

    /**
     * The following test client takes from the command line the name of a synset file,
     * the name of a hypernym file, followed by the names of outcast files, and prints out an outcast in each file
     */

    public static void main(String[] args) {
        WordNet wordNet = new WordNet("D:/Verkefni/Reiknirit/src/s4/src/wordnet_input/synsets15.txt", "D:/Verkefni/Reiknirit/src/s4/src/wordnet_input/hypernyms8ManyAncestors.txt");
        Outcast outcast = new Outcast(wordNet);

        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            System.out.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
