package com.hu.base.http;

import java.io.Serializable;

/**
 * @see TwoTuple 类
 * <pre>
 * 有点像python中的元组，利用java中的泛型返回两个值的一个Vo类
 * </pre>
 * <ul>
 * <strong>常用</strong>
 * <li>{@link #create(Object, Object)}</li>
 * <li>{@link #toString()}</li>
 * </ul>
 *
 * Created by hnusr on 2015-01-01.
 */
public class TwoTuple<A, B> implements Serializable {

    public final A first;
    public final B second;

    public TwoTuple(final A first, final B second) {
        this.first = first;
        this.second = second;
    }

    public static <A, B> TwoTuple<A, B> create(final A a, final B b) {
        return new TwoTuple<A, B>(a, b);
    }

    @Override
    public int hashCode() {
        return MyObjectUtils.hashCode(first, second);
    }

    @Override
    public String toString() {
        String format = "[first=%s, second=%s]";
        return String.format(format, first, second);
    }
}

