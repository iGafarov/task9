package buildings.lists;

import buildings.office.OfficeFloor;
import buildings.Floor;

import java.io.Serializable;

public class LinkList implements Serializable, Cloneable {
    private LinkListNode _head;
    private LinkListNode _tail;
    private int _size;


    public LinkList(){
        _head = null;
        _tail = null;
        _size = 0;
    }


    public LinkList(int size){
        for(int i = 0; i < size; ++i){
            push_back(new OfficeFloor(4));
        }
    }


    public int getSize(){
        return _size;
    }


    public void push_back(Floor floor){
        if(_head == null){
            _head = new LinkListNode(floor);
            _tail = _head;
        }
        else {
            if (_size == 1) {
                LinkListNode tmp = new LinkListNode(floor, _head, _head);
                _tail = tmp;
                _tail._next = _head;
                _tail._prev = _head;
                _head = _tail._next;
                _head._next = _tail;
            } else {
                LinkListNode elem = new LinkListNode(floor, _head, _tail);
                _tail._next = elem;
                _head._prev = elem;
                _tail = elem;
            }
        }
        ++_size;
    }


    public void push_front(Floor floor){
        LinkListNode tmp = new LinkListNode(floor,_head._next,_tail);
        _tail._next = tmp;
        _head._prev = tmp;
        _head = tmp;
        ++_size;
    }


    public void pop_front(){
        _head = _head._next;
        _tail._next = _head;
        _head._prev = _tail;
        --_size;
    }


    public LinkListNode getLinkListNode(int num){ //Исключение
        int count = 0;
        LinkListNode current = _head;
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


    public void insert(Floor floor, int num){ //Исключение выход за рамки

        if(num == 0) {
            push_front(floor);
        }
        else {
            if (num == _size) {
                push_back(floor);
            } else {
                LinkListNode prev = _head;
                for (int i = 0; i < num - 1; ++i) {
                    prev = prev._next;
                }
                LinkListNode newNode = new LinkListNode(floor, prev._next, prev);
                prev._next._prev = newNode;
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
            LinkListNode prev = _head;
            for(int i = 0; i < num - 1; ++i){
                prev = prev._next;
            }
            if(num == _size - 1){
                LinkListNode toDel = prev._next;
                prev._next = toDel._next;
                _head._prev = prev;
                _tail = prev;
            }
            else {
                LinkListNode toDel = prev._next;
                prev._next._prev = prev;
                prev._next = toDel._next;
            }
            --_size;
        }

    }


    public Object clone(){
        LinkList result = null;
        try {
            result = (LinkList)super.clone();
            result._head = (LinkListNode)_head.clone();
            result._tail = (LinkListNode)_tail.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }


    /*public void print(){
        if(_size == 1){
            _head.print();
        }
        LinkListNode tmp = _head;
        System.out.println("\t========================= Building ========================");
        while(tmp!=_tail){
            tmp.print();
            tmp = tmp._next;
        }
        _tail.print();
    }*/

}
