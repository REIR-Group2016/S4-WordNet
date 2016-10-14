package s4.src;

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

        String outcast = null;
        int totalDistance = 0;

        for(String nounA : nouns){
            int outcastDistance = 0;
            for (String nounB : nouns){
                outcastDistance += wordNet.distance(nounA, nounB);
            }
            if (outcastDistance > totalDistance){
                totalDistance = outcastDistance;
                outcast = nounA;
            }

        }
        return outcast;
    }

    /*public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]); Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            String[] nouns = In.readStrings(args[t]);
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }*/
}
