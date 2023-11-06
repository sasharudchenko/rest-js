oneUser()
function oneUser() {
    fetch("/api/user")
        .then(res => res.json())
        .then(js => {
            $('#username').append(`<a class="navbar-brand">${js.username}</a>`)

            $('#role').append(`<a class="navbar-brand">${js.roles.map((el) => el.name.toString())}</a>`)

            const user = `$(
                    <tr>
                        <td>${js.id}</td>
                        <td>${js.name}</td>
                        <td>${js.surname}</td>
                        <td>${js.city}</td>
                        <td>${js.age}</td>
                        <td>${js.username}</td>
                        <td>${js.roles.map((el) => el.name.toString())}</td>
                    </tr>)`;
            $('#user').append(user)
        })
}
