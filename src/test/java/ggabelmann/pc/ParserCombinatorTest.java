package ggabelmann.pc;

import ggabelmann.pc.node.LeafNode;
import ggabelmann.pc.core.ParserCombinator;
import ggabelmann.pc.core.Result;
import ggabelmann.pc.input.ArrayInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserCombinatorTest {
	
	@Test
	public void testDrill_1() {
		final ParserCombinator<Boolean, Boolean> pc = new SequenceParserCombinator<>(new EqualsParserCombinator(false), new EqualsParserCombinator(true)).drill(-1);
		final Result<Boolean> pcr = pc.parse(new ArrayInput<>(false, true));

		assertEquals(Result.Type.SUCCESS, pcr.type);
		assertEquals(true, pcr.getResult().getVal());
		assertEquals(2, pcr.getPos());
	}

	@Test
	public void testMapper_1() {
		final SequenceParserCombinator<Boolean, Boolean> pc = new SequenceParserCombinator<>(new EqualsParserCombinator(false), new EqualsParserCombinator(true));
		final ParserCombinator<Boolean, String> pc2 = pc.map(pc1Node -> {
			return new LeafNode<>(pc1Node.getChildren().get(0).getVal().toString() + pc1Node.getChildren().get(1).getVal().toString());
		});

		assertEquals("falsetrue", pc2.parse(new ArrayInput<>(false, true)).getResult().getVal());
	}

	@Test
	public void testMinMax_1() {
		final ParserCombinator<Boolean, Boolean> pc = new EqualsParserCombinator<>(true).minMax(1, 2, false);
		final Result<Boolean> pcr = pc.parse(new ArrayInput<>(true, true, false));

		assertEquals(Result.Type.SUCCESS, pcr.type);
		assertEquals(true, pcr.getResult().getChildren().get(0).getVal());
		assertEquals(1, pcr.getPos());
	}

	@Test
	public void testPlus() {
		final ParserCombinator<Boolean, Boolean> pc = new EqualsParserCombinator<>(true).plus();
		final Result<Boolean> pcr = pc.parse(new ArrayInput<>(true, true, false));

		assertEquals(Result.Type.SUCCESS, pcr.type);
		assertEquals(true, pcr.getResult().getChildren().get(0).getVal());
		assertEquals(true, pcr.getResult().getChildren().get(1).getVal());
		assertEquals(2, pcr.getPos());
	}

}
