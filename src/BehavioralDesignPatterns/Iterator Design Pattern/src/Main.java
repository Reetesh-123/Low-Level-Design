import Aggregate.PlayList;
import Iterator.Iterator;

void main() {

    PlayList playList = new PlayList();
    playList.addSong("Song 1");
    playList.addSong("Song 2 Favorite");
    playList.addSong("Song 3");
    playList.addSong("Song 4 Favorite");
    playList.addSong("Song 5");

    Iterator simpleIterator = playList.createIterator("SIMPLE");
    IO.println("Simple Playlist:");
    while(simpleIterator.hasNext()){
        System.out.println("Playing: " + simpleIterator.next());
    }

    IO.println("\n");

    Iterator shuffledIterator = playList.createIterator("SHUFFLE");
    IO.println("Shuffled Playlist:");
    while(shuffledIterator.hasNext()){
        System.out.println("Playing: " + shuffledIterator.next());
    }

    IO.println("\n");

    Iterator favoriteIterator = playList.createIterator("FAVORITE");
    IO.println("Favorite Playlist:");
    while(favoriteIterator.hasNext()){
        System.out.println("Playing: " + favoriteIterator.next());
    }

}
