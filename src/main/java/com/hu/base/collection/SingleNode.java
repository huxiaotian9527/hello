package com.hu.base.collection;

/**
 * @Author hutiantian
 * @Date 2019/1/15 15:59:22
 */
public class SingleNode<E> {

    private int length;

    private Node<E> head;

    private Node<E> tail;

    public SingleNode(E e){
        head = new Node(e,null);
        length = 1;
    }
    public SingleNode(){

    }

    public int add(E e){
        Node<E> node = new Node(e,null);
        if(head==null){
            head = node;
            tail = node;
            length = 1;
        }else {
            tail.next = node;
            tail = node;
            length++;
        }
        return length;
    }

    public void list(){
        Node<E> now = head;
        while (now!=null){
            System.out.println(now.value);
            now = now.next;
        }
    }

    public void reverse(){
        Node<E> now = head;
        Node<E> previous = null;
        while(now!=null){
            Node<E> next = now.next;
            now.next = previous;
            previous = now;
            now = next;
        }
        head = previous;
    }

    private static class Node<E>{
        E value;
        Node<E> next;
        public Node(E value,Node<E> next){
            this.value = value;
            this.next = next;
        }

    }
}
