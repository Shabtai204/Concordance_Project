public class Occurrence {

	private String word;
	private ListImplementation<Integer> rowNumbers;

	public Occurrence(String word) {
		this.word = word;
		rowNumbers = new ListImplementation<>();
	}

	public String getWord() {
		return word;
	}

	public ListImplementation<Integer> getRowNumbers() {
		return rowNumbers;
	}
}
