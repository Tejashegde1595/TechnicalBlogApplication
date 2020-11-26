package technicalBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import technicalBlog.model.Post;
import technicalBlog.model.User;
import technicalBlog.model.UserProfile;
import technicalBlog.service.PostService;
import technicalBlog.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @RequestMapping("users/login")
    public String login(){
        return "users/login";
    }

    @RequestMapping("users/register")
    public String registration(Model model){
        User user = new User();
        UserProfile profile = new UserProfile();
        user.setProfile(profile);

        model.addAttribute("User", user);
        return "users/register";
    }

    @RequestMapping(value = "users/login",method = RequestMethod.POST)
    public String loginUser(User user, HttpSession session){
        User existingUser = userService.login(user);
        if(existingUser!=null) {
            session.setAttribute("loggeduser",existingUser);
            return "redirect:/posts";
        }
        else
            return "users/login";
    }

    @RequestMapping(value = "users/register",method = RequestMethod.POST)
    public String registerUser(User user){
        userService.registerUser(user);
        return "users/login";
    }

    @RequestMapping(value = "users/logout",method = RequestMethod.POST)
    public String logout(Model model, HttpSession session){
        session.invalidate();

        List<Post> posts = postService.getAllPosts();

        model.addAttribute("posts", posts);
        return "index";
    }
}
