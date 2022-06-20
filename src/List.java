public interface List<T> {

	public T get(int index);

	public void set(int index, T t);

	public void remove(int index);

	public int size();

	public void add(T t);
}
