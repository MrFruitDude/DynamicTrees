package com.dtteam.dynamictrees.utility;

/**
 * Minimal immutable pair used by generation and season helpers.
 */
public record Tuple<A, B>(A a, B b) {
    public A getA() {
        return this.a;
    }

    public B getB() {
        return this.b;
    }
}
