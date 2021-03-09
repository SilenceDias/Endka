package com.company;

import java.sql.Connection;
import java.sql.Statement;

public class User {
    private String name;
    private String surname;
    public User()
    {
        this.name="Unknown";
        this.surname="Unknown";
    }
    public User(String name, String surname)
    {
        setName(name);
        setSurname(surname);
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }
    public void setUserToDb()
    {
        try{
            Connection con = null;
            Statement st = null;
            ConnectDB obj_ConnectDb=new ConnectDB();
            con=obj_ConnectDb.get_connection();
            String queryInsertCustomer="INSERT INTO users(user_name, user_surname) VALUES " +
                    "('"+this.name+"', '"+this.surname+"')";
            st=con.createStatement();
            st.executeUpdate(queryInsertCustomer);
            st.close();
            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
