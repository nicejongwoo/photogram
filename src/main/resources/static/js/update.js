// (1) 회원정보 수정
function update(userId) {
    const data = $("#profileUpdate").serialize();

    $.ajax({
        type: "put",
        url: "/api/user/" + userId,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res => {
        //console.log("success update: ", res)
        location.href=`/user/${userId}`;
    }).fail(error => {
        console.log("failed update: ", error)
    });

}