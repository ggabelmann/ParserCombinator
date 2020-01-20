package ggabelmann.pc;

import ggabelmann.pc.core.ParserCombinator;
import ggabelmann.pc.core.Result;
import ggabelmann.pc.input.ArrayInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnyParserCombinatorTest {
	
	@Test
	public void testAny() {
		final ParserCombinator<Boolean, Boolean> pc = new AnyParserCombinator<>(new EqualsParserCombinator(false), new EqualsParserCombinator(true));
		final Result<Boolean> pcr = pc.parse(new ArrayInput<>(true));

		assertEquals(Result.Type.SUCCESS, pcr.type);
		assertEquals(true, pcr.getResult().getVal());
		assertEquals(1, pcr.getPos());
	}
	
}
