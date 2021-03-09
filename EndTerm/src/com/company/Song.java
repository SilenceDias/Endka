package com.company;

import java.sql.Connection;
import java.sql.Statement;

public class Song {
    private String name;
    private int idAlbum;
    private int idArtist;
    private int idUser;
    public Song()
    {
        this.name="Unknown";
    }
    public Song(String name, int idAlbum, int idArtist, int idUser)
    {
        setName(name);
        setIdAlbum(idAlbum);
        setIdArtist(idArtist);
        setIdUser(idUser);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public void setIdArtist(int idArtist) {
        this.idArtist = idArtist;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public int getIdArtist() {
        return idArtist;
    }
    public void setSongToDb()
    {
        try{
            Connection con = null;
            Statement st = null;
            ConnectDB obj_ConnectDb=new ConnectDB();
            con=obj_ConnectDb.get_connection();
            String queryInsertCustomer="INSERT INTO Songs(song_name, artist_id, album_id, user_id) VALUES " +
                    "('"+this.name+"', '"+this.idArtist+"', '"+this.idAlbum+"', '"+this.idUser+"')";
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
