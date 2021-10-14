package se306p2.model.repository;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import se306p2.domain.interfaces.repositories.ICategoryRepository;

public final class DefaultRepository extends Activity {
    private DefaultRepository() { }

    public final static void init(@NonNull Context context) {
        FirebaseApp.initializeApp(context);
    }
}
