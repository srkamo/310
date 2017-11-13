package Business;

import java.util.Calendar;

public class Blog {
	private String title;
	private String description;
	private String creator;
	private String image;
	private String content;
	private String dateCreated;
	
	
	public Blog(String title, String description, String creator, String image, String content,
			String dateCreated){
		this.title = title;
		this. description = description;
		this.creator = creator;
		this.image = image;
		this.content = content;
		this.dateCreated = dateCreated;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
}
