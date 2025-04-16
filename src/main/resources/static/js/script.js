document.addEventListener('DOMContentLoaded', function() {
    var fetchButton = document.getElementById('fetchButton');
    var resultDiv = document.getElementById('result');
    var loader = document.getElementById('loader');

    fetchButton.addEventListener('click', function() {
        // 로딩 시작: 스피너 보이기, 이전 결과 초기화
        loader.style.display = "block";
        resultDiv.innerText = "";

        fetch('/fetch-policies')
            .then(function(response) {
                // 응답 받으면 스피너 숨기기
                loader.style.display = "none";
                if (!response.ok) {
                    throw new Error('네트워크 응답이 정상적이지 않습니다.');
                }
                return response.text();
            })
            .then(function(data) {
                // 성공 메시지 표시
                resultDiv.innerText = "성공: " + data;
            })
            .catch(function(error) {
                // 오류 메시지 표시 (스피너는 이미 숨김 처리)
                resultDiv.innerText = "실패: " + error.message;
            });
    });
});
