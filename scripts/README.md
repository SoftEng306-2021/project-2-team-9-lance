# Firestore Insert Data Scripts

The following are scripts to insert data into our firestore database.

This folder also includes a chrome extension that is helpful when scraping data from MECCA's official website.

## Scripts

Run `npm install` to install dependencies for the following scripts:
```bash
# Insert and Update Categories
npm run category

# Insert and Update Brands
npm run brand

# Insert and Update Products and it's sub documents
npm run product
```

Notes:
- Errors using script are usually caused by missing IDs e.g. add missing category or brand.
- Inserting same id would replace the previous document.
- `product.json` is used as it is easier to insert new products through chrome extension.

## Chrome Extension

The extension allows developers to copy a single product into a element that can be copied directly into `product.json`. Note that missing category or brand must be added first before running the script.

The extension is a basic script that extracts data from the html page and covers it into a JSON element.

### Install Extension

Install chrome extension by enabling developer mode in extensions tab and loading the extension using the folder `chrome-extension`. 

### How to use

After installing and enabling the extension users would see a `copy` button on the top left of MECCA's offical website (note: require `.co.nz`). Once you are on a product page you can click the copy button to copy an extracted JSON element.
