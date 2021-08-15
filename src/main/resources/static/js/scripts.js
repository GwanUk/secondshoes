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
    console.log("click");
    const xhr = new XMLHttpRequest();
    xhr.open('post', '/item/items')
    xhr.setRequestHeader('Content-type', 'application/json')

    xhr.onload = () => {
        console.log(xhr.responseText);
    };

    let gender_value = null;
    for (let i = 0; i < document.getElementsByName("gender").length; i++) {
        if (document.getElementsByName("gender")[i].checked == true) {
            gender_value = document.getElementsByName("gender")[i].value;
        }
    }

    let sizes_arr = new Array();
    for (let i = 0; i < document.getElementsByName("sizes").length; i++) {
        if (document.getElementsByName("sizes")[i].checked == true) {
            sizes_arr.push(document.getElementsByName("sizes")[i].value);
        }
    }

    let brands_arr = new Array();
    for (let i = 0; i < document.getElementsByName("brands").length; i++) {
        if (document.getElementsByName("brands")[i].checked == true) {
            brands_arr.push(document.getElementsByName("brands")[i].value);
        }
    }

    let categories_arr = new Array();
    for (let i = 0; i < document.getElementsByName("categories").length; i++) {
        if (document.getElementsByName("categories")[i].checked == true) {
            categories_arr.push(document.getElementsByName("categories")[i].value);
        }
    }

    let data = {
        gender : gender_value,
        minPrice : document.getElementById("minPrice").value,
        maxPrice : document.getElementById("maxPrice").value,
        sizes : sizes_arr,
        brands : brands_arr,
        categories : categories_arr
    }
    xhr.send(JSON.stringify(data));
}



window.addEventListener('DOMContentLoaded', function () {
    if (document.getElementById("find_target")) {
        const xhr = new XMLHttpRequest();
        xhr.open("get", "/item/items");
        xhr.onload = () => {
            let pageable = JSON.parse(xhr.responseText);
            console.log(pageable);
            pageable.content.forEach((itemDetailDto) => {
                console.log("어허");
                for (const key in itemDetailDto) {
                    console.log(key + " : " + itemDetailDto[key]);
                }
                console.log(itemDetailDto.uploadImages);
                console.log(itemDetailDto.memberInfoDto);

                document.getElementById("items_target").innerHTML += "<div class=\"col mb-5\" >\n" +
                    "                    <div class=\"card h-100 item-card\" onclick=\"item()\">\n" +
                    "                        <!-- Sale badge-->\n" +
                    "                        <div class=\"badge bg-dark text-white position-absolute\" style=\"top: 0.5rem; right: 0.5rem\">Sale</div>\n" +
                    "                        <!-- Product image-->\n" +
                    "                        <img class=\"card-img-top\" src=/item/image/"+itemDetailDto.uploadImages[0].storeImageName+" alt=\"...\"/>\n" +
                    "                        <!-- Product details-->\n" +
                    "                        <div class=\"card-body p-4\">\n" +
                    "                            <div class=\"text-center\">\n" +
                    "                                <!-- Product name-->\n" +
                    "                                <h5 class=\"fw-bolder\">"+itemDetailDto.title+"</h5></h5>\n" +
                    "                                <!-- Product reviews-->\n" +
                    "                                <div class=\"d-flex justify-content-center small text-warning mb-2\">\n" +
                    "                                    <div class=\"bi-star-fill\"></div>\n" +
                    "                                    <div class=\"bi-star-fill\"></div>\n" +
                    "                                    <div class=\"bi-star-fill\"></div>\n" +
                    "                                    <div class=\"bi-star-fill\"></div>\n" +
                    "                                    <div class=\"bi-star-fill\"></div>\n" +
                    "                                </div>\n" +
                    "                                <!-- Product price-->\n" +
                    "                                <span class=\"text-muted text-decoration-line-through\">$20.00</span>\n" +
                    "                                $18.00\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                        <!-- Product actions-->\n" +
                    "                        <div class=\"card-footer p-4 pt-0 border-top-0 bg-transparent\">\n" +
                    "                            <div class=\"text-center\"><a class=\"btn btn-outline-dark mt-auto\" href=\"#\">Add to cart</a></div>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>"
            });
        };
        xhr.send();
    }
});






