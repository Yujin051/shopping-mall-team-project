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

            $('.item_detail').text("모델명 : \n기종 : \n플레이 모드 : \n대응 언어 : \n메이커 : ");

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

            $('.item_detail').text("발매일 : \n장르 : \n플레이 인원 수 : \n언어 : \n플레이 모드 : \n대응 컨트롤러 : ");

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

            $('.item_detail').text("제품명 : \n장르 : \n원산지 : \n구성품 : ");

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

            $('.item_detail').text("제품명 : \n장르 : \n원산지 : \n구성품 : ");

            $('.game_chk').hide();
            $('.goods_chk').show();
        }


    })

    // 이미지 미리보기 기능
    $(function () {
        $(".select_img").on('change', function (){
            readURL(this);
        });
    });

    function readURL(input) {
        if(input.files && input.files[0]) {
            let reader = new FileReader();
            reader.onload = function (e) {
                $("#preView").attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $(function () {
        $(".reg_btn").click(function () {
            if(!confirm('등록하시겠습니까?')) {
                return false;
            }
        })
    })

})