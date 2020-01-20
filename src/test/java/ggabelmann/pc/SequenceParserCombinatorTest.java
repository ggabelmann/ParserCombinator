package ggabelmann.pc;

import ggabelmann.pc.core.ParserCombinator;
import ggabelmann.pc.core.Result;
import ggabelmann.pc.input.ArrayInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SequenceParserCombinatorTest {
	
	@Test
	public void testSequence_1() {
		final ParserCombinator<Boolean, Boolean> pc = new SequenceParserCombinator<>(new EqualsParserCombinator(false), new EqualsParserCombinator(true));
		final Result<Boolean> pcr = pc.parse(new ArrayInput<>(false, true));

		assertEquals(Result.Type.SUCCESS, pcr.type);
		assertEquals(2, pcr.getResult().getChildren().size());
		assertEquals(false, pcr.getResult().getChildren().get(0).getVal());
		assertEquals(true, pcr.getResult().getChildren().get(1).getVal());
		assertEquals(2, pcr.getPos());
	}

	@Test
	public void testSequenceEnd_1() {
		final ParserCombinator<Boolean, Boolean> pc = new SequenceParserCombinator<>(new EqualsParserCombinator(false), new EndParserCombinator()).drill(0);
		final Result<Boolean> pcr = pc.parse(new ArrayInput<>(false));

		assertEquals(Result.Type.SUCCESS, pcr.type);
		assertEquals(false, pcr.getResult().getVal());
	}

}
