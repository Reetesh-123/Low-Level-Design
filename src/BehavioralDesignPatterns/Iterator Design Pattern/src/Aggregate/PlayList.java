package Aggregate;

import Iterator.Iterator;
import Iterator.SimplePlaylistIterator;
import Iterator.SuffledPlaylistIterator;
import Iterator.FavoritePlaylistIterator;

import java.util.ArrayList;
import java.util.List;

public class PlayList implements Aggregate{

    List<String> songs;

    public PlayList(){
        songs = new ArrayList<>();
    }

    public void addSong(String song) {
       songs.add(song);
    }

    @Override
    public Iterator createIterator(String type){
        switch (type){
            case "SIMPLE":
                return new SimplePlaylistIterator(songs);

            case "SHUFFLE":
                return new SuffledPlaylistIterator(songs);


            case "FAVORITE":
                return new FavoritePlaylistIterator(songs);

        }
        return null;
    }
}
