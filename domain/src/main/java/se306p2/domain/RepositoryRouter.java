package se306p2.domain;

import se306p2.model.interfaces.IBrandRepository;
import se306p2.model.interfaces.ICategoryRepository;
import se306p2.model.interfaces.IProductRepository;
import se306p2.model.interfaces.IRatingRepository;
import se306p2.model.interfaces.IUserRepository;

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

    public static RepositoryRouter init(IBrandRepository brandRepo, ICategoryRepository categoryRepo,
            IProductRepository productRepo, IRatingRepository ratingRepo, IUserRepository userRepo) {
        return new RepositoryRouter(brandRepo, categoryRepo, productRepo, ratingRepo, userRepo);
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