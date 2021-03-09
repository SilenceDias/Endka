package com.company;

import java.sql.Connection;
import java.sql.Statement;

public class Album {
    private String name;
    private int artist_id;
    public Album()
    {
        this.name="Unknown";
    }
    public Album(String name, int id)
    {
        setName(name);
        setArtist_id(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getName() {
        return name;
    }

    public int getArtist_id() {
        return artist_id;
    }
    public void setAlbumToDb()
    {
        try{
            Connection con = null;
            Statement st = null;
            ConnectDB obj_ConnectDb=new ConnectDB();
            con=obj_ConnectDb.get_connection();
            String queryInsertCustomer="INSERT INTO Albums(album_name, artist_id) VALUES " +
                    "('"+this.name+"', '"+this.artist_id+"')";
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
