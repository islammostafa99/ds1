package eg.edu.alexu.csd.filestructure.sort;
import java.util.ArrayList;
public class HeapSort implements ISort {
    private Object temp;
    private Heap heap;
    private ArrayList heapsort;
    @Override
    public IHeap heapSort(ArrayList unordered) {
        heap=new Heap(unordered);
        heap.build(unordered);
        for(int i=unordered.size()-1;i>=0;i--){
            temp=unordered.remove(0);
            unordered.add(0,unordered.get(i));
            heapsort.add(0,temp);
            unordered.remove(i);
            heap.build(unordered);
        }
        return (IHeap) heapsort;
    }
    @Override
    public void sortSlow(ArrayList unordered) {
        //Bubble sort...
        for(int i = 0;i<unordered.size();i++){
            for(int j = 1;j<unordered.size();j++){
                if((int)unordered.get(i)>(int)unordered.get(j)){
                    temp=unordered.get(i);
                    unordered.add(i,unordered.get(j));
                    unordered.add(j,temp);
                }
            }
        }
    }
    @Override
    public void sortFast(ArrayList unordered) {

    }
}