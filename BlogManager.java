package Business;

import java.util.ArrayList;

public class BlogManager {
	private ArrayList<Blog> blogs;
	
	public BlogManager(){
		blogs = new ArrayList<Blog>();
	}
	
	public void setBlogList(ArrayList<Blog> blogs){
		this.blogs = blogs;
	}
	
	public void addBlog(Blog newBlog){
		blogs.add(newBlog);
	}
	
	public Blog getBlog(int id) {
		
		for (int i = 0; i < blogs.size(); i++) {
			Blog blog = blogs.get(i); 
			if (blog.getID() == id) {
				return blog; 
			}
		}
		return null;
	}
	
	public ArrayList<Blog> getAllBlogs() {
		return blogs; 
	}
	
	public void newComment(int blogID, String userEmail, String newComment, Boolean isAnon) { // added userEmail as a parameter
		CommentAction comment = new CommentAction(isAnon, userEmail, blogID, newComment);
		Blog blog = getBlog(blogID);
		blog.addComment(comment);
	}//newComment()
}