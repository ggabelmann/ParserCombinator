package ggabelmann.pc.node;

import ggabelmann.pc.core.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompositeNode<R> implements Result.Node<R> {
	
	private final List<Result.Node<R>> children;
	
	public CompositeNode() {
		this.children = new ArrayList<>();
	}
	
	/**
	 * @return A list of nodes, never null.
	 */
	@Override
	public List<Result.Node<R>> getChildren() {
		return children;
	}
	
	/**
	 * @return Always null.
	 */
	@Override
	public R getVal() {
		return null;
	}
	
	public CompositeNode<R> addChild(final Result.Node<R> node) {
		children.add(node);
		return this;
	}
	
	public CompositeNode<R> addChildren(final Collection<Result.Node<R>> nodes) {
		children.addAll(nodes);
		return this;
	}
}
