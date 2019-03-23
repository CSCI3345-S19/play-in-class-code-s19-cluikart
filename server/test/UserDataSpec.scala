import org.scalatestplus.play._

class UserDataSpec extends PlaySpec{
  "UserDataModel" must {
    "Add User" in {
      Models.users.addUser("Jack", "Dorsie")
      Models.users.passwords.contains("Jack") mustBe (true)
      Models.users.passwords("Jack") mustBe "Dorsie"
    }
    "Validate User" in {
      Models.users.addUser("Jack", "Dorsie")
      Models.users.validateUser("Jack", "Dorsie") mustBe true
    }
    "Add Data" in {
      Models.users.addUser("Jack", "Dorsie")
      Models.users.addData("Jack", "Chirp")
      Models.users.usersData.contains("Jack") mustBe true
      Models.users.usersData("Jack") mustBe collection.mutable.ArrayBuffer[String]("Chirp")
    }
    "Remove Data" in {
      Models.users.addUser("Jack", "Dorsie")
      Models.users.addData("Jack", "Chirp")
      Models.users.removeData("Jack", "Chirp") 
      Models.users.usersData("Jack") mustBe collection.mutable.ArrayBuffer[String]()
    }
    "Get Data" in {
      Models.users.addUser("Jack", "Dorsie")
      Models.users.addData("Jack", "Chirp")
      Models.users.getData("Jack") mustBe collection.mutable.ArrayBuffer[String]("Chirp")
      
    }
  }
}