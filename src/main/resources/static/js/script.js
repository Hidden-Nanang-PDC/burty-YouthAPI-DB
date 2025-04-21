document.addEventListener('DOMContentLoaded', function() {
    var policiesBtn = document.getElementById('fetchPoliciesBtn');
    var contentBtn  = document.getElementById('fetchContentBtn');
    var centersBtn  = document.getElementById('fetchCentersBtn');
    var resultDiv   = document.getElementById('result');
    var loader      = document.getElementById('loader');

    // SSE 연결 및 이벤트 처리 공통 함수
    function startStream(endpoint) {
        // 결과 초기화 및 로더 표시
        resultDiv.innerHTML = "";
        loader.style.display = "block";

        var eventSource = new EventSource(endpoint);

        // 진행 상황 메시지 수신
        eventSource.addEventListener("progress", function(event) {
            var p = document.createElement("p");
            p.textContent = event.data;
            resultDiv.appendChild(p);
        });

        // 완료 시 처리
        eventSource.addEventListener("complete", function(event) {
            var p = document.createElement("p");
            p.textContent = event.data;
            resultDiv.appendChild(p);
            loader.style.display = "none";
            eventSource.close();
        });

        // 오류 처리
        eventSource.addEventListener("error", function(event) {
            var p = document.createElement("p");
            p.textContent = "오류 발생: " + (event.data || "알 수 없는 오류");
            resultDiv.appendChild(p);
            loader.style.display = "none";
            eventSource.close();
        });
    }

    // 버튼 클릭 시 각 API 저장 스트림 실행
    policiesBtn.addEventListener('click', function() {
        startStream('/fetch-policies-stream');
    });

    contentBtn.addEventListener('click', function() {
        startStream('/fetch-contents-stream');
    });

    centersBtn.addEventListener('click', function() {
        startStream('/fetch-centers-stream');
    });
});
