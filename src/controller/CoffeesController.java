/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Coffee;
import model.CoffeeDb;
import views.CreateCoffee;
import views.ListCoffees;

/**
 *
 * @author francisleide
 */
public class CoffeesController implements ActionListener{
    private CreateCoffee createCoffee;
    private CoffeeDb coffeedb;
    private ListCoffees listCoffees;
    public CoffeesController(CreateCoffee createCoffee, ListCoffees listCoffees,CoffeeDb coffeedb){
        this.coffeedb = coffeedb;
        this.createCoffee = createCoffee;
        this.listCoffees = listCoffees;
        this.createCoffee.btnSave.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.createCoffee.btnSave ){
            Coffee coffee = new Coffee();
            coffee.setBrand(this.createCoffee.txtBrand.getText());
            String priceString = this.createCoffee.txtPrice.getText();
            float price = Float.parseFloat(priceString);
            coffee.setPrice(price);
            String weightString = this.createCoffee.txtWeight.getText();
            float weight = Float.parseFloat(weightString);
            coffee.setWeight(weight);
           if(this.coffeedb.insertCoffee(coffee)){
               JOptionPane.showMessageDialog(null, "Sucesso ao inserir o café!");
               fillTable(this.listCoffees.tableCoffee);
               this.listCoffees.setVisible(true);
           }else{
               JOptionPane.showMessageDialog(null, "Erro ao inserir os dados", "", JOptionPane.ERROR_MESSAGE);
           }
        } 
   }
    // JTable table é a tabela que vai estar na tela
    public void fillTable(JTable table){
        //criar essa defaultTable para a gente conseguir manipular os dados que vão estar nela
        DefaultTableModel defaultTable = new DefaultTableModel();
        //apontar nossa tabela que estar na tela para a que a gente está montando localmente
        table.setModel(defaultTable);
        //adicionar as colunas com os nomes que a gente quer que seja exibido
        defaultTable.addColumn("Marca");
        defaultTable.addColumn("Preço");
        defaultTable.addColumn("Peso");
        // criar as colunas de cada linha
        Object [] colunas  = new Object[3];
        ArrayList<Coffee> coffees = this.coffeedb.listCoffees();
        for (Coffee coffee : coffees) {
            colunas[0] = coffee.getBrand();
            colunas[1] = coffee.getPrice();
            colunas[2] = coffee.getWeight();
            defaultTable.addRow(colunas);
        }
    }
    
}
