function deleteData() {
    pathTmp = pathTmp.slice(0, -1);

    apiReq.onreadystatechange = function () {
        if (apiReq.readyState === 4) {
            if (apiReq.status === 200) {
                const apiResponse = apiReq.responseText;
                console.log("API Response:", apiResponse);
                getData('dir');
            } else {
                console.error('API Request failed. Status:', apiReq.status);
                alert('API Request failed in deleting the data. Cause can be existing items in the folder');
            }
        }
    };

    apiReq.open('DELETE', pathTmp);
    apiReq.setRequestHeader('Authorization', 'Basic ' + btoa('admin:' + token));
    apiReq.send();
} //delete data from the API

function uploadData(uploadInput, dataType) {
    dataTypeSuperiorAccess = dataType;
    let pathTmp;
    if (dataType !== 'dir') {
        pathTmp = path.slice(0, -14);
    }

    apiReq.onreadystatechange = function () {
        if (apiReq.readyState === 4) {
            if (apiReq.status === 200) {
                const apiResponse = apiReq.responseText;
                console.log("API Response:", apiResponse);
                if (dataType !== 'dir') {
                    path = pathTmp + '/';
                }
                getData(dataType);
            } else {
                console.error('API Request failed. Status:', apiReq.status);
                alert('API Request failed in loading the data.');
            }
        }
    };

    apiReq.open('POST', path);
    apiReq.setRequestHeader('Authorization', 'Basic ' + btoa('admin:' + token));
    if (dataType === 'dir') {
        apiReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        apiReq.send('type=dir'); // Send the content in the request body
    } else {
        apiReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        apiReq.send('content=' + encodeURIComponent(uploadInput.content)); // Send the content in the request body
    }
} //post data to the API

function getData(dataType, checkHistoryNavigation) {
    pathMemory(dataType, checkHistoryNavigation);
    apiReq.onreadystatechange = function () {
        if (apiReq.readyState === 4) {
            if (apiReq.status === 200) {
                apiResponse = apiReq.responseText;
                console.log("API Response:", apiResponse);
                selectDataType(dataType);
            } else {
                console.error('API Request failed. Status:', apiReq.status);
                alert('API Request failed in loading the data.');
            }
        }
    };

    let pathTmp;
    if (dataType !== 'dir') {
        pathTmp = path.slice(0, -1);
        pathTmp += '?format=base64';
        path = pathTmp;
    }

    apiReq.open('GET', path);
    apiReq.setRequestHeader('Authorization', 'Basic ' + btoa('admin:' + token));
    apiReq.send();
} //get data from the API

function selectDataType(dataType) { //function to select the dataType, need in getData
    if (dataType === 'dir') {
        openFolder(apiResponse);
        disableButtonsOpenedFolder();
    } else {
        removeAllItems();
        createActionButtons();
        disableButtonsOpenedFile();
        if (dataType === 'text/plain') {
            openTextFile(apiResponse, dataType, initialiseDownload)
        } else if (dataType === 'image/png') {
            openPngFile(apiResponse, initialiseDownload);
        } else if (dataType === 'audio/mpeg') {
            openMp4File(apiResponse, initialiseDownload);
        } else if(dataType === 'video/mp4') {
            openVideoFile(apiResponse, initialiseDownload);
        }
        else {
            alert('Error finding file!');
        }
    }
}