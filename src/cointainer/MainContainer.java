package cointainer;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class MainContainer {

	public static void main(String[] args) throws Exception {
		Runtime runtime = Runtime.instance();
		ProfileImpl profil = new ProfileImpl();
		profil.setParameter(Profile.GUI, "true");
		AgentContainer mainContainer = runtime.createMainContainer(profil);
		mainContainer.start();
	}

}
