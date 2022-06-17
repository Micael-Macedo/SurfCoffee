/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surfcoffee;

import controller.CoffeesController;
import model.CoffeeDb;
import views.CreateCoffee;
import views.ListCoffees;

/**
 *
 * @author francisleide
 */
public class SurfCoffee {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CoffeeDb cdb = new CoffeeDb();
        if(cdb.connect()){
            System.out.println("Conexão OK com o banco de dados");
        }else{
            System.out.println("Deu ruim com a conexão! :'(");
        }
        CreateCoffee createCoffee = new CreateCoffee();
        createCoffee.setVisible(true);
        ListCoffees lc = new ListCoffees();
        CoffeesController coffeesController = new CoffeesController(createCoffee,lc ,cdb);
                 
    }
    
}
