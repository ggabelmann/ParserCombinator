package ggabelmann.pc;

import ggabelmann.pc.node.CompositeNode;
import ggabelmann.pc.core.Input;
import ggabelmann.pc.core.ParserCombinator;
import ggabelmann.pc.core.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Tries to parse Inputs by trying one ParserCombinator after another, such that all succeed.
 *
 * @param <I> The type of the Input.
 * @param <R> The type of the Result.
 */
public class SequenceParserCombinator<I, R> implements ParserCombinator<I, R> {
	
	private final ParserCombinator<I, R>[] pcs;
	
	public SequenceParserCombinator(final ParserCombinator<I, R>... pcs) {
		this.pcs = pcs;
	}
	
	@Override
	public Result<R> parse(final Input<I> input) {
		final List<Result<R>> pcrs = new ArrayList<>(pcs.length);
		
		for (int i = 0; i < pcs.length; i++) {
			int cumulativePos = 0;
			for (int j = 0; j < i; j++) {
				cumulativePos += pcrs.get(j).getPos();
			}
			pcrs.add(pcs[i].parse(input.subInput(cumulativePos)));
			
			if (pcrs.get(i).type == Result.Type.FAILURE) {
				return new Result<>();
			}
		}
		
		int cumulativePos = 0;
		final CompositeNode<R> node = new CompositeNode<>();
		for (int i = 0; i < pcrs.size(); i++) {
			cumulativePos += pcrs.get(i).getPos();
			node.addChild(pcrs.get(i).getResult());
		}
		
		return new Result<>(node, cumulativePos);
	}
	
}
