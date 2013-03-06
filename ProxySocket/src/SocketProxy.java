import java.io.*;  
import java.net.*;

class SocketProxy implements SocketInterface {
	 // 1. Create a "wrapper" for a remote,
	  // or expensive, or sensitive target
    private Socket      socket;
	private BufferedReader in;
    private PrintWriter   out;	
    public SocketProxy( String host, int port, boolean wait ) {
	    try {
	      if (wait) {
	        // 2. Encapsulate the complexity/overhead of the target in the wrapper
	        ServerSocket server = new ServerSocket( port );
	        socket = server.accept();
	      } else
	        socket = new Socket( host, port );
	        in  = new BufferedReader( new InputStreamReader(
	                                        socket.getInputStream()));
	        out = new PrintWriter( socket.getOutputStream(), true );
         } catch( IOException e ) {
	        e.printStackTrace();
	     }
     }    

	@Override
	public void dispose() {
		try {
		    socket.close();
		} catch( IOException e ) {
		    e.printStackTrace();
		}
	}

	@Override
	public String readLine() {
        String str = null;
	    try {
	      str = in.readLine();
	    } catch( IOException e ) {
	      e.printStackTrace();
	    }
	    return str;
    }

	@Override
	public void writeLine(String str) {
		out.println( str );
	}

}
