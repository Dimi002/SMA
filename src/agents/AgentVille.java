package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AgentVille extends Agent {
	
	private int montantTotalEnvoye = 0;
	private int montantTotalRecu = 0;
	private int nombreEnvois = 0;
	private int nombreReceptions = 0;
	
	protected void setup() {
		System.out.println("Agent Ville " + getAID().getName() + " est prêt.");
		
		addBehaviour(new CyclicBehaviour() {
			public void action() {
				// Attente d'un message
				ACLMessage msg = receive();
				if (msg != null) {
					// Traitement du message
					if (msg.getPerformative() == ACLMessage.REQUEST) {
						int montant = Integer.parseInt(msg.getContent());
						montantTotalEnvoye += montant;
						nombreEnvois++;
						System.out.println("Agent Ville " + getAID().getName() + " a envoyé " + montant + " euros à l'Agent Utilisateur.");
						System.out.println("Montant total envoyé : " + montantTotalEnvoye + " euros.");
						System.out.println("Fréquence d'envoi : " + (double)nombreEnvois/montantTotalEnvoye);
					}
					else if (msg.getPerformative() == ACLMessage.INFORM) {
						int montant = Integer.parseInt(msg.getContent());
						montantTotalRecu += montant;
						nombreReceptions++;
						System.out.println("Agent Ville " + getAID().getName() + " a reçu " + montant + " euros de l'Agent Utilisateur.");
						System.out.println("Montant total reçu : " + montantTotalRecu + " euros.");
						System.out.println("Fréquence de réception : " + (double)nombreReceptions/montantTotalRecu);
					}
				}
				else {
					block();
				}
			}
		});
	}
}
