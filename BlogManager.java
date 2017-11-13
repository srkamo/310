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
	
	public Blog getBlog(String title) {
		
		for (int i = 0; i < blogs.size(); i++) {
			Blog blog = blogs.get(i); 
			if (blog.getTitle() == title) {
				return blog; 
			}
		}
		return null;
	}
}
