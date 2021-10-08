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

//        List<Product> products = new ArrayList<Product>();
//        try {
//            QuerySnapshot snapshots = Tasks.await(db.collection("product").get());
//            for (QueryDocumentSnapshot document: snapshots ) {
//
//                //Product product = new Product(document.getId(),)
//                document.getData();
//            }
//
//        } catch (ExecutionException | InterruptedException ex) {
//            //return products;
//        }
//
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//
//            List<IProduct> products;
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.d(TAG, document.getId() + " => " + document.getData());
//                        products.add(document.getData().get(document.getId()));
//
//                    }
//                } else {
//                    Log.d(TAG, "Error getting documents: ", task.getException());
//                }
//            }
//        });


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


            QuerySnapshot snapshot = Tasks.await(db.collection("product").orderBy("name").startAt(searchTerm).endAt(searchTerm + '\uf8ff').get());

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                products.add(ProductTransformer.unpack(ds.getId(), ds.getData().get("brandName").toString(),ds.getData()));
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

            QuerySnapshot snapshot;
            if (min == null && max == null) {
                snapshot = Tasks.await(db.collection("product").whereEqualTo("categoryId", categoryId).whereEqualTo("brandId", categoryId).get());
            } else if (min != null) {
                snapshot = Tasks.await(db.collection("product").whereEqualTo("categoryId", categoryId).whereEqualTo("brandId", categoryId).orderBy("price", Query.Direction.ASCENDING).get());
            } else {
                snapshot = Tasks.await(db.collection("product").whereEqualTo("categoryId", categoryId).whereEqualTo("brandId", categoryId).orderBy("price", Query.Direction.DESCENDING).get());
            }

            for (DocumentSnapshot ds : snapshot.getDocuments()) {
                products.add(ProductTransformer.unpack(ds.getId(), ds.getData().get("brandName").toString(),ds.getData()));
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
