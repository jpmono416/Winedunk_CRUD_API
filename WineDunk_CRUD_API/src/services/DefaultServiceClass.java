package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class DefaultServiceClass {
	/*
	//Constructor
	public DefaultServiceClass() {}
	
	//Default object
	Object record;
	public Object getRecord() { return record; }
	public void setRecord(Object record) {
		this.record = record;
	}

	public void testMethod()
	{
		System.out.println("Record type: " + record);
	}
	
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;
	
	private String modelsPath = "models."; //TODO make a properties file

    @SuppressWarnings("unchecked")
	public List<Object> getList(String tableName) 
    {
        Query query	= em.createNativeQuery("SELECT * FROM " + tableName);
        try { return  query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Object getById(String tableName, Integer id)
    {
    	Query query	= em.createNativeQuery("SELECT * FROM " + tableName + " WHERE id = " + id);
    	System.out.println("query: " + query); //TODO
        try 
        {
        	return query.getSingleResult();
        }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Integer addRecord(String tableName, String jsonToObject) throws ClassNotFoundException, 
    InstantiationException, IllegalAccessException, JsonProcessingException, IOException {

    	ObjectMapper mapper = new ObjectMapper();
    	
    	//Create object of the relevant class
    	Class<?> cls = Class.forName(modelsPath+tableName);
    	Object clsInstance = cls.newInstance();
    	JsonNode jsonNode = mapper.readTree(jsonToObject);
    	
    	if(jsonNode instanceof ObjectNode)
    	{
    		ObjectNode object = (ObjectNode) jsonNode;
    		if(object.get("id") != null) { object.remove("id"); }
    	}
    	
    	clsInstance = mapper.readValue(jsonToObject, cls);
    	em.persist(clsInstance);
    	
    	Integer newId = findLastIndex(tableName);
    	return newId;
    }

    public Boolean updateRecord(String tableName, String jsonOfObject) throws ClassNotFoundException, 
    JsonProcessingException, IOException, InstantiationException, IllegalAccessException
    {
    	ObjectMapper mapper = new ObjectMapper();
    	
    	//Create object of the relevant class
    	Class<?> cls = Class.forName(modelsPath+tableName);
    	Object clsInstance = cls.newInstance();
    	JsonNode jsonNode = mapper.readTree(jsonOfObject);
    	
    	if(jsonNode instanceof ObjectNode)
    	{
    		ObjectNode object = (ObjectNode) jsonNode;
    		if(object.get("id") == null) { return false; }
    	}
    	
    	clsInstance = mapper.readValue(jsonOfObject, cls);
    	em.merge(clsInstance);
    	return true;
    }

    public Boolean deleteRecord(String tableName, Integer id) throws InstantiationException, 
    IllegalAccessException, ClassNotFoundException, JsonProcessingException, IOException
    {
    	//Create object of the relevant class
    	Class<?> cls = Class.forName(modelsPath+tableName);
        Object record = new Object();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter();
        
        record =  getById(tableName, id);
        if(record == null) { return false; }
        
        JsonNode json = mapper.readTree(mapper.writeValueAsString(record));
        System.out.println("Json Node: "  + json.textValue());
        
        if(json instanceof ObjectNode)
    	{
    		ObjectNode object = (ObjectNode) json;
    		if(object.get("deleted") == null) { em.remove(record); }
    		else 
    		{ 
    			object.put("deleted", true); 
    			if(!updateRecord(tableName, object.asText())) { return false; }
    			System.out.println(object.asText()); //TODO DELETE
    		}
    	}
        return true;
    
    }
    
    public Integer findLastIndex(String tableName)
    {
    	Query query	= em.createNativeQuery("SELECT MAX(id) FROM " + tableName);
    	Integer id = (Integer) query.getSingleResult();
    	return id;
    }
    */
}