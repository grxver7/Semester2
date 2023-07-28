function back() {
    let diffBack = 1;
    if (dataTypeSuperiorAccess === 'dir') {
        diffBack = 2;
    }
    let arrayPath = path.split("/");
    let newPath = '';
    if (arrayPath.length <= 4) {
        home();
    } else {
        for (let i = 0; i < arrayPath.length - diffBack; i++) {
            newPath += arrayPath[i] + '/';
        }
        path = newPath;
        dataTypeSuperiorAccess = 'dir';
        getData('dir');
    }
}

function pathMemory(dataType, checkHistoryNavigation) {
    let pathStatus;
    pathStatus = 'Path: ' + path;
    document.getElementById('pathStatusForWebsite').innerHTML = pathStatus;
    if (!checkHistoryNavigation) {
        let state = {path: path, dataType: dataType};
        history.pushState(state, '', '');
    }
} //store the path, needed for navigations
function home() {
    removeAllItems();
    path = localhostUrl;
    getData('dir');
    // Hide the login container
    document.getElementById("loginContainer").style.display = "none";
    // Display the main page content
    document.getElementById("content").style.display = "block";
}

function handleHistoryChange(event) {
    if (event.state) {
        path = event.state.path;
        let dataType = event.state.dataType;
        getData(dataType, true); // Pass true to indicate history navigation
    }
}//use HistoryButton in Browser, based on HistoryAPI

window.addEventListener('popstate', handleHistoryChange);