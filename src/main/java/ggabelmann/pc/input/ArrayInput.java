package ggabelmann.pc.input;

import ggabelmann.pc.core.Input;

public class ArrayInput<I> implements Input {
	
	private final I[] input;
	private final int offset;
	
	public ArrayInput(final I... input) {
		this(0, input);
	}
	
	public ArrayInput(final int offset, final I... input) {
		if (input == null || offset < 0 || offset > input.length) {
			throw new IllegalArgumentException();
		}
		
		this.offset = offset;
		this.input = input;
	}
	
	@Override
	public I get() {
		return input[offset];
	}
	
	@Override
	public boolean isEmpty() {
		return offset == input.length;
	}
	
	@Override
	public Input<I> subInput(final int index) {
		return new ArrayInput(offset + index, input);
	}
	
}
