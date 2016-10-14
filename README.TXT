/**********************************************************************
 *  readme.txt template                                                   
 *  WordNet
**********************************************************************/

Name:    Raquelita Ros Aguilar 
Login:   raquelita15@ru.is
Hópur:	 HMV

Partner name: 	Grímur Garpsson   
Partner login:  grimur14@ru.is
Hópur: 	1


/**********************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 **********************************************************************/

A HashMap was used to store all data with key of integers
and a key of strings.

    private SeparateChainingHashST<Integer, String> synsets;

To store the data the choice was between hashtable and symbol table
and the reason a SeparateChainingHashST was used was mainly for the
reason that hash table is more like a dictionary that stores
key-value pairs which was perfect for storing the data for this project.

/**********************************************************************
 *  Describe concisely the data structure(s) you used to store the
 *  information in hypernyms.txt. Why did you make this choice?
 **********************************************************************/

A HashMap was used to store all data with key of integers
and a bag of integers.

    private SeparateChainingHashST<String, Queue<Integer>> hypernym;


To store the data the choice was between hashtable and symbol table
and the reason a SeparateChainingHashST was used was mainly for the
reason that hash table is more like a dictionary that stores
key-value pairs which was perfect for storing the data for this project.

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
length(int v, int w)			        3			        3N^2

ancestor(int v, int w)			        3			        3N^2

length(Iterable<Integer> v,		        3			        3N^2
       Iterable<Integer> w)

ancestor(Iterable<Integer> v,		    3			        3N^2
         Iterable<Integer> w)




/**********************************************************************
 *  If you implemented an extra credit optimization describe it here.
 **********************************************************************/


/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/


WordNet.java
We were having a great difficulty on optimizing the WordNet class for part C)
in Mooshak. We got the class accepted in part A) and B) but part C) was another issue.
After some research and discussing this issue with fellow students it was decided
to do some major changes in the class such as replacing some datastructure
from HashMap to SeparateChainingHashST, use Queue instead of Bag to name a few.
It turned out to be a good decision since the methods in SeparateChainingHashST
were better for this logic. It took around 10-12 Mooshak submissions to get the
class accepted but it was always failing in the distance() and sap(). The reason
was that we were checking the invalid inputs wrong. Instead of using && we were using
|| which only checked if one noun was a WordNet noun instead of both. After refactoring
the whole code a few times we finally got it to work correctly.


Outcast.java
Couldn't get outcast to function, took the entire word tree instead of just the nouns,
we could not find a way to split the strings to get the nouns we wanted.
There for it was decides to start from scratch and try to implement the whole
class all over.

/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and recitation classes, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/

I discussed this project with a friend Mr. Carbrera who helped
me to understand the logic of this algorithm. I also discussed some
ideas about WordNet with a fellow student regarding optimizing the class.
He helped me understand the possibilities on how to use SeparateChainingHashST
and Queue instead of Bag as a data structure.

/**********************************************************************
 *  Describe any serious problems you encountered.
 **********************************************************************/

Due to miscommunication it was attempted to implement Outcast before Word
Net was complete. That wasted a lot of time.

/**********************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **********************************************************************/

Grímur did the first implementation for the Outcast class and helped
fill out the README.

I did most of the other work on this project.


/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/