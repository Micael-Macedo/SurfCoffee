/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author francisleide
 */
public class CoffeeDb {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
 
    public boolean connect(){
        String server = "jdbc:mysql://localhost:3306/surfcoffee";
        String user = "root";
        String pwd = "123456";
        String driver = "com.mysql.cj.jdbc.Driver";
        
        try{
            Class.forName(driver);
            this.connection = (Connection)DriverManager.getConnection(server, user, pwd);
            this.statement = (Statement)this.connection.createStatement();
        }catch(Exception e){
            System.out.println("Erro ao conectar ao banco: "+e);
        }
        
        if(this.connection !=  null){
            return true;
        }
        return false;
    }
    
    public boolean insertCoffee(Coffee coffe){
        try{
            String sql = "insert into coffee "
                    + "(brand, price, weight) "
                    + "values ('"+coffe.getBrand()
                    +"', "+coffe.getPrice()
                    +", "+coffe.getWeight()
                    +");";
            //mostrar se o script do banco está sendo montado corretamente
            System.out.println(sql);
            this.statement.execute(sql);
            return true;
        }catch(Exception e){
            System.out.println("Erro: "+e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Coffee> listCoffees(){
        ArrayList<Coffee> coffees = new ArrayList<>();
        try{
            String sql = "select * from coffee";
            this.resultSet = statement.executeQuery(sql);
            Coffee coffee;
            while (this.resultSet.next()) {
                coffee = new Coffee();
                String brand = resultSet.getString("brand");
                coffee.setBrand(brand);
                coffee.setPrice(resultSet.getFloat("price"));
                coffee.setPrice(resultSet.getFloat("weight"));
                coffees.add(coffee);
            }
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return coffees;
    }
    
    
}
