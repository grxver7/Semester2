function getAccess() {
    const name = document.getElementById("user").value;
    const pass = document.getElementById("pass").value;

    const data = new FormData();
    data.append('username', name);
    data.append('password', pass);

    apiReq.onreadystatechange = function () {
        if (apiReq.readyState === 4) {
            if (apiReq.status === 200) {
                const response = JSON.parse(apiReq.responseText);
                token = response.token;
                home();
                createLogoutButton();
            } else {
                console.error('Request failed. Status:', apiReq.status);
                alert('API Request failed. Username/password is wrong or Api is not available');
            }
        }
    };
    apiReq.open('POST', 'http://localhost:8080/login');
    apiReq.send(data);
}

function logOut() {
    removeAllItems();
    // Hide the main page content
    document.getElementById("content").style.display = "none";
    // Display the login container
    document.getElementById("loginContainer").style.display = "block";

    // Use the token to logout
    apiReq.onreadystatechange = function () {
        if (apiReq.readyState === 4) {
            if (apiReq.status === 200) {
                const apiResponse = apiReq.responseText;
                // Process the API response as needed
                console.log("API Response:", apiResponse);
            } else {
                console.error('API Request failed. Status:', apiReq.status);
                alert('API Request failed. You are getting logged out');
            }
        }
    };
    apiReq.open('GET', 'http://localhost:8080/logout');
    apiReq.setRequestHeader('Authorization', 'Basic ' + btoa('admin:' + token));
    apiReq.send();
}