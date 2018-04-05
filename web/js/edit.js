$(document).ready(function(){

    $(window).load(function(){
        getAllDiscipline();
        getSchedules();
        getStudents();
    });

    function getAllDiscipline() {
        $.ajax
        ({
            type: "POST",
            data: {},
            url: 'GetAllDiscipline',
            success: function (serverData) {
                var result = serverData.result;
                if (result > 0) {
                    var discipline_ids = serverData.discipline_ids.split(",");
                    var names = serverData.names.split(",");

                    var prevOption =  $("#all_discipline").children();
                    if (prevOption.length > 1)
                    {
                        for (var i = 1; i < prevOption.length; i++)
                        {
                            prevOption[i].remove();
                        }
                    }

                    var select = $("#all_discipline");
                    for (var i = 0; i < result; i++) {
                        var option = $('<option>');
                        option.attr('value', discipline_ids[i]);
                        option.text(names[i]);
                        select.append(option);
                    }
                }
                else {
                    alert("Bad1");
                }
            },
            error: function (e) {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    }

    function getSchedules() {
        $.ajax
        ({
            type: "POST",
            data: {},
            url: 'GetSchedule',
            success: function (serverData) {
                var result = serverData.result;
                if (result >= 0) {
                    var schedule_ids = serverData.schedule_ids.split(",");
                    var names = serverData.names.split(",");

                    var prevOption = $("#schedules").children();
                    for (var i = 0; i < prevOption.length; i++)
                    {
                        prevOption[i].remove();
                    }

                    var select = $("#schedules");
                    for (var i = 0; i < result; i++) {
                        var option = $('<option>');
                        option.attr('value', schedule_ids[i]);
                        option.text(names[i]);
                        select.append(option);
                    }
                }
                else {
                    alert("Bad2");
                }
            },
            error: function (e) {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    }

    function getStudents() {
        $.ajax
        ({
            type: "POST",
            data: {},
            url: 'GetStudents',
            success: function (serverData) {
                var result = serverData.result;
                if (result >= 0) {
                    var student_ids = serverData.student_ids.split(",");
                    var names = serverData.names.split(",");

                    var prevOption = $("#students").children();
                    for (var i = 0; i < prevOption.length; i++)
                    {
                        prevOption[i].remove();
                    }

                    var select = $("#students");
                    for (var i = 0; i < result; i++) {
                        var option = $('<option>');
                        option.attr('value', student_ids[i]);
                        option.text(names[i]);
                        select.append(option);
                    }
                }
                else {
                    alert("Bad3");
                }
            },
            error: function (e) {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    }

    $("#add_student").click(function() {
        alert("test");
        var fio =  $("#student_fio").val();
        if (fio === "") {
            alert("Поле не было заполнено");
        } else {
            $.ajax
            ({
                type: "POST",
                data: {"fio": fio},
                url: 'AddStudent',
                success: function (serverData) {
                    var result = serverData.result;
                    if (result > 0) {
                        alert("Good");
                        getStudents();
                    }
                    else {
                        alert("Bad");
                    }
                },
                error: function (e) {
                    alert("Произошла ошибка ajax запроса!");
                }
            });
        }
    });

    $("#add_discipline").click(function() {
        var discipline_id = $("#all_discipline").val();
        if (discipline_id === "") {
            alert("Bad");
        }
        else {
            $.ajax
            ({
                type: "POST",
                data: {"discipline_id": discipline_id},
                url: 'AddDiscipline',
                success: function (serverData) {
                    var result = serverData.result;
                    if (result > 0) {
                        alert("Good");
                        getSchedules();
                    } else if (result == 0) {
                        alert ("Такая дисциплина уже была добавлена");
                    } else {
                        alert("Bad");
                    }
                },
                error: function (e) {
                    alert("Произошла ошибка ajax запроса!");
                }
            });
        }
    });

    $("#del_schedule").click(function() {
        var schedule_id = $("#schedules").val();
        if (schedule_id === "") {
            alert("Bad");
        }
        else {
            $.ajax
            ({
                type: "POST",
                data: {"schedule_id": schedule_id},
                url: 'DelSchedule',
                success: function (serverData) {
                    var result = serverData.result;
                    if (result > 0) {
                        alert("Good");
                        getSchedules();
                    } else {
                        alert("Bad");
                    }
                },
                error: function (e) {
                    alert("Произошла ошибка ajax запроса!");
                }
            });
        }
    });

    $("#del_student").click(function() {
        var student_id = $("#students").val();
        if (student_id === "") {
            alert("Bad");
        }
        else {
            $.ajax
            ({
                type: "POST",
                data: {"student_id": student_id},
                url: 'DelStudent',
                success: function (serverData) {
                    var result = serverData.result;
                    if (result > 0) {
                        alert("Good");
                        $("#update_stud").val("");
                        getStudents();
                    } else {
                        alert("Bad");
                    }
                },
                error: function (e) {
                    alert("Произошла ошибка ajax запроса!");
                }
            });
        }
    });

    $("#upd_student").click(function() {
        var upd_stud = $("#update_stud");
        var fio = upd_stud.val();
        var student_id = upd_stud.attr('data-id');
        alert(fio);
        alert(student_id);
    });

    $("#students").change(function() {
        var upd_stud = $("#update_stud");
        var sel_stud = $("#students option:selected");
        upd_stud.val(sel_stud.text());
        upd_stud.attr('data-id', sel_stud.val());
    });
});