const copy = (text) => {
  let input = document.createElement("textarea");
  input.innerHTML = text;
  document.body.appendChild(input);
  input.select();
  let result = document.execCommand("copy");
  document.body.removeChild(input);
  return result;
};

const newid = (length) => {
  let result = "";
  let characters =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  let charactersLength = characters.length;
  for (let i = 0; i < length; i++) {
    result += characters.charAt(Math.floor(Math.random() * charactersLength));
  }
  return result;
};

const uniqueArray = (arr) => {
  let a = [];
  for (let i = 0, l = arr.length; i < l; i++) {
    if (arr[i].includes("https://static.mecca.com.au/")) break;
    if (a.indexOf(arr[i]) === -1 && arr[i] !== "") a.push(arr[i]);
  }

  return a;
};

const html2text = (html) => {
  html = html.replace(/<style([\s\S]*?)<\/style>/gi, "");
  html = html.replace(/<script([\s\S]*?)<\/script>/gi, "");
  html = html.replace(/<\/div>/gi, "\n");
  html = html.replace(/<\/li>/gi, "\n");
  html = html.replace(/<li>/gi, "  *  ");
  html = html.replace(/<\/ul>/gi, "\n");
  html = html.replace(/<\/p>/gi, "\n");
  html = html.replace(/<br\s*[\/]?>/gi, "\n");
  html = html.replace(/<[^>]+>/gi, "");
  return html;
};

const copyIngredients = () => {
  let ingredientsIndex = null;
  const stuff = [
    ...document.querySelectorAll('[data-cid="accordion-label"]'),
  ].map((label, index) => {
    switch (label.innerHTML) {
      case "Ingredients":
        ingredientsIndex = index;
    }
  });

  const ingredients = !ingredientsIndex
    ? ""
    : html2text(
        document.querySelectorAll('[data-cid="accordion-content"]')[
          ingredientsIndex
        ].innerHTML
      );
  copy(ingredients.replaceAll("\n", "\\n"));
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

  const slogan = document.querySelectorAll('[data-testid="one_liner"]')[0]
    .innerHTML;

  let benefitsIndex = null;
  let detailsIndex = null;
  let usageIndex = null;
  let ingredientsIndex = null;

  const stuff = [
    ...document.querySelectorAll('[data-cid="accordion-label"]'),
  ].map((label, index) => {
    switch (label.innerHTML) {
      case "Benefits":
        benefitsIndex = index;
        break;
      case "Details":
        detailsIndex = index;
        break;
      case "Usage":
        usageIndex = index;
        break;
      case "Ingredients":
        ingredientsIndex = index;
        break;
    }
  });

  const usage = !usageIndex
    ? ""
    : html2text(
        document.querySelectorAll('[data-cid="accordion-content"]')[usageIndex]
          .innerHTML
      );

  const details = !detailsIndex
    ? ""
    : html2text(
        document.querySelectorAll('[data-cid="accordion-content"]')[
          detailsIndex
        ].innerHTML
      );

  const ingredients = !ingredientsIndex
    ? ""
    : html2text(
        document.querySelectorAll('[data-cid="accordion-content"]')[
          ingredientsIndex
        ].innerHTML
      );

  let benefits = [];
  if (benefitsIndex != null) {
    const content = document.querySelectorAll('[data-cid="accordion-content"]')[
      benefitsIndex
    ];
    console.log(content);
    benefits = [...content.getElementsByTagName("p")].map((p) => p.innerHTML);
  }

  const priceString = document
    .querySelectorAll('[data-testid="current_price"]')[0]
    .innerHTML.slice(1);

  let json = {
    id: newid(20),
    name: name,
    category: category,
    brand: brand,
    slogan: slogan,
    usage: usage,
    details: details,
    link: window.location.href,
    defaultImageURI: uniqueImages[0],
    form: "",
    price: parseFloat(priceString),
    benefits: benefits,
    ingredients: ingredients,
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

console.log("LOADED EZ 2");

let copyBtn = document.createElement("button");
copyBtn.innerHTML = "Copy";
copyBtn.style = "position: fixed; z-index:1000; top: 0; left: 0;";
copyBtn.addEventListener("click", copyJSON);

let ingredientsBtn = document.createElement("button");
ingredientsBtn.innerHTML = "Copy Ingredients";
ingredientsBtn.style = "position: fixed; z-index:1000; top: 0; left: 50px;";
ingredientsBtn.addEventListener("click", copyIngredients);

let body = document.getElementsByTagName("body")[0];
body.appendChild(copyBtn);
body.appendChild(ingredientsBtn);
