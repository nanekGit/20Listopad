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
        alert("Login za krótki, minimum 5 znaków.");
        flag = false;
    }

    if(!regex.test(pass)){
        alert("Hasło za krótkie, minimum 5 znaków.");
        flag = false;
    }

    if(pass!==pass2){
        alert("Hasło i Powtórz Hasło się różnią.");
        flag = false;
    }
    return flag;
}