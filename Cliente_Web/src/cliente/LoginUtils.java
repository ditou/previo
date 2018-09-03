package cliente;

import java.util.HashMap;

public class LoginUtils {
	private HashMap<String, Integer> loginHash;
	
	private static LoginUtils instancia;
	
	private LoginUtils() {
		this.loginHash = new HashMap<>();
	}
	
	public static LoginUtils getInstancia() {
		if(instancia == null)
			instancia = new LoginUtils();
		return instancia;
	}
	
	public Integer getClienteBySession(String session) {
		return loginHash.get(session);
	}
	
	public void saveSession(String session, Integer clienteId) {
		loginHash.put(session, clienteId);
	}
	
	
}
