import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Concordance {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		boolean fileFound = false;
		String fileName = null;
		ListImplementation<Occurrence> mylist = new ListImplementation<>(); // create linkedlist

		// checking if your file is exists
		while (fileFound == false) {
			System.out.println("Enter the name of the file (name.txt):");
			fileName = s.nextLine().trim();
			if (new File(fileName).exists()) {
				fileFound = true;
			} else {
				System.out.println("File does not exist.");
			}
		}
		s.close();

		try {
			FileReader fReader = new FileReader(fileName);
			Scanner scn = new Scanner(fReader);
			PrintWriter out = new PrintWriter("ConcordanceFile.txt");
			String currentWord = "";
			int currentLine = 1;

			while (scn.hasNextLine()) {
				String[] wordsInLine = scn.nextLine().split(" "); // current line from file as a string
				for (int i = 0; i < wordsInLine.length; i++) {
					currentWord = wordsInLine[i].toLowerCase().replaceAll("[-+.^:,;]", "");
					if (currentWord.isEmpty())
						continue;
					Occurrence occ = new Occurrence(currentWord); // a occurrence to put in the list
					occ.getRowNumbers().add(currentLine); // add the current line in occurrence
					int index = wordExist(mylist, currentWord); // check if word already in list, -1 the word is new
					if (index == -1) {
						mylist.add(occ); // add occurrence to list
					} else { // word is duplicated
						if (!rowExist(mylist.get(index).getRowNumbers(), currentLine))
							mylist.get(index).getRowNumbers().add(currentLine); // add the row/line to list
					}
				}
				currentLine++;
			}
			bubbleSort(mylist);
			saveLinklistsToFile(out, mylist);
			scn.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static int wordExist(ListImplementation<Occurrence> aList, String str) {
		for (int i = 0; i < aList.size(); i++) {
			if (aList.get(i).getWord().equals(str))
				return i;
		}
		return -1;
	}

	public static boolean rowExist(ListImplementation<Integer> aList, int row) {
		for (int i = 0; i < aList.size(); i++) {
			if (aList.get(i) == row)
				return true;
		}
		return false;
	}

	public static void bubbleSort(ListImplementation<Occurrence> mylist) {
		for (int j = 0; j < mylist.size(); j++) {
			for (int i = j + 1; i < mylist.size(); i++) {
				if (mylist.get(i).getWord().compareTo(mylist.get(j).getWord()) < 0) {
					Occurrence tempOcc = mylist.get(j);
					mylist.set(j, mylist.get(i));
					mylist.set(i, tempOcc);
				}
			}
		}
	}

	public static void saveLinklistsToFile(PrintWriter out, ListImplementation<Occurrence> mylist) {
		for (int i = 0; i < mylist.size(); i++) {
			out.write(mylist.get(i).getWord().toString() + " ");
			for (int j = 0; j < mylist.get(i).getRowNumbers().size(); j++) {
				out.write(mylist.get(i).getRowNumbers().get(j) + " ");
			}
			out.write("\n");
		}
		out.close();
	}
}