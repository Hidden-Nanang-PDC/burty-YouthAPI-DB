document.addEventListener('DOMContentLoaded', function() {
    var policiesBtn      = document.getElementById('fetchPoliciesBtn');
    var contentBtn       = document.getElementById('fetchContentBtn');
    var centersBtn       = document.getElementById('fetchCentersBtn');
    var policyCodesBtn   = document.getElementById('fetchPolicyCodesBtn');
    var resultDiv        = document.getElementById('result');
    var loader           = document.getElementById('loader');

    function startStream(endpoint) {
        resultDiv.innerHTML = "";
        loader.style.display = "block";

        var eventSource = new EventSource(endpoint);

        eventSource.addEventListener("progress", function(event) {
            var p = document.createElement("p");
            p.textContent = event.data;
            resultDiv.appendChild(p);
        });

        eventSource.addEventListener("complete", function(event) {
            var p = document.createElement("p");
            p.textContent = event.data;
            resultDiv.appendChild(p);
            loader.style.display = "none";
            eventSource.close();
        });

        eventSource.addEventListener("error", function(event) {
            var p = document.createElement("p");
            p.textContent = "오류 발생: " + (event.data || "알 수 없는 오류");
            resultDiv.appendChild(p);
            loader.style.display = "none";
            eventSource.close();
        });
    }

    policiesBtn.addEventListener('click', function() {
        startStream('/fetch-policies-stream');
    });

    contentBtn.addEventListener('click', function() {
        startStream('/fetch-contents-stream');
    });

    centersBtn.addEventListener('click', function() {
        startStream('/fetch-centers-stream');
    });

    policyCodesBtn.addEventListener('click', function() {
        startStream('/fetch-policy-codes-stream');
    });
});
