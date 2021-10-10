package se306p2.model.repository;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutionException;


import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.entity.IProductVersion;
import se306p2.domain.interfaces.repositories.IProductRepository;
import se306p2.model.entities.Benefit;
import se306p2.model.entities.ProductVersion;
import se306p2.model.transformers.BenefitTransformer;
import se306p2.model.transformers.ProductTransformer;
import se306p2.model.transformers.ProductVersionTransformer;

public class ProductRepository implements IProductRepository {

    private FirebaseFirestore db;
    private static ProductRepository instance;


    public static IProductRepository getInstance() {
        return instance;
    }

    public List<IProduct> getProducts() {

        List<IProduct> products = new ArrayList<>();
        try {
            QuerySnapshot snapshot = Tasks.await(db.collection("product").get());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {

                products.add(ProductTransformer.unpack(ds.getId(), ds.getData().get("brand").toString(),ds.getData()));
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

            return ProductTransformer.unpack(snapshot.getId(), snapshot.getData().get("brand").toString(),snapshot.getData());
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

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                products.add(ProductTransformer.unpack(ds.getId(), ds.getData().get("brand").toString(),ds.getData()));
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

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                products.add(ProductTransformer.unpack(ds.getId(), ds.getData().get("brand").toString(),ds.getData()));
            }
            return products;

        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<IProduct> getProductsBySearch(String searchTerm) {
        List<IProduct> products = new ArrayList<>();
        try {

            QuerySnapshot snapshot = Tasks.await(db.collection("product").orderBy("name").whereGreaterThan("name" ,searchTerm).whereLessThanOrEqualTo("name",searchTerm + '\uf8ff').get());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                products.add(ProductTransformer.unpack(ds.getId(), ds.getData().get("brand").toString(),ds.getData()));
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

            Query finalQuery = null;

            if (categoryId == null && brandId == null) {

                throw new UnsupportedOperationException("category and brand cannot be both null");

            } else if (categoryId != null && brandId != null) {

                DocumentReference docRefCategory =  db.collection("category").document(categoryId);
                DocumentReference docRefBrand =  db.collection("brand").document(brandId);

                Query initialQuery = db.collection("product").whereEqualTo("category", docRefCategory);
                finalQuery = initialQuery.whereEqualTo("brand", docRefBrand);

            } else if (brandId != null) {

                DocumentReference docRefBrand =  db.collection("brand").document(brandId);
                finalQuery = db.collection("product").whereEqualTo("brand", docRefBrand);


            } else {

                DocumentReference docRefCategory =  db.collection("category").document(categoryId);
                finalQuery = db.collection("product").whereEqualTo("category", docRefCategory);

            }

            if (min != null && max != null) {
                throw new UnsupportedOperationException("only one of min or max can be not null");
            } else if (min != null) {
                finalQuery = finalQuery.orderBy("price", Query.Direction.ASCENDING);

            } else if (max != null) {
                finalQuery = finalQuery.orderBy("price", Query.Direction.DESCENDING);
            }

            QuerySnapshot snapshot = Tasks.await(finalQuery.get());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                products.add(ProductTransformer.unpack(ds.getId(), ds.getData().get("brand").toString(),ds.getData()));
            }
            return products;

        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<IProductVersion> getProductVersions(String productId) {
        List<IProductVersion> productVersions = new ArrayList<>();

        try {

            QuerySnapshot snapshot = Tasks.await(db.collection("product").document(productId).collection("productVersion").get());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {

                productVersions.add(ProductVersionTransformer.unpack(ds.getId(),ds.getData()) );
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

                benefits.add(BenefitTransformer.unpack(ds.getId(),ds.getData()));
            }
            return benefits;


        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<IProduct> getFeaturedProducts() {
        throw new UnsupportedOperationException();
    }

    public List<String> searchAutoComplete(String searchTerm) {
        throw new UnsupportedOperationException();
    }
}
