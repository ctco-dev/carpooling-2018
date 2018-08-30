function collectDto(){
    var usernameTxt = document.getElementById("username-txt");
    var password1Txt = document.getElementById("password1-txt");
    var password2Txt = document.getElementById("password2-txt");
    var name = document.getElementById("name-txt");
    var surname = document.getElementById("surname-txt");
    var phoneNumber = document.getElementById("phoneNumber-txt");
    var pwd1 = password1Txt.value;
    var pwd2 = password2Txt.value;
    if (pwd1 !== pwd2) {
        showError("Passwords doesn't match!")
        return;
    }
    var dto = {
        "username": usernameTxt.value,
        "password": pwd1,
        "name": name.value,
        "surname": surname.value,
        "phoneNumber": phoneNumber.value
    }
    register(dto)
}
function switchRegistration() {
    hideError();
    var checkbox = document.getElementById("register-cb");
    var checkbox_carRegButton = document.getElementById("register_car-grp");
    var checkbox_carReg = document.getElementById("register_car-cb");
    var pwd2 = document.getElementById("password2-grp");
    var loginBtn = document.getElementById("login-btn");
    var registerBtn = document.getElementById("register-btn");
    var name = document.getElementById("name-grp");
    var surname = document.getElementById("surname-grp");
    var phoneNumber = document.getElementById("phoneNumber-grp");
    if (checkbox.checked) {
        pwd2.classList.remove("w3-hide");
        name.classList.remove("w3-hide");
        surname.classList.remove("w3-hide");
        phoneNumber.classList.remove("w3-hide");
        loginBtn.classList.add("w3-hide");
        registerBtn.classList.remove("w3-hide");
    } else {
        pwd2.classList.add("w3-hide");
        loginBtn.classList.remove("w3-hide");
        registerBtn.classList.add("w3-hide");
        name.classList.add("w3-hide");
        surname.classList.add("w3-hide");
        phoneNumber.classList.add("w3-hide");
    }
}
function login() {
    hideError();
    console.log("start login");
    var usernameTxt = document.getElementById("username-txt");
    var passwordTxt = document.getElementById("password1-txt");
    var dto = {
        "username": usernameTxt.value,
        "password": passwordTxt.value
    };
    console.log("sending login data");
    fetch('/api/auth/login', {
        "method": "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dto)
    }).then(function (response) {
        if (response.status === 200) {
            console.log("login success");
            location.href = "/main.jsp";
        } else {
            showError("Username or Password is incorrect!");
        }
    })
}
function register(data) {
    hideError();
    console.log("sending registration data");
    fetch('/api/auth/register', {
        "method": "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(function (response) {
        if (response.status === 200) {
            console.log("registration success");
            location.href = "/main.jsp";
        } else if (response.status === 401) {
            showError("Something is wrong!");
        } else {
            response.json().then(function(json) {
                switch(json.errorCode) {
                    case "CONFLICT":
                        showError("A user with the same username already exists!");
                        break;
                    case "BAD_USERNAME":
                        showError("Username is invalid!");
                        break;
                    case "BAD_PASSWORD":
                        showError("Password is invalid!");
                        break;
                    default:
                        showError("Something is wrong!");
                }
            })
        }
    })
}
function hideError() {
    var errorPanel = document.getElementById("error-panel");
    errorPanel.classList.add("w3-hide");
}
function showError(msg) {
    var errorPanel = document.getElementById("error-panel");
    errorPanel.classList.remove("w3-hide");
    w3DisplayData("error-panel", {"message" : msg});
}
