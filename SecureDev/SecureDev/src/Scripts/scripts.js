/**
 * 
 */
function validateRegistrationForm(){
//validate the ID number
var id = document.registrationForm.id.value;
if(id == null || id==""){
    alert("You must fill your ID number");
    return false;
}else
    if(id.length<9){
        alert ("Please fill in correct ID number with 9 figuers");
        return false;
    }else
    if(isNaN(id))
        {
            alert("Please fill only numbers in the ID section");
            return false;
        }
//validate first name
var name = document.registrationForm.firstname.value;
var onlyletters = /^[a-zA-Z]*$/;   
if (name == null || name=="")
    {
        alert("Please fill your first name");
        return false;
    }
else
    if(!name.match(onlyletters)){
            alert("Please enter your name with only letters");
            return false;
        }
//validate last name
var lastname = document.registrationForm.lastname.value;
if (lastname == null || lastname=="")
    {
        alert("Please fill your last name");
        return false;
    }
else
    if(!lastname.match(onlyletters)){
            alert("Please enter your last name with only letters");
            return false;
        }
//validate user name
var letterNumber = /^[0-9a-zA-Z]+$/; 
var username = document.registrationForm.username.value;
if (username == null || username=="")
    {
        alert("Please fill user name");
        return false;
    }
else
    if(!username.match(letterNumber)){
            alert("Please enter your user name with only letters and numbers");
            return false;
        }
var password = document.registrationForm.password.value;
if (password == null || password=="")
    {
        alert("Please fill password");
        return false;
    }
else
    if(!password.match(letterNumber)){
            alert("Please enter your password  with only letters and numbers");
            return false;
        }
var onlynumbers = /^[0-9]+$/; 
var bdate = document.registrationForm.bdate.value;
if (bdate == null || bdate=="")
    {
        alert("Please fill your Birth Date");
        return false;
    }
else
    if(!bdate.match(onlynumbers)){
            alert("Please enter your Birth Date with only numbers");
            return false;
        }
}