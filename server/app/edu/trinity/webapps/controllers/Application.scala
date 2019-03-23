package edu.trinity.webapps.controllers

import javax.inject._

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import edu.trinity.webapps.shared.SharedMessages
//import play.api.i18n._
//import models._

case class UserForm(name: String, pass: String)

object UserForm {
  val form: Form[UserForm]= Form(
    mapping(
          "name" -> nonEmptyText,
          "pass" -> nonEmptyText
          )(UserForm.apply)(UserForm.unapply)
    
    )    
  
}

@Singleton
class Application @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  
  
    
   // def forms() = Action { implicit request: Request[AnyContent] =>
    //Ok(views.html.index(UserForm.form))
    
    
  //}
  
  def createAccount = Action{ implicit request =>
    val postBody = request.body.asFormUrlEncoded
    postBody.map { args =>
      try {
        val name = args("newName").head.toString
        val pass = args("newPass").head.toString
        println("WEEEEE")
        Models.users.addUser(name, pass)
        Ok(views.html.UserData(Models.users.getData(name))).withSession("username" -> name)
        
        
          
        
      } catch {
        case ex: NumberFormatException => Redirect("index", 200)
      }
    }.getOrElse(Redirect("index", 200))
  }
    
    
    
  
  
  def removeData = Action { implicit request =>
    val postBody = request.body.asFormUrlEncoded
    postBody.map { args =>
      try {
        val data = args("data").head.toString
        
        
            request.session.get("username").map {  user =>
            Models.users.removeData(user, data)
            Ok(views.html.UserData(Models.users.getData(user)))   
          
        }.getOrElse{
          
          Ok(views.html.index(request.session.get("username")))
        }
        
          
        
      } catch {
        case ex: NumberFormatException => Redirect("index", 200)
      }
    }.getOrElse(Redirect("index", 200))
  }

  
  def addData = Action { implicit request =>
    val postBody = request.body.asFormUrlEncoded
    postBody.map { args =>
      try {
        val data = args("newData").head.toString
        
        
            request.session.get("username").map {  user =>
            Models.users.addData(user, data)
            Ok(views.html.UserData(Models.users.getData(user)))   
          
        }.getOrElse{
          
          Ok(views.html.index(request.session.get("username")))
        }
        
          
        
      } catch {
        case ex: NumberFormatException => Redirect("index", 200)
      }
    }.getOrElse(Redirect("index", 200))
  }

  
  
  
  def postUser = Action { implicit request =>
    val postBody = request.body.asFormUrlEncoded
    postBody.map { args =>
      try {
        val name = args("name").head.toString
        val pass = args("pass").head.toString
        
        val valid = Models.users.validateUser(name, pass).booleanValue()
        
        //println(valid)
        
        if(valid){
          //println("WEEEEEEEEEEEEEEE")
          Ok(views.html.UserData(Models.users.getData(name))).withSession("username" -> name)
        } else {
          
          Ok(views.html.index(request.session.get("username")))
          
        }
      } catch {
        case ex: NumberFormatException => Redirect("index", 200)
      }
    }.getOrElse(Redirect("index", 200))
  }

  def logOut = Action { implicit request =>
    Ok(views.html.index(request.session.get("username")))    
    
  }
  
  

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