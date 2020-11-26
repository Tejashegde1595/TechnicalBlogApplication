package technicalBlog.service;

import org.jboss.logging.annotations.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalBlog.model.Post;
import technicalBlog.repository.PostRepository;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    @PersistenceUnit(name = "techblog")
    private EntityManagerFactory emf;
    public List<Post> getAllPosts() {
        return repository.getAllPosts();
    }

    public Post getOnePost(){
       return repository.getPost();
    }

    public void createPost(Post post){
        post.setDate(new Date());
        repository.createPost(post);

        System.out.println("New post added:"+post);
    }

    public Post getPost(Integer postId) {
        return repository.getPost(postId);
    }

    public void updatePost(Post updatedPost) {
        updatedPost.setDate(new Date());
        repository.updatePost(updatedPost);
    }

    public void deletePost(Integer postId) {
        repository.deletePost(postId);
    }
}
