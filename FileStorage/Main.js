//Global needed variables with no vision-restriction
let token = null;
let apiReq = new XMLHttpRequest();
let path = 'http://localhost:8080/';
let localhostUrl = 'http://localhost:8080/';
let url = 'http://localhost:8080/';
const names = [];
const types = [];
let pathTmp;
let typeTmp;
let dataTypeSuperiorAccess;
let pathOld;
let initialiseDownload=false;
let apiResponse;

//get the end from a path, needed to upload data with the correct dataType
function pathEnd() {
    let arrayPath = path.split("?");
    let arrayTmp = arrayPath[0];
    arrayPath = arrayTmp.split("/");
    return arrayPath[arrayPath.length-1];
}