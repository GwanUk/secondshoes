function cart() {
    location.href = "cart";
}
function signUp() {
    location.href = "/member/signUp";
}
function login() {
    location.href = "/member/login";
}
function item() {
    location.href = "/item";
}

function items() {

}

window.addEventListener('DOMContentLoaded', function () {
    let ob = {test: 'test111'};
    let job = JSON.stringify(ob);

    let xhr = new XMLHttpRequest();
    xhr.open('get', '/item/items');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('post-items').innerText = xhr.responseText;
        }
    };
    xhr.send();
});
