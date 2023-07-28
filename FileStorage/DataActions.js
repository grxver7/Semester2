function encodeInput(text, dataType) {
    let textBase64 = btoa(text);
    uploadData({content: textBase64}, dataType);
} //encode to Base64

function preClick(dataName, dataType) {
    pathTmp = path + dataName + '/';
    typeTmp = dataType;
} //preSelect Path

function createFileName(dataType) {
    removeAllItems();
    const fileNameContainer = document.createElement('div');
    const fileName = document.createElement('textarea');
    const subButton = document.createElement('button');

    fileNameContainer.className = 'fileNameContainer';

    subButton.className = 'Submit';
    subButton.textContent = 'Submit';

    fileNameContainer.appendChild(fileName);
    fileNameContainer.appendChild(subButton);

    subButton.addEventListener('click', () => {
        if (dataType === 'text/plain') {
            path = path + fileName.value + '?format=base64';
            encodeInput('New Text File', 'text/plain');
        } else if (dataType === 'dir') {
            path = path + fileName.value + '/';
            uploadData('', 'dir');
        } else {
            alert('File could not get named');
        }
    });

    document.body.appendChild(fileNameContainer);
}

function downloadFile(dataType) {
    selectDataType(dataType);
    const downloadElement = document.createElement("a");
    downloadElement.href = url;
    downloadElement.download = pathEnd();
    downloadElement.click();
    initialiseDownload = false;
    let pathOperation = path.slice(0, -14);
    path = pathOperation + '/';
    getData(dataType);
}

function uploadFile(event) {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = function (e) {
        const fileContent = e.target.result;

        let dataType;
        if (file.name.endsWith('.txt')) {
            dataType = 'text/plain';
        } else if (file.name.endsWith('.mp4')) {
            dataType = 'video/mp4';
        } else if (file.name.endsWith('.mp3')) {
            dataType = 'audio/mpeg';
        } else if (file.name.endsWith('.png')) {
            dataType = 'image/png';
        } else {
            alert("Filetype not supported");
        }
        path = path + file.name + '?format=base64';
        encodeInput(fileContent, dataType);
    };
    reader.readAsBinaryString(file);
}

function openPngFile(base64String) {
    const pictureContainer = document.createElement('div');
    pictureContainer.className = 'pictureContainer';
    let image = new Image();
    image.src = 'data:image/png;base64,' + base64String;

    if (initialiseDownload === true) {
        url = image.src;
    } else {
        pictureContainer.appendChild(image);
        document.body.appendChild(pictureContainer);
    }
}

function openVideoFile(base64String) {
    const videoContainer = document.createElement('div');
    videoContainer.className = 'videoContainer';
    let video = document.createElement('video');
    video.src = 'data:video/mp4;base64,' + base64String;
    video.controls = true;

    if (initialiseDownload === true) {
        url = video.src;
    } else {
        videoContainer.appendChild(video);
        document.body.appendChild(videoContainer);
    }
}

function openMp4File(base64String) {
    const audioContainer = document.createElement('div');
    audioContainer.className = 'audioContainer';
    let audio = document.createElement('audio');
    audio.src = 'data:audio/mp3;base64,' + base64String;
    audio.controls = true;
    if (initialiseDownload === true) {
        url = audio.src;
    } else {
        audioContainer.appendChild(audio);
        document.body.appendChild(audioContainer);
    }
}

function openTextFile(apiResponse) {
    if (initialiseDownload === true) {
        const txtContainer = document.createElement('div');
        txtContainer.className = 'audioContainer';
        let text = document.createElement('txt');
        text.src = 'data:text/txt;base64,' + apiResponse;
        text.controls = true;
        url = text.src;
    } else {
        removeAllItems();
        createActionButtons();
        disableButtonsOpenedFile();
        createTextField(apiResponse);
    }
}
