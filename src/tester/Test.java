package tester;

import static utils.StudentCollectionUtils.populateList;
import static utils.StudentCollectionUtils.populateMap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

import com.app.core.Address;
import com.app.core.Student;
import com.app.core.Subject;

import IOUtils.IOHandling;
import custom_exception.DuplicateStudentException;
import custom_exception.StudentNotFoundException;

public class Test {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Map<String, Student> map = IOHandling.readData("C:\\Users\\Vaishnav Puram\\Downloads\\StudentManagement\\StudentManagement\\src\\files\\student.ser");
		Map<String, Student> readMap;
		int choice = 0;
		String rollno, name,  city, state, phono;
		LocalDate dob;
		Subject sub;
		double gpa;
		Student st;
		Scanner s = new Scanner(System.in);
		try {
			do {
				System.out.println("1.New Student Admission");
				System.out.println("2.Edit Student details");
				System.out.println("3.Display all students");
				System.out.println("4.Cancel admission");
				System.out.println("5.Remove students enrolled in <Subject>");
				System.out.println("6.Exit");
				choice = s.nextInt();
				s.nextLine();
				switch (choice) {
				case 1:
					System.out.println("Enter rollno:");
					rollno=s.nextLine();
					if(map.containsKey(rollno)) {
						throw new DuplicateStudentException("Duplicate Stuednt entry!");
					}
					
					System.out.println("Enter name");
					name=s.nextLine();
					System.out.println("Enter DOB");
					dob=LocalDate.parse(s.next());
					System.out.println("Enter subject");
					sub=Subject.valueOf(s.next());
					System.out.println("Enter GPA");
					gpa=s.nextDouble();
					System.out.println("Enter Address");
					System.out.println("Enter state:");
					state=s.next();
					System.out.println("Enter city:");
					city=s.next();
					System.out.println("Enter phoneno:");
					phono=s.next();
					st=new Student(rollno, name, dob, sub, gpa,new Address(city, state, phono));
					map.put(st.getRollNo(), st);
					System.out.println("Student admission done");
					break;
				case 2:
					
					break;
				case 3:
					System.out.println("Student details:");
					readMap=IOHandling.readData("C:\\Users\\Vaishnav Puram\\Downloads\\StudentManagement\\StudentManagement\\src\\files\\student.ser");
					if(readMap.size()==0) {
						System.out.println("No data available!");
					}else {
						readMap.forEach((k,v)->System.out.println(k+":"+v));
					}
					break;
				case 4:
					System.out.println("Enter rollno");
					rollno=s.nextLine();
					if(!map.containsKey(rollno)) {
						throw new StudentNotFoundException("Student not found!");
					}
					map.remove(rollno);
					break;
				case 5:
					System.out.println("Enter rollno");
					rollno=s.nextLine();
					System.out.println(map.get(rollno));
					break;
				case 6:
					IOHandling.storeData(map,
							"C:\\Users\\Vaishnav Puram\\Downloads\\StudentManagement\\StudentManagement\\src\\files\\student.ser");
					System.out.println("Qutting...");
					break;
				}
			} while (choice != 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
