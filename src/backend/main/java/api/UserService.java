package backend.main.java.api;

import backend.main.java.models.User;
import backend.main.java.models.Address;
import backend.main.java.models.Payment;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("user") public class UserService
{
	@GET @Path("information") @Produces(MediaType.APPLICATION_JSON) public Response getUser(
		@QueryParam("sessionID") final String session)
	{
		//get by session id
		//id = getIdfromDatabase(session)
		//getUserbyID(id)
		return Response.ok(User.getRandomUser()).build();
	}

	@POST @Consumes(MediaType.APPLICATION_JSON) public Response registerUser(@Context UriInfo uriInfo, final User user)
	{
		// create in database
		final URI locationURI = uriInfo.getAbsolutePathBuilder().path(Long.toString(user.getId())).build();
		return Response.created(locationURI).build();
	}

	@PUT @Consumes(MediaType.APPLICATION_JSON) public Response modifyUser(
		@QueryParam("sessionID") final String sessionID, final User user)
	{
		// edit in database
		return Response.ok(user).build();
	}

	@DELETE public Response deleteUser(@QueryParam("sessionID") final String sessionID)
	{
		//get user from database with sessionID and delete it
		return Response.noContent().build();
	}

	@Path("payment") @GET @Produces(MediaType.APPLICATION_JSON) public Response getUserPayment(
		@QueryParam("sessionID") String sessionID)
	{
		// get from database with sessionID
		return Response.ok(Payment.getRandomPayment()).build();
	}

	@Path("payment") @PUT @Consumes(MediaType.APPLICATION_JSON) public Response modifyUserPayment(
		@QueryParam("sessionID") String sessionID, final Payment payment)
	{
		// get from database with sessionID
		// modify payment in database
		return Response.ok(payment).build();
	}

	@Path("payment") @DELETE public Response deleteUserPayment(@QueryParam("sessionID") String sessionID)
	{
		// get user with session id and delete related payment info
		return Response.noContent().build();
	}

	@GET @Path("{id}") @Produces(MediaType.APPLICATION_JSON) public Response getAddress(
		@QueryParam("sessionID") String semester, @PathParam("id") final long id)
	{
		return Response.ok(Address.getRandomAddress()).build();
	}

	@Path("address") @POST @Produces(MediaType.APPLICATION_JSON) public Response createAddress(
		@QueryParam("sessionID") String sessionID, @Context UriInfo uriInfo, final Address address)
	{
		// post to database with sessionID
		final URI locationURI = uriInfo.getAbsolutePathBuilder().path(Long.toString(address.getId())).build();
		return Response.created(locationURI).build();
	}

	@Path("address") @PUT @Consumes(MediaType.APPLICATION_JSON) public Response modifyAddress(
		@QueryParam("sessionID") String sessionID, final Address address)
	{
		// edit in database
		return Response.ok(address).build();
	}

	@Path("address/{id}") @DELETE public Response deleteAddress(@QueryParam("sessionID") String sessionID,
		@PathParam("id") final int id)
	{
		// find user with session ID and corresponding address
		return Response.noContent().build();
	}

	@Path("mail") @GET @Produces(MediaType.APPLICATION_JSON) public Response getUserMail(
		@QueryParam("sessionID") String sessionID)
	{
		// get from database with sessionID
		return Response.ok("email@mail.com").build();
	}

	@Path("mail") @PUT @Consumes(MediaType.APPLICATION_JSON) public Response createUserMail(
		@QueryParam("sessionID") String sessionID, @Context UriInfo uriInfo, final String mail)
	{
		// get user id from database with sessionID and put new user

		final URI locationURI = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(locationURI).build();
	}

	@Path("newsletter") @POST public Response turnOnNewsletter()
	{
		// turn on newsletter
		return Response.ok("signed in for newsletter").build();
	}

	@Path("newsletter") @DELETE public Response turnOffNewsletter()
	{
		// turn off newsletter
		return Response.ok("cancelled newsletter subscription").build();
	}

	@Path("password") @PUT public Response modifyPassword(@QueryParam("sessionID") final String sessionID,
		final String password)
	{
		// get user with sessionID
		// modify in database

		return Response.ok(password).build();
	}

}
