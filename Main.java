package myassignment;

import java.io.*;
import java.util.*;

class Item {
  String name;
  int price;

  public Item(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String toString() { 
      return this.name + ": " + this.price;
  }
}

public class Main {
  public static void main(String[] args) throws Exception {
    FileInputStream fis=new FileInputStream("input3.txt");       
    Scanner sc=new Scanner(fis);
    int number_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]);
    // sc.nextLine(); sc.nextLine(); sc.nextLine();

    ArrayList<Item> goodies_items = new ArrayList<Item>();

    while(sc.hasNextLine())  
    {
      String current[] = sc.nextLine().split(": ");
      goodies_items.add(new Item(current[0], Integer.parseInt(current[1])));
    }
    sc.close();

    Item[] iarray = new Item[goodies_items.size()];
    
    Iterator it = goodies_items.iterator();
    int k = 0;
    while(it.hasNext()) {
    	Item item = (Item)it.next();
    	
    	iarray[k] = item;
    	k++;
    	
    }
    
    sort(iarray);
    
    ArrayList<Item> goodies_items1 = new ArrayList<Item>();
    for(int l = 0; l < iarray.length; l++) {
    	goodies_items1.add(iarray[l]);
    }
    
    
    
    int min_diff = goodies_items1.get(goodies_items1.size()-1).price;
    int min_index = 0;
    for(int i=0;i<goodies_items1.size()-number_of_employees+1;i++) {
      int diff = goodies_items1.get(number_of_employees+i-1).price-goodies_items1.get(i).price;

      if(diff<=min_diff) {
        min_diff = diff;
        min_index = i;
      }
    }
    
    

    FileWriter fw = new FileWriter("output.txt");
    fw.write("The goodies selected for distribution are:\n\n");
    for(int i=min_index;i<min_index + number_of_employees; i++) {
      fw.write(goodies_items1.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
	  fw.close();
  }

  private static void sort(Item [] arr) {
        Item temp;
        for (int i = 0; i < arr.length; i++) {     
            for (int j = i+1; j < arr.length; j++) {     
               if(arr[i].price > arr[j].price) {    
                   temp = arr[i];    
                   arr[i] = arr[j];    
                   arr[j] = temp;    
               }     
            }     
        }     
        } 
}