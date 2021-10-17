package se306p2.model.repository;

import android.util.Pair;

import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Client;
import com.algolia.search.saas.Index;
import com.algolia.search.saas.RequestOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.entity.IBrand;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.entity.IProductVersion;
import se306p2.domain.interfaces.repositories.IProductRepository;
import se306p2.model.transformers.BenefitTransformer;
import se306p2.model.transformers.BrandTransformer;
import se306p2.model.transformers.ProductTransformer;
import se306p2.model.transformers.ProductVersionTransformer;

public class ProductRepository implements IProductRepository {

    private FirebaseFirestore db;
    private static ProductRepository instance;
    private Client algolia;

    private ProductRepository(FirebaseFirestore firestore) {
        this.db = firestore;
        // This is search key so can be public
        this.algolia = new Client("JE24UH34RN", "a93d90ac0bbf715ade4ec77ac35b504c");
    }

    public static IProductRepository getInstance() {
        if (instance == null) {
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            instance = new ProductRepository(firestore);
        }
        return instance;
    }


    public List<IProduct> getProducts() {

        List<IProduct> products = new ArrayList<>();
        try {
            QuerySnapshot snapshot = Tasks.await(db.collection("product").get());

            Map<DocumentReference, String> brandsMap = getBrandsFromSnapshot(snapshot.getDocuments());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                String brandName = brandsMap.get((DocumentReference) ds.getData().get("brand"));
                products.add(ProductTransformer.unpack(ds.getId(), brandName, ds.getData()));
            }
            return products;
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public IProduct getProductById(String id) {
        try {
            DocumentSnapshot snapshot = Tasks.await(db.collection("product").document(id).get());

            if (!snapshot.exists()) {
                return null;
            }

            DocumentReference brandRef = (DocumentReference) snapshot.getData().get("brand");
            DocumentSnapshot brand = Tasks.await(brandRef.get());
            return ProductTransformer.unpack(snapshot.getId(), brand.getData().get("name").toString(), snapshot.getData());
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<IProduct> getProductsByCategoryId(String id) {
        List<IProduct> products = new ArrayList<>();
        try {

            DocumentReference docRef = db.collection("category").document(id);
            QuerySnapshot snapshot = Tasks.await(db.collection("product").whereEqualTo("category", docRef).get());

            Map<DocumentReference, String> brandsMap = getBrandsFromSnapshot(snapshot.getDocuments());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                if (ds.getData() == null) break;
                DocumentReference brandRef = (DocumentReference) ds.getData().get("brand");
                products.add(ProductTransformer.unpack(ds.getId(), brandsMap.get(brandRef), ds.getData()));
            }
            return products;

        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<IProduct> getProductsByCategory(ICategory category) {
        List<IProduct> products = new ArrayList<>();
        try {

            DocumentReference docRef = db.collection("category").document(category.getId());
            QuerySnapshot snapshot = Tasks.await(db.collection("product").whereEqualTo("category", docRef).get());

            Map<DocumentReference, String> brandsMap = getBrandsFromSnapshot(snapshot.getDocuments());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                if (ds.getData() == null) break;
                DocumentReference brandRef = (DocumentReference) ds.getData().get("brand");
                products.add(ProductTransformer.unpack(ds.getId(), brandsMap.get(brandRef), ds.getData()));
            }
            return products;

        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<IProduct> getProductsByFilter(String categoryId, String brandId, BigDecimal min, BigDecimal max) {
        List<IProduct> products = new ArrayList<>();
        try {

            QuerySnapshot snapshot = null;
            Query idQuery = null;
            Query finalQuery = null;


            if (categoryId == null && brandId == null) {

                throw new UnsupportedOperationException("category and brand cannot be both null");

            } else if (categoryId != null && brandId != null) {

                DocumentReference docRefCategory = db.collection("category").document(categoryId);
                DocumentReference docRefBrand = db.collection("brand").document(brandId);

                Query initialQuery = db.collection("product").whereEqualTo("category", docRefCategory);
                idQuery = initialQuery.whereEqualTo("brand", docRefBrand);

            } else if (brandId != null) {

                DocumentReference docRefBrand = db.collection("brand").document(brandId);
                idQuery = db.collection("product").whereEqualTo("brand", docRefBrand);


            } else {

                DocumentReference docRefCategory = db.collection("category").document(categoryId);
                idQuery = db.collection("product").whereEqualTo("category", docRefCategory);

            }

            if (min != null && max != null) {
                Query priceQuery = idQuery.whereGreaterThanOrEqualTo("price", min.floatValue()).whereLessThanOrEqualTo("price", max.floatValue()).orderBy("price", Query.Direction.DESCENDING);
                snapshot = Tasks.await(priceQuery.get());

            } else if (max != null) {
                Query priceQuery = idQuery.whereGreaterThanOrEqualTo("price", max.floatValue()).orderBy("price", Query.Direction.DESCENDING);
                snapshot = Tasks.await(priceQuery.get());
            } else if (min != null) {
                Query priceQuery = idQuery.whereGreaterThanOrEqualTo("price", min.floatValue()).orderBy("price", Query.Direction.ASCENDING);
                snapshot = Tasks.await(priceQuery.get());
            } else {
                snapshot = Tasks.await(idQuery.get());

            }

            Map<DocumentReference, String> brandsMap = getBrandsFromSnapshot(snapshot.getDocuments());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                String brandName = brandsMap.get((DocumentReference) ds.getData().get("brand"));
                products.add(ProductTransformer.unpack(ds.getId(), brandName, ds.getData()));
            }
            return products;

        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private Map<DocumentReference, String> getBrandsFromSnapshot(List<DocumentSnapshot> snapshots) throws ExecutionException, InterruptedException {
        Set<DocumentReference> brandsRefUnique = new HashSet<>();
        for (DocumentSnapshot ds : snapshots) {
            if (ds.getData() == null) break;
            DocumentReference brandRef = (DocumentReference) ds.getData().get("brand");
            brandsRefUnique.add(brandRef);
        }

        List<Task<DocumentSnapshot>> brands = new ArrayList<>();
        for (DocumentReference df : brandsRefUnique) {
            brands.add(df.get());
        }

        List<DocumentSnapshot> documentSnapshots = Tasks.await(Tasks.whenAllSuccess(brands));

        Map<DocumentReference, String> brandsMap = new HashMap<>();
        for (DocumentSnapshot ds : documentSnapshots) {
            brandsMap.put(ds.getReference(), ds.getData().get("name").toString());
        }
        return brandsMap;
    }

    public List<IProductVersion> getProductVersions(String productId) {
        List<IProductVersion> productVersions = new ArrayList<>();

        try {

            QuerySnapshot snapshot = Tasks.await(db.collection("product").document(productId).collection("productVersion").get());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                productVersions.add(ProductVersionTransformer.unpack(ds.getId(), ds.getData()));
            }
            return productVersions;


        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public List<IBenefit> getBenefits(String productId) {
        List<IBenefit> benefits = new ArrayList<>();

        try {

            QuerySnapshot snapshot = Tasks.await(db.collection("product").document(productId).collection("benefits").get());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {

                benefits.add(BenefitTransformer.unpack(ds.getId(), ds.getData()));
            }
            return benefits;


        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<IProduct> getFeaturedProducts() {
        List<IProduct> products = new ArrayList<>();
        try {
            QuerySnapshot snapshot = Tasks.await(db.collection("product").get());

            Map<DocumentReference, String> brandsMap = getBrandsFromSnapshot(snapshot.getDocuments());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                String brandName = brandsMap.get((DocumentReference) ds.getData().get("brand"));
                products.add(ProductTransformer.unpack(ds.getId(), brandName, ds.getData()));
            }
            return products;

        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<String> searchAutoComplete(String searchTerm) {
        List<String> searchAutoCompletes = new ArrayList<>();

        Index index = algolia.getIndex("products");
        com.algolia.search.saas.Query query = new com.algolia.search.saas.Query(searchTerm)
                .setAttributesToRetrieve("name")
                .setHitsPerPage(4);

        try {
            JSONObject content = index.search(query, new RequestOptions());
            JSONArray array = content.getJSONArray("hits");
            for (int i = 0; i < array.length(); i++) {
                searchAutoCompletes.add(array.getJSONObject(i).getString("name"));
            }
        } catch (JSONException | AlgoliaException e) {
            e.printStackTrace();
            return null;
        }

        return searchAutoCompletes;
    }


    public Pair<List<IProduct>, List<IBrand>> getProductsBySearch(String searchTerm) {
        Index index = algolia.getIndex("products");
        com.algolia.search.saas.Query query = new com.algolia.search.saas.Query(searchTerm)
                .setAttributesToRetrieve("id")
                .setHitsPerPage(100);

        List<String> productIds = new ArrayList<>();
        try {
            JSONObject content = index.search(query, new RequestOptions());
            JSONArray array = content.getJSONArray("hits");
            for (int i = 0; i < array.length(); i++) {
                productIds.add(array.getJSONObject(i).getString("id"));
            }
            System.out.println(productIds);
        } catch (JSONException | AlgoliaException e) {
            e.printStackTrace();
            return null;
        }


        List<Task<DocumentSnapshot>> tasks = new ArrayList<>();
        for (String productId : productIds) {
            tasks.add(db.collection("product").document(productId).get());
        }

        try {
            List<DocumentSnapshot> documentSnapshots = Tasks.await(Tasks.whenAllSuccess(tasks));

            Set<DocumentReference> brandsRefUnique = new HashSet<>();
            for (DocumentSnapshot ds : documentSnapshots) {
                if (ds.getData() == null) break;
                DocumentReference brandRef = (DocumentReference) ds.getData().get("brand");
                brandsRefUnique.add(brandRef);
            }

            List<Task<DocumentSnapshot>> brands = new ArrayList<>();
            for (DocumentReference df : brandsRefUnique) {
                brands.add(df.get());
            }

            List<DocumentSnapshot> brandsDocumentSnapshots = Tasks.await(Tasks.whenAllSuccess(brands));

            Map<DocumentReference, String> brandsMap = new HashMap<>();
            List<IBrand> brandsArray = new ArrayList<>();
            for (DocumentSnapshot ds : brandsDocumentSnapshots) {
                brandsMap.put(ds.getReference(), ds.getData().get("name").toString());
                brandsArray.add(BrandTransformer.unpack(ds.getId(), ds.getData()));
            }

            List<IProduct> products = new ArrayList<>();
            for (DocumentSnapshot ds : documentSnapshots) {
                if (ds.getData() == null) break;
                DocumentReference brandRef = (DocumentReference) ds.getData().get("brand");
                products.add(ProductTransformer.unpack(ds.getId(), brandsMap.get(brandRef), ds.getData()));
            }
            return new Pair(products, brandsArray);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<IProduct> getProductsBySearchAndFilter(String searchTerm, String brandId, BigDecimal min, BigDecimal max) {
        Index index = algolia.getIndex("products");
        com.algolia.search.saas.Query query = new com.algolia.search.saas.Query(searchTerm)
                .setAttributesToRetrieve("id")
                .setHitsPerPage(100);

        if (brandId != null && min != null && max != null) {
            try {
                DocumentSnapshot brand = Tasks.await(db.collection("brand").document(brandId).get());
                System.out.println(brand.getString("name"));
                query.setFilters("brand:" + brand.getString("name") + " AND price:" + min + " TO " + max);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                query.setFilters("price:" + min + " TO " + max);
            }
        } else if (min != null && max != null) {
            query.setFilters("price:" + min + " TO " + max);
        }

        List<String> productIds = new ArrayList<>();
        try {
            JSONObject content = index.search(query, new RequestOptions());
            JSONArray array = content.getJSONArray("hits");
            for (int i = 0; i < array.length(); i++) {
                productIds.add(array.getJSONObject(i).getString("id"));
            }
            System.out.println(productIds);
        } catch (JSONException | AlgoliaException e) {
            e.printStackTrace();
            return null;
        }

        List<Task<DocumentSnapshot>> tasks = new ArrayList<>();
        for (String productId : productIds) {
            tasks.add(db.collection("product").document(productId).get());
        }

        try {
            List<DocumentSnapshot> documentSnapshots = Tasks.await(Tasks.whenAllSuccess(tasks));

            Map<DocumentReference, String> brandsMap = getBrandsFromSnapshot(documentSnapshots);

            List<IProduct> products = new ArrayList<>();
            for (DocumentSnapshot ds : documentSnapshots) {
                if (ds.getData() == null) break;
                DocumentReference brandRef = (DocumentReference) ds.getData().get("brand");
                products.add(ProductTransformer.unpack(ds.getId(), brandsMap.get(brandRef), ds.getData()));
            }
            return products;

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
