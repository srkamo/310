package Business;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;
import Business.Entity;

public class EntityTest {
	@SuppressWarnings("null")
	@Test
	public void testGetTitle() {
		String title1 = "Pizza Studio";
		int id1 = 2; 
		String description = "Pizza in a studio"; 
		ArrayList<String> tags1 = new ArrayList<String>(); 
		tags1.add("pizza"); 
		tags1.add("studio");
		tags1.add("good");
		tags1.add("times"); 
		Boolean isInfinite1 = true; 
		Calendar timeEnd1 = Calendar.getInstance(); 
		timeEnd1.set(2019, 04, 05, 23, 59, 59);
		String image1 = "https://pbs.twimg.com/profile_images/515257364681728001/Hk7MD3Cb.png";
		
		
		Entity entity = new Entity(title1, id1, description, tags1, isInfinite1, timeEnd1, image1);
	    String title = entity.getTitle(); 
	    assertEquals("Pizza Studio", title);
	}
	
	@SuppressWarnings("null")
	@Test
	public void testGetID() {
		String title1 = "Pizza Studio";
		int id1 = 2; 
		String description = "Pizza in a studio"; 
		ArrayList<String> tags1 = new ArrayList<String>(); 
		tags1.add("pizza"); 
		tags1.add("studio");
		tags1.add("good");
		tags1.add("times"); 
		Boolean isInfinite1 = true; 
		Calendar timeEnd1 = Calendar.getInstance(); 
		timeEnd1.set(2019, 04, 05, 23, 59, 59);
		String image1 = "https://pbs.twimg.com/profile_images/515257364681728001/Hk7MD3Cb.png";
		
		
		Entity entity = new Entity(title1, id1, description, tags1, isInfinite1, timeEnd1, image1);
	    int subjectID = entity.getID(); 
	    assertEquals(2, subjectID);
	}
	
	@SuppressWarnings("null")
	@Test
	public void testGetDescription() {
		String title1 = "Pizza Studio";
		int id1 = 2; 
		String description = "Pizza in a studio"; 
		ArrayList<String> tags1 = new ArrayList<String>(); 
		tags1.add("pizza"); 
		tags1.add("studio");
		tags1.add("good");
		tags1.add("times"); 
		Boolean isInfinite1 = true; 
		Calendar timeEnd1 = Calendar.getInstance(); 
		timeEnd1.set(2019, 04, 05, 23, 59, 59);
		String image1 = "https://pbs.twimg.com/profile_images/515257364681728001/Hk7MD3Cb.png";
		
		
		Entity entity = new Entity(title1, id1, description, tags1, isInfinite1, timeEnd1, image1);
	    String testDescription = entity.getDescription(); 
	    assertEquals("Pizza in a studio", testDescription);
	}
	
	@SuppressWarnings("null")
	@Test
	public void testGetTags() {
		String title1 = "Pizza Studio";
		int id1 = 2; 
		String description = "Pizza in a studio"; 
		ArrayList<String> tags1 = new ArrayList<String>(); 
		tags1.add("pizza"); 
		tags1.add("studio");
		tags1.add("good");
		tags1.add("times"); 
		Boolean isInfinite1 = true; 
		Calendar timeEnd1 = Calendar.getInstance(); 
		timeEnd1.set(2019, 04, 05, 23, 59, 59);
		String image1 = "https://pbs.twimg.com/profile_images/515257364681728001/Hk7MD3Cb.png";
		
		
		Entity entity = new Entity(title1, id1, description, tags1, isInfinite1, timeEnd1, image1);
	    ArrayList<String> testTags = new ArrayList<String>();
	    testTags.add("pizza"); 
		testTags.add("studio");
		testTags.add("good");
		testTags.add("times"); 
	    assertEquals(tags1, testTags);
	}
	
	@SuppressWarnings("null")
	@Test
	public void testAddTag() {
		String title1 = "Pizza Studio";
		int id1 = 2; 
		String description = "Pizza in a studio"; 
		ArrayList<String> tags1 = new ArrayList<String>(); 
		tags1.add("pizza"); 
		tags1.add("studio");
		tags1.add("good");
		tags1.add("times"); 
		Boolean isInfinite1 = true; 
		Calendar timeEnd1 = Calendar.getInstance(); 
		timeEnd1.set(2019, 04, 05, 23, 59, 59);
		String image1 = "https://pbs.twimg.com/profile_images/515257364681728001/Hk7MD3Cb.png";
		
		Entity entity = new Entity(title1, id1, description, tags1, isInfinite1, timeEnd1, image1);
		entity.addTag("hi"); 
		tags1.add("hi"); 
	    ArrayList<String> testTags = new ArrayList<String>();
	    testTags.add("pizza"); 
		testTags.add("studio");
		testTags.add("good");
		testTags.add("times"); 
		testTags.add("hi"); 
	    assertEquals(tags1, testTags);
	}
	
	@SuppressWarnings("null")
	@Test
	public void testGetImage() {
		String title1 = "Pizza Studio";
		int id1 = 2; 
		String description = "Pizza in a studio"; 
		ArrayList<String> tags1 = new ArrayList<String>(); 
		tags1.add("pizza"); 
		tags1.add("studio");
		tags1.add("good");
		tags1.add("times"); 
		Boolean isInfinite1 = true; 
		Calendar timeEnd1 = Calendar.getInstance();
		timeEnd1.set(2019, 4, 5, 23, 59, 59);
		String image1 = "https://pbs.twimg.com/profile_images/515257364681728001/Hk7MD3Cb.png";
		
		
		Entity entity = new Entity(title1, id1, description, tags1, isInfinite1, timeEnd1, image1);
	    String testImage = entity.getImage(); 
	    assertEquals(image1, testImage);
	}
	
	@SuppressWarnings("null")
	@Test
	public void testGetIsInfinite() {
		String title1 = "Pizza Studio";
		int id1 = 2; 
		String description = "Pizza in a studio"; 
		ArrayList<String> tags1 = new ArrayList<String>(); 
		tags1.add("pizza"); 
		tags1.add("studio");
		tags1.add("good");
		tags1.add("times"); 
		Boolean isInfinite1 = true; 
		Calendar timeEnd1 = Calendar.getInstance(); 
		timeEnd1.set(2019, 04, 05, 23, 59, 59);
		String image1 = "https://pbs.twimg.com/profile_images/515257364681728001/Hk7MD3Cb.png";
		
		
		Entity entity = new Entity(title1, id1, description, tags1, isInfinite1, timeEnd1, image1);
	    Boolean isInfinite = entity.isInfinite(); 
	    assertEquals(isInfinite1, isInfinite);
	}
	
	public static void main(String [] args){
		EntityTest et = new EntityTest(); 
		et.testGetTitle(); 
		et.testGetID(); 
		et.testGetDescription(); 
		et.testGetTags(); 
		et.testAddTag(); 
		et.testGetImage(); 
		et.testGetIsInfinite(); 
	}
}
