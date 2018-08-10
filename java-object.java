// Java Object

public class Puppy{

	int puppyAge; // instance variable 

	// constructor
	public Puppy(int age){
		this.puppyAge = age;
	}

	//getter and setter
	public int getAge(){
		System.out.println("Puppy's age is :" + puppyAge );
		return puppyAge;
	}

	public void setAge(int age){
		puppyAge = age;

	}
	public static void main(String []args){
		Puppy myPuppy = new Puppy(2);
		myPuppy.getAge();
		myPuppy.setAge(5);
		myPuppy.getAge();
	}
}