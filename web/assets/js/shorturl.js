$(function(){
        $('#table-sortable').dataTable();
        $(".select-role").change(function(){
            var userId = $(this).data("user");
            var role = $(this).val();
            var action = "update";
            alert(userId + " " + role + " " + action);
            $.post("ServletUser",{user_id: userId, user_role: role, servlet_action: action}, function(data){
                console.log(data);
            })
            .fail(function(data){
                console.log(data);
            });
        });
        $(".deleteUrl").click(function(e){
           e.preventDefault();
           var id = $(this).data("url");
           var urlRow = $(this);
           $.post( "./DestroyURL", { id: id }, function() {
                    urlRow.parent().parent().hide("slow");
                } )

                .fail(function() {
                    alert( "error" );
                });

        });
});