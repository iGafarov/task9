package buildings.lists;


import buildings.Space;

import javax.swing.*;
import java.io.Serializable;

public class ArrayListNode implements Serializable, Cloneable {
    public Space _space;
    public ArrayListNode _next;

    public ArrayListNode(Space space, ArrayListNode next){
        _space = space;
        _next = next;
    }

    public ArrayListNode(Space space){
        _space = space;
    }


    public Object clone(){
        Object result = null;
        try {
            result = super.clone();
            ((ArrayListNode)result)._space = (Space)_space.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void print(){
        _space.print();
    }
}
