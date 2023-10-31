// getCurrentUser()
//
// function getCurrentUser() {
//     fetch("http://localhost:8080/user")
//         .then(res => res.json())
//         .then(js => {
//             $('#emailCurrentUser').append(`<span>${js.email}</span>`)
//             $('#roleCurrentUser').append(`<span>${js.shortRole}</span>`)
//             const user = `$(
//                     <tr>
//                         <td>${js.id}</td>
//                         <td>${js.firstName}</td>
//                         <td>${js.lastName}</td>
//                         <td>${js.age}</td>
//                         <td>${js.email}</td>
//                         <td>${js.shortRole}</td>
//                     </tr>)`;
//             $('#oneUser').append(user)
//         })
// }
fetchUserData();
function fetchUserData() {
    fetch("http://localhost:8080/user")
        .then(response => response.json())
        .then(data => {

            const userTable = document.getElementById('user');
            data.forEach(user => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.city}</td>
                    <td>${user.age}</td>
                    <td>${user.username}</td>
                    <td>${user.role}</td>
                    <tr/>
                `;
                userTable.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Ошибка:', error);
        });
}

// function fetchUserData() {
//     $.ajax({
//         url: "http://localhost:8080/user",
//         method: 'GET',
//         success: function (data) {
//             const userTable = $('#user');
//             data.forEach(user => {
//                 const row = `
//                     <tr>
//                         <td>${user.id}</td>
//                         <td>${user.name}</td>
//                         <td>${user.surname}</td>
//                         <td>${user.city}</td>
//                         <td>${user.age}</td>
//                         <td>${user.username}</td>
//                         <td>${user.role}</td>
//                     </tr>
//                 `;
//                 userTable.append(row);
//             });
//         },
//         error: function (error) {
//             console.error('Ошибка:', error);
//         }
//     });
// }
fetchUserData();