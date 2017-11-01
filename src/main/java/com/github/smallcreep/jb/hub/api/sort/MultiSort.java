package com.github.smallcreep.jb.hub.api.sort;

import com.github.smallcreep.jb.hub.api.Sort;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.UncheckedScalar;
import org.cactoos.text.JoinedText;
import org.cactoos.text.TextOf;

/**
 * Sort by Multi Sort values.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @since 0.2.0
 */
public final class MultiSort implements Sort {

    /**
     * Origin Sorts.
     */
    private final Iterable<Sort> origins;

    /**
     * Ctor.
     * @param origins Origin Sorts
     */
    public MultiSort(final Sort... origins) {
        this(new IterableOf<>(origins));
    }

    /**
     * Ctor.
     * @param origins Origin Sorts
     */
    public MultiSort(final Iterable<Sort> origins) {
        this.origins = origins;
    }

    @Override
    public String value() throws Exception {
        final Iterator<Sort> iterator = this.origins.iterator();
        return new JoinedText(
            new TextOf(", "),
            () -> new Iterator<Text>() {
                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public Text next() {
                    if (this.hasNext()) {
                        return new TextOf(
                            new UncheckedScalar<>(
                                iterator.next()
                            )
                                .value()
                        );
                    }
                    throw new NoSuchElementException();
                }
            }
        ).asString();
    }
}
