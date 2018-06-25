package edu.northeastern.cs5200.query.create.developer;

import edu.northeastern.cs5200.dao.developer.DeveloperDao;
import edu.northeastern.cs5200.dao.developer.DeveloperDaoInterface;
import edu.northeastern.cs5200.models.person.Developer;

import java.util.Date;

public class CreateDeveloper {

    private static Date getDate(){
        return new Date();
    }

    public static void createDeveloper(){

        // Create Alice
        createDeveloper("Alice", "Wonder", "alice", "alice",
                "alice@wonder.com", getDate(),"4321rewq");

        //Create Bob
        createDeveloper("Bob", "Marley", "bob", "bob",
                "bob@marley.com", getDate(),"5432trew");

        //Create Charlie
        createDeveloper("Charles", "Garcia", "charlie", "charlie",
                "chuch@garcia.com", getDate(),"6543ytre");

        //Create Dan
        createDeveloper("Dan", "Martin", "dan", "dan",
                "dan@martin.com", getDate(),"7654fda");

        //Create Ed
        createDeveloper("Ed", "Karaz", "ed", "ed",
                "ed@kar.com", getDate(),"5678dfgh");
    }

    private static void createDeveloper(String firstName, String lastName, String username, String password,
                                        String email, Date dob, String developerKey){
        DeveloperDaoInterface dao = DeveloperDao.getInstance();
        int result;
        Developer developer = new Developer(firstName, lastName, username, password, email, dob, developerKey);
        result = dao.createDeveloper(developer);
        if(result==1)
            System.out.println(String.format("Developer %s successfully created.", username));
        else
            System.out.println(String.format("Not able to create Developer %s.", username));

    }
}
