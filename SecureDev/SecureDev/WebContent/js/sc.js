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
var onlyletters = /^[a-zA-Z]{2,20}$/;   
if (name == null || name=="")
    {
        alert("Please fill your first name");
        return false;
    }
else
    if(!name.match(onlyletters)){
            alert("Please enter your name with only 2-20 letters");
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
            alert("Please enter your last name with only 2-20 letters");
            return false;
        }
//validate user name
var letterNumber = /^[0-9a-zA-Z]{2,12}$/; 
var username = document.registrationForm.username.value;
if (username == null || username=="")
    {
        alert("Please fill user name");
        return false;
    }
else
    if(!username.match(letterNumber)){
            alert("Please enter your user name with only 2-12 letters and numbers");
            return false;
        }
var passreg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,16}$/;
var password = document.registrationForm.password.value;
if (password == null || password=="")
    {
        alert("Please fill password");
        return false;
    }
else
    if(!password.match(passreg)){
            alert("Please enter your password  with 8-16 charachters, at least one sign and at least one capital letter");
            return false;
        }
var bdayreg = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/; 
var bdate = document.registrationForm.bdate.value;
if (bdate == null || bdate=="")
    {
        alert("Please fill your Birth Date");
        return false;
    }
else
    if(!bdate.match(bdayreg)){
            alert("Please enter your Birth Date in format dd/mm/yyyy");
            return false;
        }
var image = document.registrationForm.img.value;
if (image) {
        var Extension = FileUploadPath.substring(
        image.lastIndexOf('.') + 1).toLowerCase();
        	if (!Extension == "jpg") {
        		alert("Please upload only jpg image");
        		return false;
        	}
     }
var phoneReg = /^\d{10}$/;
var phone = document.registrationForm.telephone.value;
if (phone == null || phone=="")
{
    alert("Please fill the phone number");
    return false;
}
else
if(!phone.match(phoneReg)){
        alert("Please enter your phone number with only 10 numbers");
        return false;
    }
return true;
}

function validatepostForm(){
	var newPTitle = document.postForm.title.value;
	var newP = document.postForm.content.value;
	var onlyletters = /^[a-zA-Z]{2,20}$/;   
	var regex = /^([A-Za-z0-9\s\d])*(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)?([a-z0-9])+([\-\.]{1}[a-z0-9]+)*(\.[a-z]{2,5})?(:[0-9]{1,5})?(\/.*)?([A-Za-z0-9\s\d])+$/;
    if(newP == null || newP=="" ){
		alert("Please add your content");
		return false;
	}else
	if(newPTitle == null || newPTitle==""){
		alert("Please add your title");
		return false;
	}else
	if (!newPTitle.match(regex)){
		alert("Please fill only letters in the title");
		return false;
	}else
		if (!newP.match(regex)){
			alert("Please fill only regular Characheters in the post");
			return false;
		}
	return true;

}
function validatecommentForm(){
	var newc = document.commentForm.content.value;
	var regex = /^([A-Za-z0-9\s\d])*(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)?([a-z0-9])+([\-\.]{1}[a-z0-9]+)*(\.[a-z]{2,5})?(:[0-9]{1,5})?(\/.*)?([A-Za-z0-9\s\d])+$/;
    if(newC == null || newC=="" ){
		alert("Please add your content");
		return false;
	}else
	if (!newC.match(regex)){
		alert("Please fill only regular Characheters");
		return false;
	}
	return true;

}
function validateeventForm(){
	var event_name = document.eventForm.event_name.value;
	var onlyletters = /^[a-zA-Z]{2,20}$/;   
	var regex = /^([A-Za-z0-9\s\d])*(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)?([a-z0-9])+([\-\.]{1}[a-z0-9]+)*(\.[a-z]{2,5})?(:[0-9]{1,5})?(\/.*)?([A-Za-z0-9\s\d])+$/;
    if(event_name == null || event_name=="" ){
		alert("Please add event name");
		return false;
	}else
	if (!event_name.match(regex)){
			alert("Please fill only letters in the event name");
			return false;
		}
	var date = document.eventForm.date.value;
	var datereg = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;
	if (date == null || date=="")
    {
        alert("Please add the event Date");
        return false;
    }
else
    if(!bdate.match(datereg)){
            alert("Please enter the event Date in format dd/mm/yyyy");
            return false;
        }
	var time = document.eventForm.time.value;
	var timereg = /^\s*([01]?\d|2[0-3]):?([0-5]\d)\s*$/;
	if (time == null || time=="")
    {
        alert("Please fill the event time");
        return false;
    }
else
    if(!time.match(timereg)){
            alert("Please enter the event time in format hh:mm");
            return false;
        }
	var description = document.eventForm.description.value;
	if(description == null || description=="" ){
		alert("Please add the event description");
		return false;
	}else
	if (!description.match(regex)){
		alert("Please fill only regular Characheters in the description");
		return false;
	}
	var letterNumber = /^[0-9a-zA-Z]{2,12}$/; 
	var location = document.eventForm.location.value;
	if(location == null || location=="" ){
		alert("Please add the event description");
		return false;
	}else
	if (!location.match(letterNumber)){
		alert("Please fill only regular only letters and numbers in the location");
		return false;
	}

	return true;

}

