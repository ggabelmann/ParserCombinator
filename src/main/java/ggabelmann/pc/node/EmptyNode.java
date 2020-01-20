package ggabelmann.pc.node;

import ggabelmann.pc.core.Result;

import java.util.List;

public class EmptyNode<R> implements Result.Node<R> {
	
	/**
	 * @return Always null.
	 */
	@Override
	public List<Result.Node<R>> getChildren() {
		return null;
	}
	
	/**
	 * @return Always null.
	 */
	@Override
	public R getVal() {
		return null;
	}
}
