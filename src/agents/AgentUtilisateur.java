package agents;

import java.util.HashMap;
import java.util.Map;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentUtilisateur extends Agent {
	
	private int montantTotalEnvoye = 0;
	private int montantTotalRecu = 0;
	private int nbEnvois = 0;
	private int nbReceptions = 0;
	private Map<AID, Integer> villesReception = new HashMap<AID, Integer>();
	private DatabaseManager dbManager;

	protected void setup() {
	    System.out.println("Agent Utilisateur " + getAID().getName() + " est prêt.");
	    dbManager = new DatabaseManager();
	    dbManager.connect();

	    addBehaviour(new CyclicBehaviour() {
	        public void action() {
	            // Attente d'un message
	            ACLMessage msg = receive();
	            if (msg != null) {
	                if (msg.getPerformative() == ACLMessage.REQUEST) {
	                    // Traitement du message
	                    int montant = Integer.parseInt(msg.getContent());
	                    montantTotalEnvoye += montant;
	                    nbEnvois++;
	                    AID villeReception = msg.getSender();
	                    if (villesReception.containsKey(villeReception)) {
	                        int nb = villesReception.get(villeReception);
	                        villesReception.put(villeReception, nb + 1);
	                    } else {
	                        villesReception.put(villeReception, 1);
	                    }
	                    dbManager.insertData(getAID().getName(), montantTotalEnvoye, montantTotalRecu, nbEnvois, nbReceptions);
	                    System.out.println("Agent Utilisateur " + getAID().getName() + " a envoyé " + montant + " euros à l'Agent Ville " + villeReception.getLocalName() + ".");
	                    System.out.println("Montant total envoyé : " + montantTotalEnvoye + " .");
	                    System.out.println("Fréquence d'envoi : " + nbEnvois + " envois.");
	                    System.out.println("Villes de réception des montants : " + villesReception);
	                } else if (msg.getPerformative() == ACLMessage.INFORM) {
	                    // Traitement du message
	                    int montant = Integer.parseInt(msg.getContent());
	                    montantTotalRecu += montant;
	                    nbReceptions++;
	                    dbManager.insertData(getAID().getName(), montantTotalEnvoye, montantTotalRecu, nbEnvois, nbReceptions);
	                    System.out.println("Agent Utilisateur " + getAID().getName() + " a reçu " + montant + " euros de l'Agent Ville " + msg.getSender().getLocalName() + ".");
	                    System.out.println("Montant total reçu : " + montantTotalRecu + " euros.");
	                    System.out.println("Fréquence de réception : " + nbReceptions + " réceptions.");
	                }
	            }
	            else {
	                block();
	            }
	        }
	    });
	}

	protected void takeDown() {
	    dbManager.disconnect();
	}
}