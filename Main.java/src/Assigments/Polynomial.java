package Assigments;

import java.util.Map;
import java.util.HashMap;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Pair class contains the pair of degree and coefficient
 */
class Pair {

   public int degree;
   public int coefficient;

   Pair(int degree, int coefficient) {
      this.coefficient = coefficient;
      this.degree = degree;
   }

}

/**
 * Polynomial class have a member polynomial to store the expression and have
 * methods -
 * 
 * 1 addValues() : adds the coefficient and its respective degree in polynomial
 * 2 getDegree() : returns the degree of polynomial
 * 3 addPoly() : takes 2 polynomial and returns their addition
 * 4 multiPoly() : takes 2 polynomial and returns their multiplication
 */

public class Polynomial {

   // contains the polynomial expression
   ArrayList<Pair> polynomial;

   Polynomial() {
      polynomial = new ArrayList<>();
   }

   /**
    * Takes degree and coefficient and stores in polynomial
    * 
    * @param degree
    * @param coefficient
    */
   void addValues(int degree, int coefficient) {
      if (degree < 0) {
         throw new IllegalArgumentException("Degree can't be negetive");
      }
      if (coefficient == 0) {
         return;
      }
      Pair pair = new Pair(degree, coefficient);
      polynomial.add(pair);

   }

   private float roundOff(float input) {
      DecimalFormat df = new DecimalFormat("0.00");
      String st = df.format(input);
      return Float.parseFloat(st);
   }

   /**
    * This method returns the evaluted value for the polynomial
    * 
    * @param input takes value of X from user
    * @return returns the evaluated value
    */
   public float evaluate(float input) {
      float totalValue = 0.00f;

      for (int index = 0; index < polynomial.size(); index++) {
         totalValue += polynomial.get(index).coefficient * Math.pow(input, polynomial.get(index).degree);
      }
      return roundOff(totalValue);
   }

   public int getDegree() {
      int maxPower = 0;
      for (int index = 0; index < polynomial.size(); index++) {
         maxPower = Math.max(maxPower, polynomial.get(index).degree);
      }
      return maxPower;
   }

   /**
    * Takes 2 polynomial and returns their addition
    * 
    * @param poly1 takes from polynomial user
    * @param poly2 takes from polynomial user
    * @return returns new polynomial after adding polynomial
    */

   public Polynomial addPoly(Polynomial poly1, Polynomial poly2) {

      Map<Integer, Integer> map = new HashMap<>();
      for (Pair pair : poly1.polynomial) {
         map.put(pair.degree, pair.coefficient);
      }
      for (Pair pair : poly2.polynomial) {
         if (map.containsKey(pair.degree)) {
            map.put(pair.degree, pair.coefficient + map.get(pair.degree));
         } else {
            map.put(pair.degree, pair.coefficient);
         }
      }
      Polynomial additionPoly = new Polynomial();
      for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
         additionPoly.addValues(entry.getKey(), entry.getValue());
      }
      return additionPoly;
   }

   /**
    * Takes 2 polynomial and returns their multiplication
    * 
    * @param poly1 takes from polynomial user
    * @param poly2 takes from polynomial user
    * @return returns new polynomial after applying multiplying input polynomials
    */
   public Polynomial multiplyPoly(Polynomial poly1, Polynomial poly2) {

      Map<Integer, Integer> map = new HashMap<>();

      for (Pair firstPair : poly1.polynomial) {
         for (Pair secondPair : poly2.polynomial) {
            Pair newPair = new Pair(firstPair.degree + secondPair.degree,
                  firstPair.coefficient * secondPair.coefficient);
            if (map.containsKey(newPair.degree)) {
               map.put(newPair.degree, newPair.coefficient + map.get(newPair.degree));
            } else {
               map.put(newPair.degree, newPair.coefficient);
            }
         }
      }

      Polynomial multiplyPoly = new Polynomial();
      for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
         multiplyPoly.addValues(entry.getKey(), entry.getValue());
      }
      return multiplyPoly;
   }

   /**
    * Prints the polynomial
    */
   void printPoly() {
      ArrayList<Pair> reverseList = new ArrayList<>();
      for (int index = polynomial.size() - 1; index > 0; index--) {
         reverseList.add(polynomial.get(index));
      }

      for (Pair temp : reverseList) {
         System.out.print(temp.coefficient + "x^" + temp.degree + " ");
      }
      System.out.println();
   }

}