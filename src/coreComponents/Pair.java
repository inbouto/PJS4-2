package coreComponents;

public class Pair<L, R> {

	private L left;
	private R right;
	
	public Pair(L l, R r){
		this.left = l;
		this.right = r;
	}

	public L getLeft() {
		return left;
	}

	public void setLeft(L left) {
		this.left = left;
	}

	public R getRight() {
		return right;
	}

	public void setRight(R right) {
		this.right = right;
	}

}
