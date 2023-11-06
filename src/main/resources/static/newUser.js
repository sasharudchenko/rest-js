const addForm = document.getElementById("add-form");
addForm.addEventListener('submit', (e) => {  //
    e.preventDefault();

    let nameValue = document.getElementById("newName").value;
    let surnameValue = document.getElementById("newSurname").value;
    let cityValue = document.getElementById("newCity").value;
    let ageValue = document.getElementById("newAge").value;
    let usernameValue = document.getElementById("newUsernameu").value;
    let passwordValue = document.getElementById("newPassword").value;
    let roles = getRoles(Array.from(document.getElementById("addRoles").selectedOptions).map(role => role.value));
    let newUser = {
        name: nameValue,
        surname: surnameValue,
        city: cityValue,
        age: ageValue,
        username: usernameValue,
        password: passwordValue,
        roles: roles
    }
    console.log(newUser)
      fetch("http://localhost:8080/api/admin", { //
        method: "POST",
        headers: {
            // 'Accept': 'application/json',
            'Content-Type': 'application/json;'
        },
        body: JSON.stringify(newUser)

    })

          .then((data) => {
             console.log(JSON.stringify(newUser))
              allUsers(data)
        addForm.reset()
        document.getElementById("nav-home-tab").click();
        })
        .catch(err => {
            console.error(err);
        });
})
function getRoles(role) {
    let roles = [];
    if (role.indexOf("ADMIN") >= 0) {
        roles.push({"name": 'ROLE_ADMIN'});
    }
    if (role.indexOf("USER") >= 0) {
        roles.push({"name": 'ROLE_USER'});
    }
    return roles;
}