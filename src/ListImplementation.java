public class ListImplementation<T> implements List<T> {
	private NodeImplementation<T> start;
	private int size;

	private Node<T> getNode(int index) {
		try {
			Node<T> tmp = start;
			for (int i = 0; i < index; i++)
				tmp = tmp.getNextNode();
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Get the object inside the chosen node
	@Override
	public T get(int index) {
		try {
			return this.getNode(index).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void set(int index, T t) {
		try {
			this.getNode(index).set(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(int index) {
		try {
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException();
			} else if (index == 0) {
				start = (NodeImplementation<T>) start.getNextNode();
			} else {
				NodeImplementation<T> a = (NodeImplementation<T>) this.getNode(index - 1);
				NodeImplementation<T> b = (NodeImplementation<T>) this.getNode(index);
				a.setNextNode(b.getNextNode(), true);
			}
			this.size--;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void add(T t) {
		if (start == null) {
			start = new NodeImplementation<T>();
			start.set(t);
		} else {
			NodeImplementation<T> tmpNode = new NodeImplementation<T>();
			tmpNode.set(t);
			start.setNextNode(tmpNode, false);
		}
		this.size++;
	}

}
