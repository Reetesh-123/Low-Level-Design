package Iterator;

import java.util.List;

public class SimplePlaylistIterator implements Iterator {

    List<String> playList;
    private int index = 0;

    @Override
    public boolean hasNext() {
        return index < playList.size();
    }

    @Override
    public String next() {
        return playList.get(index++);
    }

    public SimplePlaylistIterator(List<String> playList) {
        this.playList = playList;
        this.index = 0;
    }
}
