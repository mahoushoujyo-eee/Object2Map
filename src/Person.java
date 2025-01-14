import java.util.List;

public class Person
{
    private String name;
    private int age;
    private String gender;
    private int[] numbers;
    private String[] names;
    private Person[] persons;
    private List<Person> personsList;

    public Person()
    {
    }

    public Person(String name, int age, int[] numbers, String gender)
    {
        this.name = name;
        this.age = age;
        this.numbers = numbers;
        this.gender = gender;
    }

    public Person(String name, int age, int[] numbers, String gender, Person[] persons, String[] names, List<Person> personsList)
    {
        this.name = name;
        this.age = age;
        this.numbers = numbers;
        this.gender = gender;
        this.persons = persons;
        this.names = names;
        this.personsList = personsList;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public List<Person> getPersonsList() {
        return personsList;
    }

    public void setPersonsList(List<Person> personsList) {
        this.personsList = personsList;
    }
}
