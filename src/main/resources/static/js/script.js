document.addEventListener('DOMContentLoaded', function() {
    var policiesBtn = document.getElementById('fetchPoliciesBtn');
    var contentBtn  = document.getElementById('fetchContentBtn');
    var resultDiv   = document.getElementById('result');
    var loader      = document.getElementById('loader');

    // 지정된 엔드포인트로 SSE 연결을 시작하고, 진행/완료/오류 이벤트를 처리
    function startStream(endpoint) {
        // 초기화
        resultDiv.innerHTML = "";
        loader.style.display = "block";

        var eventSource = new EventSource(endpoint);

        // 진행 상황 업데이트
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

        // 오류 시 처리
        eventSource.addEventListener("error", function(event) {
            var p = document.createElement("p");
            p.textContent = "오류 발생: " + (event.data || "알 수 없는 오류");
            resultDiv.appendChild(p);
            loader.style.display = "none";
            eventSource.close();
        });
    }

    // 버튼 클릭 시 각각의 API 저장 스트림 시작
    policiesBtn.addEventListener('click', function() {
        startStream('/fetch-policies-stream');
    });

    contentBtn.addEventListener('click', function() {
        startStream('/fetch-contents-stream');
    });
});
