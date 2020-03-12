package eg.edu.alexu.csd.filestructure.sort;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.function.Predicate;
public class Heap implements IHeap {
    private Node left;
    private Node right;
    private Node largest;
    private Node swap;
    private Comparable temp;
    private ArrayList unordered = new ArrayList();
    private int index;
    private int lastPosititon;
    private int a, b;

    public Heap(ArrayList unordered) {
        this.unordered = unordered;
    }

    @Override
    public INode getRoot() {
        return (INode) this.unordered.get(0);
    }

    @Override
    public int size() {
        return this.unordered.size();
    }

    @Override
    public void heapify(INode node) {
        left = (Node) node.getLeftChild();
        right = (Node) node.getRightChild();
        if (this.unordered.contains(node)) {
            index = this.unordered.indexOf(node);
        }
        if (this.unordered.indexOf(left.getValue()) <= this.unordered.size() && (Integer) this.unordered.get(this.unordered.indexOf(left.getValue())) > (Integer) this.unordered.get(this.unordered.indexOf(node.getValue()))) {
            largest = left;
        } else {
            largest = (Node) node;
        }
        if (this.unordered.indexOf(right.getValue()) <= this.unordered.size() && (Integer) this.unordered.get(this.unordered.indexOf(right.getValue())) > (Integer) this.unordered.get(this.unordered.indexOf(largest.getValue()))) {
            largest = right;
        }
        if (this.unordered.indexOf(largest.getValue()) != this.unordered.indexOf(node.getValue())) {
            a = this.unordered.indexOf(node.getValue());
            b=this.unordered.indexOf(largest.getValue());
            swap.setValue(largest.getValue());
            this.unordered.remove(b);
            this.unordered.add(b, node.getValue());
            this.unordered.remove(a);
            this.unordered.add(a, swap);
            heapify((INode) this.unordered.get(this.unordered.indexOf(largest)));
        }
    }

    @Override
    public Comparable extract() {
        temp = (Comparable) this.unordered.get(0);
        this.unordered.remove(0);
        this.unordered.add(0,this.unordered.get(lastPosititon));
        this.unordered.remove(lastPosititon);
        this.unordered.add(lastPosititon,temp);
        lastPosititon--;
        trickleDown(0);
        return temp;
    }

    private void trickleDown(int parent) {
        if (parent * 2 + 1 == lastPosititon && (int) this.unordered.get(parent) < (int) this.unordered.get(parent * 2 + 1)) {
            swap = (Node) this.unordered.get(parent);
            this.unordered.remove(parent);
            this.unordered.add(parent, this.unordered.get(parent * 2 + 1));
            this.unordered.remove(parent*2+1);
            this.unordered.add(parent*2+1, swap);
            return;
        }
        if (parent * 2 + 2 == lastPosititon && (int) this.unordered.get(parent) < (int) this.unordered.get(parent * 2 + 2)) {
            swap = (Node) this.unordered.get(parent);
            this.unordered.remove(parent);
            this.unordered.add(parent, this.unordered.get(parent * 2 + 2));
            this.unordered.remove(parent*2+2);
            this.unordered.add(this.unordered.indexOf(parent * 2 + 2), swap);
            return;
        }
        if (parent * 2 + 1 >= lastPosititon || parent * 2 + 2 >= lastPosititon) {
            return;
        }
        if ((int) this.unordered.get(parent * 2 + 1) > (int) this.unordered.get(parent * 2 + 2) && (int) this.unordered.get(parent) < (int) this.unordered.get(parent * 2 + 1)) {
            swap = (Node) this.unordered.get(parent);
            this.unordered.remove(parent);
            this.unordered.add(parent, this.unordered.get(parent * 2 + 1));
            this.unordered.remove(parent*2+1);
            this.unordered.add(parent * 2 + 1, swap);
            trickleDown(parent * 2 + 1);
        } else if ((int) this.unordered.get(parent * 2 + 2) > (int) this.unordered.get(parent * 2 + 1) && (int) this.unordered.get(parent) < (int) this.unordered.get(parent * 2 + 2)) {
            swap = (Node) this.unordered.get(parent);
            this.unordered.remove(parent);
            this.unordered.add(parent, this.unordered.get(parent * 2 + 2));
            this.unordered.remove(parent*2+2);
            this.unordered.add(parent * 2 + 2, swap);
            trickleDown(parent * 2 + 2);
        }
    }

    @Override
    public void insert(Comparable element) {
        lastPosititon = this.unordered.size() - 1;
        this.unordered.add(++lastPosititon, element);
        trickleUp(lastPosititon);
    }

    private void trickleUp(int lastPosititon) {
        if (lastPosititon == 0) {
            return;
        }
        Node child = (Node) this.unordered.get(lastPosititon);
        Node parent = (Node) child.getParent();
        if ((int) this.unordered.get(lastPosititon) > (int) ((Object) parent)) {
            swap = (Node) this.unordered.get(lastPosititon);
            this.unordered.remove(lastPosititon);
            this.unordered.add(lastPosititon, parent );
            this.unordered.remove(this.unordered.indexOf(parent));
            this.unordered.add(this.unordered.indexOf(parent), swap);
            trickleUp(this.unordered.indexOf(parent));
        }
    }
    @Override
    public void build(Collection unordered) {
        Object[] x = new Object[100];
        x = unordered.toArray();
        for (int j = 0; j < x.length && x[j] != null; j++) {
            this.unordered.add(x[j]);
        }
        for (int i = (this.unordered.size() / 2)-1; i >= 0; i--) {
            heapify((INode) this.unordered.get(i));
        }
    }
}