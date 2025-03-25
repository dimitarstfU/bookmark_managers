package src.main.java.bookmark_manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SavedDataParser {
    public static List<User> readFromFile(File file)
    {
        Scanner scanner = new Scanner(file.getName());
        List<User> users = new ArrayList<User>();
        String line = scanner.nextLine();
        String[] words = line.split(" ");
        if(!words[0].equals("User"))
        {
            scanner.close();
            throw Exception;//nesh da ima
        }
        while(!words[0].equals("U_end"))
        {
            String username = words[1];
            String password = words[2];
            User user = new User(username,password);
            
            line = scanner.nextLine();
            words = line.split(" ");
            
            while(!words[0].equals("G_end"))
            {
                String gr_name = words[2];//words[2] bi trqbvalo da e imeto na grupata
                Group gr = new Group(gr_name);
                line = scanner.nextLine();
                words = line.split(" ");
                while(!words[0].equals("bk_end"))
                {
                    String bk_title = words[0];
                    String bk_link = words[1];
                    Bookmark bk = new Bookmark(bk_title, bk_link);
                    gr.getBookmarks().add(bk);
                    line = scanner.nextLine();
                    words = line.split(" ");
                }
                user.getGroups().add(gr);
            }
            users.add(user);
        }
        return users;
    }
    
    public static void saveToFile(File file, List<User> data) throws IOException
    {
        var writer = new BufferedWriter(new FileWriter(file));
        for (User user : data)
        {
            writer.append("User " + user.getGroups().size() + "\n" + user.getUsername() + " " + user.getPassword());
            for (Group group : user.getGroups())
            {
                writer.append("Group " + group.getBookmarks().size() + group.getName() + "\n");
                for (Bookmark bookmark : group.getBookmarks())
                {
                    writer.append(bookmark.getTitle() + " " + bookmark.getLink() + "\n");
                }
                writer.append("bk_end\n");
                writer.append("G_end\n");
            }    
            writer.append("U_end\n");
        }
        writer.close();
    }
}
