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
    const xhr = new XMLHttpRequest();
    xhr.open('post', '/item/items')
    xhr.setRequestHeader('Content-type', 'application/json')
    xhr.onload = () => {
        console.log('!!! header !!!: '+xhr.getAllResponseHeaders())
        console.log('!!! response !!!: '+xhr.response)
    };

    let data = '';
    if (document.querySelector("option #gender").value) {
        data += 'gender'+document.querySelector("option #gender").value;
    }
    // data += 'minPrice';
    // data += 'maxPrice';
    if (document.querySelector("option #sizes").value) {
        data += 'sizes'+document.querySelector("option #sizes").value;
    }
    if (document.querySelector("option #brands").value) {
        data += 'brands'+document.querySelector("option #brands").value;
    }
    if (document.querySelector("option #categories").value) {
        data += 'categories'+document.querySelector("option #categories").value;
    }

    console.log('@@@ test @@@' + data);

    xhr.send(data);
}


