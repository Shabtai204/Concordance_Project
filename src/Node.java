public interface Node<T> {
	public T get();

	public void set(T t);

	public Node<T> getNextNode();

	public void setNextNode(Node<T> node, boolean override);
}
