userPrincipal()

function userPrincipal() {
    fetch("api/admin/userInfo")
        .then(res => res.json())
        .then(user => {
            $('#username').append(`<a class="navbar-brand">${user.username}</a>`)
            $('#role').append(`<a class="navbar-brand">${user.roles.map((el) => el.name.toString())}</a>`)
        })
}


allUsers()

function allUsers() {
    fetch("/api/admin")
        .then(res => res.json())
        .then(users => {


            const userTable = document.getElementById('userOutput');
            userTable.innerHTML = ''
            users.forEach(user => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.city}</td>
                    <td>${user.age}</td>
                    <td>${user.username}</td>
                    <td>****</td>
                    <td>${user.roles.map((el) => el.name.toString())}</td>
                    <td>
                    <button class="btn btn-info" type="button" data-bs-toggle="modal"
                     data-bs-target="#modalEdit"
                    onclick="editModal(${user.id})">Обновить</button></td>
                    <td>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                     data-bs-target="#modalDelete"
                     onclick="deleteModal(${user.id})">Удалить</button></td>
                    <tr/>
                `;
                userTable.appendChild(row);
            });
        })
}

function editModal(id) {
    fetch("api/admin/" + id, {
        headers: {

            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(us => {

            document.getElementById('idEdit').value = us.id;
            document.getElementById('nameEdit').value = us.name;
            document.getElementById('surnameEdit').value = us.surname;
            document.getElementById('cityEdit').value = us.city;
            document.getElementById('ageEdit').value = us.age;
            document.getElementById('usernameEdit').value = us.username;
            document.getElementById('passwordEdit').value = us.password;

        })
    });


}

const editUser = document.getElementById("editUser");
editUser.addEventListener('submit', (e) => {
    // e.show();
    e.preventDefault();
    let idValue = document.getElementById("idEdit").value;
    let nameValue = document.getElementById("nameEdit").value;
    let surnameValue = document.getElementById("surnameEdit").value;
    let cityValue = document.getElementById("cityEdit").value;
    let ageValue = document.getElementById("ageEdit").value;
    let usernameValue = document.getElementById("usernameEdit").value;
    let passwordValue = document.getElementById("passwordEdit").value;
    let roles = editRoles(Array.from(document.getElementById("rolesEdit").selectedOptions).map(role => role.value));
    let newUser = {
        id: idValue,
        name: nameValue,
        surname: surnameValue,
        city: cityValue,
        age: ageValue,
        username: usernameValue,
        password: passwordValue,
        roles: roles
    }
    fetch("api/admin/" + document.getElementById('idEdit').value, {
        method: "PATCH",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newUser)
    }).then(() => {
        document.getElementById("closeEditForm").click();
        allUsers()
    })
})

function editRoles(rols) {
    let roles = [];
    if (rols.indexOf("ADMIN") >= 0) {
        roles.push({"name": 'ROLE_ADMIN'});
    }
    if (rols.indexOf("USER") >= 0) {
        roles.push({"name": 'ROLE_USER'});
    }
    return roles;
}

function deleteModal(id) {
    fetch("api/admin/" + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(us => {
            document.getElementById("idDelete").value = us.id
            document.getElementById("nameDelete").value = us.name;
            document.getElementById("surnameDelete").value = us.surname;
            document.getElementById("cityDelete").value = us.city;
            document.getElementById("ageDelete").value = us.age;
            document.getElementById("usernameDelete").value = us.username;
            document.getElementById("passwordDelete").value = us.password;
        })
    });
}

const deleteUser = document.getElementById("deleteUser");
deleteUser.addEventListener('submit', (e) => {
    e.preventDefault()
    fetch("api/admin/" + document.getElementById('idDelete').value, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(() => {
        document.getElementById("closeDeleteForm").click();

        allUsers()
    })
})


