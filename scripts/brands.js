const { db } = require("./firestore");

const GOOGLE_STORAGE =
  "https://storage.googleapis.com/se306-project-2-team-9.appspot.com";

const brands = [
  {
    id: "9JuHN4lqKU1l3727EktG",
    name: "NARS",
  },
  {
    id: "YS6Inh0AQHnJzglXGPap",
    name: "Morphe",
  },
  {
    id: "b3Akp0esKx4EmOsVDTQU",
    name: "Laura Mercier",
  },
  {
    id: "3on11mBaT1kavvlMnURW",
    name: "Clinique",
  },
  {
    id: "xA4r22VbWdXbrTEUihSE",
    name: "Kiehl's",
  },
  {
    id: "xrW3Tz5N2jZhAJmyFt8Y",
    name: "Origins",
  },
  {
    id: "CjZjM49LGeundUZw1cSh",
    name: "AVEDA",
  },
  {
    id: "d4C3HrH0XWvbkCOWF2gs",
    name: "IGK",
  },
  {
    id: "CqNgX6pCGngaTYFvVdsr",
    name: "Living Proof",
  },
];

const init = async () => {
  await Promise.all(
    brands.map(async (brand) => {
      const data = {
        name: brand.name,
      };

      return await db.collection("brand").doc(brand.id).set(data);
    })
  );
};

init();

exports.brands = brands;
