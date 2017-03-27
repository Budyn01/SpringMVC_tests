var selectedEmployees = new Set();

$(document).ready(function () {


    $('.employee')
        .find('.editButton').on('click', function(){
        $.getJSON('../../resources/employee/' + $(this).attr("employeeID"), function (data) {
            console.log(data);
            $('#employeeForm').removeClass("hidden");
            $('#myModalLabel').text("Editing " + data.name + " " + data.surname);
            $('#employeeId').val(data.id);
            $('#employeeName').val(data.name);
            $('#employeeSurname').val(data.surname);
            $('#employeeEmail').val(data.email);
            $('#employeeAddress').val(data.address);
            $('#employeePhone').val(data.phone);
            $('#employeeJob').find("[value=" + data.jobId + "]").attr("selected","selected");
        });
    });
    $('#addButton').on('click', function () {
        $('#myModalLabel').text("New employee");
        $('#employeeForm').removeClass("hidden");
        $('#employeeId').val(null);
        $('#employeeName').val(null);
        $('#employeeSurname').val(null);
        $('#employeeEmail').val(null);
        $('#employeeAddress').val(null);
        $('#employeePhone').val(null);
    });
    validateForm();
    selectEmployees();
    deleteSelected()
});

function deleteSelected(){

    function deleteByID(value1, value2, set) {
        $.get('/dashboard/employee/delete/' + value1);
    }

    $('#deleteSelectedButton').on('click', function () {
       // console.log(selectedJob);
       selectedEmployees.forEach(deleteByID);
        location.reload();
    });
}

function selectEmployees(){
    $(".employee").on('click', function () {
        var selectedEmployee = $(this).find('.editButton').attr('employeeID');
        if($(this).find("input").is(':checked')){
            $(this).prop("style", "background-color: ");
            $(this).find("input").prop('checked', false);
            selectedEmployees.delete(selectedEmployee);
        } else {
            $(this).prop("style", "background-color: #E0E0E0");
            $(this).find("input").prop('checked', true);
            selectedEmployees.add(selectedEmployee);
        }
    });
}
function validateForm() {
    $('#employeeForm').validate({
            rules: {
                name : {
                    required : true,
                    minlength: 2,
                    maxlength: 12
                },
                surname : {
                    required : true,
                    minlength: 2,
                    maxlength: 12
                },
                email : {
                    required : true,
                    email : true
                },
                phone : {
                    required : true
                },
                address : {
                    required : true,
                    minlength : 6
                }
            },
            messages: {
                name: "Your name must be between 2 and 12 characters long",
                surname: "Your surname must be between 2 and 12 characters long",
                email: "Wrong email",
                phone: "Wrong phone",
                address: "Your address must be at least 6 characters long"
            }

        }
    )
}