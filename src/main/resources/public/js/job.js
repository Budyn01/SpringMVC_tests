var selectedJob = new Set();

$(document).ready(function () {
    editJob();
    validateForm();
    selectEmployees();
    deleteSelected()
});

function editJob(){
    $('.job')
        .find('.editButton').on('click', function(){
        $.getJSON('../../resources/job/' + $(this).attr("jobID"), function (data) {
            console.log(data);
            $('#jobForm').removeClass("hidden");
            $('#myModalLabel').text("Editing " + data.name);
            $('#jobId').val(data.id);
            $('#jobName').val(data.name);
            $('#jobSalary').val(data.salary);
            $('#jobDescription').val(data.description);
        });
    });
    $('#addButton').on('click', function () {
        $('#myModalLabel').text("New employee");
        $('#jobForm').removeClass("hidden");
        $('#jobId').val(null);
        $('#jobName').val(null);
        $('#jobSalary').val(null);
        $('#jobDescription').val(null);
    });
}

function deleteSelected(){

    function deleteByID(value1, value2, set) {
        $.get('/dashboard/job/delete/' + value1);
    }

    $('#deleteSelectedButton').on('click', function () {
        // console.log(selectedJob);
        selectedJob.forEach(deleteByID);
        location.reload();
    });
}

function selectEmployees(){
    $(".job").on('click', function () {
        var selectedEmployee= $(this).find('.editButton').attr('jobID');
        if($(this).find("input").is(':checked')){
            $(this).prop("style", "background-color: ");
            $(this).find("input").prop('checked', false);
            selectedJob.delete(selectedEmployee);
        } else {
            $(this).prop("style", "background-color: #E0E0E0");
            $(this).find("input").prop('checked', true);
            selectedJob.add(selectedEmployee);
        }
    });
}
function validateForm() {
    $('#jobForm').validate({
            rules: {
                name : {
                    required : true,
                    minlength: 2,
                    maxlength: 12
                },
                salary : {
                    required : true,
                    number: true
                },
                description : {
                    required : true,
                    minlength : 6
                }
            },
            messages: {
                name: "Name must be between 2 and 12 characters long",
                salary: "Salary must be decimal number",
                description: "Description must be at least 6 characters long"
            }

        }
    )
}