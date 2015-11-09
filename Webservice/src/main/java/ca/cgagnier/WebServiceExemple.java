//package ca.cgagnier;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.Cookie;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.NewCookie;
//import javax.ws.rs.core.Response;
//
//import org.deguet.json.CustomGson;
//import org.deguet.model.User;
//import org.deguet.service.Service;
//import org.deguet.service.Service.BadEmail;
//import org.deguet.service.Service.NoToken;
//import org.deguet.service.ServiceForDev;
//import org.deguet.service.Singleton;
//import org.deguet.transfer.BabyPlusLast;
//import org.deguet.transfer.C2SSignup;
//import org.deguet.model.Token;
//
//import com.google.gson.Gson;
//
//import java.util.List;
//
//@Path("/social")
//public class WebService {
//
//    private static final String Cookie = "babytracker-id-cookie";
//
//	private Gson gson;
//
//	public WebService() throws BadEmail{
//		gson = CustomGson.getIt();
//	}
//
//	@POST					@Path("/signin")
//	@Produces("text/json")
//	public Response signin(@FormParam("login") String login, @FormParam("password") String password){
//        Token t = Singleton.getService().signin(login, password);
//        System.out.println("WEB SERVICE : SIGNIN " + t.id );
//        NewCookie cookiee = new NewCookie(Cookie, t.id, "/", "", "id token", 604800, false); // setting the cookie
//       return Response.ok(gson.toJson(t),MediaType.APPLICATION_JSON)
//                .cookie(cookiee)
//                .build();
//	}
//
//	// expose the method as a get possibility to test in browser
//	// http://localhost:8080/rest/social/signin/joris.deguet@gmail.com/pass
//    @GET					@Path("/signin/{email}/{password}")
//    @Produces("text/json")
//    public Response signinGet(@PathParam("email") String login, @PathParam("password") String password){
//        System.out.println("WEB SERVICE : SIGNIN  THROUGH GET" );
//        return signin(login, password);
//    }
//
//	// simple example of a straight get access
//	@GET					@Path("/all")
//	@Produces("text/json")
//	public String all(@CookieParam(Cookie) Cookie cookie){
//        System.out.println("WEB SERVICE : GET ALL USERS " + cookie);
//        return gson.toJson( Singleton.getService().allPeople());
//	}
//
//	// http://localhost:8080/rest/social/fakeload
//	@GET					@Path("/fakeload")
//	@Produces("text/json")
//	public String fakeLoad(@CookieParam(Cookie) Cookie cookie){
//        System.out.println("WEB SERVICE : FAKE LOAD " + cookie);
//        Service s = Singleton.getService();
//        if (s.allPeople().size() == 0){
//            ServiceForDev sdev = new ServiceForDev(s);
//            sdev.createFakeData();
//        }
//		return all(cookie);
//	}
//
//    // http://localhost:8080/rest/social/deleteall
//	@GET					@Path("/deleteall")
//	@Produces("text/json")
//	public String deleteAll(@CookieParam(Cookie) Cookie cookie) throws NoToken{
//        System.out.println("WEB SERVICE : DELETE ALL " + cookie);
//        if (cookie == null) throw new NoToken();
//        Singleton.getService().deletePeople();
//		return this.all(cookie);
//	}
//
//
//
//	// http://localhost:8080/rest/social/returnin/80bbdd51-a02c-4281-8630-d2012113473a
//	@GET					@Path("/returnin")
//	@Produces("text/json")
//	public String returninGet(@CookieParam(Cookie) Cookie cookie, @PathParam("token") String token)
//    throws NoToken
//    {
//        System.out.println("WEB SERVICE : RETURN IN  " + cookie +"   ("+token+")");
//        User p = Singleton.getService().returnWithToken(cookie.getValue());
//        return gson.toJson(p);
//
//	}
//
//    // http://localhost:8080/rest/social/homelist
//    @GET					@Path("/homelist")
//    @Produces("text/json")
//    public String homeScreen(@CookieParam(Cookie) Cookie cookie)
//            throws NoToken
//    {
//        if (cookie == null) throw new NoToken();
//        User u = Singleton.getService().returnWithToken(cookie.getValue());
//        List<BabyPlusLast> result = Singleton.getService().listForHomeScreen(u);
//        System.out.println("WEB SERVICE : Home screen list babies  " + cookie +"   : "+result);
//        return gson.toJson(result);
//
//    }
//
//    /**
//     * exemple de construction de la reponse
//     *
//      */
//	@POST					@Path("/signup")
//	@Produces("text/json")
//	public Response signup(String signup) throws BadEmail{
//		// Get the person that is candidate
//        System.out.println("WEB SERVICE : SIGN UP   " + signup);
//		 C2SSignup request = gson.fromJson(signup, C2SSignup.class);
//		try {
//			User u = Singleton.getService().signup(request.login, request.password);
//			return Response.ok(gson.toJson(u.id),MediaType.APPLICATION_JSON).build();
//		} catch (Exception e) {
//            // construction d'une reponse avec un code d'erreur HTTP
//			return Response.status(Response.Status.BAD_REQUEST).entity(e.getClass().getSimpleName()).build();
//		}
//	}
//
//
//    @GET					@Path("/signout")
//    @Produces("text/json")
//    public Response signout(@CookieParam(Cookie) Cookie cookie, @PathParam("token") String token){
//        if (cookie == null) return Response.ok("No cookie", MediaType.TEXT_PLAIN).build();
//        Singleton.getService().signout(cookie.getValue());
//
//        // cree un cookie ave une duree de vie de 0 secondes qui sera donc le remplacement de l'actuel
//        // puis supprime
//        NewCookie toDelete = new NewCookie(Cookie, null, "/", null, null, 0, true);
//        Response res = Response.ok("DONE",MediaType.TEXT_PLAIN)
//                .cookie(toDelete)
//                .build();
//        return res;
//    }
//
//
//
//}
