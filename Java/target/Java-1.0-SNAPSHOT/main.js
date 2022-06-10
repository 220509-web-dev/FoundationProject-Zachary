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
        console.log("Logged in")
    }) .catch ((err) => {
        console.log("FAILED")
    }) 
}