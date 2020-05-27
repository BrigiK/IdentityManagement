package standaloneClient;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.unitbv.FirstStatelessEjbRemote;

public class Main {

	public static void main(String[] args) throws NamingException {
		InitialContext context = new InitialContext();
		
		FirstStatelessEjbRemote firstEjb = (FirstStatelessEjbRemote) context
				.lookup("java:global/IdentityManagementEAR/IdentityManagementEJB/FirstStatelessEjb!com.unitbv.FirstStatelessEjbRemote");
		
		System.out.println("i'm here");
		firstEjb.insert("gandalf.thegrey@gmail.com", "Gandalf", "The Grey", "gandalfthegrey", "PowerfulWizard");
	}
}
