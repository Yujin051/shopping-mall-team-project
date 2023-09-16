$(document).ready(function() {

    $(function () {
        $(".delete_btn").click(function () {
            if(!confirm('정말 삭제하시겠습니까?')) {
                return false;
            }
        })
    })

})