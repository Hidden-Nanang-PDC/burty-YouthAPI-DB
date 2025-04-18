document.addEventListener('DOMContentLoaded', function() {
    var fetchButton = document.getElementById('fetchButton');
    var resultDiv = document.getElementById('result');
    var loader = document.getElementById('loader');

    fetchButton.addEventListener('click', function() {
        // 초기화: 결과 영역 초기화, 로더 표시
        resultDiv.innerText = "";
        loader.style.display = "block";

        // /fetch-policies-stream 엔드포인트에 SSE 연결
        var eventSource = new EventSource("/fetch-policies-stream");

        // 페이지 진행 상황 (데이터 불러오기 관련 메시지) 업데이트
        eventSource.addEventListener("progress", function(event) {
            var p = document.createElement("p");
            p.textContent = event.data;
            resultDiv.appendChild(p);
        });

        // DB 저장 진행 상황을 표시하는 이벤트 수신 처리
        eventSource.addEventListener("dbProgress", function(event) {
            var p = document.createElement("p");
            p.textContent = event.data;
            resultDiv.appendChild(p);
        });

        // 전체 저장 완료 이벤트 수신 처리
        eventSource.addEventListener("complete", function(event) {
            var p = document.createElement("p");
            p.textContent = event.data;
            resultDiv.appendChild(p);
            loader.style.display = "none";
            eventSource.close();
        });

        // 오류 발생 시 처리
        eventSource.addEventListener("error", function(event) {
            var p = document.createElement("p");
            p.textContent = "오류 발생: " + event.data;
            resultDiv.appendChild(p);
            loader.style.display = "none";
            eventSource.close();
        });
    });
});
