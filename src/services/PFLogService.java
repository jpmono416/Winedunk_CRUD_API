package services;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import constants.PFLogGeneralSettingsConstants;
import models.TblPFLog;
import models.TblPFLogPK;
import models.TblPFLogTypes;
import models.tblPartners;

@Stateless
@LocalBean 
public class PFLogService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

	@EJB
	PFLogCategoriesService pfLogCategoriesService;
	
	@EJB
	PFLogTypesService pfLogTypesService;

	@EJB
	PFLogProcessesService pfLogProcessesService;

	@EJB
	PartnersService partnersService;

	PFLogGeneralSettingsConstants pfLogGeneralSettingsConstants = new PFLogGeneralSettingsConstants();
	
	private void RotateLogs() {
		
		try {
			Integer daysBack = pfLogGeneralSettingsConstants.getRotateLogsDays();
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -daysBack);
			Date date = calendar.getTime();
			Query query = em.createQuery("DELETE FROM TblPFLog x WHERE x.id.logDate < :logDate");
			query.setParameter("logDate", date).executeUpdate();		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// TODO AQUI, (comprobar): la primera línea del log debe incrementar el executionNumber, ahora mismo mantiene el anterior - funciona bien solo si no hay ninguna (0)
	
	public TblPFLog ProductFeedsPocessorBegin(tblPartners partner, String pfName) {

		// rotating log
		RotateLogs();
					
		if (partner != null) {
			TblPFLog pfLog = new TblPFLog();
			try {
				
				pfLog.setPartner(partner);
				pfLog.setLogProcess(pfLogProcessesService.getLogProcessProductFeedsPocessor());
				pfLog.setLogCategory(pfLogCategoriesService.getLogcategoryPROCESSSTARTED());
				pfLog.setLogType(pfLogTypesService.getLogTypeInformation());	    	
				pfLog.setPartnerProductId("");
				pfLog.setEntityName("");
				pfLog.setEntityId(0);
				pfLog.setDescription(pfLogGeneralSettingsConstants.getProductsProcessorBeginDescription().concat(" [pfName=").concat(pfName).concat("]"));
		    	
				return this.AddLogLine(pfLog);
				
			} catch (Exception e) {
				System.out.println("Exception while executing CRUD / services / PFLogService / ProductFeedsPocessorBegin()");
				return null;
			}
			
		} else {
			System.out.println("Attention! - CRUD / services / ProductFeedsPocessorBegin() - Log has NOT been inserted because some mandatory information is missinig in partner [" + partner + "]");
			return null;
		}

	}
	
	public TblPFLog ProductFeedsPocessorEnd(tblPartners partner, String pfName) {
		
		if (partner != null) {
			TblPFLog pfLog = new TblPFLog();
			try {
				
				pfLog.setPartner(partner);
				pfLog.setLogProcess(pfLogProcessesService.getLogProcessProductFeedsPocessor());
				pfLog.setLogCategory(pfLogCategoriesService.getLogcategoryPROCESSFINISHED());
				pfLog.setLogType(pfLogTypesService.getLogTypeInformation());	    	
				pfLog.setPartnerProductId("");
				pfLog.setEntityName("");
				pfLog.setEntityId(0);
				pfLog.setDescription(pfLogGeneralSettingsConstants.getProductsProcessorEndDescription().concat(" [pfName=").concat(pfName).concat("]"));
		    	
				return this.AddLogLine(pfLog);
				
			} catch (Exception e) {
				System.out.println("Exception while executing CRUD / services / PFLogService / ProductFeedsPocessorBegin()");
				return null;
			}
			
		} else {
			System.out.println("Attention! - CRUD / services / ProductFeedsPocessorBegin() - Log has NOT been inserted because some mandatory information is missinig in partner [" + partner + "]");
			return null;
		}

	}

	public TblPFLog ProductStandardisationBegin(tblPartners partner, String partnerProductId) {

		if (partner != null) {
			TblPFLog pfLog = new TblPFLog();
			try {
				
				pfLog.setPartner(partner);
				pfLog.setLogProcess(pfLogProcessesService.getLogProcessProductFeedsPocessor());
				pfLog.setLogCategory(pfLogCategoriesService.getLogcategoryPRODUCTSTANDARDIZATIONSTARTED());
				pfLog.setLogType(pfLogTypesService.getLogTypeInformation());	    	
				pfLog.setPartnerProductId(partnerProductId);
				pfLog.setEntityName("");
				pfLog.setEntityId(0);
				pfLog.setDescription(pfLogGeneralSettingsConstants.getStandardizingProductBeginDescription().concat(" [partnerId=").concat(partner.getId().toString()).concat(", partnerProductId=").concat(partnerProductId).concat("]"));
				
				return this.AddLogLine(pfLog);
				
			} catch (Exception e) {
				System.out.println("Exception while executing CRUD / services / PFLogService / ProductStandardisationBegin()");
				return null;
			}
			
		} else {
			System.out.println("Attention! - CRUD / services / ProductStandardisationBegin() - Log has NOT been inserted because some mandatory information is missinig in partner [" + partner + "]");
			return null;
		}

	}
	
	public TblPFLog ProductStandardisationEnd(tblPartners partner, String partnerProductId) {

		if (partner != null) {
			TblPFLog pfLog = new TblPFLog();
			try {
				
				pfLog.setPartner(partner);
				pfLog.setLogProcess(pfLogProcessesService.getLogProcessProductFeedsPocessor());
				pfLog.setLogCategory(pfLogCategoriesService.getLogcategoryPRODUCTSTANDARDIZATIONFINISHED());
				pfLog.setLogType(pfLogTypesService.getLogTypeInformation());	    	
				pfLog.setPartnerProductId(partnerProductId);
				pfLog.setEntityName("");
				pfLog.setEntityId(0);
				pfLog.setDescription(pfLogGeneralSettingsConstants.getStandardizingProductEndDescription().concat(" [partnerId=").concat(partner.getId().toString()).concat(", partnerProductId=").concat(partnerProductId).concat("]"));
				
				return this.AddLogLine(pfLog);
				
			} catch (Exception e) {
				System.out.println("Exception while executing CRUD / services / PFLogService / ProductProcessingBegin()");
				return null;
			}
			
		} else {
			System.out.println("Attention! - CRUD / services / ProductProcessingBegin() - Log has NOT been inserted because some mandatory information is missinig in partner [" + partner + "]");
			return null;
		}

	}
	
	public TblPFLog ProductsProcessorBegin(tblPartners partner) {

		if (partner != null) {
			TblPFLog pfLog = new TblPFLog();
			try {
				
				pfLog.setPartner(partner);
				pfLog.setLogProcess(pfLogProcessesService.getLogProcessProductsProcessor());
				pfLog.setLogCategory(pfLogCategoriesService.getLogcategoryPROCESSSTARTED());
				pfLog.setLogType(pfLogTypesService.getLogTypeInformation());	    	
				pfLog.setPartnerProductId("");
				pfLog.setEntityName("");
				pfLog.setEntityId(0);
				pfLog.setDescription(pfLogGeneralSettingsConstants.getProductsProcessorBeginDescription());
		    	
				return this.AddLogLine(pfLog);
				
			} catch (Exception e) {
				System.out.println("Exception while executing CRUD / services / PFLogService / ProductsProcessorBegin()");
				return null;
			}
			
		} else {
			System.out.println("Attention! - CRUD / services / ProductsProcessorBegin() - Log has NOT been inserted because some mandatory information is missinig in partner [" + partner + "]");
			return null;
		}

	}
	
	public TblPFLog ProductsProcessorEnd(tblPartners partner) {

		if (partner != null) {
			TblPFLog pfLog = new TblPFLog();
			try {
				
				pfLog.setPartner(partner);
				pfLog.setLogProcess(pfLogProcessesService.getLogProcessProductsProcessor());
				pfLog.setLogCategory(pfLogCategoriesService.getLogcategoryPROCESSFINISHED());
				pfLog.setLogType(pfLogTypesService.getLogTypeInformation());	    	
				pfLog.setPartnerProductId("");
				pfLog.setEntityName("");
				pfLog.setEntityId(0);
				pfLog.setDescription(pfLogGeneralSettingsConstants.getProductsProcessorEndDescription());
		    	
				return this.AddLogLine(pfLog);
				
			} catch (Exception e) {
				System.out.println("Exception while executing CRUD / services / PFLogService / ProductsProcessorBegin()");
				return null;
			}
			
		} else {
			System.out.println("Attention! - CRUD / services / ProductsProcessorBegin() - Log has NOT been inserted because some mandatory information is missinig in partner [" + partner + "]");
			return null;
		}

	}

	public TblPFLog ProductProcessingBegin(tblPartners partner, String partnerProductId) {

		if (partner != null) {
			TblPFLog pfLog = new TblPFLog();
			try {
				
				pfLog.setPartner(partner);
				pfLog.setLogProcess(pfLogProcessesService.getLogProcessProductsProcessor());
				pfLog.setLogCategory(pfLogCategoriesService.getLogcategoryPRODUCTPROCESSINGSTARTED());
				pfLog.setLogType(pfLogTypesService.getLogTypeInformation());	    	
				pfLog.setPartnerProductId(partnerProductId);
				pfLog.setEntityName("");
				pfLog.setEntityId(0);
				pfLog.setDescription(pfLogGeneralSettingsConstants.getProcessingProductBeginDescription().concat(" [partnerId=").concat(partner.getId().toString()).concat(", partnerProductId=").concat(partnerProductId).concat("]"));
				
				return this.AddLogLine(pfLog);
				
			} catch (Exception e) {
				System.out.println("Exception while executing CRUD / services / PFLogService / ProductProcessingBegin()");
				return null;
			}
			
		} else {
			System.out.println("Attention! - CRUD / services / ProductProcessingBegin() - Log has NOT been inserted because some mandatory information is missinig in partner [" + partner + "]");
			return null;
		}

	}
	
	public TblPFLog ProductProcessingEnd(tblPartners partner, String partnerProductId) {

		if (partner != null) {
			TblPFLog pfLog = new TblPFLog();
			try {
				
				pfLog.setPartner(partner);
				pfLog.setLogProcess(pfLogProcessesService.getLogProcessProductsProcessor());
				pfLog.setLogCategory(pfLogCategoriesService.getLogcategoryPRODUCTPROCESSINGFINISHED());
				pfLog.setLogType(pfLogTypesService.getLogTypeInformation());	    	
				pfLog.setPartnerProductId(partnerProductId);
				pfLog.setEntityName("");
				pfLog.setEntityId(0);
				pfLog.setDescription(pfLogGeneralSettingsConstants.getProcessingProductEndDescription().concat(" [partnerId=").concat(partner.getId().toString()).concat(", partnerProductId=").concat(partnerProductId).concat("]"));
				
				return this.AddLogLine(pfLog);
				
			} catch (Exception e) {
				System.out.println("Exception while executing CRUD / services / PFLogService / ProductProcessingBegin()");
				return null;
			}
			
		} else {
			System.out.println("Attention! - CRUD / services / ProductProcessingBegin() - Log has NOT been inserted because some mandatory information is missinig in partner [" + partner + "]");
			return null;
		}

	}
	
	public TblPFLog ProductProcessing(tblPartners partner, String logtypeName, String partnerProductId, String entityName, Integer entityId, String description) {

		if (partner != null) {
			TblPFLog pfLog = new TblPFLog();
			try {

				TblPFLogTypes logType = new TblPFLogTypes();
				logType = pfLogTypesService.getOneByName(logtypeName);
				if ( (logType != null) && (logType.getId() > 0) ) {
				
					pfLog.setPartner(partner);
					pfLog.setLogProcess(pfLogProcessesService.getLogProcessProductsProcessor());
					pfLog.setLogCategory(pfLogCategoriesService.getLogcategoryPRODUCTPROCESSING());
					pfLog.setLogType(logType);	    	
					pfLog.setPartnerProductId(partnerProductId);
					pfLog.setEntityName(entityName);
					pfLog.setEntityId(entityId);
					pfLog.setDescription(description);
					
					return this.AddLogLine(pfLog);
					
				} else {
					return null;
				}
				
			} catch (Exception e) {
				System.out.println("Exception while executing CRUD / services / PFLogService / ProductProcessing()");
				return null;
			}
			
		} else {
			System.out.println("Attention! - CRUD / services / ProductProcessing() - Log has NOT been inserted because some mandatory information is missinig in partner [" + partner + "]");
			return null;
		}

	}

	public TblPFLog StoredprocedureCalled(tblPartners partner, String spName) {

		if (partner != null) {
			TblPFLog pfLog = new TblPFLog();
			try {
				
				pfLog.setPartner(partner);
				pfLog.setLogProcess(pfLogProcessesService.getLogProcessProductsProcessor());
				pfLog.setLogCategory(pfLogCategoriesService.getLogcategorySPCALLED());
				pfLog.setLogType(pfLogTypesService.getLogTypeInformation());	    	
				pfLog.setPartnerProductId("");
				pfLog.setEntityName("");
				pfLog.setEntityId(0);
				pfLog.setDescription(spName.concat(" has been called"));
				
				return this.AddLogLine(pfLog);
				
			} catch (Exception e) {
				System.out.println("Exception while executing CRUD / services / PFLogService / ProductProcessingBegin()");
				return null;
			}
			
		} else {
			System.out.println("Attention! - CRUD / services / ProductProcessingBegin() - Log has NOT been inserted because some mandatory information is missinig in partner [" + partner + "]");
			return null;
		}

	}
	
	private TblPFLog AddLogLine(TblPFLog pfLog) {
		
		if ( (pfLog != null) && (pfLog.getLogProcess() != null) &&
			 (pfLog.getLogCategory() != null) && (pfLog.getLogType() != null) && (pfLog.getPartner() != null) &&
			 (pfLog.getDescription() != null) && (pfLog.getDescription() != "") ) {
			
			if (pfLog.getId() == null) {
				TblPFLogPK pfLogPK = new TblPFLogPK();
				pfLog.setId(pfLogPK);
			}
			
			// pk.logDate
			if (pfLog.getId().getLogDate() == null) {
				pfLog.getId().setLogDate(Calendar.getInstance().getTime() ); // today
			}
	    	// pk.executionNumber
	    	if (pfLog.getId().getExecutionNumber() == 0) {						
	    		pfLog.getId().setExecutionNumber(GetLogExecutionNumber(pfLog.getId().getLogDate(), pfLog.getLogProcess().getId() ) );
			}
	    	// pk.pfprocessId
	    	pfLog.getId().setPfprocessId(pfLog.getLogProcess().getId() );
			// pk.lineNumber
	    	pfLog.getId().setLineNumber(GetLogLineNumber(pfLog.getId().getLogDate(), pfLog.getId().getExecutionNumber(), pfLog.getId().getPfprocessId() ));
	    	
	    	Integer maxColumnLength = 0;
	    	// I'm leaving `partnerProductId` as it comes in pfLog, just making sure it fits column length 
	    	try {
				maxColumnLength = pfLog.getClass().getDeclaredField("partnerProductId").getAnnotation(Column.class).length();
			} catch (Exception e) {
				maxColumnLength = 45;
			} 
        	if (pfLog.getPartnerProductId() != null && pfLog.getPartnerProductId().length() > maxColumnLength) {
        		pfLog.setPartnerProductId( pfLog.getPartnerProductId().substring(0, maxColumnLength - 3).concat("...") );
        	}
	    	
	    	// I'm leaving `entityName` as it comes in pfLog, just making sure it fits column length 
	    	try {
				maxColumnLength = pfLog.getClass().getDeclaredField("entityName").getAnnotation(Column.class).length();
			} catch (Exception e) {
				maxColumnLength = 45;
			} 
        	if (pfLog.getEntityName() != null && pfLog.getEntityName().length() > maxColumnLength) {
        		pfLog.setEntityName( pfLog.getEntityName().substring(0, maxColumnLength - 3).concat("...") );
        	}
	    	
	    	// I'm leaving `Description` as it comes in pfLog, just making sure it fits column length 
	    	try {
				maxColumnLength = pfLog.getClass().getDeclaredField("description").getAnnotation(Column.class).length();
			} catch (Exception e) {
				maxColumnLength = 45;
			} 
        	if (pfLog.getDescription().length() > maxColumnLength) {
        		pfLog.setDescription( pfLog.getDescription().substring(0, maxColumnLength - 3).concat("...") );
        	}
	    	
			try {
				em.persist(pfLog);
				em.flush();
				
				return pfLog;
			} catch (Exception e) {
				System.out.println("Exception at CRUD / services / AddLogLine("+pfLog+")");
				e.printStackTrace();
				return null;
			}     
						
		} else {
			
			System.out.println("Attention! - CRUD / services / AddLogLine - Log has NOT been inserted because some mandatory information is missinig in pfLog [" + pfLog + "]");
			return null;	
		}
		
	}	
	
	private int GetLogLineNumber(Date logDate, Integer executionNumber, int pfprocessId) {
		if ( (logDate != null) && (executionNumber != null) && (executionNumber > 0) && (pfprocessId > 0) ) {
			
			try {
				Integer maxlineNumber;
				maxlineNumber = (Integer) em.createQuery("SELECT MAX(x.id.lineNumber) FROM TblPFLog x WHERE x.id.logDate=:logDate AND x.id.executionNumber=:executionNumber AND x.id.pfprocessId=:pfprocessId")
				  .setParameter("logDate", logDate)
				  .setParameter("executionNumber", executionNumber)
				  .setParameter("pfprocessId", pfprocessId)
				  .getSingleResult();
				return maxlineNumber + 1;
			} catch (NoResultException nre) {
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
			
		} else {
			return 0;	
		}
	}


	private int GetLogExecutionNumber(Date logDate, int pfprocessId) {
		
		if ( (logDate != null) && (pfprocessId > 0) ) {
			
			try {
						  
				Integer maxExecutionNumber;
				maxExecutionNumber = (Integer) em.createQuery("SELECT MAX(x.id.executionNumber) FROM TblPFLog x WHERE x.id.logDate=:logDate AND x.id.pfprocessId=:pfprocessId")
				  .setParameter("logDate", logDate)
				  .setParameter("pfprocessId", pfprocessId)
				  .getSingleResult();
				if (maxExecutionNumber==0) { maxExecutionNumber = 1; }
				return maxExecutionNumber;
			} catch (NoResultException nre) {
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
				return 0; // returning 0 because there is a problem and I shouldn't go ahead
			}
			
		} else {
			return 0;	
		}
	}
	
}