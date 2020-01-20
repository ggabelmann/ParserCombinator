package ggabelmann.pc;

import ggabelmann.pc.node.EmptyNode;
import ggabelmann.pc.core.Input;
import ggabelmann.pc.core.ParserCombinator;
import ggabelmann.pc.core.Result;

/**
 * Returns a successful result if it's given an empty Input.
 *
 * @param <I> The type of the Input.
 * @param <R> The type of the Result.
 */
public class EndParserCombinator<I, R> implements ParserCombinator<I, R> {
	
	@Override
	public Result<R> parse(final Input<I> input) {
		if (input.isEmpty()) {
			return new Result<>(new EmptyNode<>(), 0);
		}
		else {
			return new Result<>();
		}
	}
	
}
