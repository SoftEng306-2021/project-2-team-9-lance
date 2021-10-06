package se306p2.model.repository;

import com.google.firebase.firestore.FirebaseFirestore;

import java.math.BigDecimal;
import java.util.List;

import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.entity.IProductVersion;
import se306p2.domain.interfaces.repositories.IProductRepository;

public class ProductRepository implements IProductRepository {

    private FirebaseFirestore db;
    private static ProductRepository instance;


    public static IProductRepository getInstance() {
        return instance;
    }

    public List<IProduct> getProducts() {

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

        throw new UnsupportedOperationException();
    }

    public IProduct getProductById(String id) {
        throw new UnsupportedOperationException();
    }

    public List<IProduct> getProductsByCategoryId(String id) {
        throw new UnsupportedOperationException();
    }

    public List<IProduct> getProductsByCategory(ICategory category) {
        throw new UnsupportedOperationException();
    }

    public List<IProduct> getProductsBySearch(String searchTerm) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IProduct> getProductsByFilter(String categoryId, String brandId, BigDecimal min, BigDecimal max) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IProductVersion> getProductVersions(String productId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IBenefit> getBenefits(String productId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IProduct> getFeaturedProducts() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> searchAutoComplete(String searchTerm) {
        throw new UnsupportedOperationException();
    }
}
