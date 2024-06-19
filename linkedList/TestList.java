package linkedList;
import java.util.Objects;

public class TestList {
    public static void main(String[] args) {
        // Prueba con enteros
    	OrderListLinked<Integer> intList = new OrderListLinked<>();
        intList.insert(5);
        intList.insert(10);
        intList.insert(1);
        intList.printList();
        intList.remove(5);
        intList.printList();
        
        // Prueba con cadenas
        OrderListLinked<String> stringList = new OrderListLinked<>();
        stringList.insert("Hello");
        stringList.insert("World");
        stringList.insert("Java");
        stringList.printList();
        stringList.remove("World");
        stringList.printList();

        // Prueba con el tipo Person
        OrderListLinked<Person> personList = new OrderListLinked<Person>();
        personList.insert(new Person("Alice", "Smith", 25));
        personList.insert(new Person("John", "Doe", 30));
        personList.insert(new Person("Bob", "Johnson", 40));
        personList.printList();
        personList.remove(new Person("Bob", "Johnson", 40));
        personList.printList();
    }
}

class Person implements Comparable<Person>{
    private String name;
    private String lastName;
    private int age;

    public Person(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Person person = (Person) obj;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(lastName, person.lastName);
    }
    
    @Override
    public int compareTo(Person otherPerson) {
        // Comparar por apellido
        int lastNameComparison = this.lastName.compareTo(otherPerson.lastName);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }
        
        // Si los apellidos son iguales, comparar por nombre
        return this.name.compareTo(otherPerson.name);
    }

	@Override
	public String toString() {
		return "Person [name=" + name + ", lastName=" + lastName + ", age=" + age + "]";
	}

}

