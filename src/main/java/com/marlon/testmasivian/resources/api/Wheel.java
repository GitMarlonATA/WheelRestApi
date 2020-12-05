package com.marlon.testmasivian.resources.api;

import com.marlon.testmasivian.actions.ActionsDatabase;
import com.marlon.testmasivian.logic.Logic;
import com.marlon.testmasivian.model.Bet;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("wheel")
public class Wheel {
    @Context
    private UriInfo context;
    private ActionsDatabase actions = new ActionsDatabase();
    private Logic logic = new Logic();
    public Wheel() {
    }
    @POST
    @Produces("application/json; charset=UTF-8")
    @Consumes("application/json; charset=UTF-8")
    public String createWheels() {
        int resultado = actions.createWheel();
        if(resultado!=-1){
            return "{\"state\":\"success\", \"info\" : \" the id is " + resultado + "\"}";
        }
        return "{\"state\":\"error\", \"info\" : \" the id is " + resultado + "\"}";
    }
    @POST
    @Path("/activate/{idWheel}")
    @Produces("application/json; charset=UTF-8")
    @Consumes("application/json; charset=UTF-8")
    public String activateWheel(@PathParam("idWheel") int idWheel) {
        String resultado = actions.activateWheel(idWheel);
        if(!resultado.equals("")){
            return "{\"state\":\"success\", \"info\" : \" " + resultado + "\"}";
        }
        return "{\"state\":\"error\", \"info\" : \"there was a problem acivating the wheel " + idWheel + "\"}";
    }
    
    @POST
    @Path("/bet")
    @Produces("application/json; charset=UTF-8")
    @Consumes("application/json; charset=UTF-8")
    public String createBet(Bet bet) {
        if(logic.verifyBet(bet)){
            String resultado = actions.createBet(bet);
            if(!resultado.equals("")){
                return "{\"state\":\"success\", \"info\" : \" " + resultado + "\"}";
            }else{
                return "{\"state\":\"error\", \"info\" : \"there was a problem creating the bet \"}";
            }
        }else{
         return "{\"state\":\"error\", \"info\" : \"there was a problem creating the bet, if the kind is color, it must be black or red, if the kind is number, it must be between 0 and 36 \"}";   
        }
    }
    
    @GET
    @Produces("application/json; charset=UTF-8")
    @Consumes("application/json; charset=UTF-8")
    public String listWheels() {
        String resultado = actions.listWheels();
        if(!resultado.equals("")){
            return "{\"state\":\"success\", \"info\" : \" " + resultado + "\"}";
        }else{
            return "{\"state\":\"error\", \"info\" : \"there was a problem listing the wheels \"}";
        }
    }
    
    @POST
    @Path("/deactivate/{idWheel}")
    @Produces("application/json; charset=UTF-8")
    @Consumes("application/json; charset=UTF-8")
    public String deactivateWheel(@PathParam("idWheel") int idWheel) {
        String resultado = actions.deactivateWheel(idWheel);
        if(!resultado.equals("")){
            List<Bet> bets = actions.findBets(idWheel);
            String winners = logic.defineWinners(bets);
            return "{\"state\":\"success\", \"info\" : \" " + resultado + "\", \"winners\":" + winners + "}";
        }
        return "{\"state\":\"error\", \"info\" : \"there was a problem acivating the wheel " + idWheel + "\"}";
    }
}
