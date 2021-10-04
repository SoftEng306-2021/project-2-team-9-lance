package se306p2.model.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.entity.IProductVersion;
import se306p2.model.entities.Product;
import se306p2.model.interfaces.IProductRepository;

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

    public IProduct getProductByID(String id) {
        throw new UnsupportedOperationException();
    }

    public List<IProduct> getProductsByCategoryID(String id) {
        throw new UnsupportedOperationException();
    }

    public List<IProduct> getProductsByCategory(ICategory category) {
        throw new UnsupportedOperationException();
    }

    public List<IProduct> getProductsBySearch(String searchTerm) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IProduct> getProductsByFilter(String categoryID, String brandID, BigDecimal min, BigDecimal max) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IProductVersion> getProductVersions(String productID) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<IBenefit> getBenefits(String productID) {
        throw new UnsupportedOperationException();
    }
}
