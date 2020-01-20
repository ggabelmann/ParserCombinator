package ggabelmann.pc;

import ggabelmann.pc.core.Input;
import ggabelmann.pc.node.LeafNode;
import ggabelmann.pc.core.ParserCombinator;
import ggabelmann.pc.core.Result;

/**
 * Tries to parse Inputs by matching the first parts of them.
 *
 * @param <I> The type of the Input and Result.
 */
public class EqualsParserCombinator<I> implements ParserCombinator<I, I> {
	
	private final I expected;
	
	public EqualsParserCombinator(final I expected) {
		this.expected = expected;
	}
	
	@Override
	public Result<I> parse(final Input<I> input) {
		
		if (input.isEmpty() == false && expected.equals(input.get())) {
			return new Result<>(new LeafNode<>(expected), 1);
		}
		else {
			return new Result<>();
		}
	}
	
}
