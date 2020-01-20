package ggabelmann.pc.core;

/**
 * An input that can be parsed by a ParserCombinator.
 */
public interface Input<I> {
	
	/**
	 * @return The first part of this Input.
	 */
	I get();
	
	/**
	 * @return true if this Input is empty.
	 */
	boolean isEmpty();
	
	/**
	 * Retrieves a sub-Input that may share this Input's underlying data,
	 * which can improve efficiencies by avoiding copying data.
	 *
	 * @param index The relative start of the sub-Input.
	 * @return The sub-Input.
	 */
	Input<I> subInput(int index);
}
