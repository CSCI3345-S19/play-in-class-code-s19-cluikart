package Models

object users {
  var usersData = collection.mutable.Map[String, collection.mutable.ArrayBuffer[String]]()
  var passwords = collection.mutable.Map[String, String]()
  
 
  
  addUser("Shea", "Jelly")
  addUser("Santa", "Claus")
  

  
  addData("Shea", "Beans")
  addData("Shea", "Ton")
  addData("Shea", "Belly")
  
  def addData(name: String, data: String){
    var tmp = usersData(name)
    tmp += data
    usersData(name) = tmp
    
    
  }
  
  def removeData(name: String, data:String){
    var tmp = usersData(name)
    tmp -= data
    usersData(name) = tmp
    
    
  }
  
  
  
  def addUser(name: String, pass: String) {
    
    usersData += (name -> collection.mutable.ArrayBuffer[String]())
    passwords += (name -> pass)
    
  }
  
  def getData(name: String): collection.mutable.ArrayBuffer[String] ={
    return usersData(name)
    
    
  }
  
  def validateUser(name:String, pass:String): Boolean = {
    //print(passwords)
    //println(name, pass)
    if(passwords.contains(name)){
      //println("Contains Pass")
    if(passwords(name) != pass){
      //println("False")
      return false
      
    } else {
       //println("True")
      return true
    }
    } else { 
      //println("False")
      return false}
    
  }
  
}