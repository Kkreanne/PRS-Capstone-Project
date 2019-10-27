package busLogic;

public class PRSapp {

	public static void main(String[] args) {

		System.out.println("Welcome to the Purchase Request System! \n");
		
		Request PR1 = new Request();
		
		User user1 = new User();
		
		user1.setId(1);
		user1.setUserName("kabrams2");
		user1.setPassword("queenBee3");
		user1.setFirstName("Karlee");
		user1.setLastName("Abrams");
		user1.setPhoneNumber("513-608-1672");
		user1.setEmail("karkar1030@yahoo.com");
		user1.setReviewer(true);
		user1.setAdmin(false);
		
		Line lineItem1 = new Line();
		
		Vendor vendor1 = new Vendor();
		
		vendor1.setId(1);
		vendor1.setCode("1234");
		vendor1.setName("Staples");
		vendor1.setAddress("123 Main St");
		vendor1.setCity("Cincinnati");
		vendor1.setState("OH");
		vendor1.setZip("45248");
		vendor1.setPhoneNumber("513-123-2345");
		vendor1.setEmail("staplesVending.com");
		vendor1.setPreApproved(true);
		
		Product product1 = new Product();
		
		System.out.println(user1.toString());
		System.out.println(vendor1.toString());

	}

}
