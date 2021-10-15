const copy = (text) => {
  var input = document.createElement("textarea");
  input.innerHTML = text;
  document.body.appendChild(input);
  input.select();
  var result = document.execCommand("copy");
  document.body.removeChild(input);
  return result;
};

const newid = (length) => {
  var result = "";
  var characters =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  var charactersLength = characters.length;
  for (var i = 0; i < length; i++) {
    result += characters.charAt(Math.floor(Math.random() * charactersLength));
  }
  return result;
};

const uniqueArray = (arr) => {
  var a = [];
  for (var i = 0, l = arr.length; i < l; i++) {
    if (arr[i].includes("https://static.mecca.com.au/")) break;
    if (a.indexOf(arr[i]) === -1 && arr[i] !== "") a.push(arr[i]);
  }

  return a;
};

const copyJSON = () => {
  // Images
  const images = [
    ...document.querySelectorAll('[data-testid="imageReponsiveImg"]'),
  ].map((img) => img.getAttribute("src"));

  const uniqueImages = uniqueArray(images);

  const name = document.querySelectorAll(
    '[data-testid="product_name_label"]'
  )[0].innerHTML;

  const category = document.querySelectorAll(
    '[data-testid="breadcrumbs-category-undefined-link"]'
  )[0].innerHTML;

  const brand = document.querySelectorAll('[data-testid="brand_name"]')[0]
    .innerHTML;

  const slogan = document.querySelectorAll(
    '[data-testid="product_name_label"]'
  )[0].innerHTML;

  const priceString = document
    .querySelectorAll('[data-testid="current_price"]')[0]
    .innerHTML.slice(1);

  let json = {
    id: newid(20),
    name: name,
    category: category,
    brand: brand,
    slogan: slogan,
    usage: ``,
    details: ``,
    link: window.location.href,
    defaultImageURI: uniqueImages[0],
    form: "",
    price: parseFloat(priceString),
    productVersion: [
      {
        id: newid(20),
        name: name,
        hexColor: "",
        imageURI: uniqueImages,
        order: 1,
      },
    ],
  };

  copy(JSON.stringify(json, null, 2));
};

console.log("LOADED EZ");

let copyBtn = document.createElement("button");
copyBtn.innerHTML = "Copy";
copyBtn.style = "position: fixed; z-index:1000; top: 0; left: 0;";
copyBtn.addEventListener("click", copyJSON);

let body = document.getElementsByTagName("body")[0];
body.appendChild(copyBtn);
