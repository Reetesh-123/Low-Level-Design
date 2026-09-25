package Iterator;

import java.util.List;

public class FavoritePlaylistIterator implements Iterator {
    List<String> playList;
    private int index = 0;

    @Override
    public boolean hasNext() {
        while(index < playList.size()){
            if(playList.get(index).contains("Favorite")){
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public String next() {
        return playList.get(index++);
    }

    public FavoritePlaylistIterator(List<String> playList) {
        this.playList = playList;
        this.index = 0;
    }
}
