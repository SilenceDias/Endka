package com.company;
import java.sql.*;
public class Artist {
    private String name;
    public Artist(){
        this.name="Unknown";
    }
    public Artist(String name)
    {
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setArtistToDb(){
        try{
            Connection con = null;
            Statement st = null;
            ConnectDB obj_ConnectDb=new ConnectDB();
            con=obj_ConnectDb.get_connection();
            String queryInsertCustomer="INSERT INTO Artists(artist_name) VALUES ('"+this.name+"')";
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
