window.onload = function() {
    document.getElementById("login-btn").addEventListener("click", login)
}

function login() {
    let username = document.getElementById("username").value
    let password = document.getElementById("password").value

    fetch('/FoundationProject/auth', {
        method: 'POST',
        body: JSON.stringify({username, password})
    }) .then((res) => {
        console.log(res)
        if (res.ok) {
            console.log("Logged in")
        } else {

        console.log("Invalid credentials")
        }
    }) .catch ((err) => {
        console.log("FAILED")
    }) 
}