package com.hu.base.test.interview;

import lombok.Data;

/**
 * @Author hutiantian
 * @Date 2020/2/27 10:27:58
 */
public class Test {

    public Node reverse(Node head){
        Node now = head;
        Node next;
        Node pre = null;
        while((next = now.next())!=null){
            now.next = pre;
            pre = now;
            now = next;

        }
        return pre;






    }

}

@Data
 class Node{
    Node next;

    public Node next(){
        return null;
    }

}