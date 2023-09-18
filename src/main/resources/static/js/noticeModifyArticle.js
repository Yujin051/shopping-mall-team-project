$(document).ready(function() {

    $(function () {
        $(".posting").click(function () {
            if(!confirm('수정하시겠습니까?')) {
                return false;
            }
        })
    })

})