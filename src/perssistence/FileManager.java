package perssistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import models.Player;

public class FileManager {

	Document document;
	private String path;

	public FileManager() {
//		this.path = getClass().getResource(ConstantsMOD.URL_INFO_FILE).getPath();
		this.path = "src" + System.getProperty("file.separator") + "perssistence" + System.getProperty("file.separator")+ "game.xml";
	}

	private void readFileXml() throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		document = docBuilder.parse(new File(path));
		document.getDocumentElement().normalize();
	}
	
	public ArrayList<Player> loadInfoPlayers() throws ParserConfigurationException, SAXException, IOException{
		readFileXml();
		return loadPlayers();
	}
	
	private ArrayList<Player> loadPlayers(){
		ArrayList<Player> players = new ArrayList<>();
		
		return players;
	}
	
	private Element writePlayer(Document document, Element elementPlayer, Player player) {
		Element idPlayer = document.createElement("idPlayer");
		Text nodeidPlayer = document.createTextNode(String.valueOf(player.getId()));
		idPlayer.appendChild(nodeidPlayer);
		
		Element namePlayer = document.createElement("namePlayer");
		Text nodeNamePlayer = document.createTextNode(player.getName());
		namePlayer.appendChild(nodeNamePlayer);
		
		Element scorePlayer = document.createElement("scorePlayer");
		Text nodeScorePlayer = document.createTextNode(String.valueOf(player.getScore()));
		scorePlayer.appendChild(nodeScorePlayer);
		
		Element idAvatar = document.createElement("idAvatar");
		Text nodeIdAvatar = document.createTextNode(String.valueOf(player.getAvatar()));
		idAvatar.appendChild(nodeIdAvatar);
		
		Element posInitX = document.createElement("posXPlayer");
		Text nodePosX = document.createTextNode(String.valueOf(player.getX()));
		posInitX.appendChild(nodePosX);

		Element posInitY = document.createElement("posYPlayer");
		Text nodePosY = document.createTextNode(String.valueOf(player.getY()));
		posInitY.appendChild(nodePosY);
		
		Element life = document.createElement("lifePlayer");
		Text nodeLife = document.createTextNode(String.valueOf(player.getLife()));
		life.appendChild(nodeLife);
		
		Element fort = document.createElement("fortPlayer");
		Text nodeFort = document.createTextNode(String.valueOf(player.getFort()));
		fort.appendChild(nodeFort);
		
		elementPlayer.appendChild(idPlayer);
		elementPlayer.appendChild(namePlayer);
		elementPlayer.appendChild(scorePlayer);
		elementPlayer.appendChild(idAvatar);
		elementPlayer.appendChild(posInitX);
		elementPlayer.appendChild(posInitY);
		elementPlayer.appendChild(life);
		elementPlayer.appendChild(fort);
		return elementPlayer;
	}
	
	private Element writePlayers(Document document, Element elementPlayers, ArrayList<Player> players) {
		for (int i = 0; i < 4; i++) {
			Player pl = players.get(i);
			Element elementPl = document.createElement("Player" + i);
			
			writePlayer(document, elementPl, pl);
			elementPlayers.appendChild(elementPl);
		}
		return elementPlayers;
	}
	
	public void writeGame(ArrayList<Player> players) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementation = builder.getDOMImplementation();
		Document document = implementation.createDocument(null, "game", null);
		document.setXmlVersion("1.0");
		Source source = new DOMSource(document);

		Element root = document.getDocumentElement();

		Element elementPlayers = document.createElement("Players");
		root.appendChild(writePlayers(document, elementPlayers, players));

		StreamResult result = new StreamResult(new File(this.path));
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, result);
	}
	
}