package ggabelmann.pc.core;

import java.util.List;

/**
 * The result of a parse of an Input.
 * Either SUCCESS or FAILURE.
 * If the parse was successful then it has a position and may contain a tree of values.
 */
public class Result<R> {
	
	public enum Type {
		SUCCESS,
		FAILURE
	}
	
	public final Type type;
	
	/**
	 * A tree of values.
	 */
	public interface Node<R> {
		List<Node<R>> getChildren();
		R getVal();
	}
	
	private final int pos;
	
	private final Node<R> result;
	
	/**
	 * Constructor for failed parses.
	 */
	public Result() {
		this.type = Type.FAILURE;
		this.result = null;
		this.pos = 0;
	}
	
	/**
	 * Constructor for successful parses.
	 */
	public Result(final Node<R> result, final int pos) {
		this.type = Type.SUCCESS;
		this.result = result;
		this.pos = pos;
	}
	
	/**
	 * @return If the type is FAILURE then this is 0.
	 */
	public int getPos() {
		return pos;
	}
	
	/**
	 * @return If the type is FAILURE then this is null.
	 */
	public Node<R> getResult() {
		return result;
	}
	
}
