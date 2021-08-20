function signUp() {
    location.href = "/member/signUp";
}
function login() {
    location.href = "/member/login";
}

function items(size, number) {
    const xhr = new XMLHttpRequest();
    xhr.open('post', '/item/items/' + size + '/' + number);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.onload = () => {
        let page = JSON.parse(xhr.responseText);
        /*items form*/
        document.getElementById("items_target").innerHTML="";
        page.content.forEach((itemDetailDto) => {
            document.getElementById("items_target").innerHTML +=
                "<div class=\"col mt-5\">\n" +
                "    <div class=\"card h-100 item-card\" onclick=\"item()\">\n" +
                "        <!-- Sale badge-->\n" +
                "        <div class=\"badge bg-dark text-white position-absolute\" style=\"top: 0.5rem; right: 0.5rem\">Sale</div>\n" +
                "        <!-- Product image-->\n" +
                "        <img class=\"card-img-top\" src=/item/image/"+(itemDetailDto.uploadImages.length == 0 ? null : itemDetailDto.uploadImages[0].storeImageName)+" alt=\"...\"/>\n" +
                "        <!-- Product details-->\n" +
                "        <div class=\"card-body p-4\">\n" +
                "            <div class=\"text-center\">\n" +
                "                <!-- Product name-->\n" +
                "                <h5 class=\"fw-bolder\">"+itemDetailDto.title+"</h5></h5>\n" +
                "                <!-- Product reviews-->\n" +
                "                <div class=\"d-flex justify-content-center small text-warning mb-2\">\n" +
                "                    <div class=\"bi-star-fill\"></div>\n" +
                "                    <div class=\"bi-star-fill\"></div>\n" +
                "                    <div class=\"bi-star-fill\"></div>\n" +
                "                    <div class=\"bi-star-fill\"></div>\n" +
                "                    <div class=\"bi-star-fill\"></div>\n" +
                "                </div>\n" +
                "                <!-- Product price-->\n" +
                "                <span class=\"text-muted text-decoration-line-through\">$20.00</span>$18.00\n" +
                "            </div>\n" +
                "        </div>\n" +
                "            <!-- Product actions-->\n" +
                "        <div class=\"card-footer p-4 pt-0 border-top-0 bg-transparent\">\n" +
                "            <div class=\"text-center\"><a class=\"btn btn-outline-dark mt-auto\" href=\"#\">Add to cart</a></div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n";
        });
        paging(page);
    };

    /*options*/
    let search_value = document.getElementById("search").value;

    let gender_value = null;
    for (let i = 0; i < document.getElementsByName("gender").length; i++) {
        if (document.getElementsByName("gender")[i].checked == true) {
            gender_value = document.getElementsByName("gender")[i].value;
        }
    }

    let priceGoe_value = document.getElementById("priceGoe").value;
    let priceLoe_value = document.getElementById("priceLoe").value;

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
        search: search_value,
        gender: gender_value,
        priceGoe: priceGoe_value,
        priceLoe: priceLoe_value,
        sizes: sizes_arr,
        brands: brands_arr,
        categories: categories_arr,
    };
    xhr.send(JSON.stringify(data));
}

function paging(page) {
    console.log(page)
    let page_ul = document.getElementById("page_ul");
    page_ul.innerHTML = "";

    if (page.number > 0) {
        page_ul.innerHTML += "<li class=\"page-item\"><a class=\"page-link\" href=\"javascript:void(0)\" onclick='items("+page.size+","+(page.number-1)+")'>이전</a></li>";
    }

    let offset = parseInt(page.number/10);
    for (let i = offset; i < offset+10; i++) {
        page_ul.innerHTML += "<li class=\"page-item\"><a class=\"page-link\" href=\"javascript:void(0)\" onclick='items("+page.size+","+i+")'>"+(i+1)+"</a></li>";
        if (i == page.totalPages) {
            break;
        }
    }

    if (page.number < page.totalPages) {
        page_ul.innerHTML += "<li class=\"page-item\"><a class=\"page-link\" href=\"javascript:void(0)\" onclick='items("+page.size+","+(page.number+1)+")'>다음</a></li>";
    }
}

window.addEventListener('DOMContentLoaded', function () {
    if (document.getElementById("find_target")) {
        items(document.getElementById("dataPerPage").value, 0);
    }
});







