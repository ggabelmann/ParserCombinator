package ggabelmann.pc.node;

import ggabelmann.pc.core.Result;

import java.util.List;

public class LeafNode<R> implements Result.Node<R> {
	
	private final R val;
	
	public LeafNode(final R val) {
		this.val = val;
	}
	
	/**
	 * @return Always null.
	 */
	@Override
	public List<Result.Node<R>> getChildren() {
		return null;
	}
	
	/**
	 * @return A value, may be null.
	 */
	@Override
	public R getVal() {
		return val;
	}
}
