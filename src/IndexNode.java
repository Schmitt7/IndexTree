import java.util.ArrayList;
import java.util.List;

public class IndexNode  {

	// The word for this entry
	String word;
	// The number of occurrences for this word
	int occurences;
	// A list of line numbers for this word.
	List<Integer> list;



	IndexNode left;
	IndexNode right;




	// Constructors
	// Constructor should take in a word and a line number
	// it should initialize the list and set occurrences to 1

	public IndexNode(String word, int lineNumber) {

		this.list = new ArrayList<>();
		occurences = 1;
		this.word = word;
		list.add(lineNumber);

	}




	// Complete This
	// return the word, the number of occurrences, and the lines it appears on.
	// string must be one line

	public String toString(){

		String output = "";

		output += "Word: " + this.word + "Occurrences: " + occurences + "Line Number: " + list;

		return output;
	}

	private String toString(IndexNode root){

		if(root == null){
			return "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append(toString(root.left));
		builder.append(" ");
		builder.append(root.word);
		builder.append(" ");
		builder.append(toString(root.right));

		return builder.toString();
	}



}

