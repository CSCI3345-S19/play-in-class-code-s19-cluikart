package edu.trinity.webapps.controllers

import javax.inject._

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import edu.trinity.webapps.shared.SharedMessages
//import play.api.i18n._
//import models._



@Singleton
class Application @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  
  
    
   // def forms() = Action { implicit request: Request[AnyContent] =>
    //Ok(views.html.index(UserForm.form))
    
    
  //}
  
  
  
  

  def index = Action { implicit request =>
    
    Ok(views.html.index(request.session.get("username")))
        //SharedMessages.itWorks))
  }
  
  def multTable = Action {
    
    Ok(views.html.multTable(12))
  }

  def deepFile = Action {
    Ok("This isn't a file.")
  }
  
  def enterName = Action { implicit request =>
    println("WEEEEEEEEEEE");
    Ok(views.html.enterName(request.session.get("username")))
  }  
  
  def rememberName(name: String) = Action { implicit request =>
    Redirect(routes.Application.enterName).
      withSession("username" -> name, "userid" -> "000")
  }
  
  def forgetName = Action { implicit request =>
    Redirect(routes.Application.enterName).withNewSession
  }
  
  
  
}