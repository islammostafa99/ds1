package eg.edu.alexu.csd.filestructure.sort;
import java.util.Vector;
public class Node implements INode {
    private Object node;
    private int index;
    private int f;
    public Node(Object object){
        node=object;
        if(Controller.arr.contains(node)){
            index=Controller.arr.indexOf(node);
        }
    }
    @Override
    public INode getLeftChild() {
        return (INode) Controller.arr.elementAt(index*2+1);
    }
    @Override
    public INode getRightChild() {
        return (INode) Controller.arr.elementAt(index*2+2);
    }
    @Override
    public INode getParent() {
        return (INode) Controller.arr.elementAt((index-1)/2);
    }
    @Override
    public Comparable getValue() {
        return (Comparable) Controller.arr.elementAt(index);
    }
    @Override
    public void setValue(Comparable value) {
        Controller.arr.remove(index);
        Controller.arr.add(index, value);
    }
}