package com.github.smallcreep.misc;

import org.cactoos.Scalar;

/**
 * Optional.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @param <T> Type of item
 * @since 0.2.0
 */
public interface Optional<T> extends Scalar<T> {

    /**
     * Returns true if contains instance.
     * @return True if present
     */
    boolean has();

    /**
     * Holder for a single element only.
     * @param <T> Type of item
     */
    final class Single<T> implements Optional<T> {

        /**
         * Origin object.
         */
        private final T object;

        /**
         * Ctor.
         * @param obj Origin object
         */
        public Single(final T obj) {
            this.object = obj;
        }

        @Override
        public boolean has() {
            return true;
        }

        @Override
        public T value() throws Exception {
            return this.object;
        }
    }

    /**
     * Empty instance.
     * @param <T> Type of item
     */
    final class Empty<T> implements Optional<T> {

        @Override
        public boolean has() {
            return false;
        }

        @Override
        public T value() throws Exception {
            throw new UnsupportedOperationException(
                "there is nothing here, use has() first, to check"
            );
        }
    }

}
