package ggabelmann.pc;

import ggabelmann.pc.core.ParserCombinator;
import ggabelmann.pc.core.Result;
import ggabelmann.pc.input.StringInput;
import ggabelmann.pc.node.LeafNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathTest {
	
	@Test
	public void testMathBetter() {
		// Setup the lowest level parsing.
		final ParserCombinator<String, String> optionalSpaces = new EqualsParserCombinator<>(" ").star();
		final ParserCombinator<String, String> minusOperator = new EqualsParserCombinator<>("-");
		final ParserCombinator<String, String> plusOperator = new EqualsParserCombinator<>("+");
		final ParserCombinator<String, String> digits =
				new AnyParserCombinator<>(new EqualsParserCombinator<>("1"), new EqualsParserCombinator<>("2"))
				.plus()
				.map(node -> {
					final StringBuilder sb = new StringBuilder();
					for (final Result.Node<String> child : node.getChildren()) {
						sb.append(child.getVal());
					}
					return new LeafNode<>(sb.toString());
				});
		
		// Setup actual parsing of math.
		final ParserCombinator<String, Integer> minusMath = new SequenceParserCombinator<>(optionalSpaces, digits, optionalSpaces, minusOperator, optionalSpaces, digits, optionalSpaces, new EndParserCombinator<>())
				.map(node -> new LeafNode<>(Integer.parseInt(node.getChildren().get(1).getVal()) - Integer.parseInt(node.getChildren().get(5).getVal())));
		
		final ParserCombinator<String, Integer> plusMath = new SequenceParserCombinator<>(optionalSpaces, digits, optionalSpaces, plusOperator, optionalSpaces, digits, optionalSpaces, new EndParserCombinator<>())
				.map(node -> new LeafNode<>(Integer.parseInt(node.getChildren().get(1).getVal()) + Integer.parseInt(node.getChildren().get(5).getVal())));
		
		final ParserCombinator<String, Integer> math = new AnyParserCombinator<>(minusMath, plusMath);

		// Tests
		final Result<Integer> pcr1 = math.parse(new StringInput("12 +  21 "));
		assertEquals(33, pcr1.getResult().getVal());

		final Result<Integer> pcr2 = math.parse(new StringInput("112 -  221 "));
		assertEquals(-109, pcr2.getResult().getVal());
	}
	
}
