package com.hu.base.collection;

/**
 * @Author hutiantian
 * @Date 2019/1/16 9:42:59
 */
public class LRUList<E> {
    private final int DEFAULT_CAPITAL = 10;
    private int maxSize;
    private int size;
    private Node<E> head;
    private Node<E> tail;

    public LRUList(int length){
        this.maxSize = length;
    }

    public LRUList(){
        this.maxSize = DEFAULT_CAPITAL;
    }

    public Node<E> addNode(E node){
        Node<E> now = new Node(node,null,null);
        Node<E> first = head;
        head=now;
        head.after = first;
        if(first!=null){
            first.before = head;
        }else {
            tail = now;
        }
        if(size==maxSize){
            Node<E> last = tail;
            tail = last.before;
            tail.after = null;
            return last;
        }else {
            size++;
        }
        return null;
    }


    public void say(){
        Node<E> node = head;
        while(node!=null){
            System.out.println(node.value);
            node = node.after;
        }
    }

    /**
     * Node节点
     */
    private static class Node<E>{
        E value;
        Node<E> before;
        Node<E> after;

        Node(E value,Node<E> before,Node<E> after){
            this.value = value;
            this.before = before;
            this.after = after;
        }
    }
}
