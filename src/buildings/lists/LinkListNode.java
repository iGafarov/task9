package buildings.lists;

import buildings.Floor;

import java.io.Serializable;

public class LinkListNode implements Serializable, Cloneable {
    public Floor _floor;
    public LinkListNode _next;
    public LinkListNode _prev;

    public LinkListNode(Floor floor, LinkListNode next, LinkListNode prev){
        _floor = floor;
        _next = next;
        _prev = prev;
    }

    public LinkListNode(Floor floor){
        _floor = floor;
    }

    public Object clone(){
        LinkListNode result = null;
        try {
            result = (LinkListNode)super.clone();
            result._floor = (Floor)_floor.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }


}
