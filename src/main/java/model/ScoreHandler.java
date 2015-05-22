package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
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

		try {

			xmlFile = new File("highscore.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse(xmlFile);

			NodeList nList = doc.getElementsByTagName("highscore");
			for (int i = 0; i < nList.getLength(); ++i) {
				Node node = nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) node;
					/* System.out.println("time: " + e.getAttribute("time")); */
					/*
					 * System.out.println("score: " +
					 * e.getElementsByTagName("score").item(0)
					 * .getTextContent()); System.out.println("time: " +
					 * e.getAttribute("time"));
					 */
					/*
					 * this.setScore(Integer.parseInt(e
					 * .getElementsByTagName("score").item(0)
					 * .getTextContent()));
					 */
					this.score = Integer.parseInt(e
							.getElementsByTagName("score").item(0)
							.getTextContent());
					e.setNodeValue(Integer.toString(this.getScore()));
					// log.Read(this.score, e.getAttribute("time"));
					logger.info("Input file found with a highscore of "
							+ this.getScore() + " at " + new Date().toString());
				}
			}

		} catch (ParserConfigurationException e) {
			logger.error("ParserConfigurationException", e);
		} catch (SAXException e) {
			logger.error("SAXException", e);
		} catch (FileNotFoundException e) {
			// ---------
			try {

				xmlFile = new File("highscore.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
				Document doc = dbBuilder.newDocument();

				Element root = doc.createElement("highscore");
				doc.appendChild(root);

				/*
				 * Attr attr = doc.createAttribute("value"); attr.setValue("0");
				 * root.setAttributeNode(attr);
				 */
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
				// log.Create();
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

		try {

			/*
			 * //File xmlFile = new File("highscore.xml");
			 * DocumentBuilderFactory dbFactory = DocumentBuilderFactory
			 * .newInstance(); DocumentBuilder dbBuilder =
			 * dbFactory.newDocumentBuilder(); Document doc =
			 * dbBuilder.parse(xmlFile);
			 * 
			 * NodeList nList = doc.getElementsByTagName("highscore"); for (int
			 * i = 0; i < nList.getLength(); ++i) { Node node = nList.item(i);
			 * if (node.getNodeType() == Node.ELEMENT_NODE) { Element e =
			 * (Element) node; ///*System.out.println("time: " +
			 * e.getAttribute("time"));/
			 * 
			 * System.out.println(this.getScore());
			 * e.appendChild(doc.createTextNode("10"));
			 * System.out.println("scoreQ_Q: " +
			 * e.getElementsByTagName("score").item(0) .getTextContent()); } }
			 */
			xmlFile = new File("highscore.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.newDocument();

			Element root = doc.createElement("highscore");
			doc.appendChild(root);

			/*
			 * Attr attr = doc.createAttribute("value"); attr.setValue("0");
			 * root.setAttributeNode(attr);
			 */

			Date d = new Date();
			root.setAttribute("time", d.toString());

			Element name = doc.createElement("score");
			name.appendChild(doc.createTextNode(Integer.toString(this
					.getScore())));
			root.appendChild(name);

			// this.setScore(0);

			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer transform = tfactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("highscore.xml"));
			transform.transform(source, result);
			// log.Set(this.score, d.toString());
			logger.trace("New highscore of " + this.score
					+ " was reached at " + d);

		} catch (ParserConfigurationException e) {
			logger.error("ParserConfigurationException", e);
		} catch (TransformerException e) {
			logger.error("TransformerException", e);
		}
	}

}