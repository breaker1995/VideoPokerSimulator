package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class creates and updates the XML file that has the current highscore.
 * 
 * @author breaker
 *
 */
public class ScoreHandler {
	
	
	/*
	 * String for storing the file path.
	 */
	final String path = "target" + File.separatorChar + "classes" + File.separatorChar;

	/**
	 * The current highscore.
	 */
	private int score;
	/**
	 * The XML file that stores the highscore.
	 */
	File xmlFile;
	/**
	 * The logger that logs changes to the highscore and errors.
	 */
	private static Logger logger = LoggerFactory.getLogger(ScoreHandler.class);

	/**
	 * The highscore is loaded from the XML file.
	 * If it does not exist, a default file is created.
	 */
	public ScoreHandler() {
		System.out.println("WATWAT");

		try {
			//getClass().getResource
			xmlFile = new File(this.getClass().getResource("/highscore.xml").getFile());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse(xmlFile);
			System.out.println("ASDASDASD");

			NodeList nList = doc.getElementsByTagName("highscore");
			for (int i = 0; i < nList.getLength(); ++i) {
				Node node = nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) node;

					this.score = Integer.parseInt(e
							.getElementsByTagName("score").item(0)
							.getTextContent());
					System.out.println(this.score + " " + Integer.parseInt(e
							.getElementsByTagName("score").item(0)
							.getTextContent()));
					e.setNodeValue(Integer.toString(this.getScore()));
					logger.info("Input file found with a highscore of "
							+ this.getScore() + " at " + new Date().toString());
				}
			}

		} catch (ParserConfigurationException e) {
			logger.error("ParserConfigurationException", e);
		} catch (SAXException e) {
			logger.error("SAXException", e);
		} catch (NullPointerException | FileNotFoundException e) {
			System.out.println(":C");
			// ---------
			try {

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
				Document doc = dbBuilder.newDocument();

				Element root = doc.createElement("highscore");
				doc.appendChild(root);

				root.setAttribute("time", "never");

				Element name = doc.createElement("score");
				name.appendChild(doc.createTextNode("0"));
				root.appendChild(name);

				this.setScore(0);

				TransformerFactory tfactory = TransformerFactory.newInstance();
				Transformer transform = tfactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(
						new File("highscore.xml"));
				transform.transform(source, result);
				logger.info("No input file found, creating default file.");

			} catch (TransformerException ex) {
				logger.error("TransformerException", ex);
			} catch (ParserConfigurationException exc) {
				logger.error("ParserConfigurationException", exc);
			}
			// ---------
		} catch (IOException exce) {
			logger.error("IOException", exce);
		}
	}

	/**
	 * Returns the current highscore.
	 * 
	 * @return the current highscore
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the current highscore.
	 * 
	 * @param score the highscore to be set
	 */
	public void setScore(int score) {
		this.score = score;

		/*try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.newDocument();

			Element root = doc.createElement("highscore");
			doc.appendChild(root);

			Date d = new Date();
			root.setAttribute("time", d.toString());

			Element name = doc.createElement("score");
			name.appendChild(doc.createTextNode(Integer.toString(this
					.getScore())));
			root.appendChild(name);

			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer transform = tfactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("highscore.xml"));
			transform.transform(source, result);
			logger.trace("New highscore of " + this.score
					+ " was reached at " + d);

		} catch (ParserConfigurationException e) {
			logger.error("ParserConfigurationException", e);
		} catch (TransformerException e) {
			logger.error("TransformerException", e);
		}*/
		
		try {
			//getClass().getResource
			xmlFile = new File(this.getClass().getResource("/highscore.xml").getFile());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse(xmlFile);

			NodeList nList = doc.getElementsByTagName("highscore");
			for (int i = 0; i < nList.getLength(); ++i) {
				Node node = nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) node;

					System.out.println(this.score);
					/*this.score = Integer.parseInt(e
							.getElementsByTagName("score").item(0)
							.getTextContent());*/
					Date d = new Date();
					//FUCK
					e.setAttribute("time", d.toString());
					e.setNodeValue(Integer.toString(this.getScore()));
					logger.trace("New highscore of " + this.score
							+ " was reached at " + d.toString());
				}
			}
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer transform = tfactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(
					xmlFile);
			transform.transform(source, result);

		} catch (ParserConfigurationException e) {
			logger.error("ParserConfigurationException", e);
		} catch (SAXException e) {
			logger.error("SAXException", e);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}