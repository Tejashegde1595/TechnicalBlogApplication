package technicalBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import technicalBlog.model.Category;
import technicalBlog.model.Post;
import technicalBlog.model.User;
import technicalBlog.service.PostService;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("posts")
    public String getUserPosts(Model model) throws SQLException {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts",posts);
        return "posts";
    }

    @RequestMapping("posts/newpost")
    public String newPost(){
       return "posts/create";
    }

    @RequestMapping(value = "posts/create",method = RequestMethod.POST)
    public String createPost(Post post, HttpSession session){
        User user = (User)session.getAttribute("loggeduser");
        post.setUser(user);

        if (post.getSpringBlog() != null) {
            Category springBlogCategory = new Category();
            springBlogCategory.setCategory(post.getSpringBlog());
            post.getCategories().add(springBlogCategory);
        }

        if (post.getJavaBlog() != null) {
            Category javaBlogCategory = new Category();
            javaBlogCategory.setCategory(post.getJavaBlog());
            post.getCategories().add(javaBlogCategory);
        }
        postService.createPost(post);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.GET)
    public String editPost(@RequestParam(name="postId") Integer postId, Model model) {
        Post post = postService.getPost(postId);
        model.addAttribute("post",post);
        return "posts/edit";
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.PUT)
    public String editPostSubmit(@RequestParam(name="postId") Integer postId, Post updatedPost,HttpSession session) {
        updatedPost.setId(postId);
        User user = (User)session.getAttribute("loggeduser");
        updatedPost.setUser(user);
        if (updatedPost.getSpringBlog() != null) {
            Category springBlogCategory = new Category();
            springBlogCategory.setCategory(updatedPost.getSpringBlog());
            updatedPost.getCategories().add(springBlogCategory);
        }

        if (updatedPost.getJavaBlog() != null) {
            Category javaBlogCategory = new Category();
            javaBlogCategory.setCategory(updatedPost.getJavaBlog());
            updatedPost.getCategories().add(javaBlogCategory);
        }

        postService.updatePost(updatedPost);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/deletePost", method = RequestMethod.DELETE)
    public String deletePostSubmit(@RequestParam(name="postId") Integer postId) {
        postService.deletePost(postId);
        return "redirect:/posts";
    }
}
