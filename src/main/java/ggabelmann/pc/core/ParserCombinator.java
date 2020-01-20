package ggabelmann.pc.core;

import ggabelmann.pc.node.CompositeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * A POC of a parser combinator.
 *
 * This interface contains one method that must be implemented by classes: parse.
 *
 * It also contains many default methods that provide functionality.
 * The default methods could have been left out and implemented as classes.
 * For example, instead of the star() method there could have been a StarParserCombinator class.
 * Its constructor would have taken a single ParserCombinator and modified it match 0 or more times.
 * But, it was written as a default method because it's easy to do so when only a single PC is being "wrapped" and thus modified.
 */
public interface ParserCombinator<I, R> {
	
	/**
	 * Parses the given Input and returns a Result.
	 *
	 * @param input Cannot be null.
	 * @return The result.
	 */
	Result<R> parse(Input<I> input);
	
	/**
	 * Transforms a list of Nodes (which are inside a ParserCombinatorResult) to another list of Nodes.
	 * For example, a list of strings representing algebra to a singleton list containing a double.
	 */
	default <M> ParserCombinator<I, M> map(final Function<Result.Node<R>, Result.Node<M>> mapper) {
		final ParserCombinator<I, R> self = this;
		
		return new ParserCombinator<I, M>() {
			@Override
			public Result<M> parse(final Input<I> input) {
				final Result<R> pcr = self.parse(input);
				
				if (pcr.type == Result.Type.SUCCESS) {
					return new Result<>(mapper.apply(pcr.getResult()), pcr.getPos());
				}
				else {
					return new Result<>();
				}
			}
		};
	}
	
	/**
	 * Replaces a list of Nodes with another list of Nodes, which are all under one of the top-level Nodes.
	 * Supports both positive indexing (0 is left-most) and negative indexing (-1 is right-most).
	 * Often used to discard surrounding whitespace, quotes, etc, from some characters of interest
	 */
	default ParserCombinator<I, R> drill(final int index) {
		return map(node -> {
			if (node.getChildren() == null) {
				throw new IllegalArgumentException();
			}
			
			final int actualIndex;
			if (index >= 0) {
				actualIndex = index;
			}
			else {
				actualIndex = index + node.getChildren().size();
			}
			
			return node.getChildren().get(actualIndex);
		});
	}
	
	/**
	 * Keeps some Nodes.
	 * Often used to discard surrounding whitespace, quotes, etc, from some Nodes of interest.
	 */
	default ParserCombinator<I, R> keep(final boolean[] keep) {
		return map(node -> {
			if (node.getChildren().size() != keep.length) {
				throw new IllegalArgumentException();
			}
			
			final CompositeNode<R> result = new CompositeNode<>();
			for (int i = 0; i < node.getChildren().size(); i++) {
				if (keep[i]) {
					result.addChild(node.getChildren().get(i));
				}
			}
			return result;
		});
	}
	
	/**
	 * Allows some number of matches.
	 *
	 * @param min The minimum number of matches.
	 * @param max The maximum number of matches, inclusive.
	 * @param greedy
	 * 		If true then the ParserCombinator will try to match as much as it can.
	 * 		If false then the ParserCombinator will try to match as little as it can.
	 */
	default ParserCombinator<I, R> minMax(final int min, final int max, final boolean greedy) {
		final ParserCombinator<I, R> self = this;
		
		return new ParserCombinator<I, R>() {
			@Override
			public Result<R> parse(final Input<I> input) {
				final List<Result.Node<R>> results = new ArrayList<>();
				
				int cumulativePos = 0;
				
				while ((greedy && results.size() < max) || (!greedy && results.size() < min)) {
					final Result<R> result = self.parse(input.subInput(cumulativePos));
					
					if (result.type == Result.Type.SUCCESS) {
						results.add(result.getResult());
						cumulativePos += result.getPos();
					}
					else {
						break;
					}
				}
				
				if (results.size() >= min && results.size() <= max) {
					return new Result<>(new CompositeNode<R>().addChildren(results), cumulativePos);
				}
				else {
					return new Result<>();
				}
			}
		};
	}
	
	/**
	 * Like a regexp's '?' operator.
	 */
	default ParserCombinator<I, R> optional() {
		return minMax(0, 1, true);
	}
	
	/**
	 * Like a regexp's '*" operator.
	 */
	default ParserCombinator<I, R> star(){
		return minMax(0, Integer.MAX_VALUE, true);
	}
	
	/**
	 * Like a regexp's '+" operator.
	 */
	default ParserCombinator<I, R> plus(){
		return minMax(1, Integer.MAX_VALUE, true);
	}
	
}
