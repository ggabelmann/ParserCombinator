package ggabelmann.pc;

import ggabelmann.pc.core.ParserCombinator;
import ggabelmann.pc.core.Result;
import ggabelmann.pc.input.ArrayInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EndParserCombinatorTest {
	
	@Test
	public void testEnd1() {
		final ParserCombinator pc = new EndParserCombinator<>();
		final Result pcr = pc.parse(new ArrayInput());

		assertEquals(Result.Type.SUCCESS, pcr.type);
		assertNull(pcr.getResult().getChildren());
		assertNull(pcr.getResult().getVal());
		assertEquals(0, pcr.getPos());
	}

	@Test
	public void testEnd2() {
		final EndParserCombinator pc = new EndParserCombinator();
		final Result pcr = pc.parse(new ArrayInput(true));

		assertEquals(Result.Type.FAILURE, pcr.type);
	}

	
}
