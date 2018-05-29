package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.ViewWineReviewsAndRatingByUsers;
import models.tblUserWineReviews;
import models.tblUserWinesRatings;
import models.tblUsers;
import models.tblWines;

@Stateless
@LocalBean
public class ViewWineReviewsAndRatingByUsersService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;
	
	@EJB
	UsersService userService = new UsersService();
	
	@EJB
	WinesService wineService = new WinesService();
	
	@SuppressWarnings("unchecked")
	public List<ViewWineReviewsAndRatingByUsers> getAllWineReviewsAndRatingForUser(Integer userId) {
		if ( (userId != null) && (userId > 0) ) {
			
			Query query = em.createNativeQuery("select * from `viewWineReviewsAndRatingByUsers` where `userId`=?1", ViewWineReviewsAndRatingByUsers.class);
	    	query.setParameter(1, userId);
	    	
	    	try {
	    	
	    		List<ViewWineReviewsAndRatingByUsers> results = query.getResultList();
		    	return results;
		    	
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		return null;
	    	}
	    	
		} else {
			return null;
		}
		
	}

	public Boolean deleteRating(Integer ratingId) {
		if ( (ratingId != null) && (ratingId > 0) ) {
			
			tblUserWinesRatings rating = getRatingById(ratingId);
			if ( (rating != null) && (rating.getId() > 0) ) {
				try {
					em.remove(rating);
		            return true;
				} catch (Exception e) {
					return false;
				}
			} else {
				return false;
			}
		} else return false;
		
	}

	public Boolean updateRating(Integer ratingId, Integer ratingValue) {
		if ( (ratingId != null) && (ratingId > 0) ) {
			
			if ( (ratingValue != null) && (ratingValue > 0) ) {
			
				tblUserWinesRatings rating = getRatingById(ratingId);
				if ( (rating != null) && (rating.getId() > 0) ) {
					try {
						rating.setRating(ratingValue);
						em.merge(rating);
			            return true;
					} catch (Exception e) {
						return false;
					}
				} else {
					return false;
				}
				
			} else {
				
				return deleteRating(ratingId);
			}
		} else {
			
			return false;
		}
		
	}

	public Boolean insertRating(Integer userId, Integer wineId, Integer ratingValue) {
		
		if ( (userId != null) && (userId > 0) && (wineId != null) && (wineId > 0) && (ratingValue != null) && (ratingValue > 0)) {
			
			tblUsers user = userService.getUserById(userId);
			tblWines wine = wineService.getWineById(wineId);
			
			if ( (user != null) && (user.getId() > 0) && (wine != null) && (wine.getId() > 0) ) {
			
				tblUserWinesRatings rating = new tblUserWinesRatings();
				
				try {
					rating.setUserId(user);
					rating.setWineId(wine);
					rating.setRating(ratingValue);
					em.persist(rating);
		            return true;
				} catch (Exception e) {
					return false;
				}
				
			} else {
				
				return false;
			}
			

		} else {
			
			return false;
		}
		
	}

	public Boolean deleteReview(Integer reviewId) {
		if ( (reviewId != null) && (reviewId > 0) ) {
			
			tblUserWineReviews review = getReviewById(reviewId);
			if ( (review != null) && (review.getId() > 0) ) {
				try {
					em.remove(review);
		            return true;
				} catch (Exception e) {
					return false;
				}
			} else {
				return false;
			}
		} else return false;
		
	}

	public Boolean updateReview(Integer reviewId, String reviewTitle, String reviewComments) {
		if ( (reviewId != null) && (reviewId > 0) ) {
			
			if ( (reviewComments != null) && (!reviewComments.equals("")) ) {
			
				tblUserWineReviews review = getReviewById(reviewId);
				if ( (review != null) && (review.getId() > 0) ) {
					try {
						review.setTitle(reviewTitle);
						review.setComments(reviewComments);
						em.merge(review);
			            return true;
					} catch (Exception e) {
						return false;
					}
				} else {
					return false;
				}
				
			} else {
				
				return deleteReview(reviewId);
			}
		} else {
			
			return false;
		}
		
	}

	public Boolean insertReview(Integer userId, Integer wineId, String reviewTitle, String reviewComments) {
		
		if ( (userId != null) && (userId > 0) && (wineId != null) && (wineId > 0) && (reviewComments != null) && (!reviewComments.equals(""))) {
			
			tblUsers user = userService.getUserById(userId);
			tblWines wine = wineService.getWineById(wineId);
			
			if ( (user != null) && (user.getId() > 0) && (wine != null) && (wine.getId() > 0) ) {
			
				tblUserWineReviews review = new tblUserWineReviews();
				
				try {
					review.setUserId(user);
					review.setWineId(wine);
					review.setTitle(reviewTitle);
					review.setComments(reviewComments);
					em.persist(review);
		            return true;
				} catch (Exception e) {
					return false;
				}
				
			} else {
				
				return false;
			}
			

		} else {
			
			return false;
		}
		
	}
	
	private tblUserWinesRatings getRatingById(Integer ratingId) {

		if ( (ratingId != null) && (ratingId > 0) ) {
		
			TypedQuery<tblUserWinesRatings> typedQuery = em.createNamedQuery("tblUserWinesRatings.findById", tblUserWinesRatings.class);
			typedQuery.setParameter("id", ratingId);
			try {
				tblUserWinesRatings singleResult = typedQuery.getSingleResult();
				return singleResult;
			} catch (Exception e) {
				return null;
			}
			
		} else {
			return null;
		}
		
	}
	
	private tblUserWineReviews getReviewById(Integer reviewId) {

		if ( (reviewId != null) && (reviewId > 0) ) {
		
			TypedQuery<tblUserWineReviews> typedQuery = em.createNamedQuery("tblUserWineReviews.findById", tblUserWineReviews.class);
			typedQuery.setParameter("id", reviewId);
			try {
				tblUserWineReviews singleResult = typedQuery.getSingleResult();
				return singleResult;
			} catch (Exception e) {
				return null;
			}
			
		} else {
			return null;
		}
		
	}

}
