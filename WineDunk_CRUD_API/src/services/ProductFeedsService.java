package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import models.Tblpf;
import models.Tblpfstatus;
import models.Tblpfmapping;
import models.Tblpfproduct;

@Stateless
@LocalBean
public class ProductFeedsService {

	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

	/**
	 * 
	 * @return
	 */
	public List<Tblpf> getAll()
	{
		try {
			return em.createNamedQuery("Tblpf.findAll", Tblpf.class).getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param newProductFeed
	 * @return
	 */
	public Tblpf persist(Tblpf newProductFeed)
	{
		em.persist(newProductFeed);
		return newProductFeed;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Tblpf getById(Integer id)
	{
		return id == null ? null : em.find(Tblpf.class, id);
	}

	/**
	 * 
	 * @param productFeedId
	 */
	public void delete(Integer productFeedId)
	{
		em.remove(productFeedId);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Tblpf setStatusFailed(Integer id)
	{
		Tblpf productFeed = em.find(Tblpf.class, id);
		Tblpfstatus status = em.createNamedQuery("Tblpfstatus.findByName", Tblpfstatus.class).setParameter("name", "Error").getSingleResult();

		productFeed.setStandardisationStatus(status);
		return productFeed;
	}

	/**
	 * 
	 * @param id
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void processProductFeed(Integer id) throws MalformedURLException, IOException
	{
		Tblpf productFeed = this.getById(id);
		Tblpfmapping pfMapping = em.createNamedQuery("Tblpfmapping.findByPFId", Tblpfmapping.class)
								   .setParameter("tblpf", productFeed.getId())
								   .getSingleResult();

		HttpURLConnection connection = (HttpURLConnection) new URL(productFeed.getDownloadURL()).openConnection();

		//TODO If zip unzip the final file on a stream, add isZip field to database

		connection.setRequestMethod("GET");

		try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())))
		{
			/**
			 * TODO add hasHeader property
			 * if(productFeed.getHasHeader())
			 * 	reader.readLine();
			 */

			final String line[] = new String[1];
			//in this list we will store the IDs of the products we process (to set te old products as deleted)
			final List<Integer> productsFound = new ArrayList<Integer>();
			ExecutorService executor = Executors.newFixedThreadPool(20);

			while((line[0] = reader.readLine()) != null)
			{
				executor.submit(new Runnable() {

					@Override
					public void run() {
						Tblpfproduct product;
						boolean newProduct = false;
						try {
							product = em.createNamedQuery("Tblpfproduct.findByPartnerIdAndMerchantId", Tblpfproduct.class)
									    .setParameter("merchantProductId", pfMapping.getMerchantProductIdColumn())
									    .setParameter("partnerProductId", pfMapping.getPartnerProductIdColumn())
									    .getSingleResult();
							//TODO catch possible exceptions on the log table yet to be developed
						} catch(NoResultException noResExc) {
							product = new Tblpfproduct();
							newProduct = true;
						}

						/* TODO productFeed.getSeparator() instead of comma*/
						String values[] = line[0].split(Pattern.quote(","));

						//populate values from the file using the mapping
						product.setClicktag(values[pfMapping.getClicktagColumn()]);
						product.setDeliveryCost(Float.valueOf(values[pfMapping.getDeliveryCostColumn()]));
						product.setImageURL(values[pfMapping.getImageURLColumn()]);
						product.setMerchantName(values[pfMapping.getMerchantNameColumn()]);
						product.setMerchantProductId(values[pfMapping.getMerchantProductIdColumn()]);
						product.setName(values[pfMapping.getNameColumn()]);
						product.setPartnerProductDescription(values[pfMapping.getPartnerProductDescriptionColumn()]);
						product.setPartnerProductId(values[pfMapping.getPartnerProductIdColumn()]);
						product.setPrice(Float.valueOf(values[pfMapping.getPriceColumn()]));
						product.setProductType(values[pfMapping.getWineTypeColumn()]);
						product.setTblpf(productFeed);

						//persist only if it's a new product
						if(newProduct)
							em.persist(product);

						synchronized(new Object()) {productsFound.add(product.getId());}
					}

				});

				executor.shutdown();
				try {
					executor.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					//if the tread is interrupted we just return without updating the last importation date, as the thread failed
					return;
				}

				//Get all the listed products from this parter and remove those which id is not contaied in the list with the current products' ids
				for(Tblpfproduct pfProduct : em.createNamedQuery("Tblpfproduct.tblpf", Tblpfproduct.class).setParameter("tblpf", productFeed).getResultList())
				{
					if(!productsFound.contains(pfProduct.getId()))
					{
						em.remove(pfProduct);
					}
				}

				//set last importation timestamp to current date
				productFeed.setLastImportation(new Timestamp(new Date().getTime()));
			}
		}
	}
}