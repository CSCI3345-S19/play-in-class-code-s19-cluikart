import org.scalatest._
import org.scalatestplus.play._
import play.api.http.MimeTypes
import play.api.test._
import org.scalatestplus.play.guice.GuiceOneServerPerSuite


class LoginSpec extends PlaySpec with GuiceOneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory{
  "The Login" must {
    "provide a username and password" in {
      go to s"http://localhost:$port/"
      click on "name"
      textField("name").value = "Shea"
      click on "pass"
      textField("pass").value = "Jelly"
      submit()
      eventually { pageTitle mustBe "Tasks"}
      
    }
    "be able to logout" in {
      go to s"http://localhost:$port/"
      click on "name"
      textField("name").value = "Shea"
      click on "pass"
      textField("pass").value = "Jelly"
      submit()
      eventually { 
        pageTitle mustBe "Tasks"
        click on id("logout")
        eventually {
          pageTitle mustBe "Login"
        }
      }
      
    }
    "provide incorrect Login Info" in {
      go to s"http://localhost:$port/"
      click on "name"
      textField("name").value = "Shea"
      click on "pass"
      textField("pass").value = "BadPass"
      submit()
      eventually { pageTitle mustBe "Login"}
    }
    "Create a New Account" in {
      go to s"http://localhost:$port/"
      click on "newName"
      textField("newName").value = "Lord"
      click on "newPass"
      textField("newPass").value = "FarQuad"
      submit()
      eventually { pageTitle mustBe "Tasks"}
    }
    "Add Tasks to Account" in {
      go to s"http://localhost:$port/"
      click on "name"
      textField("name").value = "Shea"
      click on "pass"
      textField("pass").value = "Jelly"
      submit()
      eventually { 
        pageTitle mustBe "Tasks"
        //go to s"http://localhost:$port/postUser"
        click on "newData"
        textField("newData").value = "Dr. Lewis wuz Hur"
        submit()
        eventually {
          pageTitle mustBe "Tasks"
          findAll(tagName("td")).exists(e => e.text.contains("Dr. Lewis wuz Hur")) mustBe (true)
          }
        }
    }
      "Remove Task from Account" in {
      go to s"http://localhost:$port/"
      click on "name"
      textField("name").value = "Shea"
      click on "pass"
      textField("pass").value = "Jelly"
      submit()
      eventually { 
        pageTitle mustBe "Tasks"
        //go to s"http://localhost:$port/postUser"
        click on "newData"
        textField("newData").value = "Dr. Lewis wuz not Hur"
        submit()
        eventually {
          pageTitle mustBe "Tasks"
          click on id("delDr. Lewis wuz not Hur")
          //submit()
          eventually {
            pageTitle mustBe "Tasks"
            findAll(tagName("td")).forall(e => e.text.contains("Dr. Lewis wuz not Hur")) mustBe (false)
          }
        }
          
        }
    }
      "Save user Data" in {
      go to s"http://localhost:$port/"
      click on "newName"
      textField("newName").value = "Lord"
      click on "newPass"
      textField("newPass").value = "FarQuad"
      submit()
      eventually { 
        pageTitle mustBe "Tasks"
        click on "newData"
        textField("newData").value = "Dr. Lewis wuz Hur"
        submit()
        eventually {
          pageTitle mustBe "Tasks"
          findAll(tagName("td")).exists(e => e.text.contains("Dr. Lewis wuz Hur")) mustBe (true)
          click on id("logout")
          eventually {
            pageTitle mustBe "Login"
            click on "name"
            textField("name").value = "Lord"
            click on "pass"
            textField("pass").value = "FarQuad"
            submit()
            eventually { 
              pageTitle mustBe "Tasks"
              findAll(tagName("td")).exists(e => e.text.contains("Dr. Lewis wuz Hur")) mustBe (true)
            }
          }
          }
        
        }
    }
  }
  
  
}