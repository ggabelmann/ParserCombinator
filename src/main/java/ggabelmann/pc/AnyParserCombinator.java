package ggabelmann.pc;

import ggabelmann.pc.core.Input;
import ggabelmann.pc.core.ParserCombinator;
import ggabelmann.pc.core.Result;

/**
 * Tries to parse Inputs by trying one ParserCombinator after another until one succeeds.
 *
 * @param <I> The type of the Input.
 * @param <R> The type of the Result.
 */
public class AnyParserCombinator<I, R> implements ParserCombinator<I, R> {
	
	private final ParserCombinator<I, R>[] pcs;
	
	public AnyParserCombinator(final ParserCombinator<I, R>... pcs) {
		this.pcs = pcs;
	}
	
	@Override
	public Result<R> parse(final Input<I> input) {
		for (final ParserCombinator<I, R> pc : pcs) {
			final Result<R> result = pc.parse(input);
			
			if (result.type == Result.Type.SUCCESS) {
				return result;
			}
		}
		
		return new Result<>();
	}
	
}
