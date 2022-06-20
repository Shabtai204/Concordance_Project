public class NodeImplementation<T> implements Node<T> {
	private T t;
	private Node<T> nextNode;

	@Override
	public T get() {
		return this.t;
	}

	@Override
	public void set(T t) {
		this.t = t;
	}

	@Override
	public Node<T> getNextNode() {
		return this.nextNode;
	}

	@Override
	public void setNextNode(Node<T> node, boolean override) {
		if (this.nextNode == null || override) {
			this.nextNode = node;
		} else {
			this.getNextNode().setNextNode(node, false);
		}
	}
}
