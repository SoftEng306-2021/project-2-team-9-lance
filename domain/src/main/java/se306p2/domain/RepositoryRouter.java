package se306p2.domain;

import se306p2.domain.interfaces.repositories.IBrandRepository;
import se306p2.domain.interfaces.repositories.ICategoryRepository;
import se306p2.domain.interfaces.repositories.IProductRepository;
import se306p2.domain.interfaces.repositories.IRatingRepository;
import se306p2.domain.interfaces.repositories.IUserRepository;
import se306p2.model.entities.Category;
import se306p2.model.repository.CategoryRepository;
import se306p2.model.repository.ProductRepository;
import se306p2.model.repository.RatingRepository;
import se306p2.model.repository.UserRepository;

public class RepositoryRouter {
    private IBrandRepository brandRepo;
    private ICategoryRepository categoryRepo;
    private IProductRepository productRepo;
    private IRatingRepository ratingRepo;
    private IUserRepository userRepo;

    public static RepositoryRouter instance;

    private RepositoryRouter(IBrandRepository brandRepo, ICategoryRepository categoryRepo,
            IProductRepository productRepo, IRatingRepository ratingRepo, IUserRepository userRepo) {
        this.brandRepo = brandRepo;
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
        this.ratingRepo = ratingRepo;
        this.userRepo = userRepo;
    }

    public static RepositoryRouter init() {
        return new RepositoryRouter(
                null,
                CategoryRepository.getInstance(),
                ProductRepository.getInstance(),
                RatingRepository.getInstance(),
                UserRepository.getInstance());
    }

    private static void checkInstance() throws IllegalStateException {
        if (instance == null) {
            throw new IllegalStateException("RepositoryRouter is not initialized");
        }
    }

    public static IBrandRepository getBrandRepository() throws IllegalStateException {
        checkInstance();
        return instance.brandRepo;
    }

    public static ICategoryRepository getCategoryRepository() throws IllegalStateException {
        checkInstance();
        return instance.categoryRepo;
    }

    public static IProductRepository getProductRepository() throws IllegalStateException {
        checkInstance();
        return instance.productRepo;
    }

    public static IRatingRepository getRatingRepository() throws IllegalStateException {
        checkInstance();
        return instance.ratingRepo;
    }

    public static IUserRepository getUserRepository() throws IllegalStateException {
        checkInstance();
        return instance.userRepo;
    }
}