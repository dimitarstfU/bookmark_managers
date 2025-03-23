package src.main.java.bookmark_manager;

import java.util.ArrayList;
import java.util.List;

public class Group
{
    String name;
    List<Bookmark> bookmarks;
    Group(String name)
    {
        this.name = name;
        List<Bookmark> bk = new ArrayList<Bookmark>();
    }
    boolean add(Bookmark bk)
    {
         return bookmarks.add(bk);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }
    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }    
}
