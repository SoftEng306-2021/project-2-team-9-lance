package se306p2.domain;

import se306p2.domain.interfaces.repositories.IBrandRepository;
import se306p2.domain.interfaces.repositories.ICategoryRepository;
import se306p2.domain.interfaces.repositories.IProductRepository;
import se306p2.domain.interfaces.repositories.IRatingRepository;
import se306p2.domain.interfaces.repositories.IUserRepository;

/**
 * This class is responsible for routing requests to the correct repository.
 */
public class RepositoryRouter {
    private IBrandRepository brandRepo;
    private ICategoryRepository categoryRepo;
    private IProductRepository productRepo;
    private IRatingRepository ratingRepo;
    private IUserRepository userRepo;

    public static RepositoryRouter instance;

    /**
     * Constructor for RepositoryRouter.
     * @param brandRepo
     * @param categoryRepo
     * @param productRepo
     * @param ratingRepo
     * @param userRepo
     */
    private RepositoryRouter(IBrandRepository brandRepo, ICategoryRepository categoryRepo,
                             IProductRepository productRepo, IRatingRepository ratingRepo, IUserRepository userRepo) {
        this.brandRepo = brandRepo;
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
        this.ratingRepo = ratingRepo;
        this.userRepo = userRepo;
    }

    /**
     * Setup RespositoryRouter.
     * @param brandRepo
     * @param categoryRepo
     * @param productRepo
     * @param ratingRepo
     * @param userRepo
     * @return
     */
    public static RepositoryRouter init(IBrandRepository brandRepo, ICategoryRepository categoryRepo,
                                        IProductRepository productRepo, IRatingRepository ratingRepo, IUserRepository userRepo) {
        instance = new RepositoryRouter(brandRepo, categoryRepo, productRepo, ratingRepo, userRepo);
        return instance;
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