package com.company;

import java.sql.Connection;
import java.sql.Statement;

public class Band extends Artist{
    private int howManyMembers;
    public Band()
    {
        super();
    }
    public Band(String name, int members)
    {
        super(name);
        setHowManyMembers(members);
    }

    public void setHowManyMembers(int howManyMembers) {
        this.howManyMembers = howManyMembers;
    }

    public int getHowManyMembers() {
        return howManyMembers;
    }

    @Override
    public void setArtistToDb() {
        try{
            Connection con = null;
            Statement st = null;
            ConnectDB obj_ConnectDb=new ConnectDB();
            con=obj_ConnectDb.get_connection();
            String queryInsertCustomer="INSERT INTO Artists(artist_name, how_many_members) VALUES ('"+super.getName()+"'" +
                    ", '"+this.howManyMembers+"')";
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
