package buildings.lists;

import buildings.office.Office;
import buildings.Space;

import java.io.Serializable;

public class ArrList implements Serializable, Cloneable {

    private ArrayListNode _head;
    private ArrayListNode _tail;
    private int _size = 0;

    public ArrList(){
        _head = null;
        _tail = null;
        _size = 0;
    }


    public ArrList(int size){
        for(int i = 0; i < size; ++i){
            push_back(new Office());
        }
    }


    public int getSize(){
        return _size;
    }


    private boolean isEmpty(){
        return _head == null;
    }

    /*public void add(buildings.office.Office office){
        buildings.lists.ArrayListNode tmp = new buildings.lists.ArrayListNode(office);
        tmp._next = _head;
        _head = tmp;
        ++_size;
    }*/


    public void push_back(Space space){
        if(_head == null){
            _head = new ArrayListNode(space);
            _tail = _head;
        }
        else {
            if (_size == 1) {
                ArrayListNode tmp = new ArrayListNode(space, _head);
                _tail = tmp;
                _head._next = _tail;
            } else {
                ArrayListNode elem = new ArrayListNode(space, _head);
                this._tail._next = elem;
                _tail = elem;
            }
        }
        ++_size;
    }


    public void push_front(Space space){

        if(_head == null){
            _head = new ArrayListNode(space);
            _tail = _head;
        }
        else {
            if (_size == 1) {
                ArrayListNode tmp = new ArrayListNode(space,_tail);
                _head = tmp;
                _tail._next = _head;
            } else {
                ArrayListNode tmp = new ArrayListNode(space, _head);
                _head = tmp;
                _tail._next = _head;
            }
        }
        ++_size;
    }


    public void pop_front(){
        _head = _head._next;
        _tail._next = _head;
        --_size;
    }


    public ArrayListNode getArrayListNode(int num){ //Исключение
        int count = 0;
        ArrayListNode current = _head;
        if(num == _size - 1) return _tail;
        while(current!=_tail){
            if(count == num){
                break;
            }
            current = current._next;
            ++count;
        }
        return current;
    }


    public void insert(Space space, int num){ //Исключение выход за рамки

        if(num == 0) {
            push_front(space);
        }
        else {
            if (num == _size) {
                push_back(space);
            } else {
                ArrayListNode prev = _head;
                for (int i = 0; i < num - 1; ++i) {
                    prev = prev._next;
                }
                ArrayListNode newNode = new ArrayListNode(space, prev._next);
                prev._next = newNode;
                ++_size;
            }
        }

    }


    public void removeAt(int num){//Throw

        if(num == 0){
            pop_front();
        }
        else{
            ArrayListNode prev = _head;
            for(int i = 0; i < num - 1; ++i){
                prev = prev._next;
            }
            if(num == _size - 1){
                ArrayListNode toDel = prev._next;
                prev._next = toDel._next;
                _tail = prev;
            }
            else {
                ArrayListNode toDel = prev._next;
                prev._next = toDel._next;
            }
            --_size;
        }

    }

    public Object clone(){
        ArrList result = null;
        try {
            result = (ArrList)super.clone();
            result._head = (ArrayListNode)this._head.clone();
            result._tail = (ArrayListNode)this._head.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
