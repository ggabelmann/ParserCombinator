package ggabelmann.pc.input;

import ggabelmann.pc.core.Input;

public class StringInput implements Input {
	
	private final String input;
	private final int offset;
	
	public StringInput(final String input) {
		this(0, input);
	}
	
	public StringInput(final int offset, final String input) {
		if (input == null || offset < 0 || offset > input.length()) {
			throw new IllegalArgumentException();
		}
		
		this.input = input;
		this.offset = offset;
	}
	
	@Override
	public String get() {
		return input.substring(offset, offset + 1);
	}
	
	@Override
	public boolean isEmpty() {
		return offset == input.length();
	}
	
	@Override
	public Input<String> subInput(final int index) {
		return new StringInput(offset + index, input);
	}
	
}
