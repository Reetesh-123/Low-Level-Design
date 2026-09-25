package Iterator;

import java.util.Collections;
import java.util.List;

public class SuffledPlaylistIterator implements Iterator{

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

    public SuffledPlaylistIterator(List<String> playList) {
        this.playList = playList;
        this.index = 0;
        Collections.shuffle(this.playList);
    }
}
