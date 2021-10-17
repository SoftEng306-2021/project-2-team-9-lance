const { db } = require("./firestore");

const GOOGLE_STORAGE =
  "https://storage.googleapis.com/se306-project-2-team-9.appspot.com";

const categories = [
  {
    id: "gxcn5w7AsbIgFLLzb9nW",
    name: "Makeup",
    image: `${GOOGLE_STORAGE}/categories/makeup.png`,
    order: 2
  },
  {
    id: "AZc4nze30eOCxjGmMiVS",
    name: "Skin Care",
    image: `${GOOGLE_STORAGE}/categories/skincare.png`,
    order: 1
  },
  {
    id: "ZXqgovMgJ666L1UaBjiL",
    name: "Hair Care",
    image: `${GOOGLE_STORAGE}/categories/haircare.png`,
    order: 5
  },
  {
    id: "xBeOpIqRGbLorhAgYskL",
    name: "Body Care",
    image: `${GOOGLE_STORAGE}/categories/bodycare.png`,
    order: 4
  },
  {
    id: "TMQjHswd34pclUuFNeT3",
    name: "Fragrance",
    image: `${GOOGLE_STORAGE}/categories/fragrance.png`,
    order: 3
  },
  {
    id: "dGvaSxSK2eSVju1PdiMs",
    name: "Accessories",
    image: `${GOOGLE_STORAGE}/categories/accessories.png`,
    order: 6
  },
];

const init = async () => {
  await Promise.all(
    categories.map(async (category) => {
      const data = {
        name: category.name,
        imageURI: category.image,
        order: category.order
      };

      return await db.collection("category").doc(category.id).set(data);
    })
  );
};

init();

exports.categories = categories;
