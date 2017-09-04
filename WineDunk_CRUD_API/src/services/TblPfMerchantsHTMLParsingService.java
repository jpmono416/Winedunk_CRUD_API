package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import models.Tblpfmerchanthtmlparsing;
import models.tblShops;

/**
 * Session Bean implementation class TblPfMerchantsHTMLParsingService
 */
@Stateless
@LocalBean
public class TblPfMerchantsHTMLParsingService {

	@PersistenceContext(unitName="Winedunk")
	EntityManager em;

	public List<Tblpfmerchanthtmlparsing> getAll()
	{
		return em.createNamedQuery("Tblpfmerchantshtmlparsing.findAll", Tblpfmerchanthtmlparsing.class).getResultList();
	}
	
	public List<Tblpfmerchanthtmlparsing> getByMerchant(Integer merchantId)
	{
		try {
			tblShops merchant = em.find(tblShops.class, merchantId);
			return em.createNamedQuery("Tblpfmerchantshtmlparsing.tblShops", Tblpfmerchanthtmlparsing.class).setParameter(0, merchant).getResultList();
		} catch (NoResultException noResExc) {
			return null;
		}
	}
}
