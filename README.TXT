/**********************************************************************
 *  readme.txt template                                                   
 *  WordNet
**********************************************************************/

Name:    Grímur Garpsson
Login:   grimur14@ru.is
Hópur:	1

Partner name: 	   Raquelita Ros Aguilar 
Partner login:    raquelita15@ru.is
Hópur: 	HMV


/**********************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 **********************************************************************/

A HashMap was used to store all data with key of integers
and a key of strings.

    private HashMap<Integer, String> synsets;

To store the data the choice was between hashtable and symble table
and the reason a hashmap was used was mainly for the reason that
hash table is more like a dictionary that stores key-value pairs
which was perfect for storing the data for this project.

/**********************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 **********************************************************************/

A HashMap was used to store all data with key of integers
and a bag of integers.

    private HashMap<Integer, Bag<Integer>> hypernym;


To store the data the choice was between hashtable and symble table
and the reason a hashmap was used was mainly for the reason that
hash table is more like a dictionary that stores key-value pairs
which was perfect for storing the data for this project.

/**********************************************************************
 *  Describe concisely the algorithm you used to check if the digraph 
 *  is rooted and the algorithm you used to check if the digrah is a DAG.  
 *  What is the order of growth of the best case 
 *  running time as a function of the number of vertices V and the 
 *  number of edges E in the digraph? And what is the order of growth 
 *  of the worst case running time?
 *
 *  Be careful! It is very easy to get these wrong. Keep in mind
 *  what the 'best case' and 'worst case' entail. Don't forget about
 *  the fact that starting a breadth first search in Java means 
 *  initializing edgeTo[] arrays, etc.
 **********************************************************************/

Description:

IS_ROOTED
Root was initialized and a if-loop was used to go through the graph and
check the roots. Then if the roots were !=1 a exception was thrown
saying that graph was not rooted.

IS_CYCLE
The obvious choice here was to use DirectedCycle class which has a method called
hascycle() and checks if a graph has a cycle or not. Then it was checked
if the graph had cycle and if so an exception was thrown saying that graph
was not acyclic.


method                               best case            worst case
------------------------------------------------------------------------
rooted check                         constant               V

DAG check                               V                   V+E



/**********************************************************************
 *  Describe concisely your algorithm to compute the shortest ancestral
 *  path in SAP.java? What is the order of growth of the worst-case
 *  running time of your methods as a function of the number of
 *  vertices V and the number of edges E in the digraph? What is the
 *  order of growth of the best-case running time?
 **********************************************************************/

Description:

A helper methods was created to take care of duplicate logic so that the below
methods only had to call those helper methods in relevant places which made
the code cleaner and more understandable.

The following helper methods were created:
 - sapHelper()
   -> which took care of all the computation for shortest path and common ancestor

 - isOutOfBoundaries
   -> which checked if input was inside of valid boundaries, if it was not then
      an exception was thrown. Since it was a requirement that all methods should
      throw IndexOutOfBoundsException it was ideal to create a specific method for
      that purpose.

method                               best case            worst case
------------------------------------------------------------------------
length(int v, int w)			3			3N^2

ancestor(int v, int w)			3			3N^2

length(Iterable<Integer> v,		3			3N^2
       Iterable<Integer> w)

ancestor(Iterable<Integer> v,		3			3N^2
         Iterable<Integer> w)




/**********************************************************************
 *  If you implemented an extra credit optimization describe it here.
 **********************************************************************/


/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/

Couldn't get outcast to function, but couldn't see anthing wrong with its logic.

Throws an error based on checking for nouns in the distance function of Word Net.

/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and recitation classes, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/


Raquelita discussed this project with a friend Mr. Carbrera who helped
her to understand the logic of this algorithm.

/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/

Due to miscommunication it was attempted to implement Outcast before Word Net was complete. This wasted a lot of time.

/**********************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **********************************************************************/

I did our work on the Outcast class, helped fill in the README and submitted the work.

Raquelita did most of the other work on this project.


/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/