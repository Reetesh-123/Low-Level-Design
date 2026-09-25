package Aggregate;

import Iterator.Iterator;

public interface Aggregate {
   public Iterator createIterator(String type);
}
