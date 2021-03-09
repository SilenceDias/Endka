package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void setUser(String name, String surname)
    {
        User u=new User(name, surname);
        u.setUserToDb();
    }
    public static void setArtist(String name)
    {
        Artist a=new Artist(name);
        a.setArtistToDb();
    }
    public static void setBand(String name, int members)
    {
        Band a=new Band(name, members);
        a.setArtistToDb();
    }
    public static void printArtists()
    {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        ConnectDB obj_ConnectDb=new ConnectDB();
        con=obj_ConnectDb.get_connection();
        try {
            st= con.createStatement();
            String querySelect="SELECT * FROM Artists";
            rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                System.out.println("Artist id: "+rs.getInt(1));
                System.out.println("Artist name: "+rs.getString(2));
            }
            st.close();
            con.close();
            rs.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    public static void printAlbums(int artistId)
    {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        ConnectDB obj_ConnectDb=new ConnectDB();
        con=obj_ConnectDb.get_connection();
        try {
            st= con.createStatement();
            String querySelect="SELECT * FROM Albums WHERE artist_id="+artistId+"";
            rs=st.executeQuery(querySelect);
            while(rs.next())
            {
                System.out.println("Album id: "+rs.getInt(1));
                System.out.println("Album name: "+rs.getString(2));
            }
            st.close();
            con.close();
            rs.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    public static void setAlbum(String name, int idOfArtist)
    {
        Album a=new Album(name, idOfArtist);
        a.setAlbumToDb();
    }
    public static void setSong(String name, int idAlbum, int idArtist, int idUser)
    {
        Song s=new Song(name,idAlbum,idArtist,idUser);
        s.setSongToDb();
    }
    public static int getId(String name, String surname)
    {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        ConnectDB obj_ConnectDb=new ConnectDB();
        con=obj_ConnectDb.get_connection();
        int idOfUser=0;
        try {
            st= con.createStatement();
            String querySelect="select user_id from users where user_name='"+name+"' and user_surname='"+surname+"'";
            rs=st.executeQuery(querySelect);

            while(rs.next())
            {
                idOfUser=rs.getInt(1);
            }
            st.close();
            con.close();
            rs.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return idOfUser;
    }
    public static int getIdOfArtist(String name)
    {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        ConnectDB obj_ConnectDb=new ConnectDB();
        con=obj_ConnectDb.get_connection();
        int idOfArtist=0;
        try {
            st= con.createStatement();
            String querySelect="select artist_id from Artists where artist_name='"+name+"'";
            rs=st.executeQuery(querySelect);

            while(rs.next())
            {
                idOfArtist=rs.getInt(1);
            }
            st.close();
            con.close();
            rs.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return idOfArtist;
    }
    public static int getIdOfAlbum(String name)
    {
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        ConnectDB obj_ConnectDb=new ConnectDB();
        con=obj_ConnectDb.get_connection();
        int idOfAlbum=0;
        try {
            st= con.createStatement();
            String querySelect="select album_id from Albums where album_name='"+name+"'";
            rs=st.executeQuery(querySelect);

            while(rs.next())
            {
                idOfAlbum=rs.getInt(1);
            }
            st.close();
            con.close();
            rs.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return idOfAlbum;
    }
    public static void menu()
    {
        int choose=0;
        System.out.println("Hello human. This is the program for your playlist. You can find or add your favorite songs");
        System.out.println("Enter your name: ");
        String name, surname;
        Scanner in=new Scanner(System.in);
        name=in.nextLine();
        System.out.println("Enter your surname: ");
        surname=in.next();
        do{

            System.out.println(name);
            System.out.println(surname);
            setUser(name,surname);
            System.out.println("1-add your favorite song");
            System.out.println("2-print all your favorite songs");
            System.out.println("3-end the program");
            choose=in.nextInt();
            if(choose==1)
            {
               System.out.println("Is your artist solo or band?Enter 1 if solo, anything if band:");
                int chooseOfBand=in.nextInt();
                String nameOfArtist;
                if(chooseOfBand==1)
                {
                    System.out.println("Enter name of artist:");
                    nameOfArtist=in.next();
                    setArtist(nameOfArtist);
                }
                else
                {
                    System.out.println("Enter name of band:");
                    nameOfArtist=in.next();
                    System.out.println("How many members in band: ");
                    int members=in.nextInt();
                    setBand(nameOfArtist,members);
                }
                int idOfArtist=getIdOfArtist(nameOfArtist);
                System.out.println("Enter name of album: ");
                String nameOfAlbum=in.next();
                setAlbum(nameOfAlbum, idOfArtist);
                System.out.print("Enter name of song: ");
                String nameOfSong=in.next();
                int idOfUser=getId(name,surname);
                int idOfAlbum=getIdOfAlbum(nameOfAlbum);
                setSong(nameOfSong,idOfAlbum,idOfArtist,idOfUser);

            }
            if(choose==2)
            {
                Connection con = null;
                ResultSet rs = null;
                Statement st = null;
                ConnectDB obj_ConnectDb=new ConnectDB();
                con=obj_ConnectDb.get_connection();
                int idOfUser=getId(name,surname);
                try {



                    st= con.createStatement();
                    String querySelect2="SELECT Songs.song_name, Artists.artist_name, Albums.album_name FROM Songs \n" +
                            "INNER JOIN Artists ON Songs.artist_id=Artists.artist_id\n" +
                            "INNER JOIN Albums ON Songs.album_id=Albums.album_id\n" +
                            "WHERE Songs.user_id="+idOfUser+"";
                    rs=st.executeQuery(querySelect2);
                    while(rs.next())
                    {
                        System.out.println("Song name: "+rs.getString(1));
                        System.out.println("Artist name: "+rs.getString(2));
                        System.out.println("Album name: "+rs.getString(3));
                    }
                    st.close();
                    con.close();
                    rs.close();

                }
                catch(Exception e) {
                    System.out.println(e);
                }
            }
        }
        while(choose!=3);
    }
    public static void main(String[] args) {
        menu();
    }
}
