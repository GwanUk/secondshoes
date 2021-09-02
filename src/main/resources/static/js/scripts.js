/* ===== item ===== */
function condition(page) {
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

    items(JSON.stringify(data), document.getElementById("dataPerPage").value, page);
}

/*findAll*/
function items(data, size, number) {
    const xhr = new XMLHttpRequest();
    xhr.open('post', '/item/findAll/' + size + '/' + number);
    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.onload = () => {
        let page = JSON.parse(xhr.responseText);
        document.getElementById("items_target").innerHTML="";
        page.content.forEach((itemThumbDto) => {
            let now = new Date();
            document.getElementById("items_target").innerHTML +=
                "<div class=\"col mt-5\">\n" +
                "    <div class=\"card h-100 item-card\">\n" +
                "        <!-- Sale badge-->\n" +
                "        <div class=\"badge bg-dark text-white position-absolute\" style=\"top: 0.5rem; right: 0.5rem\">" + itemThumbDto.createdDate + "<i class=\"bi bi-eye mx-1\"></i>" + itemThumbDto.viewCount + "</div>\n" +
                "        <!-- Product image-->\n" +
                "        <img class=\"card-img-top img-thumbnail hover-custom w-100 h-100\" src=\"" + (itemThumbDto.uploadImage ? "/item/image/" + itemThumbDto.uploadImage.storeImageName : "https://dummyimage.com/225x225/dee2e6/6c757d.jpg" ) + "\"  alt=\"...\"  onclick=\"location.href='/item/find/" + itemThumbDto.id + "'\"/>\n" +
                "        <!-- Product details-->\n" +
                "        <div class=\"card-body p-4\">\n" +
                "            <div class=\"text-center\">\n" +
                "                <!-- Product name-->\n" +
                "                <h5 class=\"fw-bolder\">"+itemThumbDto.title+"</h5></h5>\n" +
                "                <!-- Product price-->\n" +
                "                <span class=\"text-\"><i class=\"bi bi-cash-coin me-1\"></i>" + itemThumbDto.price + "</span><br/>" +
                "            </div>\n" +
                "        </div>\n" +
                "            <!-- Product actions-->\n" +
                "        <div class=\"card-footer p-4 pt-0 border-top-0 bg-transparent\">\n" +
                "            <div class=\"text-center\">" +
                "                <a class=\"btn btn-outline-primary mt-auto\" href=\"/member/find/"+itemThumbDto.memberInfoDto.id+"\">" + itemThumbDto.memberInfoDto.name + "</a>" +
                "                <a class=\"" + (itemThumbDto.wished ? "btn btn-danger mt-auto" : "btn btn-outline-danger mt-auto") + "\" href=\"javascript:void(0)\" onclick=\"wish(event," + itemThumbDto.id + ")\"><i class=\"bi bi-heart \"></i></a>" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n";
        });
        paging(page);
    };
    xhr.send(data);
}

/*paging*/
function paging(page) {
    let page_ul = document.getElementById("page_ul");
    page_ul.innerHTML = "";

    if (page.number > 0) {
        page_ul.innerHTML += "<li class=\"page-item item-card\"><a class=\"page-link link-secondary\" href=\"javascript:void(0)\" onclick=\"condition(" + (page.number - 1) + ")\">&laquo</a></li>";
    }

    let offset = parseInt(page.number/10);
    for (let i = offset*10; i < offset*10+10; i++) {
        if (i == page.totalPages) {
            break;
        }
        if (i == page.number) {
            page_ul.innerHTML += "<li class=\"page-item\"><a class=\"page-link link-dark\" href=\"javascript:void(0)\" onclick=\"condition(" + i + ")\">" + (i + 1) + "</a></li>";
        } else {
            page_ul.innerHTML += "<li class=\"page-item item-card\"><a class=\"page-link link-secondary\" href=\"javascript:void(0)\" onclick=\"condition(" + i + ")\">" + (i + 1) + "</a></li>";
        }

    }

    if (page.number < page.totalPages - 1) {
        page_ul.innerHTML += "<li class=\"page-item item-card\"><a class=\"page-link link-secondary\" href=\"javascript:void(0)\" onclick=\"condition(" + (page.number + 1) + ")\">&raquo</a></li>";
    }
}

/*이미지 미리보기*/
function imagePreview(event) {
    let fileArr = Array.from(event.target.files);
    fileArr.forEach(file => {
        let fileReader = new FileReader();
        let imgTag = document.createElement("img");
        document.getElementById("image_container").appendChild(imgTag);

        fileReader.onload = e => {
            imgTag.setAttribute("src", e.target.result);
        };

        fileReader.readAsDataURL(file);
    });
}

/*wish*/
function wish(event, itemId) {
    let xhr = new XMLHttpRequest();
    xhr.open("get", "/wish/ajax/" + itemId);
    xhr.onload = () => {
        if (xhr.responseText == "save") {
            event.target.classList.remove("btn-outline-danger");
            event.target.classList.add("btn-danger");
        } else if (xhr.responseText == "delete") {
            event.target.classList.remove("btn-danger");
            event.target.classList.add("btn-outline-danger");
        } else if (xhr.responseText == "HaveToLogin") {
            location.href = "/member/login";
        }
    };
    xhr.send();
}

/*comment*/
function commentFind(itemId) {
    let xhr = new XMLHttpRequest();
    xhr.open("get", "/comment/" + itemId);
    xhr.onload = () => {
        JSON.parse(xhr.responseText).forEach((itemCommentDto) => {  commentForm(itemCommentDto)  });
    };
    xhr.send();
}

function commentForm(itemCommentDto) {
    document.getElementById("comment_target").innerHTML += "" +
        "<div class=\"d-flex flex-row p-3\">\n" +
        "    <div class=\"w-100\">\n" +
        "        <div class=\"d-flex justify-content-between align-items-center\">\n" +
        "            <div class=\"d-flex flex-row align-items-center\"> <span class=\"mr-2\">"+ itemCommentDto.name +"</span> <small class=\"c-badge\">Top Comment</small> </div> <small>"+ itemCommentDto.createdDate +"</small>\n" +
        "        </div>\n" +
        "        <p class=\"text-justify comment-text mb-0\">"+ itemCommentDto.comment +"</p>\n" +
        "        <div class=\"d-flex flex-row user-feed\"> <span class=\"itemWish\"><i class=\"fa fa-heartbeat mr-2\"></i>14</span> <span class=\"ml-3\"><i class=\"fa fa-comments-o mr-2\"></i>Reply</span> </div>\n" +
        "    </div>\n" +
        "</div>";
}

window.addEventListener('DOMContentLoaded', function () {
    /*indexPage 조회*/
    if (document.getElementById("find_target")) {
        condition(0);
    }

    /*댓글 조회*/
    if (document.getElementById("itemId")) {
        commentFind(document.getElementById("itemId").value);
    }

    /*image-slide css*/
    if (document.getElementById("imageListSlide")) {
        document.getElementsByClassName("dot")[0].classList.add("active");
    }
});

/*item image-slide*/
function slide(ob) {
    let imageListSlide = document.getElementById("imageListSlide");
    let nowDot = ob.getAttribute("name");
    imageListSlide.style.transform = "translate(" + (1 - nowDot) * 522 + "px, 0px)";
    for (let dot of document.getElementsByClassName("dot")) {
        dot.className = "dot";
    }
    ob.classList.add("active");
}









