package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-09-18T14:16:45.352+0100")
@StaticMetamodel(Tblpfproduct.class)
public class Tblpfproduct_ {
	public static volatile SingularAttribute<Tblpfproduct, Integer> id;
	public static volatile SingularAttribute<Tblpfproduct, String> clicktag;
	public static volatile SingularAttribute<Tblpfproduct, Float> deliveryCost;
	public static volatile SingularAttribute<Tblpfproduct, String> imageURL;
	public static volatile SingularAttribute<Tblpfproduct, String> merchantName;
	public static volatile SingularAttribute<Tblpfproduct, String> merchantProductId;
	public static volatile SingularAttribute<Tblpfproduct, String> name;
	public static volatile SingularAttribute<Tblpfproduct, String> partnerMerchantId;
	public static volatile SingularAttribute<Tblpfproduct, String> partnerProductDescription;
	public static volatile SingularAttribute<Tblpfproduct, String> partnerProductId;
	public static volatile SingularAttribute<Tblpfproduct, Float> price;
	public static volatile SingularAttribute<Tblpfproduct, String> productType;
	public static volatile SingularAttribute<Tblpfproduct, String> productURL;
	public static volatile SingularAttribute<Tblpfproduct, Tblpf> tblpf;
}
