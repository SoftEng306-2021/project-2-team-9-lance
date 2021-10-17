const { db } = require("./firestore");
const fs = require("fs");

const GOOGLE_STORAGE =
  "https://storage.googleapis.com/se306-project-2-team-9.appspot.com";

const { form } = require("./form");
const { categories } = require("./categories");
const { brands } = require("./brands");

/* TEMPLATE
{
  id: "",
  name: "",
  category: "",
  brand: "",
  slogan: "",
  usage: ``,
  details: ``,
  link: "",
  defaultImageURI:
    "",
  form: form.,
  price: 0,
  productVersion: [{
    id: "",
    name: "",
    hexColor: "",
    imageURI: [],
    order: 1,
  }],
},
*/

const products = require("./products.json");

const benefitsLoad = require("./benefits.json");
let benefits = Object.assign({}, benefitsLoad);

const init = async () => {
  const categoriesMap = categories.reduce((map, category) => {
    map[category.name] = category;
    return map;
  }, {});

  const brandsMap = brands.reduce((map, brand) => {
    map[brand.name] = brand;
    return map;
  }, {});

  await Promise.all(
    products.map(async (product) => {
      product.benefits.map((benefit) => {
        if (benefits[benefit] === undefined) {
          benefits[benefit] = "";
        }
      });

      const data = {
        name: product.name,
        category: db.doc(`category/${categoriesMap[product.category].id}`),
        brand: db.doc(`brand/${brandsMap[product.brand].id}`),
        slogan: product.slogan,
        usage: product.usage,
        details: product.details,
        link: product.link,
        defaultImageURI: product.defaultImageURI,
        form: product.form,
        price: product.price,
      };

      await db.collection("product").doc(product.id).set(data);

      await Promise.all(
        product.productVersion.map(async (version) => {
          const data = {
            name: version.name,
            hexColor: version.hexColor,
            imageURI: version.imageURI,
            order: version.order,
          };

          await db
            .collection("product")
            .doc(product.id)
            .collection("productVersion")
            .doc(version.id)
            .set(data);
        })
      );

      await Promise.all(
        product.benefits.map(async (benefit) => {
          const data = {
            name: benefit,
            imageURI: `${GOOGLE_STORAGE}/benefits/${benefits[benefit]}`,
          };

          await db
            .collection("product")
            .doc(product.id)
            .collection("benefits")
            .doc(benefit.replace("/", "-"))
            .set(data);
        })
      );
    })
  );

  let data = JSON.stringify(benefits, null, 2);
  fs.writeFileSync("benefits.json", data);
};

init();

exports.products = products;
