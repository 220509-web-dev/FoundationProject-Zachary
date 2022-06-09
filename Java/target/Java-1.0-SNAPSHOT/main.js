window.onload = function() {
    document.getElementById("login-btn").addEventListener("click", login)
}

function login() {
    console.log("TEST")
    let username = document.getElementById("username").value
    let password = document.getElementById("password").value

    fetch('/FoundationProject/auth/login', {
        method: 'POST',
        body: JSON.stringify({username, password})
    }) .then((res) => {
        console.log(res)
    }) .catch ((err) => {
        console.log("FAILED")
    })
}