function validateSearch(){
	var search = document.search.firstname.value;
	var onlyletters = /^[a-zA-Z]{2,20}$/;   
	if (search == null || search=="")
	    {
	        alert("Please fill the search with first name");
	        return false;
	    }
	else
	    if(!search.match(onlyletters)){
	            alert("Please enter name to search with only 2-20 letters");
	            return false;
	        }
	return true;
}
function validateProfileForm(){
	//validate first name
	var name = document.editProfile.firstname.value;
	var onlyletters = /^[a-zA-Z]{2,20}$/;   
	if (name == null || name=="")
	    {
	        alert("Please fill your first name");
	        return false;
	    }
	else
	    if(!name.match(onlyletters)){
	            alert("Please enter your name with only 2-20 letters");
	            return false;
	        }
	//validate last name
	var lastname = document.editProfile.lastname.value;
	if (lastname == null || lastname=="")
	    {
	        alert("Please fill your last name");
	        return false;
	    }
	else
	    if(!lastname.match(onlyletters)){
	            alert("Please enter your last name with only 2-20 letters");
	            return false;
	        }
	//validate user name
	var letterNumber = /^[0-9a-zA-Z]{2,12}$/; 
	var username = document.editProfile.username.value;
	if (username == null || username=="")
	    {
	        alert("Please fill user name");
	        return false;
	    }
	else
	    if(!username.match(letterNumber)){
	            alert("Please enter your user name with only 2-12 letters and numbers");
	            return false;
	        }
	var passreg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,16}$/;
	var password = document.editProfile.password.value;
	if (password == null || password=="")
	    {
	        alert("Please fill password");
	        return false;
	    }
	else
	    if(!password.match(passreg)){
	            alert("Please enter your password  with 8-16 charachters, at least one sign and at least one capital letter");
	            return false;
	        }
	var bdayreg = /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/; 
	var bdate = document.editProfile.bdate.value;
	if (bdate == null || bdate=="")
	    {
	        alert("Please fill your Birth Date");
	        return false;
	    }
	else
	    if(!bdate.match(bdayreg)){
	            alert("Please enter your Birth Date in format dd/mm/yyyy");
	            return false;
	        }
	var phoneReg = /^\d{10}$/;
	var phone = document.editProfile.telephone.value;
	if (phone == null || phone=="")
    {
        alert("Please fill the phone number");
        return false;
    }
else
    if(!phone.match(phoneReg)){
            alert("Please enter your phone number with only 10 numbers");
            return false;
        }
	return true;
}