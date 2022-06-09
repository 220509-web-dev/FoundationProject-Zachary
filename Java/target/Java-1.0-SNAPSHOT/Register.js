window.onload = function() {
    document.getElementById("login-btn").addEventListener("click", Register)
}

function Register() {
    let first_name = document.getElementById("first_name").value
    let last_name = document.getElementById("last_name").value
    let email = document.getElementById("email").value
    let username = document.getElementById("username").value
    let password = document.getElementById("password").value
    let role_id = document.getElementById("role_id").value
    let department_id = document.getElementById("department_id").value

    fetch('/FoundationProject/users', {
        method: "POST",
        body: JSON.stringify({first_name, last_name, email, username, password, role_id, department_id})
    }) .then(res => {
        console.log(res)
        window.location.href = 'index.html'
    }) .catch (err => {
        console.log(err)
    })
}