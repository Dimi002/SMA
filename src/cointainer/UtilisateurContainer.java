package cointainer;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class UtilisateurContainer {
	public static void main(String[] args) throws Exception {
			
			Runtime rt = Runtime.instance();
			ProfileImpl p = new ProfileImpl();;
			p.setParameter(Profile.MAIN_HOST, "localhost");
			AgentContainer container = rt.createAgentContainer(p);
	
			AgentController ac = container.createNewAgent("AgentUtilisateur", "agents.AgentUtilisateur", new Object[] {});
			ac.start();
		}
}
