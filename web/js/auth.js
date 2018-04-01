function authAjax() {
    var mail =  $("#e-mail").val();
    var password = $("#password").val();
    if (mail !== "" && password !== "") {
        $.ajax({
            url: "Authentication",
            data: {"mail": mail, "password": password},
            type: 'POST',
            success: function(serverData){
                var result = serverData.result;
                if (result == -1) {
                    alert("Не верный e-mail");
                } else if (result == -2) {
                    alert("Не верный пароль");
                }
                else if (result == 0) {
                    alert("Заполнены не все поля");
                } else {
                    alert("Good");
                    var url = "http://localhost:8080/edit.html";
                    $(location).attr('href',url);
                }
            },
            error: function () {

            }
        });
    }
}

function regAjax() {
    var fio =  $("#fio").val();
    var mail =  $("#r_e-mail").val();
    var password1 =  $("#password1").val();
    var password2 =  $("#password2").val();
    var number =  $("#number").val();
    var name = $("#name").val();
    if (fio !== "" && mail !== "" && password1 !== "" && password2 !== "" && number !== "" && name !== "") {
        if (password1 === password2) {
            $.ajax({
                url: "Registration",
                data: {"fio": fio, "mail": mail,  "password": password1, "number": number, "name": name},
                type: 'POST',
                success: function(serverData){
                    var result = serverData.result;
                    if (result == -1) {
                        alert("Группа не добавлена");
                    } else if (result == -2) {
                        alert("Пользователь не добавлен");
                    }
                    else if (result == 0) {
                        alert("Заполнены не все поля");
                    } else {
                        alert("Good");
                    }
                },
                error: function () {

                }
            });
        }
        else {
            alert("Пароли не совпадают");
        }
    } else {
        alert("Заполнены не все поля");
    }
}