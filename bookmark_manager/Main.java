package bookmark_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main()
    {
        List<Group> groups = new ArrayList<Group>();

        Scanner sc = new Scanner(System.in);
        while(true)
        {
            String line = sc.nextLine();
            String[] words = line.split(" ");
            if(words[0].equals("login"))
            {

            }
            else if(words[0].equals("add-to"))
            {
                String group_name = words[1];
                Group gr = null;
                for (Group group : groups) {
                    if(group.getName().equals(group_name))
                    {
                        gr = group;
                    }
                }
                if(gr == null)
                {
                    gr = new Group(group_name);
                    groups.add(gr);
                } 
                String bookmark_link = words[2];
                Bookmark bk = new Bookmark("", bookmark_link);
                gr.add(bk);
            }
            else if(words[0].equals("remove-from"))
            {
                String group_name = words[1];
                String bookmark_title = words[2];
                Group gr = null;
                for (Group group : groups)
                {
                    if(group.getName().equals(group_name))
                    {
                        gr = group;
                    }    
                }
                if(gr == null) 
                {
                    System.out.println("group with such name does not exist!");
                }
                else
                {
                    int remove_index = -1;
                    for (Bookmark bk : gr.bookmarks)
                    {
                        if(bk.getTitle().equals(bookmark_title))
                        {
                            remove_index = gr.bookmarks.indexOf(bk);
                        }    
                    }
                    if(remove_index == -1) 
                    {
                        System.out.println("bookmark with such title does not exist!");
                    }
                    else
                    {
                        gr.bookmarks.remove(remove_index);
                    }
                }
            }
            else if(words[0].equals("list") && words.length == 1)
            {
                for (Group gr : groups)
                {
                    System.out.println("group: " + gr.getName());
                    for (Bookmark bookmark : gr.bookmarks)
                    {
                        System.out.println("bookmark title: " + bookmark.title + "and link: " + bookmark.link);
                    }
                    System.out.println("%n");
                }
            }
            
            else if(words[0].equals("list") && words.length > 1)
            {
                String group_name = words[1];
                for (Group group : groups)
                {
                    if(group.getName().equals(group_name))
                    {
                        
                    }
                }    
                System.out.println("group: " + gr.getName());
                for (Bookmark bookmark : gr.bookmarks)
                {
                    System.out.println("bookmark title: " + bookmark.title + "and link: " + bookmark.link);
                }
                System.out.println("%n");
        
            }
            else if(words[0].equals("search"))
            {
                String title = words[1];
                for (Group gr : groups)
                {
                    for (Bookmark bookmark : gr.bookmarks)
                    {
                        if(title.equals(bookmark.title))
                        {
                            System.out.println(title);
                        }    
                    }    
                }
            }
            else if(words[0].equals("clean"))
            {
                for (Group gr : groups)
                {
                    for (Bookmark bookmark : gr.bookmarks)
                    {
                        if(extractContent(bookmark) == null)
                        {
                            
                        }    
                    }    
                }
            }
        }
    }    
}
