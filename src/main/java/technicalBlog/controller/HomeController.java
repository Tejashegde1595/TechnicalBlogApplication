package technicalBlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import technicalBlog.model.Post;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String getAllPosts(Model model){

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

        model.addAttribute("posts",posts);
        return "index";
    }
}
