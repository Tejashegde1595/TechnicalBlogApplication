package technicalBlog.service;

import org.springframework.stereotype.Service;
import technicalBlog.model.Post;

import java.util.ArrayList;
import java.util.Date;

@Service
public class PostService {
    public ArrayList<Post> getAllPosts(){
        Post post1  = new Post();
        post1.setTitle("Post1");
        post1.setBody("Post1 Body");
        post1.setDate(new Date());

        Post post2 = new Post();
        post2.setTitle("Post1");
        post2.setBody("Post1 Body");
        post2.setDate(new Date());

        Post post3 = new Post();
        post3.setTitle("Post1");
        post3.setBody("Post1 Body");
        post3.setDate(new Date());

        ArrayList<Post> posts = new ArrayList<Post>();
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);

        return posts;
    }

    public ArrayList<Post> getOnePost(){
        Post post1  = new Post();
        post1.setTitle("First Post");
        post1.setBody("First Post Body");
        post1.setDate(new Date());

        ArrayList<Post> posts = new ArrayList<Post>();
        posts.add(post1);
        return posts;
    }
}
