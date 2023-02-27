/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testjava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
 *
 * @author MOUMNI
 */
public class ClientLoader {
    
    
     /**
     * @param filename
     * @return
     * @throws IOException
     */
    /**
     * Il lit un fichier CSV et renvoie une liste d'objets Client
     * 
     * @param filename le nom du fichier à lire
     * @return Une liste de clients
     */
    public static List<Client> loadCSV(String filename) throws IOException {
        List<Client> clients = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine(); // ignore first line
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String nom = parts[0].trim();
            String prenom = parts[1].trim();
            int age = Integer.parseInt(parts[2].trim());
            String profession = parts[3].trim();
            double salaire = Double.parseDouble(parts[4].trim());
            Client client = Client.create(nom, prenom, age, profession, salaire);
            clients.add(client);
        }
        reader.close();
        return clients;
    }
    
  
  /**
   * Il prend un nom de fichier en paramètre et renvoie une liste de clients
   * 
   * @param filename le nom du fichier à charger
   * @return Une liste de clients.
   */
    public static List<Client> loadJSON(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Client> clients = objectMapper.readValue(new File(filename), new TypeReference<List<Client>>() {});
        return clients;
    }
       
     /**
     * @param filename
     * @return
     * @throws IOException
     */
    

    /**
     * Il prend un nom de fichier comme paramètre, lit le fichier, analyse le XML et renvoie une liste
     * d'objets Client
     * 
     * @param filename le nom du fichier à lire
     * @return Une liste de clients.
     */
    public static List<Client> loadXML(String filename) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource input = new InputSource(new FileReader(filename));
        Document result = builder.parse(input);
        NodeList list = result.getElementsByTagName("row");
        List<Client> clients = new ArrayList<>();

        for (int i=0, len=list.getLength(); i<len; ++i) {
            Node node = list.item(i);

            String[] parts = node.getTextContent().trim().split("\n");
           
            Client c = Client.create(
                parts[0].trim(),
                parts[1].trim(),
                Integer.valueOf(parts[2].trim()) ,
                parts[3].trim(),
                Double.parseDouble(parts[4].trim())  
            );
            clients.add(c);
        }

        return new ArrayList<>(clients);
    }
     
      /**
       * "Si l'extension de fichier est csv, chargez le fichier au format csv, si c'est json,
       * chargez-le au format json, si c'est xml, chargez-le au format xml, sinon lancez une
       * exception."
       * 
       * Ceci est un exemple très simple du modèle de stratégie
       * 
       * @param filename Le nom du fichier à charger.
       * @return Une liste de clients.
       */
       public static List<Client> loadFile(String filename) throws IOException, ParserConfigurationException, SAXException {
        String extension = FilenameUtils.getExtension(filename);

        if (extension.equalsIgnoreCase("csv")) {
            return loadCSV(filename);
        } else if (extension.equalsIgnoreCase("json")) {
            return loadJSON(filename);
        } else if (extension.equalsIgnoreCase("xml")) {
            return loadXML(filename);
        } else {
            throw new IOException("Unsupported file format: " + extension);
        }
    }
     
   /**
    * Il prend une liste de clients, les regroupe par profession, puis calcule le salaire moyen pour
    * chaque profession
    * 
    * @param clients une liste d'objets Client
    * @return Une carte du salaire moyen pour chaque profession.
    */
    public static Map<String, Double> calculerMoyenneSalairesParProfession(List<Client> clients) {
        Map<String, List<Client>> clientsParProfession = clients.stream().collect(Collectors.groupingBy(Client::getProfession));
        Map<String, Double> moyennes = new HashMap<>();

        for (String profession : clientsParProfession.keySet()) {
            List<Client> clientsDeLaProfession = clientsParProfession.get(profession);
            double totalDesSalaires = clientsDeLaProfession.stream().mapToDouble(Client::getSalaire).sum();
            double moyenneDesSalaires = totalDesSalaires / clientsDeLaProfession.size();
            moyennes.put(profession, moyenneDesSalaires);
        }

        return moyennes;
    }

    /**
     * Cette fonction imprime une liste de clients au format CSV
     * 
     * @param clients La liste des clients à imprimer
     */
    public static void printCSV(List<Client> clients) {
        for (Client client : clients) {
            System.out.println(client);
        }
    }
      
      
      
    
    
}
