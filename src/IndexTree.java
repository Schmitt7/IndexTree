import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

// Your class. Notice how it has no generics.
// This is because we use generics when we have no idea what kind of data we are getting
// Here we know we are getting two pieces of data:  a string and a line number
public class IndexTree {

	// This is your root
	// again, your root does not use generics because you know your nodes
	// hold strings, an int, and a list of integers
	private IndexNode root;
	private int size;


	// Make your constructor
	// It doesn't need to do anything
	public IndexTree(){
		this.root = null;
		this.size = 0;
	}

	public int size(){
		return size;
	}


	// complete the methods below

	// this is your wrapper method
	// it takes in two pieces of data rather than one
	// call your recursive add method
	public void add(String word, int lineNumber){

		this.root = add(this.root, word, lineNumber);
		size++;
	}


	// your recursive method for add
	// Think about how this is slightly different then the regular add method
	// When you add the word to the index, if it already exists,
	// you want to  add it to the IndexNode that already exists
	// otherwise make a new indexNode
	private IndexNode add(IndexNode root, String word, int lineNumber){

		if(root == null){
			return new IndexNode(word, lineNumber);
		}

		//Compares root to word and if it is the same then returns root
		int compare = word.compareTo(root.word);
		if(compare == 0){
			return new IndexNode(word, lineNumber);
		}

		//Compares root to word and if it is less then root it creates a new node
		//to the left of the main root
		else if(compare < 0){
			root.left = add(root.left, word, lineNumber);
			return root;
		}

		//If word is not equal or less then the root, creates a new node to right
		//of main root
		else{
			root.right = add(root.right, word, lineNumber);
			return root;
		}

	}




	// returns true if the word is in the index
	public boolean contains(String word){



		return contains(root, word);
	}

	private boolean contains(IndexNode root, String word){

		if(root == null){
			return false;
		}
		int comparison = word.compareTo(root.word);

		if(comparison == 0){
			return true;
		}

		else if(comparison < 0){
			return contains(root.left, word);
		}

		else{
			return contains(root.right, word);
		}
	}

	// call your recursive method
	// use book as guide
	public void delete(String word){

		this.root = this.delete(this.root, word);
		size--;
	}

	// your recursive case
	// remove the word and all the entries for the word
	// This should be no different then the regular technique.
	private IndexNode delete(IndexNode root, String word){

		//If root is empty then return null
		if(root == null){
			return null;
		}
		int comparison = word.compareTo(root.word);

		//If word is less than the root, remove the left leaf
		if(comparison < 0){
			root.left = delete(root.left, word);
			return root;
		}
		//If word is greater than the root, remove right leaf
		else if(comparison > 0){
			root.right = delete(root.right, word);
			return root;
		}
		//Checks if node has any children
		else{

			//If node has no children then it deletes the node
			if(root.left == null && root.right == null){
				return null;
			}

			//If node has child on the LEFT then has its parent node adopt its child
			//Doing this changes the pointers and has java garbage collector delete the node
			else if(root.left != null && root.right == null){
				return root.left;
			}

			//If node has child on the RIGHT then has its parent node adopt its child
			//Doing this changes the pointers and has java garbage collector delete the node
			else if(root.left == null && root.right != null){
				return root.right;
			}

			else{
				IndexNode current = root.left;
				while(current.right != null){
					current = current.right;
				}
				root.word = current.word;
				root.left = delete(root.left, root.word);
				return root;
			}
		}
	}


	// prints all the words in the index in inorder order
	// To successfully print it out
	// this should print out each word followed by the number of occurrences and the list of all occurrences
	// each word and its data gets its own line
	public static void printIndex(IndexNode root){

		if(root == null){
			return;
		}
		printIndex(root.left);
		System.out.println(root);
		printIndex(root.left);

	}

	public static void main(String[] args){

		IndexTree index = new IndexTree();
		int counter = 0;
		String filename = "pg100.txt";

		try{
			Scanner scan = new Scanner(new File("pg100.txt"));

			while(scan.hasNextLine()){
				counter++;
				String line = scan.nextLine();
				line = line.replaceAll(",", "");
				String[] words = line.split("\\s+");
				System.out.println(line);
				for(String word : words){
					word = word.replaceAll(":", "");
					word = word.replaceAll(",", "");

					index.add(word, counter);
					System.out.println(word);

				}
			}
			scan.close();
		}
		catch(FileNotFoundException e1){
			e1.printStackTrace();
		}


		// add all the words to the tree


		// print out the index
		printIndex(index.root);

		// test removing a word from the index
		index.delete("threaten");



	}
}
