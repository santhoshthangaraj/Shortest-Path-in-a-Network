//SANTHOSH THANGARAJ
//Student id:800868156, unccid: sthangar@uncc.edu

import java.util.Map;
import java.util.Map.Entry;
//MinHeap.java
//implementation of minheap
public class MinHeap
{
    Float[] minheap;
    int size;
    private int size1;
     int high;
    Vertex[] hvertex;
 
    private static final int start = 1;
 //MinHeap constructor
    public MinHeap(int high, Vertex source)
    {
        this.high = high;
        this.size = 0;
        this.size1 = 0;
        minheap = new Float[this.high + 1];
        hvertex = new Vertex[this.high + 1];
        hvertex[0]=source;
        minheap[0] = Float.MIN_VALUE;
    }
 
    private int parent(int value)
    {
        return value / 2;
    }
 
    private int leftChild(int value)
    {
        return (2 * value);
    }
 
    private int rightChild(int value)
    {
        return (2 * value) + 1;
    }
 
    private boolean isLeaf(int value)
    {
        if (value >=  (size / 2)  &&  value <= size)
        { 
            return true;
        }
        return false;
    }
 //used to exchange/swap the value
    private void exchangge(int fpos, int spos)
    {
    	Float tmp;
        tmp = minheap[fpos];
        minheap[fpos] = minheap[spos];
        minheap[spos] = tmp;
        
        Vertex tmp1;
        tmp1 = hvertex[fpos];
        hvertex[fpos] = hvertex[spos];
        hvertex[spos] = tmp1;
    }
 //maintains heap order of the min heap
    private void heaporder(int value)
    {
        if (!isLeaf(value))
        { 
        	int i=leftChild(value);
        	int j=rightChild(value);
        	if(i<high+1&&j<high+1)
        	{
        		if ( (minheap[value] > minheap[i]  || minheap[value] > minheap[i])&&i<high+1&&j<high+1)
                {
                    if (minheap[leftChild(value)] < minheap[rightChild(value)])
                    {
                    	exchangge(value, leftChild(value));
                        heaporder(leftChild(value));
                    }else
                    {
                    	exchangge(value, rightChild(value));
                        heaporder(rightChild(value));
                    }
                }
        	}
            
        }
    }
 //intoheap function is used to insert into the min heap
    public void intoheap(Float element, Vertex v)
    {
        
    	minheap[++size] = element;
    	hvertex[++size1] = v;
        int current = size;
 
        while (minheap[current] < minheap[parent(current)])
        {
            exchangge(current,parent(current));
            current = parent(current);
        }	
    }
    //maintain heapproperty
    public void heapproperty()
    {
        for (int value = (size / 2); value >= 1 ; value--)
        {
            heaporder(value);
        }
    }
 //extract min value
    public Vertex extractminimum()
    {
    	Float local = minheap[start];
    	Vertex temp = hvertex[start];   	
    	minheap[start] = minheap[size--];         
        hvertex[start] = hvertex[size1--]; 
        
        heaporder(start);      
        return temp;
    }
    //used to clean the min heap
    public void clean()
    {
    	for(int i=1;i<high;i++)
    	{
    		minheap[i]=(float) 0.0;
    		hvertex[i]=null;
    		size=0;
    		size1=0;
    	}
    }
 
    
}