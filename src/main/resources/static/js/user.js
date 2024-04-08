let index = {
    init: function () {
        $("#btn-save").on("click", () => { //this 바인딩 하려면 () => 써야한다
            this.save();
        });
    },

    save: function () {
        // alert('user의 save함수 호출됨');
        let data={
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        // console.log(data);

        //ajax 호출시 default가 비동기 호출
        $.ajax({
            type:"POST",
            url:"/auth/joinProc",
            data:JSON.stringify(data), // http body 데이터
            contentType:"application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
            dataType:"json" //서버에서 응답이 왔을 경우 모두 문자열로 받게 되는데 json 구조일 경우 javascript 오브젝트로 변환해서 응답하게 해줌
        }).done(function(resp){
            alert("회원가입이 완료되었습니다.");
            // console.log(resp);
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        }); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청

    }
}


index.init();