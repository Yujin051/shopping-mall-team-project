$(document).ready(function() {

    // 중분류 카테고리 초기값 숨기기
    $('.game_chk').hide();
    $('.goods_chk').hide();

    $("input[name='mainCate']").change(function() {
        // 하드웨어 라디오 버튼 클릭 시
        if($("input[name='mainCate']:checked").val() == '하드웨어') {

            const checkboxes1
                = document.getElementsByName('subCate');
            checkboxes1.forEach((checkbox) => {
                checkbox.checked = false;
            })
            const checkboxes2
                = document.getElementsByName('subCate');
            checkboxes2.forEach((checkbox) => {
                checkbox.checked = false;
            })

            $('.game_chk').hide();
            $('.goods_chk').hide();
        }

        // 게임 라디오 버튼 클릭 시
        else if($("input[name='mainCate']:checked").val() == '게임') {

            const checkboxes2
                = document.getElementsByName('subCate');
            checkboxes2.forEach((checkbox) => {
                checkbox.checked = false;
            })

            $('.game_chk').show();
            $('.goods_chk').hide();
        }

        // 악세서리 라디오 버튼 클릭 시
        else if($("input[name='mainCate']:checked").val() == '악세서리') {

            const checkboxes
                = document.getElementsByName('subCate');
            checkboxes.forEach((checkbox) => {
                checkbox.checked = false;
            })
            const checkboxes2
                = document.getElementsByName('subCate');
            checkboxes2.forEach((checkbox) => {
                checkbox.checked = false;
            })

            $('.game_chk').hide();
            $('.goods_chk').hide();
        }

        // 굿즈 라디오 버튼 클릭 시
        else if($("input[name='mainCate']:checked").val() == '굿즈') {

            const checkboxes1
                = document.getElementsByName('subCate');
            checkboxes1.forEach((checkbox) => {
                checkbox.checked = false;
            })

            $('.game_chk').hide();
            $('.goods_chk').show();
        }


    })
})