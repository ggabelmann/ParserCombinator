package ggabelmann.pc;

import ggabelmann.pc.core.ParserCombinator;
import ggabelmann.pc.core.Result;
import ggabelmann.pc.input.ArrayInput;
import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EqualsParserCombinatorTest {
	
	private final Random random = new Random();
	
	@Test
	public void testEquals1() {
		final boolean val = random.nextBoolean();
		final ParserCombinator<Boolean, Boolean> pc = new EqualsParserCombinator<>(val);
		final Result<Boolean> pcr = pc.parse(new ArrayInput<>(val));

		assertEquals(Result.Type.SUCCESS, pcr.type);
		assertEquals(val, pcr.getResult().getVal());
		assertEquals(1, pcr.getPos());
	}

	@Test
	public void testEquals2() {
		final boolean val = random.nextBoolean();
		final ParserCombinator<Boolean, Boolean> pc = new EqualsParserCombinator<>(val);
		final Result<Boolean> pcr = pc.parse(new ArrayInput<>(!val));

		assertEquals(Result.Type.FAILURE, pcr.type);
	}

}
