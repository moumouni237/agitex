/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.testjava;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 *
 * @author MOUMNI
 */
public class TestJava {

    /**
     * @param args
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        System.out.println("Hello World!");

        String filename= "C:/Users/MOUMNI/Documents/NetBeansProjects/TestJava/src/main/java/com/mycompany/testjava/exemple1.csv";
     
        String JsonFilePath ="C:/Users/MOUMNI/Documents/NetBeansProjects/TestJava/src/main/java/com/mycompany/testjava/exemple2.json";


       
       // Chargement d'un fichier CSV et calcul du salaire moyen par métier.
        List<Client> clientsFromCSV = ClientLoader.loadCSV(filename);
        Map<String, Double> avvgCsv = ClientLoader.calculerMoyenneSalairesParProfession(clientsFromCSV);
        System.out.println("moyenne des salaires par type de profession csv : " + avvgCsv);

      
        // Chargement du fichier JSON puis calcul du salaire moyen par métier.
        List<Client> jsonClients = ClientLoader.loadJSON(JsonFilePath);
        Map<String, Double> averageSalariesByProfession = ClientLoader.calculerMoyenneSalairesParProfession(jsonClients);
        System.out.println("Le salaire moyen par profession JSON: " + averageSalariesByProfession);

    
      // Le code ci-dessus charge le fichier XML puis calcule le salaire moyen par profession.
        List<Client> clientsFromXML = ClientLoader.loadXML("C:/Users/MOUMNI/Documents/NetBeansProjects/TestJava/src/main/java/com/mycompany/testjava/exemple3.xml");
        Map<String, Double> avgXml = ClientLoader.calculerMoyenneSalairesParProfession(clientsFromXML);
        System.out.println("Le salaire moyen par profession XML: " + avgXml);
    }

}
