const { db } = require("./firestore");

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

const products = [
  {
    id: "gxcn5w7AsbIgFLLzb9nW",
    name: "Bijoux Eyeshadow Palette",
    category: "Makeup",
    brand: "NARS",
    slogan: "NARS’ largest eyeshadow palette ever.",
    usage:
      "Apply shadow directly on top of primer to create full intensity that lasts all day. The versatile formula can be used as an eyeshadow or liner, applied wet or dry.",
    details: `Strike it rich with a palette of sixteen limited-edition intensely pigmented eyeshadows in decadent jewel tones and velvety neutrals in matte, satin, and shimmer finishes, plus glitter toppers. Swipe and shade your way to style.
Palette shades:
Under your skin (shimmering beige silver)
Devotion (sparkling antique gold)
Copycat (shimmering rose)
Fuel the fire (sparkling amber)
Do it again (light pink satin)
Limelight (sparkling pink/gold)
Pandemonium (shimmering amethyst
Thrill seeker (light forest green matte)
Dirty deeds (mauve matte)
On fire (shimmering burnt orange)
Get hot (ruby satin)
Midnight rider (shimmering emerald)
Idolized (shimmering cocoa)
Go deep (burnt sienna matte)
Girl gang (cocoa matte)
Powerhouse (deep brown satin)`,
    link: "https://www.meccabeauty.co.nz/nars/bijoux-eyeshadow-palette/I-052040.html?cgpath=makeup",
    defaultImageURI:
      "https://www.meccabeauty.co.nz/on/demandware.static/-/Sites-mecca-online-catalog/default/dwe926c1cd/product/nars/hr/i-052040-bijoux-eyeshadow-palette-1-940.jpg",
    form: form.Palette,
    price: 95.0,
    productVersion: [{
      id: "qTZENjVk4sHRsjunBZ8c",
      name: "Bijoux Eyeshadow Palette",
      hexColor: "",
      imageURI: [],
      order: 1,
    }],
  },
  {
    id: "tHQLXsFtsnQ2YyBp3f8S",
    name: "High Profile Cheek Palette",
    category: "Makeup",
    brand: "NARS",
    slogan: "A holiday-themed limited-edition cheek palette.",
    usage: `Use #16 Blush Brush to apply your selected shade of Blush to the apples of the cheek.
Sweep any excess across the hairline, bridge of the nose and chin.
To enhance your glow, swirl #14 Bronzer Brush or Yachiyo Kabuki Brush over two or more shades in the palette and buff on cheeks.`,
    details: `A holiday-themed cheek palette featuring six limited-edition shades in one of NARS’ most innovative formulas. Made for all skin tones, these flattering shades allow you to contour, highlight, and pop with natural-looking dimension. This innovative, lightweight powder base achieves maximum color clarity. It builds effortlessly and blends seamlessly onto skin, revealing color in its truest, most vibrant form. All in one coveted palette.

Includes:
Nude Revue 3g
Spin Off 3g
High Demand 3g
Hit It Off 3g
Showdown 3g
Just Lust 3g`,
    link: "https://www.meccabeauty.co.nz/nars/bijoux-eyeshadow-palette/I-052040.html?cgpath=makeup",
    defaultImageURI:
      "https://www.meccabeauty.co.nz/on/demandware.static/-/Sites-mecca-online-catalog/default/dwb2ba5fb0/product/nars/hr/i-052041-high-profile-cheek-palette-1-940.jpg",
    form: form.Palette,
    price: 95.0,
    productVersion: [{
      id: "C9PwyH3RFv29kQdsYSyN",
      name: "High Profile Cheek Palette",
      hexColor: "",
      imageURI: [],
      order: 1,
    }],
  },
];

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
      const data = {
        name: product.name,
        category: db.doc(`category/${categoriesMap[product.category].id}`),
        brand: db.doc(`brand/${brandsMap[product.brand].id}`),
        slogan: product.slogan,
        usage: product.usage,
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
    
          await db.collection("product").doc(product.id).collection("productVersion").doc(version.id).set(data);
        })
      )
    })
  );
};

init();

exports.products = products;
