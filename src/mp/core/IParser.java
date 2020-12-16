package mp.core;

@FunctionalInterface
public interface IParser<T> {
	T parse(String expression);
}
