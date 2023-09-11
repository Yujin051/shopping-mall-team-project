$(document).ready(function() {

    // 중분류 카테고리 초기값 숨기기
    $('.game_chk').hide(); 
    $('.goods_chk').hide();

    $("input[name='radio_cate']").change(function() {
        // 하드웨어 라디오 버튼 클릭 시
        if($("input[name='radio_cate']:checked").val() == '하드웨어') {
            $('.game_chk').hide();
            $('.goods_chk').hide();
        }

        // 게임 라디오 버튼 클릭 시
        else if($("input[name='radio_cate']:checked").val() == '게임') {
            $('.game_chk').show();
            $('.goods_chk').hide();
        }

        // 악세서리 라디오 버튼 클릭 시
        else if($("input[name='radio_cate']:checked").val() == '악세서리') {
            $('.game_chk').hide();
            $('.goods_chk').hide();
        }

        // 굿즈 라디오 버튼 클릭 시
        else if($("input[name='radio_cate']:checked").val() == '굿즈') {
            $('.game_chk').hide();
            $('.goods_chk').show();
        }


    })
})







