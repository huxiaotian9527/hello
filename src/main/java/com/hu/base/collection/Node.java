package com.hu.base.collection;

import lombok.Data;

/**
 * @Author hutiantian
 * @Date 2018/12/26 17:31:41
 */
@Data
public class Node {
    private int value;
    private Node next;

    public static void main(String[] args) {
        Node node = initNode();
        seeNode(node);
        node = reversal(node);
        seeNode(node);
    }

    public static Node initNode(){
        Node a = new Node();
        a.setValue(1);
        Node b = new Node();
        b.setValue(2);
        Node c = new Node();
        c.setValue(3);
        Node d = new Node();
        d.setValue(4);
        a.setNext(b);
        b.setNext(c);
        c.setNext(d);
        return a;
    }

    public static void seeNode(Node node){
        do{
            System.out.print(node.value+"  ");
            node = node.getNext();
        }while (node!=null);
        System.out.println();
    }


    public static Node reversal(Node node) {
        Node previous = null;
        Node now = node;
        Node next;
        while(now != null){
            next = now.getNext();
            now.setNext(previous);
            previous = now;
            now = next;
        }
        return previous;


    }

}
