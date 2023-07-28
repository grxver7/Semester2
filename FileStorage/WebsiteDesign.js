function openFolder(apiResponse) {
    removeAllItems();
    createActionButtons();

    let startFiles;
    try {
        startFiles = JSON.parse(apiResponse);
    } catch (error) {
        console.error('Error parsing API response:', error);
        alert('Error parsing API response.');
        return;
    }

    const container = document.createElement('div');
    container.className = 'icon-container'; // Add a container for the icons

    for (let i = 0; i < startFiles.length; i++) {
        const iconContainer = document.createElement('div');
        iconContainer.className = 'icon-wrapper'; // Add a wrapper for each icon

        const icon = document.createElement('i');
        const dataNames = document.createElement('span');
        const data = startFiles[i];
        names.push(data.Name);
        types.push(data.Type);

        // Set the icon class based on the file type
        if (data.Type === 'dir') {
            icon.className = 'far fa-folder';
            dataTypeSuperiorAccess = 'dir';
        } else if (data.Type === 'text/plain') {
            icon.className = 'far fa-file-alt';
        } else if (data.Type === 'image/png') {
            icon.className = 'far fa-file-image';
        } else if (data.Type === 'audio/mpeg') {
            icon.className = 'far fa-file-audio';
        } else if (data.Type === 'video/mp4') {
            icon.className = 'far fa-file-video';
        } else {
            alert("Type of document doesn't exist or isn't supported on this website");
        }

        iconContainer.appendChild(icon);
        container.appendChild(iconContainer); // Append the icon wrapper to the container
        iconContainer.appendChild(dataNames);

        dataNames.textContent = data.Name;

        iconContainer.addEventListener('click', () => {
            dataTypeSuperiorAccess = data.Type;
            preClick(data.Name, data.Type);
        });
        iconContainer.addEventListener('dblclick', () => {
            dataTypeSuperiorAccess = data.Type;
            path = path + data.Name + '/';
            getData(data.Type);
        });
    }

    document.body.appendChild(container); // Append the container to the document body

    console.log(names);
    console.log(types);
}
function createLogoutButton() {
    const logoutContainer = document.createElement('div');
    logoutContainer.className = 'logoutContainer';
    const logoutButton = document.createElement('button');
    logoutButton.className = 'Logout';
    logoutButton.textContent = 'Logout';
    logoutContainer.appendChild(logoutButton);
    document.body.appendChild(logoutContainer);

    logoutButton.addEventListener('click', () => {
        logOut();
    });
}
function createActionButtons() {
    const buttonContainer = document.createElement('div');
    buttonContainer.className = 'buttonContainer'; // Add a container for the icons

    //Back
    const backButton = document.createElement('i');
    backButton.className = 'fas fa-arrow-left';
    buttonContainer.appendChild(backButton);

    backButton.addEventListener('click', () => {
        back();
    });

    //Home
    const homeButton = document.createElement('i');
    homeButton.className='fas fa-home';
    buttonContainer.appendChild(homeButton);

    homeButton.addEventListener('click', () => {
        home();
    });

    //Open
    const openButton = document.createElement('button');
    openButton.className = 'Open';
    openButton.textContent = 'Open';
    buttonContainer.appendChild(openButton);

    openButton.addEventListener('click', () => {
        pathOld = path;
        path = pathTmp;
        getData(typeTmp);
    });

    //Delete
    const deleteButton = document.createElement('button');
    deleteButton.className = 'Delete';
    deleteButton.textContent = 'Delete';
    buttonContainer.appendChild(deleteButton);

    deleteButton.addEventListener('click', () => {
        deleteData();
    });

    //new txt-File
    const createTextFile = document.createElement('button');
    createTextFile.className = 'createTextFile';
    createTextFile.textContent = 'new txt-File';
    buttonContainer.appendChild(createTextFile);

    createTextFile.addEventListener('click', () => {
        createFileName('text/plain');
    });

    //new Folder
    const createFolder = document.createElement('button');
    createFolder.className = 'createFolder';
    createFolder.textContent = 'new Folder';
    buttonContainer.appendChild(createFolder);

    createFolder.addEventListener('click', () => {
        createFileName('dir');
    });

    //Download
    const downloadButton = document.createElement('button');
    downloadButton.className = 'Download';
    downloadButton.textContent = 'Download';
    buttonContainer.appendChild(downloadButton);
    downloadButton.addEventListener('click', () => {
        initialiseDownload = true;
        downloadFile(typeTmp);
    });

    //Upload
    const uploadButton = document.createElement('button');
    uploadButton.textContent = 'Upload';
    uploadButton.className = 'Upload';
    uploadButton.addEventListener('click', function () {
        uploadData.click();
    });
    const uploadData = document.createElement('input');
    uploadData.hidden = true;
    uploadData.type = 'file';
    buttonContainer.appendChild(uploadButton);
    buttonContainer.appendChild(uploadData);
    uploadData.addEventListener('change', uploadFile);


    document.body.appendChild(buttonContainer); // Append the container to the document body
}

function removeAllItems() {
    const containers = document.querySelectorAll('.icon-container, .textContainer, .pictureContainer, .videoContainer, .audioContainer, .buttonContainer, .fileNameContainer');
    containers.forEach((container) => {
        container.remove();
    });
}

function disableButtonsOpenedFile() {
    const containers = document.querySelectorAll('.Open, .createTextFile, .Upload, .createFolder, .Delete');
    containers.forEach((container) => {
        container.disabled = true;
    });
}

function disableButtonsOpenedFolder() {
    const containers = document.querySelectorAll('.Download');
    containers.forEach((container) => {
        container.disabled = true;
    });
}

function createTextField(apiResponse) {
    const textContainer = document.createElement('div');
    const textEditor = document.createElement('textarea');
    const subButton = document.createElement('button');

    textEditor.value = atob(apiResponse); // Set the text content of the textarea

    textContainer.className = 'textContainer';
    subButton.className = 'Submit';
    subButton.textContent = 'Submit';

    textContainer.appendChild(textEditor);
    textContainer.appendChild(subButton);

    subButton.addEventListener('click', () => {
        encodeInput(textEditor.value, 'text/plain');
    });

    document.body.appendChild(textContainer);
} //If text-field is need, function to create text-field
