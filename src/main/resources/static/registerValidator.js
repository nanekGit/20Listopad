function validate(){
    var login = document.forms["register-form"]["login"].value;
    var pass = document.forms["register-form"]["pass"].value;
    var pass2 = document.forms["register-form"]["pass2"].value;

    //To samo tylko bardziej unikatowe, oba value biorą ID nie NAME
    //login = document.getElementById("login").value;
    //pass = document.getElementById("pass").value;
    //pass2 = document.getElementById("pass2").value;

    //Minimum 5 znaków z zakresów A-Z a-z 0-9, a potem byle co
    var regex = new RegExp(/[A-Za-z0-9]{5}.*/);
    var flag = true;

    if(!regex.test(login)){
        document.getElementById("login").style.backgroundColor = "red";
        flag = false;
    }else{
        document.getElementById("login").style.backgroundColor = "white";
    }

    if(!regex.test(pass)){
        document.getElementById("pass").style.backgroundColor = "red";
        flag = false;
    }else{
        document.getElementById("pass").style.backgroundColor = "white";
    }

    if(!regex.test(pass2)){
        document.getElementById("pass2").style.backgroundColor = "red";
        flag = false;
    }else{
        document.getElementById("pass2").style.backgroundColor = "white";
    }

    if(pass!==pass2){
        document.getElementById("pass").style.backgroundColor = "red";
        document.getElementById("pass2").style.backgroundColor = "red";
        flag = false;
    }else if(flag){
        document.getElementById("pass").style.backgroundColor = "white";
        document.getElementById("pass2").style.backgroundColor = "white";
    }

    return flag;
}