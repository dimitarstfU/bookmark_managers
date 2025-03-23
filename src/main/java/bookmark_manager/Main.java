package src.main.java.bookmark_manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main()
    {
        final String saveFilename = "data.txt";
        List<User> users = SavedDataParser.readFromFile(new File(saveFilename));
        User loggedUser = null;

        Scanner sc = new Scanner(System.in);

        while(true)
        {
            String line = sc.nextLine();
            String[] words = line.split(" ");
            
            if(words[0].equals("login"))
            {
                String username = words[1];
                String password = words[2];
                boolean foundUser = false;
                for (User user : users)
                {
                    if (user.getUsername().equals(username))
                    {
                        foundUser = true;
                        if (user.getPassword().equals(password))
                        {
                            loggedUser = user;
                            break;
                        }
                        else
                        {
                            System.out.println("Wrong password, try logging in again");
                        }
                    }
                }
                if (!foundUser)
                {
                    System.out.println("No such username");
                }
            }
            else if (words[0].equals("register"))
            {
                String username = words[1];
                String password = words[2];
                boolean alreadyExists = false;
                for (User user : users)
                {
                    if (user.getUsername().equals(username))
                    {
                        System.out.println("Username " + username + " already exists.");
                        alreadyExists = true;
                        break;
                    }        
                }
                if (!alreadyExists)
                {
                    User newUser = new User(username, password);
                    users.add(newUser);
                }
            }
            else if (loggedUser != null) {
                if(words[0].equals("add-to"))
                {
                    String group_name = words[1];
                    Group gr = null;
                    for (Group group : loggedUser.getGroups()) {
                        if(group.getName().equals(group_name))
                        {
                            gr = group;
                        }
                    }
                    if(gr == null)
                    {
                        gr = new Group(group_name);
                        loggedUser.getGroups().add(gr);
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
                    for (Group group : loggedUser.getGroups())
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
                    for (Group gr : loggedUser.getGroups())
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
                    Group gr = null;
                    for (Group group : loggedUser.getGroups())
                    {
                        if(group.getName().equals(group_name))
                        {
                            gr = group;
                        }
                    }    
                    if (gr == null)
                    {
                        System.out.println("No such group");
                    }
                    else
                    {
                        System.out.println("group: " + gr.getName());
                        for (Bookmark bookmark : gr.bookmarks)
                        {
                            System.out.println("bookmark title: " + bookmark.title + "and link: " + bookmark.link);
                        }
                        System.out.println();
                    }
            
                }
                else if(words[0].equals("search"))
                {
                    String title = words[1];
                    for (Group gr : loggedUser.getGroups())
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
                    List<Group> newGroups = new ArrayList<>();
                    for (Group gr : loggedUser.getGroups())
                    {
                        for (Bookmark bookmark : gr.bookmarks)
                        {
                            if(bookmark.extractContent() != null)
                            {
                                newGroups.add(gr);
                            }    
                        }    
                    }
                    loggedUser.setGroups(newGroups);
                }
                else if (words[0].equals("save"))
                {
                    SavedDataParser.saveToFile(new File(saveFilename), users);
                }
                else if (words[0].equals("exit"))
                {
                    break;
                }
            }
            else
            {
                System.out.println("You cannot make any commands without logging in first.");
            }
        }
    }    
}
