


import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kaan Suner 
 * @version 1.8.0_111
 */
public class Path {
    /**
     * root node
     */
    static Node root;
    /**
     * arraylist that we keep the values of nodes from reading user input file
     */
    
    static ArrayList<Integer> numbers = new ArrayList<>();
    
    /**
     * arraylist that we keep the node positions
     */
    static ArrayList<Integer> nodepositions = new ArrayList<>(); 
    
    public static void main(String[] args) throws IOException {
   
    String inputPath=args[0];
    readFile(inputPath);
    FileWriter outputPath= new FileWriter(args[1]);
    String stringsum=args[2];
    int sum = Integer.parseInt(stringsum);
    PrintWriter pw = new PrintWriter(outputPath);
    Path tree = new Path(); 
    tree.root = tree.insertLevelOrder(numbers, tree.root, 0); 
  
     printPaths(root,0, sum,0,nodepositions,numbers,pw);
     pw.close();
     }
    
    /**
     * reads the user input file and store values int integer type in arraylist.
     * @param filename is given file path by user input file
     */
    public static void readFile(String filename){
            try {

            File f = new File(filename);
            Scanner s =new Scanner(f);

            while(s.hasNext()){
                
            String nextLine = s.next();
            String[] distinct = nextLine.split(" ");
            String single= distinct[0];
            if(single.equals("-"))
                single="0";
            int intnumbers = Integer.parseInt(single);
              numbers.add(intnumbers);
                
            }
            
            s.close();
            
            
         
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
        }
         
}
    /**
     * Node class
     */
    static class Node { 
        int data; 
        Node left, right; 
        Node(int data) 
        { 
            this.data = data; 
            this.left = null; 
            this.right = null; 
        } 
    } 
    /**
     * creates a binary tree using levelorder according to the values we get based on Arraylist.
     * @param list list that we keep values of nodes is given by user file
     * @param root root parameter is changing recursively. In base case, first root of tree
     * @param i integer parameter getting values from arraylist and setting the values to new node to create tree
     * @return root
     */
    public Node insertLevelOrder(ArrayList<Integer> list, Node root, int i) 
    { 
        // Base case for recursion 
        if (i < list.size()) { 
            if(list.get(i)!=0){
            Node temp = new Node(list.get(i)); 
            root = temp; 
  
            // insert left child 
            root.left = insertLevelOrder(list, root.left, 2 * i + 1); 
  
            // insert right child 
            root.right = insertLevelOrder(list, root.right, 2 * i + 2); 
            }else{ 
            Node temp = null;
            
            root = temp; 
 
            }
        } 
        return root; 
    } 
    

    /**
     * prints the path which is equal to given sum value
     * @param curr_node current node
     * @param index node index in tree based on array
     * @param sum given sum value by user input
     * @param sum_so_far total value up to the current node
     * @param position Arraylist where we keep node positions
     * @param list Arraylist that we keep the values of the nodes.    
     * @param p "PrintWriter" parameter that we use to print files
     */
    
    static void printPaths(Node currentNode,int index, int sum, int sum_so_far,ArrayList<Integer> position, ArrayList<Integer> list,PrintWriter p){ 
    
    if(index<list.size()){
    if (currentNode == null) 
        return; 
          
    // Add the current node's value 
    sum_so_far += currentNode.data; 
  
    // Add the value to path 
    position.add(index); 
  
    // Print the required path 
    if (sum_so_far == sum)  
    { 
        for(int i = 0; i < position.size()-1; i++) 
            p.print("T["+position.get(i)+"]"+"+"); 
        p.println("T["+position.get(position.size()-1)+"]"+"="+sum);      
    } 
  
    // If left child exists 
    if (currentNode.left != null){ 
        printPaths(currentNode.left,2*index+1, sum, sum_so_far, position, list,p); 
    }
  
    // If right child exists 
    if (currentNode.right != null){ 
        printPaths(currentNode.right,2*index+2, sum, sum_so_far, position, list,p); 
    }
  
    // Remove last element from path 
    // and move back to parent 
    position.remove(position.size() - 1); 
} 
    return;
}
    
}
