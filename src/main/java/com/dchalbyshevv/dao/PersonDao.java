package com.dchalbyshevv.dao;

import com.dchalbyshevv.models.Person;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private  static int PEOPLE_COUNT;
    private List<Person> people;
    {
        people = new ArrayList<>();
        people.add(new Person("Dim",++PEOPLE_COUNT));
        people.add(new Person("Vika",++PEOPLE_COUNT));
        people.add(new Person("Bob",++PEOPLE_COUNT));
        people.add(new Person("valera",++PEOPLE_COUNT));


    }

    public List<Person> index(){
        return people;

    }

    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);

    }

    public void save(Person person){
      person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
     public  void update(int id,Person person){
        Person personToBeUpdate = show(id);
        personToBeUpdate.setName(person.getName());

     }

     public void  delete(int id){

        people.removeIf(p -> p.getId() == id);
     }

}
