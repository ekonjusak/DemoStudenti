package demo.app.rest.model;

import demo.app.mgmt.PropertiesReader;

public class StudentModelValidationInput {

    public boolean isStudentModelUpdateInputOk(StudentModelCreateUpdate sm) throws Exception {

        PropertiesReader pr = new PropertiesReader();

        if(!(sm.getName().matches("[a-zA-Z]{2,"+pr.name_max_char+"}")) ){
            System.out.println("Name should contains from 2 to 20 letters, a-zA-Z.");
            throw new Exception("Name should contains from 2 to 20 letters, a-zA-Z.");
        } else if (!(sm.getOib().matches("[0-9]{8}")) ){
            System.out.println("Oib should have 8 numbers.");
            throw new Exception("Oib should have 8 integers");
        }else if(!(sm.getMobilePhone().matches("[0-9]{9,10}")) ){
            System.out.println("Mobile phone should have 10 numbers.");
            throw new Exception("Mobile phone should have 10 numbers.");
        }else if(!(sm.getEmail().matches("[a-zA-Z0-9]{1,}[@]{1}[a-z]{5,}[.]{1}+[a-z]{3}")) ){
            System.out.println("Email should have @ and .");
            throw new Exception("Email should have @ and .");
        }else if(!(sm.getMentorId().toString().matches("[1-9]")) ){
            System.out.println("Mentor id should be number.");
            throw new Exception("Mentor id should be number.");
        }
        return true;
    }

}
