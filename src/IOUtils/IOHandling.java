package IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.app.core.Student;

public interface IOHandling {
	static void storeData(Map<String, Student> map,String filename) throws FileNotFoundException, IOException {
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))){
			oos.writeObject(map);
			System.out.println("Saving changes.....");
		}
	}
@SuppressWarnings("unchecked")
static HashMap<String,Student> readData(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
	try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filename))){
		while(true)	{
			return (HashMap<String, Student>) ois.readObject();
		}
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	return null;
		
	}
}
